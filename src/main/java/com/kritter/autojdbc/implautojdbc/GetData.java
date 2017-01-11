package com.kritter.autojdbc.implautojdbc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Created by alan on 8/12/16.
 */
public class GetData {
    String username="",password="";
    public String getData(String str){
        BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(System.in));
        if(str.equals("username")){
            System.out.println("PLEASE ENTER YOUR USERNAME");
            try{
                username=bufferedReader.readLine();
                return username;
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        else if(str.equals("password")){
            System.out.println("PLEASE ENTER YOUR PASSWORD");
            try{
                password=bufferedReader.readLine();
                return password;
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
