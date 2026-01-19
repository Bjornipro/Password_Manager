package org.example.passwordmanager;
import java.io.*;
public class Storage {
    private static final String File_PATH = "data/user.txt";

    public static void file(){
        try{
            File dir =  new File("data");
            if(!dir.exists()){
                dir.mkdir();
            }
            File file =  new File(File_PATH);
            if(!file.exists()){
                file.createNewFile();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void save_data(User user) {
        file();
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter(File_PATH, true));
            w.write(user.get_username() +":"+ user.getHashedpassword());
            w.newLine();
            w.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static User find (String username){
        file();
        try{
            BufferedReader reader =  new BufferedReader(new FileReader(File_PATH));
            String line;
            while((line= reader.readLine()) != null){
                String[] parts = line.split(":");
                if(parts[0].equals(username)){
                    reader.close();
                    return new User(parts[0], parts[1]);
                }
            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null ;
    }
}


