import java.util.*;

// PhysicalFrame object class
class PhysicalFrame {
    int id;
    int inserted;
    int next;
    int last;
    int timesUsed;

    // Constructor
    PhysicalFrame(int n) {
        id = n;
        inserted = -1;
        next = -1;
        last = -1;
        timesUsed = 0;
    }

    // SET number function
    void setNum(int n) {
        id = n;
    }

    // GET number function
    int getNum() {
        return id;
    }

    // SET inserted method
    void setInserted(int n) {
        inserted = n;
    }

    // GET inserted method
    int getInserted() {
        return inserted;
    }

    // SET next method
    void setNextUse(int n) {
        next = n;
    }

    // GET next method
    int getNextUse() {
        return next;
    }

    // SET last method
    void setLastUse(int n) {
        last = n;
    }

    // GET last method
    int getLastUse() {
        return last;
    }

    void incrementTimesUsed() {
        timesUsed += 1;
    }

    int getTimesUsed() {
        return timesUsed;
    }
}

// Memory Simulator constructor
class MemorySimulator {

    // Reference string variables
    ArrayList<Integer> rfString;
    int rsLen;

    // Removed pages tracker
    int[] rmPages;
    // Physical frames tracker
    int[] pageCalled;
    // Page faults tracker
    boolean[] pageFaults;

    int numOfPhyFrames;
    int numOfVirPages;

    // Physical memory [time][memory]
    int[][] phyMemory;
    // Tracks virtual frames
    PhysicalFrame[] virtualFrames;
    // Algorithm chosen
    String typeAlg;

    MemorySimulator(ArrayList<Integer> refs, int p, int v) {
        rfString = refs;
        rsLen = rfString.size();
        rmPages = new int[rsLen];
        pageCalled = new int[rsLen];
        numOfPhyFrames = p;
        numOfVirPages = v;
        phyMemory = new int[rfString.size()][p];
        virtualFrames = new PhysicalFrame[v];
        pageFaults = new boolean[rsLen];
    }

    // Generates simulations based on chosen algorithm
    void generate(String alg) {
        initialize();
        typeAlg = alg;
        int currentFrame = 0;
        int insertFrame;
        int empty;
        int replaceFrame;
        int[] listOfFrames;
        int inMemory;
        // While loop through each stage of simulation
        while (currentFrame < rsLen) {
            insertFrame = rfString.get(currentFrame);
            if (alg.equals("LRU")) {
                virtualFrames[insertFrame].setLastUse(currentFrame);
            } else if (alg.equals("LFU")) {
                virtualFrames[insertFrame].incrementTimesUsed();
            }
            empty = findIndex(phyMemory[currentFrame], -1);
            // Check if page needed is already in physical memory
            inMemory = findIndex(phyMemory[currentFrame], insertFrame);
            if (inMemory != -1) {
                pageCalled[currentFrame] = inMemory;
                // Set no page fault
                pageFaults[currentFrame] = false;
            }
            // Insert into memory if its not already and there is room
            else if (empty >= 0) {
                pageCalled[currentFrame] = empty;
                phyMemory[currentFrame][empty] = insertFrame;
                virtualFrames[insertFrame].setInserted(currentFrame);
            }
            // When it is not in memory, but there is no space...
            else {
                // Switch case to find which frame to use based on algorithm
                switch (alg) {
                    case "FIFO":
                        // Oldest frame
                        replaceFrame = findOldest(phyMemory[currentFrame]);
                        // Update insertion time
                        virtualFrames[insertFrame].setInserted(currentFrame);
                        break;
                    case "OPT":
                        // Calc next uses
                        calculateNextUses(currentFrame);
                        // Find the least optimal page
                        replaceFrame = findLeastOptimal(phyMemory[currentFrame]);
                        break;
                    case "LFU":
                        // Find least recently used
                        replaceFrame = findLfu(phyMemory[currentFrame]);
                        break;
                    case "LRU":
                        // Find least recently used
                        replaceFrame = findLru(phyMemory[currentFrame]);
                        // Update last frame called
                        break;
                    default:
                        System.out.println("Error: algorithm not recognized. Please try again!");
                        return;
                }
                // Track removed frame
                rmPages[currentFrame] = phyMemory[currentFrame][replaceFrame];
                // Track new frame spot
                pageCalled[currentFrame] = replaceFrame;
                // Insert new frame into fram spot
                phyMemory[currentFrame][replaceFrame] = insertFrame;
            }
            // Create physical memory for next call
            if ((currentFrame + 1) < rsLen) {
                if (numOfPhyFrames >= 0)
                    System.arraycopy(phyMemory[currentFrame], 0, phyMemory[currentFrame + 1], 0, numOfPhyFrames);
            }
            currentFrame += 1;
        }
    }

    // Find oldest inserted Frame in array of Frames
    int findOldest(int[] a) {
        int oldest = virtualFrames[a[0]].getInserted();
        int oldestIndex = 0;
        int checking;
        for (int i = 1; i < a.length; i++) {
            checking = virtualFrames[a[i]].getInserted();
            if (checking < oldest) {
                oldest = checking;
                oldestIndex = i;
            }
        }
        return oldestIndex;
    }

    // Find least used Frame in array of Frames
    int findLfu(int[] a) {
        int lfuIndex = 0;
        int lfuTimesUsed = virtualFrames[a[lfuIndex]].getTimesUsed();
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            int tempTimesUsed = virtualFrames[a[i]].getTimesUsed();

            if (tempTimesUsed < lfuTimesUsed) {
                lfuIndex = i;
                lfuTimesUsed = tempTimesUsed;
            }
        }
        return lfuIndex;
    }

    // Find oldest used Frame in array of Frames
    int findLru(int[] a) {
        int lruIndex = 0;
        int lruLastUse = virtualFrames[a[lruIndex]].getLastUse();
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            int tempLastUse = virtualFrames[a[i]].getLastUse();

            if (tempLastUse < lruLastUse) {
                lruIndex = i;
                lruLastUse = tempLastUse;
            }
        }
        return lruIndex;
    }

    // Identify least optimatal index frame
    int findLeastOptimal(int[] a) {
        int leastOptimal = a[0];
        int index = 0;
        int leastOptNextUse = virtualFrames[leastOptimal].getNextUse();
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            int tempNextUse = virtualFrames[temp].getNextUse();
            if (tempNextUse > leastOptNextUse)
            {
                leastOptimal = temp;
                leastOptNextUse = virtualFrames[leastOptimal].getNextUse();
                index = i;
            }
        }
        return index;
    }

    // Calculate next uses
    void calculateNextUses(int n) {
        for (int i = 0; i < numOfVirPages; i++) {
            virtualFrames[i].setNextUse(rsLen + 1);
        }
        for (int i = rsLen - 1; i >= n; i--) {
            int called = rfString.get(i);
            virtualFrames[called].setNextUse(i);
        }
    }

    // Initialize generated arrays
    void initialize() {
        Arrays.fill(pageFaults, true);
        Arrays.fill(rmPages, -1);
        Arrays.fill(pageCalled, -1);
        for (int i = 0; i < numOfVirPages; i++)
            virtualFrames[i] = new PhysicalFrame(i);
        for (int i = 0; i < rsLen; i++)
            for (int j = 0; j < numOfPhyFrames; j ++)
                phyMemory[i][j] = -1;
        typeAlg = "";
    }

    // Print function for paging VM simulation
    void printFrameInfo() {
        System.out.println("Memory information: ");
        System.out.println("Algorithm type: " + typeAlg);
        System.out.println("Length of ref. string: " + rsLen);
        System.out.println("Number of virtual pages: " + numOfVirPages);
        System.out.println("Number of physical pages: " + numOfPhyFrames);
        System.out.println("---");
        System.out.println("Press enter to step through snapshots of physical memory");
        System.out.println("Or, enter \"q\" at any time to return to main menu.");

        Scanner sc = new Scanner(System.in);
        int steppingSlice = 0;
        String prompt;
        int frameNum;
        int removedInt;
        while (steppingSlice < rsLen) {
            prompt = sc.nextLine();
            if (prompt.equals("q")) {
                System.out.println("Exiting Simulation.");
                break;
            }
            System.out.println("Snapshot at step " + (steppingSlice + 1) + ":");
            System.out.println("Program called virtual frame # "
                    + rfString.get(steppingSlice));
            for (int i = 0; i < numOfPhyFrames; i ++) {
                System.out.print("Physical frame " + i + ":");
                frameNum = phyMemory[steppingSlice][i];
                if (frameNum >= 0) {
                    if (i == pageCalled[steppingSlice]) {
                        System.out.println("[" + frameNum + "]");
                    } else {
                        System.out.println(" " + frameNum);
                    }
                } else {
                    System.out.println("x");
                }
            }
            removedInt = rmPages[steppingSlice];
            System.out.println("Page faults: " + (pageFaults[steppingSlice] ? "True" : "False"));
            System.out.println("Victim frames: " + (removedInt == -1 ? "None." : removedInt));
            steppingSlice += 1;
        }
        System.out.print("Simulation completed. Press enter to continue.");
        sc.nextLine();
    }

    int findIndex(int[] a, int n) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == n) {
                return i;
            }
        }
        return -1;
    }
}

// The simulator class to bring it all together!
public class demandPagingVMSimulator {

    // Initialize class variables
    int numPhyFrames;
    ArrayList<Integer> refString;

    // Constructor function
    public demandPagingVMSimulator() {
        int numPhyFrames;
        ArrayList<Integer> refString;
    }

    // GET number of physical frames
    public int getNumPhyFrames() {
        return numPhyFrames;
    }

    // SET number of physical frames
    private void setNumPhyFrames(int numPhyFrames) {
        this.numPhyFrames = numPhyFrames;
    }

    // GET reference string
    public ArrayList<Integer> getRefString() {
        return refString;
    }

    // SET reference string
    private void setRefString(ArrayList<Integer> refString) {
        this.refString = refString;
    }

    // Helper function to get user input for physical frame N
    public static int inputPhyFrameN() {
        Scanner console = new Scanner(System.in);
        String input;
        int inputNum = 0;
        boolean isValid;

        // Prompt user until inputNum is an integer within range:
        // (numbered from 0 to N-1, N<8)
        do {
            isValid = true;
            System.out.print("Please enter number N of physical frames (1-7): ");
            input = console.nextLine();
            try {
                inputNum = Integer.parseInt(input);
                if (inputNum < 1 || inputNum > 7) {
                    isValid = false;
                    System.out.println("Invalid range. Try again!");
                }
            } catch (NumberFormatException e) {
                isValid = false;
                System.out.println("Invalid. Please enter an integer. Try again!");
            }
        } while (!isValid);

        return inputNum;
    }

    // Helper function to get user input for reference string
    public static ArrayList<Integer> inputRefString(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a series of numbers separated by a space (non-number values will be ignored): ");
        ArrayList<Integer> list = new ArrayList<Integer>();

        // Prompt user for the (reference) string of integers
        do {
            String line = sc.nextLine();
            // Scanner specifically for line being entered
            Scanner stdInput = new Scanner(line);
            // Extract the integers from user input
            String temp;
            int tempInt = -1;
            boolean isInt;
            // Loop through user input & verify
            while (stdInput.hasNext()) {
                temp = stdInput.next();
                isInt = false;
                try {
                    tempInt = Integer.parseInt(temp);
                    isInt = true;
                } catch (NumberFormatException e) {
                    // Verify that user input is all integers
                    System.out.println("Non-integer; \""
                            + temp + "\" is ignored.");
                }
                // Verify that user input are integers between 0 and 9
                if (isInt && (tempInt < 0 || tempInt > 10)) {
                    System.out.println("Numbers must be between 0 and " + (9 - 1) + "; \"" + temp + "\" ignored.");
                } else if (isInt) {
                    // Add valid integers to (reference string) list
                    list.add(tempInt);
                }
            }
            // Verify that user input is not null
            if (list.size() < 1) {
                System.out.println("Reference string must be at least one integer (0 to 9). Please try again!");
            }
        } while (list.size() < 1);
        // Return list of valid integers (to be made into reference string)
        return list;
    }

    // Helper function to get user input for generated string length
    public static int getStringLength() {
        Scanner sc = new Scanner(System.in);
        int stringLength = 0;
        // Validate user input as a valid string (positive integer) string length
        while (stringLength < 1) {
            try {
                stringLength = sc.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("String size must be an integer. Try again!");
            }
            sc.nextLine();
            if (stringLength < 1) {
                System.out.println("String size must be a POSITIVE integer. Try again!");
            }
        }
        return stringLength;
    }

    // Helper function to generate reference string
    public static ArrayList<Integer> generateString(int lengthNum) {
        Random rand = new Random();
        ArrayList<Integer> genRefString = new ArrayList<Integer>();

        // Generate as many (single digit) numbers as given string length
        for (int i = 0; i < lengthNum; i++) {
            genRefString.add(rand.nextInt(10));
        }
        // Return generated list of valid integers (to be made into reference string)
        return genRefString;
    }

    // Helper function to check is reference string is set or not
    static boolean rsIsSet(ArrayList<Integer> rs) {
        if (rs != null) {
            return true;
        }
        System.out.println("Error: reference string not yet entered/generated! Please chose option 1 or 2 first!");
        return false;
    }

    // Main menu function
    public static void main(String[] args) {

        // Initialize demand paging VM simulator constructor
        demandPagingVMSimulator testPager = new demandPagingVMSimulator();

        System.out.println("\nWelcome to Kylee's Demand Paging VM Simulator project!");
        Scanner sc = new Scanner(System.in);

        // Get valid number of physical frames from user input
        int inputNumPhyFrames = inputPhyFrameN();
        System.out.println("Number of physical frames set to " + inputNumPhyFrames + ".");
        testPager.setNumPhyFrames(inputNumPhyFrames);

        String line; // input from user
        ArrayList<Integer> refString = null;
        MemorySimulator simulator;

        // Print main menu
        while (true) {
            System.out.println();
            System.out.println("Please choose from the following options:");
            System.out.println("0 - Exit Program");
            System.out.println("1 - Read reference string");
            System.out.println("2 - Generate reference string");
            System.out.println("3 - Display current reference string");
            System.out.println("4 - Simulate FIFO Algorithm");
            System.out.println("5 - Simulate OPT Algorithm");
            System.out.println("6 - Simulate LRU Algorithm");
            System.out.println("7 - Simulate LFU Algorithm");
            System.out.println();
            // Read user input
            line = sc.next();
            sc.nextLine();
            // Switch case user input handler
            switch (line) {
                case "0":
                    // exit
                    System.out.println("--You chose option 0 - Exit Program. Goodbye!--");
                    System.exit(0);
                    break;
                case "1":
                    System.out.println("--You chose option 1 - Read reference string--");
                    // Call functions to get user input for reference string & set in class
                    refString = inputRefString();
                    System.out.println("Your reference string is: " + refString);
                    testPager.setRefString(refString);
                    break;
                case "2":
                    System.out.println("--You chose option 2 - Generate reference string--");
                    // Call functions to get ref string length, generate ref string, and set in class
                    System.out.println("How long do you want the reference string to be?");
                    int stringSize = getStringLength();
                    System.out.println("Your reference string length is " + stringSize);
                    refString = generateString(stringSize);
                    System.out.println("Your generated reference string is: " + refString);
                    testPager.setRefString(refString);
                    break;
                case "3":
                    System.out.println("--You chose option 3 - Display current reference string--");
                    // Print reference string if it is set
                    refString = testPager.getRefString();
                    if (refString != null) {
                        System.out.print("Current reference string: ");
                        int i;
                        for (i = 0; i < refString.size() - 1; i++) {
                            System.out.print(refString.get(i) + ", ");
                        }
                        System.out.print(refString.get(i));
                        System.out.print(".\n");
                    } else {
                        System.out.println("No reference string set. Please chose option 1 or 2 first!");
                    }
                    break;
                case "4":
                    System.out.println("--You chose option 4 - Simulate FIFO Algorithm--");
                    // Check if refString has been set
                    if (rsIsSet(refString)) {
                        simulator = new MemorySimulator(refString, inputNumPhyFrames, 10);
                        simulator.generate("FIFO");
                        simulator.printFrameInfo();
                    }
                    break;
                case "5":
                    System.out.println("--You chose option 5 - Simulate OPT Algorithm--");
                    // Check if refString has been set
                    if (rsIsSet(refString)) {
                        simulator = new MemorySimulator(refString, inputNumPhyFrames, 10);
                        simulator.generate("OPT");
                        simulator.printFrameInfo();
                    }
                    break;
                case "6":
                    System.out.println("--You chose option 6 - Simulate LRU Algorithm--");
                    // Check if refString has been set
                    if (rsIsSet(refString)) {
                        simulator = new MemorySimulator(refString, inputNumPhyFrames, 10);
                        simulator.generate("LRU");
                        simulator.printFrameInfo();
                    }
                    break;
                case "7":
                    System.out.println("--You chose option 7 - Simulate LFU Algorithm--");
                    // Check if refString has been set
                    if (rsIsSet(refString)) {
                        simulator = new MemorySimulator(refString, inputNumPhyFrames, 10);
                        simulator.generate("LFU");
                        simulator.printFrameInfo();
                    }
                    break;
                default:
                    System.out.println("Please pick a valid menu option!");
                    break;
            }
        }
    }
}

