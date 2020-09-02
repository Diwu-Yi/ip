import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class serves to save users' task lists to local machines' disk
 * so that the previous run's list will be available to users for reference.
 */
public class Storage {

    //public static String home = System.getProperty("src/data/duke.txt");
    private static final String home = "data";

    private static java.nio.file.Path path = java.nio.file.Paths.get(home, "duke.txt");

    private static boolean directoryExists = java.nio.file.Files.exists(path);

    /**
     * Retrieves stored tasks data from storage and print it for the user.
     */
    public static void read() {
        try {
            File storage = new File(home);

            if (!directoryExists) {
                System.out.println("I am sorry, but the folder does not exist yet.\n" +
                        "Let me create one for you now :).");

                if (storage.mkdir()) {
                    System.out.println("Folder is created");
                } else {
                    System.out.println("Folder cannot be created");
                }
            }

            File myFile = path.toFile();

            if (!myFile.createNewFile()) {
                Scanner sc = new Scanner(myFile);

                while (sc.hasNextLine()) {
                    String item = sc.nextLine();
                    System.out.println(item);

                }
                sc.close();
                System.out.println("All items successfully shown");

            } else {
                System.out.println("I am sorry, but the file does not exist yet.\n" +
                        "Please create one.");
            }

        } catch (IOException e) {

            e.printStackTrace();
            //throw e;

        }
    }

    /**
     * Writes(stores) user data to hard disk.
     *
     * @param newTaskStorage the array list to store all tasks of the user
     */
    public static void write(ArrayList<Task> newTaskStorage) {
        try {

            FileWriter fw = new FileWriter(path.toFile());
            for (Task task : newTaskStorage) {
                String[] formatter = task.toString().split(" ");
                fw.write(formatter[0] + " " +
                        formatter[1] + " " +
                        (formatter.length > 2 ? formatter[2] : ""));
            }

            fw.close();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
