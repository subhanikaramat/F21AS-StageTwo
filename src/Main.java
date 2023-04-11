import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Main {
  static final String PATH = "out/";  // File path constant
  
  public static void main(String[] args) {
    AtomicInteger score = new AtomicInteger(0);  // Atomic integer to keep track of the score
    ArrayList<String> outputs = new ArrayList<>();  // ArrayList to store output messages
    try (Stream<Path> paths = Files.list(Paths.get("out/", new String[0]))) {  // Open a stream of paths in the "out/" directory
      paths
        .forEach(pathToFile -> {  // For each path in the stream
            try {
              File file = pathToFile.toFile();  // Convert the path to a file
              if (file.length() > 0L) {  // Check if the file is not empty
                if (OutputFileHandler.handleFile(pathToFile)) {  // Call a method to handle the file and check if it passes
                  outputs.add(pathToFile + " PASSED");  // Add a passed message to the outputs
                  score.incrementAndGet();  // Increment the score
                } else {
                  outputs.add(pathToFile + " FAILED");  // Add a failed message to the outputs
                } 
              } else {
                outputs.add(pathToFile + " FAILED");  // Add a failed message to the outputs
                outputs.add(pathToFile + " has FAILED due to empty output file");  // Add a specific error message to the outputs
              } 
            } catch (Exception e) {
              e.printStackTrace();  // Print stack trace if an exception occurs
            } 
          });
    } catch (IOException e) {
      e.printStackTrace();  // Print stack trace if an I/O exception occurs
    } 
    Collections.sort(outputs);  // Sort the outputs alphabetically
    outputs.stream().forEach(System.out::println);  // Print each output message
    System.out.println("TOTAL: " + score.get() + "/50");  // Print the total score
  }
}
