package objects;


import com.google.gson.annotations.SerializedName;

public class UserObject {

    @SerializedName("ID")
    private int id;
    @SerializedName("FULLNAME")
    private String fullname;
    @SerializedName("PHONE")
    private String phone;
    @SerializedName("EMAIL")
    private String email;
    @SerializedName("SESSION")
    private String seesion;

    public int getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getSeesion() {
        return seesion;
    }
}



//{"state":"SUCCESS","UserObject":{"ID":"38","EMAIL":"amir@gmail.com","PHONE":"09102305286","FULLNAME":"\u0627\u0645\u06cc\u0631 \u0642\u0647\u0631\u0645\u0627\u0646\u06cc","SESSION":"614fba2dbeef06894595cd8ad9f2e8eb"}}

