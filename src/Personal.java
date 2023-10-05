import java.text.ParseException;
import java.util.Date;

public class Personal extends Recipient implements Greetable{
    private String name;
    private String nickName ;
    private String email;
    private Date birthDay;
    private String resType;

    public Personal(String name, String nickName, String email, String birth_day) throws ParseException {
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.birthDay = sDFormat.parse(birth_day);
        this.resType = "Personal";
    }

    public void greet(){
        Mail personalWish = new Mail(this.email, "Birthday", "Hugs and love on your birthday!\n\n~Nilupul");
        personalWish.sendMail();
    }

    public Date getBirthDay() {
        return birthDay;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getResType() {
        return resType;
    }
}
