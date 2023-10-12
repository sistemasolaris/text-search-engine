import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.util.Objects;

public class SearchEngine {

    private static final String baseDir = new File("").getAbsolutePath();
    private static final File searchDir = new File(baseDir + "/search");
    private static final List<File> searchFiles = new ArrayList<File>();
    private static final List<String> validFormats = Arrays.asList("txt", "md");

    public static void main(String[] args) {

        if (searchDir.exists() && searchDir.isDirectory()) {
            for (File file : Objects.requireNonNull(searchDir.listFiles())) {
                if (checkForValidFileFormat(file)) {
                    System.out.println(file);
                }
            }
        } else {
            System.out.println("No directory found.");
        }

    }

    private static boolean checkForValidFileFormat(File file) {
        int extensionIndex = file.getName().lastIndexOf(".");
        if (extensionIndex == -1) return false;

        String extension = file.getName().substring(extensionIndex + 1);
        return validFormats.contains(extension);
    }
}
