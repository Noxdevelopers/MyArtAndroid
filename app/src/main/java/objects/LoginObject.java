package objects;

public class LoginObject {

    private String state;
    private int id;
    private UserObject UserObject;
    private String session;

    public String getState() {
        return state;
    }
    public int getId() {
        return id;
    }

    public UserObject getUserObject() {
        return UserObject;
    }

    public String getSession() {
        return session;
    }
}



//{"state":"SUCCESS","UserObject":{"ID":"38","EMAIL":"amir@gmail.com","PHONE":"09102305286","FULLNAME":"\u0627\u0645\u06cc\u0631 \u0642\u0647\u0631\u0645\u0627\u0646\u06cc","SESSION":"76d02b212b5980e1a0c8fc8b92a08f42"}}