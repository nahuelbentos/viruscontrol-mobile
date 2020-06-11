package com.grupo14.viruscontrol.viruscontroluy.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String displayName;
    private String imageURL;
    private String userIdFacebook;
    private String userName;
    private String userLastNaem;
    private String token;

    public LoggedInUser(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

    public LoggedInUser(String userId, String displayName, String imageURL, String userIdFacebook, String userName, String userLastNaem) {
        this.userId = userId;
        this.displayName = displayName;
        this.imageURL = imageURL;
        this.userIdFacebook = userIdFacebook;
        this.userName = userName;
        this.userLastNaem = userLastNaem;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getUserIdFacebook() {
        return userIdFacebook;
    }

    public void setUserIdFacebook(String userIdFacebook) {
        this.userIdFacebook = userIdFacebook;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastNaem() {
        return userLastNaem;
    }

    public void setUserLastNaem(String userLastNaem) {
        this.userLastNaem = userLastNaem;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }
}
