import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SearchEngine {

    private static final String baseDir = new File("").getAbsolutePath();
    private static final File searchDir = new File(baseDir + "/search");
    private static final List<File> searchFiles = new ArrayList<>();
    private static final List<String> validFormats = Arrays.asList("txt", "md");

    public static void main(String[] args) {

        if (searchDir.exists() && searchDir.isDirectory()) {
            for (File file : Objects.requireNonNull(searchDir.listFiles())) {
                if (checkForValidFileFormat(file)) {
                    searchFiles.add(file);
                }
            }
        } else {
            System.out.println("No directory found.");
            System.exit(1);
        }

        for (String match : fileSearch(searchFiles, "this")) {
            System.out.println(match);
        }
    }

    private static boolean checkForValidFileFormat(File file) {
        int extensionIndex = file.getName().lastIndexOf(".");
        if (extensionIndex == -1) return false;

        String extension = file.getName().substring(extensionIndex + 1);
        return validFormats.contains(extension);
    }

    public static List<String> fileSearch(List<File> searchFiles, String query) {
        List<String> searchResults = new ArrayList<>();
        query = query.toLowerCase();

        for (File file : searchFiles) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine().toLowerCase();
                int lineNumber = 1;
                while (line != null) {
                    if (line.contains(query)) searchResults.add(
                            file.getName() + " in line " + lineNumber
                    );
                    line = reader.readLine();
                    lineNumber++;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return searchResults;
    }
}
