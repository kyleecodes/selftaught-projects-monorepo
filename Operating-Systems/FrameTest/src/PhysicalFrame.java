import java.util.ArrayList;
import java.util.Scanner;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.InputMismatchException;


//PhysicalFrame.java:
public class PhysicalFrame
{
   int id;
   int inserted;
   int next;//next Use
   int last;//last Use
   int timesUsed;

   PhysicalFrame(int n) {
       id = n;
       inserted = -1;
       next = -1;
       last = -1;
       timesUsed = 0;
   }
   // Method to set the number
   void setNum(int n) {
       id = n;
   }
   //Method to get the number
   int getNum() {
       return id;
   }
   //Method to set the inserted
   void setInserted(int n) {
       inserted = n;
   }
   //Method to set the inserted
   int getInserted() {
       return inserted;
   }
   void setNextUse(int n) {
       next = n;
   }
   int getNextUse() {
       return next;
   }
   void setLastUse(int n) {
       last = n;
   }
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

//MemorySimulator.java:
class MemorySimulator
{
   ArrayList<Integer> rfString; // reference string
   int[] rmPages; // keep track of removed pages
   int[] pageCalled; // keep track of physical pages
   boolean[] pageFaults; // keeps track of page faults
   int rsLen; // Size of the reference string
   int numOfPhyFrames;//number Of Physical Frames
   int numOfVirPages;//number Of Virtual Frames
   /*physical Memory
   * first dimension represents "time",
   * 2nd is the phyiscal memory at that time
   */
   int[][] phyMemory;
   // keep track of all the virtual frames in this array
   PhysicalFrame[] virtualFrames;
   // keep track of which algorithm the simulation ran
   String typeAlg;

   MemorySimulator(ArrayList<Integer> refs, int p, int v)
   {
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

   /* the "generate()" method uses the reference
   * string and supplied information
   * about the virtual and physical memory
   * to run through simulations.
   */
   void generate(String alg) {
       initialize();
       typeAlg = alg;
       int currentFrame = 0;
       int insertFrame;
       int empty;
       int replaceFrame;
       int[] listOfFrames;
       int inMemory;
       // the while loops step through each call of the simulation
       while (currentFrame < rsLen) {
           insertFrame = rfString.get(currentFrame);
           if (alg == "LRU") {
               virtualFrames[insertFrame].setLastUse(currentFrame);
           } else if (alg == "LFU") {
               virtualFrames[insertFrame].incrementTimesUsed();
           }
           empty = findIndex(phyMemory[currentFrame], -1);
           // if the page we need is already in physical memory...
           inMemory = findIndex(phyMemory[currentFrame], insertFrame);
           if (inMemory != -1) {
               pageCalled[currentFrame] = inMemory;
               // no page fault!
               pageFaults[currentFrame] = false;
           }
           // if it's not in memory but there's an empty space for it...
           else if (empty >= 0) {
               pageCalled[currentFrame] = empty;
               phyMemory[currentFrame][empty] = insertFrame;
               virtualFrames[insertFrame].setInserted(currentFrame);
           }
           // not in memory and no empty space
           else {
               // find the frame to be removed depending on the algo
               switch (alg) {
                   case "FIFO":
                   // find the oldest frame
                   replaceFrame = findOldest(phyMemory[currentFrame]);
                   // update insertion time
                   virtualFrames[insertFrame].setInserted(currentFrame);
                   break;
                   case "OPT":
                   // calculate next uses
                   calculateNextUses(currentFrame);
                   // find the least optimal page
                   replaceFrame = findLeastOptimal(phyMemory[currentFrame]);
                   break;
                   case "LFU":
                   // find least recently used
                   replaceFrame = findLfu(phyMemory[currentFrame]);
                   break;
                   case "LRU":
                   // find least recently used
                   replaceFrame = findLru(phyMemory[currentFrame]);
                   // update information for last use of the frame just called/
                   break;
                   default:
                   System.out.println("Error: algorithm not recognized!");
                   return;
               }
               // record removed frame
               rmPages[currentFrame] = phyMemory[currentFrame][replaceFrame];
               // record new frame spot
               pageCalled[currentFrame] = replaceFrame;
               // put the new frame in that spot
               phyMemory[currentFrame][replaceFrame] = insertFrame;


           }
           // make the physical memory for the next call a copy of the physical
           // memory at the end of this call
           if ((currentFrame + 1) < rsLen) {
               for (int i = 0; i < numOfPhyFrames; i ++) {
                   phyMemory[currentFrame +1][i] = phyMemory[currentFrame][i];
               }
           }
           currentFrame += 1;
       }
   }

   // find the first inserted Frame, given an array of Frame numbers
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

   // find least frequently used frame, given an
   //array containing frame numbers
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
   // find least recently used frame, given an
   //array containing frame numbers
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

   // find "least optimal" frame
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
       //return least Optimal Index
       return index;
   }

  
   void calculateNextUses(int n)
   {      
       for (int i = 0; i < numOfVirPages; i++) {
           virtualFrames[i].setNextUse(rsLen + 1);
       }
       // then it works backwards from the end
       for (int i = rsLen - 1; i >= n; i--) {
           int called = rfString.get(i);
           virtualFrames[called].setNextUse(i);
       }
   }

   // initialize all the arrays used in generate()
   void initialize() {
       // set page faults to false
       for (int i = 0; i < pageFaults.length; i++)
           pageFaults[i] = true;
       // set removed to -1s
       for (int i = 0; i < rmPages.length; i++)
           rmPages[i] = -1;
       // set pages changed to -1s
       for (int i = 0; i < pageCalled.length; i++)
           pageCalled[i] = -1;  
       // set clean array of frames:
       for (int i = 0; i < numOfVirPages; i++)
           virtualFrames[i] = new PhysicalFrame(i);
       // clean array of slices
       for (int i = 0; i < rsLen; i++)
           for (int j = 0; j < numOfPhyFrames; j ++)
               phyMemory[i][j] = -1;
      
       typeAlg = "";
   }

   // print the results of the simluation, one step at a time
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
               System.out.println("Exitting printout.");
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
           System.out.println("Page faults: " + (pageFaults[steppingSlice] ? "Yes." : "No."));
           System.out.println("Victim frames: " + (removedInt == -1 ? "None." : removedInt));
           steppingSlice += 1;
       }
       System.out.print("Simluation finished. Press enter to continue.");
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

//DemandPagingSimulator.java:
class DemandPagingSimulator {
   //maximum number of virtual pages
   static final int MAX_VP = 10;
   // maximum number of physical pages
   static final int MAX_PP = 7;

   public static void main(String[] args) {
       // read in physical frame numbers
//       String [] my_args = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
       String [] my_args = {"0"};
       int numOfPhyFrames = readCmdLineArg(my_args);
       System.out.println("Number of page frames set to "
               + numOfPhyFrames + ".");

       // set up for main loop
       Scanner stdIn = new Scanner(System.in);
       String line; // input from user
       ArrayList<Integer> refString = null;
       MemorySimulator simulator;

       // begin main loop:
       while (true)
       {
           System.out.println();
           System.out.println("Please choose from the following options:");
           System.out.println("0 - Exit");
           System.out.println("1 - Read reference string");
           System.out.println("2 - Generate reference string");
           System.out.println("3 - Display current reference string");
           System.out.println("4 - Simulate FIFO Algorithm");
           System.out.println("5 - Simulate OPT Algorithm");
           System.out.println("6 - Simulate LRU Algorithm");
           System.out.println("7 - Simulate LFU Algorithm");
           System.out.println();

           // read input
           line = stdIn.next();
           stdIn.nextLine();
           switch (line) {
               case "0":
               // exit
               System.out.println("Goodbye.");
               System.exit(0);
               break;
               case "1":
               // read reference string
               refString = readRefString(stdIn);
               // confirm
               stringConfirm(refString);
               break;
               case "2":
               // generate reference string
               // get length of desired string
               System.out.println("How long do you want the reference string to be?");
               int stringSize = getStringSize(stdIn);
               // generate the string
               refString = generateString(stringSize, MAX_VP);
               // confirm
               stringConfirm(refString);
               break;
               case "3":
               // print reference string
               if (refString != null) {
                   System.out.print("Current reference string: ");
                   int i;
                   for (i = 0; i < refString.size() - 1; i++) {
                       System.out.print(refString.get(i) + ", ");
                   }
                   System.out.print(refString.get(i));
                   System.out.print(".");
               } else {
                   System.out.println("Error: no reference string entered.");
               }
               break;
               case "4":
               // check that refString has been set:
               // test rs:
               if (rsIsSet(refString)) {
                   // create simulation conditions, run it, and print
                   simulator = new MemorySimulator(refString, numOfPhyFrames, MAX_VP);
                   simulator.generate("FIFO");
                   simulator.printFrameInfo();
               }
               break;
               case "5":
               // check that refString has been set:
               if (rsIsSet(refString)) {
                   // create simulation conditions, run it, and print
                   simulator = new MemorySimulator(refString, numOfPhyFrames, MAX_VP);
                   simulator.generate("OPT");
                   simulator.printFrameInfo();
               }
               break;
               case "6":
               // check that refString has been set:
               if (rsIsSet(refString)) {
                   // create simulation conditions, run it, and print
                   simulator = new MemorySimulator(refString, numOfPhyFrames, MAX_VP);
                   simulator.generate("LRU");
                   simulator.printFrameInfo();
               }
               break;
               case "7":
               // check that refString has been set:
               if (rsIsSet(refString)) {
                   // create simulation conditions, run it, and print
                   simulator = new MemorySimulator(refString, numOfPhyFrames, MAX_VP);
                   simulator.generate("LFU");
                   simulator.printFrameInfo();
               }
               break;
               default:
               break;
           } // end switch
       } // end while (true)
   } // end main

   private static int readCmdLineArg(String[] args) {
//       Scanner sc = new Scanner(System.in);
//       System.out.println("Please select a menu option: ");
//       int numOfPhyFrames = sc.nextInt();

       // check for correct number of arguments
       if (args.length < 1) {
           System.out.println("Error: need to pass exactly 1"
                   +"command line argument for number of physical frames.");
           System.exit(-1);
       }
       if (args.length > 1) {
           System.out.println("Warning: Too many command line arguments."
                   +"Every argument after the 1st will be ignored.");
       }
       // n will be our # of physical page frames
       int n = -1;

       // try to parse int; catch exceptions
       try {
           n = Integer.parseInt(args[0]);
       } catch(NumberFormatException e) {
           System.out.println("Error: command line argument must be an integer.");
           System.exit(-1);
       }

       // check if n is between 0 and N - 1
       if (n < 1 || n > MAX_PP) {
           System.out.println("Error: must be between 1 and "
                   + (MAX_PP) + " physical frames.");
           System.exit(-1);
       }

       // everything worked out OK, return n!
       return n;
   }

   static ArrayList<Integer> readRefString(Scanner in) {
       System.out.println("Enter a series of numbers: ");
       // create RefString
       ArrayList<Integer> list = new ArrayList<Integer>();
       do {
           // read in a line
           String line = in.nextLine();
           // create a scanner to operate on that line
           Scanner stdInput = new Scanner(line);
           // extract the ints
           String temp;
           int tempInt = -1;
           boolean isInt;
           while (stdInput.hasNext()) {
               temp = stdInput.next();
               isInt = false;
               try {
                   tempInt = Integer.parseInt(temp);
                   isInt = true;
               } catch (NumberFormatException e) {
                   System.out.println("Warning: you entered a non-integer; \""
                           + temp + "\" ignored.");
               }
               // ensure that the numbers entered are between 0 and 9:
               if (isInt && (tempInt < 0 || tempInt >= MAX_VP))
               {
                   System.out.println("Warning: numbers must be between 0 and "
                           + (MAX_VP - 1) + "; \"" + temp + "\" ignored.");
               } else if (isInt) {
                   list.add(tempInt);
               }
           }
           // make sure at least 1 valid int entered:
           if (list.size() < 1) {
               System.out.println("Error: Ref.string must be atleast one"
           +"integer (0 to 9). Please try again.");
           }
       } while (list.size() < 1);
       return list;//return reference string
   }

   static int getStringSize(Scanner in) {
       //read in a line; parse an int
       int stringSize = 0;
       while (stringSize < 1) {
           try {
               stringSize = in.nextInt();
           }
           catch (InputMismatchException e) {
               System.out.println("You must enter an integer.");
           }
           in.nextLine();
           if (stringSize < 1) {
               System.out.println("You must enter a positive integer.");
           }
       }
       // if int is out of bounds, give error
       return stringSize;
   }

   static ArrayList<Integer> generateString(int n, int max) {

//       //maximum number of virtual pages
//       static final int MAX_VP = 10;
//       // maximum number of physical pages
//       static final int MAX_PP = 7;
//       refString = generateString(stringSize, MAX_VP);

       // NOTE: max is exclusive
       // validate input
       if (n < 1) {
           System.out.println("Error: cannot create a reference string shorter than 1.");
           return null;
              
       }
       Random rand = new Random();

       // create ArrayList for ints
       ArrayList<Integer> ar = new ArrayList<Integer>();
       // generate n random numbers and add them to the list.
       for (int i = 0; i < n; i++) {
           ar.add(rand.nextInt(max));
       }

       // use the ArrayList to create a RefString
       ArrayList<Integer> rs = ar;
       // return the RefString
       return rs;
   }

   static void stringConfirm(ArrayList<Integer> rs) {
       if (rs != null) {
           System.out.print("Valid ref.string: ");
           int i;
           for (i = 0; i < rs.size() - 1; i++) {
               System.out.print(rs.get(i) + ", ");
           }
           System.out.print(rs.get(i));
           System.out.print(".");
       } else {
           System.out.println("Invalid reference string. Please try again.");
       }
   }

   static boolean rsIsSet(ArrayList<Integer> rs) {
       if (rs != null) {
           return true;
       }
       System.out.println("Error: reference string not yet entered/generated!");
       return false;
   }
}