package in.kritter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

/**
 * Created by alan on 8/12/16.
 */
public class GetDB {

    public String getDB(String url,String username,String password){

        System.out.println("GIVEN BELOW ARE A LIST OF DATABASES");
        Connection con=null;
        ResultSet resultSet=null;
        String choice="";
        BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(System.in));
        int counter=0;
        try{
            con=DriverManager.getConnection(url,username,password);
            String query="show Databases";
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String dbName=resultSet.getString("Database");
                counter++;
                System.out.println(counter + ": " + dbName);
            }
            con.close();
            System.out.println("\nENTER THE NAME OF THE DATABASE YOU WANT TO USE");
            choice = bufferedReader.readLine();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return choice;
    }
    public void showTables(String url,String username,String password,String dbName){
        Connection con=null;
        ResultSet resultSet=null;
        Statement statement=null;
        int counter=0;
        try{
            con=DriverManager.getConnection(url,username,password);
            statement=con.createStatement();
            String query="show tables";
            resultSet=statement.executeQuery(query);
            String dbString="Tables_in_";
            dbString=dbString+dbName;
            while(resultSet.next()){
                counter++;
                String tableName=resultSet.getString(dbString);
                System.out.println(counter + ": " + tableName);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }
}
