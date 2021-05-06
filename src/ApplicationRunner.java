
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.exit;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ApplicationRunner {

    static String file = System.getProperty("user.dir") + File.separator + "traders.txt"; //path to file
    static Scanner scan = new Scanner(System.in); //scanner

    //array to hold traders
    static Trader[] traders;

    static {
        try {
            traders = new Trader[numberOfLines()];           //array size
        } catch (IOException ex) {
            System.out.println("File not found Error");
        }
    }

    //string data for each trader stored in array
    static String[] strings;

    static {
        try {
            strings = new String[numberOfLines()];         //array size
        } catch (IOException ex) {
            System.out.println("File not found Error");
        }
    }

    //method to calculate number of lines in file
    public static int numberOfLines() throws IOException {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                lines++;
            }
        }
        return lines;
    }

    //main method that loads the data into a collection
    public static void main(String[] args) {

        try {

            for (int i = 0; i < numberOfLines(); i++) {

                strings[i] = Files.readAllLines(Paths.get(file)).get(i); //creating strings from the file

                String[] parts = strings[i].split("\\:"); //String array, each element is text between dots

                String company = parts[0];
                String location = parts[1];
                String services = parts[2];
                int employees = Integer.parseInt(parts[3]);
                double rate = Double.parseDouble(parts[4]);
                String description = parts[5];

                traders[i] = new Trader(company, location, services, employees, rate, description);  //putting all traders into the array

            }

        } catch (IOException ex) {
            System.out.println("File not found error");
        }

        system(); //the method for the program ui starts

    }

    public static void system() {

        try {

            System.out.println("List traders.......1");
            System.out.println("Select trader......2");
            System.out.println("Search locations...3");
            System.out.println("Search services....4");
            System.out.println("Exit...............0");
            System.out.println("");
            System.out.print("Enter choice:> ");

            String input = scan.nextLine();

            if (null == input) {
                System.out.println("Please choose 1 or 0");
                system();
            } else {
                switch (input) {
                    case "1": {
                        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
                        System.out.print("| ");
                        System.out.format("%2s", "ID");
                        System.out.print(" | ");
                        System.out.format("%-23s", "Company Name");
                        System.out.print("| ");
                        System.out.format("%-17s", "Location");
                        System.out.print("| ");
                        System.out.format("%-69s", "Services Offered");
                        System.out.print("  |");
                        System.out.println("");
                        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
                        for (int i = 0; i < numberOfLines(); i++) {
                            System.out.print("| ");
                            System.out.format("%2d", i + 1);
                            System.out.print(" | ");
                            System.out.format("%-23s", traders[i].getCompanyName().trim());  //trimming to remove any initial and ending spaces the string might have
                            System.out.print("| ");
                            System.out.format("%-17s", traders[i].getLocation().trim());
                            System.out.print("| ");
                            System.out.format("%-69s", traders[i].getServices().trim());
                            System.out.print("  |");
                            System.out.println("");
                        }
                        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("");
                        system();
                    }
                    break;
                    case "2": {
                        System.out.println("");
                        System.out.print("Enter trader ID from list [1 - 25] :> ");
                        String choice = scan.nextLine();
                        if ((Integer.parseInt(choice) >= 1) && (Integer.parseInt(choice) <= 25)) {
                            String traderString = traders[Integer.parseInt(choice) - 1].toString(); //converting the trader object to string
                            String[] parts = traderString.split("\\:"); //String array, each element is text between dots
                            System.out.println("");
                            int tableLength; //int to determine how many times to print the dashes
                            if (parts[5].length() > parts[2].length()) {      //if description length > services length, then tableLength = description length + 25
                                tableLength = parts[5].length() + 25;
                            } else {                                          //else tableLength = services length + 25
                                tableLength = parts[2].length() + 25;
                            }
                            for (int i = 0; i < tableLength; i++) {
                                System.out.print("-");
                            }
                            System.out.println("");
                            System.out.format("%-25s", "Company name:");
                            System.out.println(parts[0].trim());
                            System.out.format("%-25s", "Location:");
                            System.out.println(parts[1].trim());
                            System.out.format("%-25s", "Services Offered:");
                            System.out.println(parts[2].trim());
                            System.out.format("%-25s", "Number of employees");
                            System.out.println(parts[3].trim());
                            System.out.format("%-25s", "Daily call out rate (£):");
                            System.out.println(parts[4].trim());
                            System.out.format("%-25s", "Other information:");
                            System.out.println(parts[5].trim());
                            for (int i = 0; i < tableLength; i++) {
                                System.out.print("-");
                            }
                            System.out.println("");
                            System.out.println("");
                            system();
                        } else {
                            System.out.println("");
                            System.out.println("Invalid input. Please enter between 1 and 25");
                            System.out.println("");
                            system();
                        }
                    }
                    break;
                    case "3": {
                        System.out.println("");
                        System.out.print("Enter search text > ");
                        String search = scan.nextLine();
                        System.out.println("");
                        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
                        System.out.print("| ");
                        System.out.format("%2s", "ID");
                        System.out.print(" | ");
                        System.out.format("%-23s", "Company Name");
                        System.out.print("| ");
                        System.out.format("%-17s", "Location");
                        System.out.print("| ");
                        System.out.format("%-69s", "Services Offered");
                        System.out.print("  |");
                        System.out.println("");
                        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
                        search = search.toLowerCase();
                        String[] locations = new String[numberOfLines()];
                        for (int i = 0; i < numberOfLines(); i++) {
                            locations[i] = traders[i].getLocation().toLowerCase();
                            if (locations[i].contains(search)) {                         //display all traders that contain the search criteria
                                System.out.print("| ");
                                System.out.format("%2d", i + 1);
                                System.out.print(" | ");
                                System.out.format("%-23s", traders[i].getCompanyName().trim());
                                System.out.print("| ");
                                System.out.format("%-17s", traders[i].getLocation().trim());
                                System.out.print("| ");
                                System.out.format("%-69s", traders[i].getServices().trim());
                                System.out.print("  |");
                                System.out.println("");
                            }
                        }
                        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("");
                        system();
                    }
                    break;
                    case "4": {
                        System.out.println("");
                        System.out.print("Enter search text > ");
                        String search = scan.nextLine();
                        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
                        System.out.print("| ");
                        System.out.format("%2s", "ID");
                        System.out.print(" | ");
                        System.out.format("%-23s", "Company Name");
                        System.out.print("| ");
                        System.out.format("%-17s", "Location");
                        System.out.print("| ");
                        System.out.format("%-69s", "Services Offered");
                        System.out.print("  |");
                        System.out.println("");
                        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
                        search = search.toLowerCase();
                        String[] services = new String[numberOfLines()];
                        for (int i = 0; i < numberOfLines(); i++) {
                            services[i] = traders[i].getServices().toLowerCase();
                            if (services[i].contains(search)) {                                   //display all traders that contain the search criteria
                                System.out.print("| ");
                                System.out.format("%2d", i + 1);
                                System.out.print(" | ");
                                System.out.format("%-23s", traders[i].getCompanyName().trim());
                                System.out.print("| ");
                                System.out.format("%-17s", traders[i].getLocation().trim());
                                System.out.print("| ");
                                System.out.format("%-69s", traders[i].getServices().trim());
                                System.out.print("  |");
                                System.out.println("");
                            }
                        }
                        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("");
                        system();
                    }
                    break;
                    case "0": //exit program                                             
                    {
                        System.out.println("Program closed");
                        exit(0);
                    }
                    break;
                    default: //if user enters anything else tell the user to choose between the possible options
                        System.out.println("");
                        System.out.println("Please choose 1, 2, 3, 4 or 0");
                        System.out.println("");
                        system();
                        break;
                }
            }

        } catch (IOException ex) {
            System.out.println("File not found error");       //catching error if file is not found
        }

    }

}
