package oneALvWebScrapper.utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtility {
    private String fileLocation = "C:\\Users\\extes\\OneDrive\\Dators\\QA\\JavaGuruQA2\\scrapper_data.txt";

    public void createTxtFile() {
        try {
            File file = new File(fileLocation);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public void appendToTxtFile(String textToAdd) {
        try {
            FileWriter myWriter = new FileWriter(fileLocation, true);
            myWriter.append(textToAdd);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void deleteFile() {
        try {
            File file = new File(fileLocation);
            if (file.delete()) {
                System.out.println(file.getName() + " file deleted.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}



