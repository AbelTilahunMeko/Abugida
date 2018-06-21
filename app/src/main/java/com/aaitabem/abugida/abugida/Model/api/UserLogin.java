package com.aaitabem.abugida.abugida.Model.api;

/**
 * Created by ${Abel_Tilahun} on ${4/9/2018}.
 */
public class UserLogin {
    //This is the class that populate what we will get from the server

    private String message;
    private String token;
    private String error;

    private boolean sucess;



    public String getError(){
        return error;
    }
    public void setError(String error){
        this.error= error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setSuccess(boolean success){
        this.sucess = success;
    }
    public boolean getSuccess(){
        return sucess;
    }

}
