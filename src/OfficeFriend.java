import java.text.ParseException;
import java.util.Date;

public class OfficeFriend extends Recipient implements Greetable {
    private String name;
    private String designation ;
    private String email;
    private Date birthDay;
    private String resType  ;
    public OfficeFriend(String name, String email, String designation, String birth_day) throws ParseException {
        this.name = name;
        this.designation = designation;
        this.email = email;
        this.birthDay = sDFormat.parse(birth_day);
        this.resType= "Office_friend";
    }

    public void greet(){
        Mail OfficeFriendWish =new Mail(this.email, "Birthday", "Wish you a Happy Birthday!\n\n~Nilupul");
        OfficeFriendWish.sendMail();
    }

    @Override
    public Date getBirthDay() {
        return birthDay;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public String getDesignation() {
        return designation;
    }

    @Override
    public String getResType() {
        return resType;
    }
}

