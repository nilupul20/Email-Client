import java.text.SimpleDateFormat;

public abstract class Recipient {
    protected static SimpleDateFormat sDFormat = new SimpleDateFormat("yyyy/MM/dd");
    private String name ;
    private String email ;
    private String resType;

    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }

    public String getResType() {
        return resType;
    }
}
