import java.io.*;
import java.util.*;
import java.util.List;

public class File_Processor {

        File dir;

        // Constructor
        public File_Processor() {
                File dir;
        }

        // Get selected directory
        public File getDirectory() {
                return dir;
        }

        // Set selected directory
        public void setDirectory(File dir) {
                this.dir = dir;
        }

        // Print first level content of selected directory
        private void listDirFirstLevel() {

                List<String> directories = new ArrayList<>();
                List<String> files = new ArrayList<>();
                File[] pathnames = dir.listFiles();

                // For each pathname in the pathnames array
                // Create two lists of files & directories
                for (File pathname : pathnames) {
                        if (pathname.isFile()) {
                                files.add(pathname.getName());
                        } else if (pathname.isDirectory()) {
                                directories.add(pathname.getName());
                        }
                }
                System.out.println("--First level directory content--");
                // Call helper function to print
                printLists(files, directories);
        }

        // Print all levels content of selected directory
        private void listDirAllLevels() throws NullPointerException {

                List<String> directories = new ArrayList<>();
                List<String> files = new ArrayList<>();

                // Build stack to analyze all levels of directory
                Stack<File> stack = new Stack<>();
                stack.push(dir);

                // While level is not empty, add contents to either directory or files list
                while (!stack.isEmpty()) {
                        File child = stack.pop();
                        File[] childFiles = child.listFiles();

                        if (child.isDirectory()) {
                                for (File f : childFiles) stack.push(f);
                                if (child.getName().equals(dir.getName())) {
                                        continue;
                                } else files.add(child.getName());
                        } else if (child.isFile()) {
                                if (child.getName().equals(dir.getName())) {
                                        continue;
                                } else directories.add(child.getName());
                        }
                }
                System.out.println("--All directory content--");
                // Call helper function to print
                printLists(directories, files);
        }

        // Delete a file within selected directory
        private void delete(String deleteFilePath) {

                for (File file : Objects.requireNonNull(dir.listFiles())) {
                        if (file.getName().equals(deleteFilePath)) {
                                System.out.println("Now deleting file " + deleteFilePath);
                                file.delete();
                                return;
                        } else {
                                continue;
                        }
                }
                // If file to be deleted is not found...
                System.out.println(deleteFilePath + " Doesn't exist. Please try again.");
        }

        // Display hexidecimal data of file
        private void display(String displayFilePath) throws IOException {
                for (File file : Objects.requireNonNull(dir.listFiles())) {
                        if (file.getName().equals(displayFilePath)) {
                                System.out.print("File found! \n");
                                FileInputStream fis = new FileInputStream(file);
                                // Holds single byte of the file data
                                int i = 0;
                                // Counter to print a new line every 16 bytes read.
                                int count = 0;
                                // Read till the end of the file and print the byte in hexadecimal
                                while ((i = fis.read()) != -1) {
                                        System.out.printf("%02X ", i);
                                        count++;
                                        if (count == 16) {
                                                System.out.println("");
                                                count = 0;
                                        }
                                }
                                System.out.print("\n");
                                return;
                        }
                }
                // If file to display is not found...
                System.out.println(displayFilePath + " Doesn't exist. Please try again.");
        }

        // Encrypt or Decryyt files
        private void encryptFile(File inFile, File outFile, byte[] key) throws Exception {
                InputStream in = null;
                OutputStream out = null;

                try {
                        // initializing in & out streams
                        in = new FileInputStream(inFile);
                        out = new BufferedOutputStream(new FileOutputStream(outFile), 10240);

                        int b = -1;
                        long i = 0;

                        // read one byte of the file in each cycle, and use the key byte array to cycle encryption or decryption
                        while ((b = in.read()) != -1) {
                                // data is XOR with key, and then XOR with low 8 bits of cyclic variable
                                b = (b ^ key[(int) (i % key.length)] ^ (int) (i & 0xFF));
                                // write a byte after encryption / decryption
                                out.write(b);
                                i++;
                        }
                        out.flush();

                } finally {
                        close(in);
                        close(out);
                }
        }

        // Helper function to close writer streams
        public static void close(Closeable c) {
                if (c != null) {
                        try {
                                c.close();
                        } catch (IOException e) {
                                // nothing
                        }
                }
        }

        // Helper function to print directory contents
        public static void printLists(List<String> files, List<String> directories) {

                System.out.println("Files: ");
                for (String file : files) {
                        System.out.println(file);
                }
                System.out.println("\nDirectories: ");
                for (String directory : directories) {
                        System.out.println(directory);
                }
        }

        // Helper function to verify a valid directory has been selected
        public boolean findDirectory() {

                try {
                        dir.isDirectory();
                        return true;
                }
                catch (NullPointerException e) {
                        System.out.println(e + "\nDirectory invalid. Please use 1. to select a valid directory first.");
                        return false;
                }
        }

        // Main menu function
        public static void main(String[] args) throws Exception {

                // Initialize file processor constructor
                File_Processor testProcessor = new File_Processor();

                System.out.println("\nWelcome to Kylee's File/directory processing project!");

                while(true) {
                        // Print main menu
                        Scanner sc = new Scanner(System.in);
                        System.out.println("\n"
                                + "0 Exit \n"
                                + "1 Select directory \n"
                                + "2 List directory content (first level)\n"
                                + "3 List directory content (all levels) \n"
                                + "4 Delete file \n"
                                + "5 Display file (hexidecimal view) \n"
                                + "6 Encrypt file (XOR with password)\n"
                                + "7 Decrypt file (XOR with password)\n");
                        System.out.println("Please select a menu option: ");
                        int opt = sc.nextInt();
                        // Switch case to handle each option
                        switch (opt) {
                                case 0:
                                        System.exit(0);
                                        break;
                                case 1:
                                        // Get user input
                                        System.out.println("You chose Select Directory:");
                                        System.out.println("Please enter directory you would like to select: \n");
                                        String dir_path = "C:\\Users\\kmfie\\Desktop\\homework-bleh\\operating systems\\projects\\Lab6\\src\\test";
                                        File dir_file = new File(dir_path);
                                        System.out.println(dir_path);
                                        // Make sure valid directory is valid, then call selected function
                                        if (dir_file.isDirectory()) {
                                                System.out.println("Directory found!");
                                                testProcessor.setDirectory(dir_file);
                                                System.out.println("Name: " + dir_file.getName() + "\n" + "Path: " + dir_path);
                                        } else
                                                System.out.println("Directory not found\nPlease try again!");
                                        break;
                                case 2:
                                        System.out.println("You chose list directory content (first level)\n");
                                        // Make sure valid directory is valid, then call selected function
                                        if (testProcessor.findDirectory()) {
                                                testProcessor.listDirFirstLevel();
                                        } else {
                                                break;
                                        }
                                        break;
                                case 3:
                                        System.out.println("You chose list directory content (all levels)\n");
                                        // Make sure valid directory is valid, then call selected function
                                        if (testProcessor.findDirectory()) {
                                                testProcessor.listDirAllLevels();
                                        } else {
                                                break;
                                        }
                                        break;
                                case 4:
                                        System.out.println("You chose to delete a file.");
                                        // Make sure valid directory is valid, then call selected function
                                        if (testProcessor.findDirectory()) {
                                                // Get user input
                                                System.out.println("Please enter file you would like to delete: ");
                                                String dirDelName = "test_delete_2.txt";
                                                System.out.println(dirDelName);
                                                System.out.println("You chose file: " + dirDelName);
                                                testProcessor.delete(dirDelName);
                                        } else {
                                                break;
                                        }
                                        break;
                                case 5:
                                        System.out.println("You chose to display a file (hexidecimal view).");
                                        // Make sure valid directory is valid, then call selected function
                                        if (testProcessor.findDirectory()) {
                                                // Get user input
                                                String dirDisplayName = "testDisplay.txt";
                                                System.out.println(dirDisplayName);
                                                System.out.println("You chose file: " + dirDisplayName);
                                                testProcessor.display(dirDisplayName);
                                        } else {
                                                break;
                                        }
                                        break;
                                case 6:
                                        System.out.println("You chose to encrypt file.");
                                        // Make sure valid directory is valid, then call selected function
                                        if (testProcessor.findDirectory()) {
                                                // Get user input
                                                System.out.println("Please enter encryption key:");
                                                byte[] encryptKey = "Qwertyuiop[123$4$567]".getBytes();
                                                System.out.println("Qwertyuiop[123$4$567]");
                                                System.out.println("Please enter file name to encrypt: ");
                                                File encryptFile = new File("C:\\Users\\kmfie\\Desktop\\homework-bleh\\operating systems\\projects\\Lab6\\src\\test\\encryptTest.txt");
                                                File encryptOutFile = new File("C:\\Users\\kmfie\\Desktop\\homework-bleh\\operating systems\\projects\\Lab6\\src\\test\\encryptOutTest.txt");
                                                System.out.println(encryptFile);
                                                System.out.println("Encrypted contents will be in: " + encryptOutFile);
                                                testProcessor.encryptFile(encryptFile, encryptOutFile, encryptKey);
                                        } else {
                                                break;
                                        }
                                        break;
                                case 7:
                                        System.out.println("You chose to decrypt file.");
                                        // Make sure valid directory is valid, then call selected function
                                        if (testProcessor.findDirectory()) {
                                                // Get user input
                                                System.out.println("Please enter decryption key:");
                                                byte[] decryptKey = "Qwertyuiop[123$4$567]".getBytes();
                                                System.out.println("Qwertyuiop[123$4$567]");
                                                System.out.println("Please enter file name to decrypt: ");
                                                File decryptFile = new File("C:\\Users\\kmfie\\Desktop\\homework-bleh\\operating systems\\projects\\Lab6\\src\\test\\encryptOutTest.txt");
                                                File decryptOutFile = new File("C:\\Users\\kmfie\\Desktop\\homework-bleh\\operating systems\\projects\\Lab6\\src\\test\\decryptOutTest.txt");
                                                System.out.println(decryptFile);
                                                System.out.println("Decrypted contents will be in: " + decryptOutFile);
                                                testProcessor.encryptFile(decryptFile, decryptOutFile, decryptKey);
                                        } else {
                                                break;
                                        }
                                        break;
                                default:
                                        break;

                        }
                }
        }
}