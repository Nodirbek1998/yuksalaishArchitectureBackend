package uz.cas.controllersestem.payload.request;

import javax.validation.constraints.NotBlank;

public class ReqUser {

    @NotBlank(message = "malumot kiritilmagan")
    private String firstName;
    @NotBlank(message = "malumot kiritilmagan")
    private String lastName;
    @NotBlank(message = "malumot kiritilmagan")
    private String username;
    @NotBlank(message = "malumot kiritilmagan")
    private String password;
    @NotBlank(message = "malumot kiritilmagan")
    private String job;

    public ReqUser() {
    }

    public ReqUser(String firstName, String lastName, String username, String password, String job) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.job = job;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
