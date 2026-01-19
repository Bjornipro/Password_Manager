package org.example.passwordmanager;

import java.security.MessageDigest;

public class Hashing {

    public static String generate_hash(String password) {
        try{
            MessageDigest md =  MessageDigest.getInstance("SHA-256");
            byte[] pass_hashed =  md.digest(password.getBytes());
            StringBuilder sb =  new StringBuilder();
            for(byte b :pass_hashed  ){
                sb.append(String.format("%02x",b));
            }
            return sb.toString() ;



        }catch(Exception e){
            e.printStackTrace();
            return null ;

        }

    }
}
