package com.aaitabem.abugida.abugida.Model.api;

/**
 * Created by ${Abel_Tilahun} on ${4/9/2018}.
 */
public class LoginModel {
    private String phone;
    private String password;

    public LoginModel(String userName, String password){
        this.phone = userName;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}
