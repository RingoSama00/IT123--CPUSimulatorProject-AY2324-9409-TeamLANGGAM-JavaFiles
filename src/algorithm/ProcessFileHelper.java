package algorithm;

// data members
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// class to help csv files to load into process list
public class ProcessFileHelper {
    public static List<Process> loadProcesses(String filename) {
        List<Process> processes = new ArrayList<>();
        // try catch block for file finder
        try {
            Scanner sc = new Scanner(new File(filename));
            sc.nextLine(); // Remove CSV Header

            while (sc.hasNextLine()) {
                // splits csv line and adds to process list as a splitted array
                String[] splittedString = sc.nextLine().split(",");
                processes.add(new Process(splittedString[0], Integer.parseInt(splittedString[1]),
                        Integer.parseInt(splittedString[2]), splittedString[3]));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("File: " + filename + " not found");
            System.exit(0);
        }
        return processes;
    }
}
