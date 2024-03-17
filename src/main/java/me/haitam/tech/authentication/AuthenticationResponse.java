package me.haitam.tech.authentication;

public class AuthenticationResponse {
    private String token;
    private boolean isEmailAlreadyExist;
    private boolean isUsernameAlreadyExist;

    public AuthenticationResponse(String token){
        this.token = token;
    }

    public AuthenticationResponse(boolean isEmailAlreadyExist, boolean isUsernameAlreadyExist){
        this.isEmailAlreadyExist = isEmailAlreadyExist;
        this.isUsernameAlreadyExist = isUsernameAlreadyExist;
    }

    public String getToken(){
        return this.token;
    }

    public boolean getIsUsernameAlreadyExist(){
        return this.isUsernameAlreadyExist;
    }

    public boolean getIsEmailAlreadyExist(){
        return this.isEmailAlreadyExist;
    }
}
