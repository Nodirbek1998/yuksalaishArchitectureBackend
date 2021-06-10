package uz.cas.controllersestem.payload;

public class ReqLogin {

    private String username;

    private String password;

    public ReqLogin() {
    }

    public ReqLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ReqLogin(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
