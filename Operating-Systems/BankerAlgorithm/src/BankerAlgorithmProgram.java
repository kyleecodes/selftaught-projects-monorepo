//BankerAlgorithmProgram.java

//import required packages

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

//Declare the class

public class BankerAlgorithmProgram {

  //main method

  public static void main(String[] args)
    throws IOException, FileNotFoundException {
    //Declare local variables

    int num_process = 0, num_resources = 0, lineCount = 0;

    int[] sumColumn, sumRow, resourceVector, availableVector;

    int[] current_available, process_order;

    int index = 0;

    boolean finish[];

    int[][] max_resource_matrix, allocationMatrix, needMatrix;

    boolean isSafe = false;

    // Variables to read in line from a file and tokenize

    String line, filename;

    StringTokenizer lines;

    @SuppressWarnings("resource")
    Scanner input = new Scanner(System.in);

    System.out.print("Enter the name of the input file : ");

    filename = input.nextLine();

    // build input stream

    FileReader fr = new FileReader(filename);

    // Use Buffered reader to read one line at a time

    BufferedReader br = new BufferedReader(fr);

    // Read first line

    line = br.readLine();

    num_process = Integer.parseInt(line);

    // Read the second line

    line = br.readLine();

    num_resources = Integer.parseInt(line);

    //Create 2D array for each of the m process and n resources

    max_resource_matrix = new int[num_process][num_resources];

    allocationMatrix = new int[num_process][num_resources];

    needMatrix = new int[num_process][num_resources];

    //Create resourceVector

    resourceVector = new int[num_resources];

    //Create availableVector

    availableVector = new int[num_resources];

    current_available = new int[num_resources];

    finish = new boolean[num_process];

    process_order = new int[num_process];

    sumColumn = new int[num_resources];

    sumRow = new int[num_process];

    line = br.readLine();

    // Read the file and get the claimMatrix from the file

    while (line != null && lineCount < num_process) {
      lines = new StringTokenizer(line);

      if (lines.hasMoreTokens()) {
        for (int j = 0; j < num_resources; j++) {
          max_resource_matrix[lineCount][j] =
            Integer.parseInt(lines.nextToken());
        }
      }

      line = br.readLine();

      lineCount++;
    }

    lineCount = 0;

    // Read the file and get the Allocation Matrix

    while (line != null && lineCount < num_process) {
      lines = new StringTokenizer(line);

      if (lines.hasMoreTokens()) {
        for (int j = 0; j < num_resources; j++) {
          allocationMatrix[lineCount][j] = Integer.parseInt(lines.nextToken());
        }
      }

      line = br.readLine();

      lineCount++;
    }

    // Read the last line and set Resource Vector

    lines = new StringTokenizer(line);

    while (lines.hasMoreTokens()) {
      for (int i = 0; i < num_resources; i++) {
        resourceVector[i] = Integer.parseInt(lines.nextToken());
      }
    }

    // Close the bufferreader and file

    br.close();

    fr.close();

    // Determine the initial need matrix

    for (int i = 0; i < num_process; i++) {
      for (int j = 0; j < num_resources; j++) {
        needMatrix[i][j] = max_resource_matrix[i][j];
        //                   allocationMatrix[i][j];
      }
    }

    // Determine the initial available vector

    for (int i = 0; i < num_process; i++) {
      for (int j = 0; j < num_resources; j++) {
        sumColumn[j] += allocationMatrix[i][j];

        sumRow[i] += needMatrix[i][j];
      }
    }

    for (int j = 0; j < num_resources; j++) {
      availableVector[j] = resourceVector[j] - sumColumn[j];
    }

    // Initialize Work and Finish

    for (int j = 0; j < num_resources; j++) {
      current_available[j] = availableVector[j];
    }

    for (int i = 0; i < num_process; i++) {
      finish[i] = false;
    }

    // Safety Algorithm (checks if the system is in a safe or unsafe state)

    boolean unsafe = false;

    do {
      // Process found flag

      unsafe = false;

      int i = 0;

      for (; i < num_process; i++) {
        if ((!finish[i])) {
          boolean good = true;

          for (int j = 0; j < num_resources; j++) {
            // If the need is greater than the available,

            //then the process is not able to run to completion

            // So it is not good

            if (needMatrix[i][j] > current_available[j]) {
              good = false;

              break;
            }
          }

          // Try another process

          if (!good) continue;

          unsafe = true;

          break;
        }
      }

      // Process is found that can run to completion, simulate execution

      if (unsafe) {
        finish[i] = true;

        for (int j = 0; j < num_resources; j++) {
          current_available[j] += allocationMatrix[i][j];
        }

        process_order[index++] = i;
      }
    } while (unsafe);

    //check whether all process are finished or not

    for (int i = 0; i < num_process; i++) {
      if (!finish[i]) {
        isSafe = false;
      } else {
        isSafe = true;
      }
    }

    // Display output

    System.out.println("Number of Processes : " + num_process);

    System.out.println("Number of Resources : " + num_resources + "\n");

    //Display the max_resource_matrix

    System.out.println("Max_resource_Matrix : ");

    for (int i = 0; i < num_process; i++) {
      for (int j = 0; j < num_resources; j++) {
        System.out.print(max_resource_matrix[i][j] + " ");
      }

      System.out.println();
    }

    //Display allocation matrix

    System.out.println("\nAllocation Matrix : ");

    for (int i = 0; i < num_process; i++) {
      for (int j = 0; j < num_resources; j++) {
        System.out.print(allocationMatrix[i][j] + " ");
      }

      System.out.println();
    }

    //Display resource matrix

    System.out.println("\nResource Vector : ");

    for (int i = 0; i < num_resources; i++) {
      System.out.print(resourceVector[i] + " ");
    }

    System.out.println();

    //Display the Need matrix

    System.out.println("\nNeed Matrix : ");

    for (int i = 0; i < num_process; i++) {
      for (int j = 0; j < num_resources; j++) {
        System.out.print(needMatrix[i][j] + " ");
      }

      System.out.println();
    }

    //Display the Available Vector

    System.out.println();

    System.out.println("Initial Available Vector : ");

    for (int j = 0; j < num_resources; j++) {
      System.out.print(availableVector[j] + " ");
    }

    System.out.println("\n");

    //Print the output

    if (isSafe) {
      System.out.print("Process order of sequence : ");

      for (int i = 0; i < process_order.length; i++) {
        System.out.print((process_order[i] + 1) + " ");
      }

      System.out.println();

      System.out.println("This system is in a safe state!!!");
    } else {
      System.out.println("This system is not in a safe state!!!");
    }
  }
}
