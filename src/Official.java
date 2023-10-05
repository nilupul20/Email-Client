public class Official extends Recipient {
    private String name;
    private String email;
    private String designation;
    private String resType;

    public Official(String name, String email, String designation){
        this.name = name;
        this.email = email;
        this.designation = designation;
        this.resType ="Official";
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

