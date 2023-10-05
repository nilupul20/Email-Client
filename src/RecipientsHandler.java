import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class RecipientsHandler {
    private  ArrayList<Recipient> recipients = new ArrayList<Recipient>() ; // store all recipient objects
    private  ArrayList<Greetable> greetables = new ArrayList<Greetable>(); // store all personal and office friends
    private RecipientTxt recipientTxt;
    public RecipientsHandler(){
        this.recipientTxt = new RecipientTxt();
    }

    // add new recipient to the text file
    public void addRecipient(String new_res) throws IOException, ParseException {
        String[] newRes = (new_res.replaceAll("\\s","")).split("[:,]");
        String resType = newRes[0];

        // check whether all parameters for a recipient are given
        if(     (resType.equalsIgnoreCase("Official") && (newRes.length == 4)) ||
                (resType.equalsIgnoreCase("Office_friend") && (newRes.length == 5)) ||
                (resType.equalsIgnoreCase("Personal") && (newRes.length == 5))){
            this.recipientTxt.addRec(new_res);
            this.create(new_res); // create new recipient object
        }
        else{
            System.out.println("ERROR : Invalid Input");
        }
    }

    // create objects for all recipients in the text file. those will store in the recipients arrayList
    // if recipient is personal friend or office friend, store it in greetables arrayList too.
    public void create() throws ParseException, IOException {

        List<String> resList = recipientTxt.getRec(); // get recipients from the text file

        for(String res : resList){
            String reS = res.replaceAll("\\s","");
            String[] strings = reS.split("[:,]");
            String resType = strings[0];

            // creating recipient objects
            if(resType.equalsIgnoreCase("Official")){
                Official o = new Official(strings[1], strings[2], strings[3]);
                this.recipients.add((Recipient) o);
            }
            else if(resType.equalsIgnoreCase("Office_friend")) {
                OfficeFriend o = new OfficeFriend(strings[1], strings[2], strings[3], strings[4]);
                this.recipients.add(o);
                this.greetables.add(o);
            }
            else if(resType.equalsIgnoreCase("Personal")) {
                Personal o = new Personal(strings[1], strings[2], strings[3], strings[4]);
                this.recipients.add(o);
                this.greetables.add(o);
            }
        }
    }

    // overload create method to create one recipient object
    // This will use to create new object when new recipient is added
    public void create(String new_res) throws ParseException, IOException {
        String reS = new_res.replaceAll("\\s", "");
        String[] strings = reS.split("[:,]");
        String resType = strings[0];

        if (resType.equalsIgnoreCase("Official")) {
            Official o = new Official(strings[1], strings[2], strings[3]);
            this.recipients.add((Recipient) o);
        } else if (resType.equalsIgnoreCase("Office_friend")) {
            OfficeFriend o = new OfficeFriend(strings[1], strings[2], strings[3], strings[4]);
            this.recipients.add(o);
            this.greetables.add(o);
        } else if (resType.equalsIgnoreCase("Personal")) {
            Personal o = new Personal(strings[1], strings[2], strings[3], strings[4]);
            this.recipients.add(o);
            this.greetables.add(o);
        } else {
            System.out.println("ERROR : Recipient type is note defined");
        }
    }
    public ArrayList<Greetable> getGreetables(){
        return greetables;
    }

    public ArrayList<Recipient> getRecipients() {
        return recipients;
    }
}
