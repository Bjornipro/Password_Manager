package org.example.passwordmanager;

public class User {
    private String username ;
    private String hashedpassword ;




    public User (String username, String hashedpassword){
        this.username =  username ;
        this.hashedpassword =  hashedpassword ;
    }
    public String get_username(){
        return username ;
    }
    public String getHashedpassword(){
        return hashedpassword ;
    }
}
