
    import java.io.*;

public class FileHandler {
    public static void main(String[] args) {
        String fileName = "example.txt";

        // Writing to a file
        writeToFile(fileName, "Hello, this is a sample file content!");

        // Reading from a file
        readFromFile(fileName);

        // Modifying a file
        modifyFile(fileName, "sample", "modified");
        
        // Reading modified file
        readFromFile(fileName);
    }

    // Method to write content to a file
    public static void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            System.out.println("File written successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read content from a file
    public static void readFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("File Content:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to modify content in a file
    public static void modifyFile(String fileName, String oldWord, String newWord) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line.replaceAll(oldWord, newWord)).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write modified content back to file
        writeToFile(fileName, content.toString());
        System.out.println("File modified successfully!");
    }
}


