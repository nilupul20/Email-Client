import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class RecipientTxt {
    private File file;
    
    public RecipientTxt(){
        try {
            File f = new File("clientList.txt");
            f.createNewFile();
            this.file = f.getAbsoluteFile();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // add new recipient
    public void addRec(String test) throws IOException {
        FileWriter Writer = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(Writer );
        bw.write(test + "\n");
        bw.close();
        Writer.close();
        System.out.println("new recipient added.");
    }

    // read all recipients to a list and return
    public List<String> getRec() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("clientList.txt"));
        return lines;
    }
}
