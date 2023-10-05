// 200569E
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Email_Client {

    public static void main(String[] args) throws IOException, ParseException {
        // store sent mails
        MailStore mailStore = MailStore.getInstance();
        // get previously stored mails to the application
        mailStore.deserializeMail();

        RecipientsHandler resHandler = new RecipientsHandler();

        // create objects for all recipients
        try {
            resHandler.create();
        }
        catch (Exception e){
            e.printStackTrace();
        }


        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdformat2 = new SimpleDateFormat("MM/dd");

        // send wishes to all personal and official friends who have birthdays today.
        System.out.println("\nSending birthday wishes. Please Wait....");
        Date today = new Date();
        for( Greetable res : resHandler.getGreetables()){
            if( (sdformat2.format(res.getBirthDay())).equals(sdformat2.format(today)) ){
                res.greet();
            }
        }
        System.out.println("All Birthday wishes sent Successfully !\n");



        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter option type: \n"
                + "1 - Adding a new recipient\n"
                + "2 - Sending an email\n"
                + "3 - Printing out all the recipients who have birthdays\n"
                + "4 - Printing out details of all the emails sent\n"
                + "5 - Printing out the number of recipient objects in the application\n"
                + "-1 - exit");

        boolean run = true;
        while (run){
            System.out.println("\nEnter option type: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    // add new recipient
                    // add recipient to the file and create new recipient object
                    System.out.println("Enter Recipient Details : ");
                    String new_recipient = scanner.nextLine();
                    try {
                        resHandler.addRecipient(new_recipient);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    // send an email
                    // emails will be serialized
                    System.out.println("Enter recipient email : ");
                    String email = scanner.nextLine();
                    System.out.println("Enter Subject : ");
                    String subject = scanner.nextLine();
                    System.out.println("Enter Body : ");
                    String body = scanner.nextLine();

                    System.out.println("Sending.... ");
                    Mail mail = new Mail(email, subject, body);
                    mail.sendMail();
                    break;

                case 3:
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // print recipients who have birthdays on the given date
                    // only check for month and date
                    System.out.println("Enter Date : ");
                    String checkDate = scanner.nextLine();
                    Date givenDay = sdformat.parse(checkDate);

                    int count = 0;
                    //
                    for(Greetable res: resHandler.getGreetables()){
                         Date resBD = res.getBirthDay();
                         if((sdformat2.format(resBD)).equals(sdformat2.format(givenDay))){
                             Recipient reS = (Recipient) res;
                             System.out.println(reS.getResType() +" : "+reS.getName());
                             count++;
                         }
                    }
                    if(count==0)
                        System.out.println("No recipient has Birthday on this day.");
                    break;

                case 4:
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // print the details of all the emails sent on the input date
                    System.out.println("Enter Date : ");
                    String checkMails = scanner.nextLine();
                    mailStore.showMails(checkMails);
                    break;

                case 5:
                    // print the number of recipient objects in the application
                    System.out.println("No of Recipients: "+resHandler.getRecipients().size());
                    break;

                case -1:
                    run = false;
                    break;
                default:
                    System.out.println("Unknown parameter !!! ");
            }
        }
    }
}


