package in.kritter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
/**
 * Created by alan on 8/12/16.
 */
public class App {
    public static void main(String[] args){

        String dbName="";
        boolean isDbSelected=false;
        TableEdit tableEdit;
        GetDB getdb;
        GetData get = new GetData();
        CheckConnection checkConnection = new CheckConnection();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        int exitCode=0,choice=0;
        String driver="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost/test";
        Connection con=null;
        Statement statement=null;
        PreparedStatement preparedStatement=null;
        try{
            Class.forName(driver);
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        do{
            System.out.println("\n\nWELCOME TO THE JDBC TRIAL");
            System.out.println("PLEASE ENTER YOUR CREDENTIALS");

            String username=get.getData("username");
            String password=get.getData("password");
            boolean connectionSuccess=checkConnection.checkConnection(url,username,password);
            if(connectionSuccess==true){
                do{
                    System.out.println("\n\n1.CHOSE DATABASE");
                    System.out.println("2.SHOW TABLES");
                    System.out.println("3.SHOW TABLE STRUCTURE");
                    System.out.println("3.INSERT INTO TABLE");
                    //System.out.println("5.UPDATE TABLE");
                    System.out.println("10.EXIT");
                    try{
                        choice=Integer.parseInt(bufferedReader.readLine());
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    switch(choice){
                        case 1:
                            getdb = new GetDB();
                            dbName=getdb.getDB(url,username,password);
                            try{
                                con=DriverManager.getConnection(url,username,password);
                                String query="use ";
                                query=query + dbName;
                                statement = con.createStatement();
                                statement.executeUpdate(query);
                            }
                            catch(Exception e){
                                e.printStackTrace();
                            }
                            isDbSelected=true;
                            break;
                        case 2:
                            if(isDbSelected==true){
                                getdb=new GetDB();
                                getdb.showTables(url,username,password,dbName);
                            }
                            else{
                                System.out.println("PLEASE SELECT A DATABASE FIRST");
                            }
                            break;
                        case 3:
                            if(isDbSelected==true){
                                tableEdit = new TableEdit();
                                tableEdit.checkTable(url,username,password);
                                //tableEdit.
                            }
                            else{
                                System.out.println("PLEASE SELECT A DATABASE FIRST");
                            }
                            break;
                        case 4:
                            //insertIntoTable = new InsertIntoTable();
                            //insertIntoTable.insertInto(url,username,password);
                            break;
                        case 10:
                            exitCode=1;
                    }
                }while(exitCode==0);
            }
            else{
                System.out.println("\nCONNECTION TO THE MYSQL SERVER FAILED..PLEASE TRY AGAIN");
                exitCode=0;
            }
        }while(exitCode==0);
        System.out.println("THANK YOU !!!...SEE YA MATEY...");
    }
}
