import java.io.*;
import java.util.ArrayList;

public class MailStore {
    // Store all mails in the ArrayList
    private ArrayList<Mail> mails = new ArrayList<>();

    private static MailStore instance;

    private MailStore(){

    }

    // Serialize arrayList to a text file everytime an email is sent.
    public void serializeMail(Mail mail) {
        mails.add(mail);
        try {
            FileOutputStream fs = new FileOutputStream("mails.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fs);
            oos.writeObject(mails);
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // deserialize mails back to arrayList
    public void deserializeMail() {
        File file = new File("mails.txt");
        if(file.exists()) {
            try {
                FileInputStream fileStream = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fileStream);
                mails = (ArrayList<Mail>) ois.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void showMails(String date){
        int count = 0;
        for( Mail m : mails){
            if(m.getDate().equals(date)){
                System.out.println(m);
                count++;
            }
        }
        if(count==0) System.out.println("No mails have sent on this day.\n");
    }

    // use singleton design pattern to ensure there is exactly one MailStore
    public static MailStore getInstance(){
        if(instance == null)
            instance = new MailStore();
        return instance;
    }


}

