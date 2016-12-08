package in.kritter;
import java.sql.*;

/**
 * Created by alan on 8/12/16.
 */
public class CheckConnection {
    Connection con;
    public boolean checkConnection(String url,String username,String password){
        try{
            con=DriverManager.getConnection(url,username,password);
            if(con!= null){
                System.out.println("\nCONNECTION SUCCESSFUL!!");
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
