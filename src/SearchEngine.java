import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Objects;

public class SearchEngine {

    private static final String baseDir = new File("").getAbsolutePath();
    private static final File searchDir = new File(baseDir + "/search");
    private static final List<File> searchFiles = new ArrayList<File>();

    public static void main(String[] args) {

        if (searchDir.exists() && searchDir.isDirectory()) {
            for (File file : Objects.requireNonNull(searchDir.listFiles())) {
                System.out.println(file);
            }
        } else {
            System.out.println("No directory found.");
        }

    }
}
