package com.tiy.hack;

/**
 * Created by RdDvls on 10/2/16.
 */
public class LoginContainer {
    String errorMessage;
    User user;

    public LoginContainer() {
    }

    public LoginContainer(String errorMessage, User user) {
        this.errorMessage = errorMessage;
        this.user = user;
    }

    //Getters and setters
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
