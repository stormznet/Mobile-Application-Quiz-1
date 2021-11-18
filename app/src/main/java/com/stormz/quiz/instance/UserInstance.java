package com.stormz.quiz.instance;


import java.util.ArrayList;
import java.util.Vector;

public class UserInstance
{

    private String idUser, name, email, pass, gender, password, dob;

    public UserInstance(String idUser, String name, String email, String pass, String gender, String password, String dob) {
        this.idUser = idUser;
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.gender = gender;
        this.password = password;
        this.dob = dob;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
