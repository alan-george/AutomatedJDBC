package in.kritter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
/**
 * Created by alan on 8/12/16.
 * Contains an array[][] which will show column number having datatype
 * 1=int 2=flaot 3=double 4=String 5=char 6=date
 *
 */
public class TableEdit {
    ResultSet resultSet=null;
    Statement statement=null;
    Connection con=null;
    BufferedReader bufferedReader=null;
    int counter=0;
    String dataTypeArray[][];

    public void checkTable(String url,String username,String password){
        int exitCode=0,choice=0;
        boolean isTableStructureKnown=false;

       bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        do{
            System.out.println("1.SHOW TABLE STRUCTURE");
            System.out.println("2.SHOW TABLE DATA");
            try{
                choice=Integer.parseInt(bufferedReader.readLine());
            }
            catch(IOException e){
                e.printStackTrace();
            }
            switch(choice){
                case 1:
                    System.out.println("ENTER THE TABLE NAME");
                    try{
                        String tableName=bufferedReader.readLine();
                        con=DriverManager.getConnection(url,username,password);
                        String query="describe ";
                        query=query + tableName;
                        statement=con.createStatement();
                        resultSet=statement.executeQuery(query);
                        while(resultSet.next()){
                            counter++;
                            String field=resultSet.getString("Field");
                            String type=resultSet.getString("Type");
                            String nul=resultSet.getString("Null");
                            String key=resultSet.getString("Key");
                            String Default=resultSet.getString("Default");
                            String extra=resultSet.getString("Extra");
                            System.out.println(field +" "+ type +" "+ nul +" "+ key +" "+ Default +" "+extra );
                            //dataTypeArray[][]
                        }
                        con.close();
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    isTableStructureKnown=true;
                    break;
                case 2:
                    if(isTableStructureKnown==true){
                        System.out.println("ENTER THE TABLE NAME");
                        try{
                            String tableName=bufferedReader.readLine();
                            con=DriverManager.getConnection(url,username,password);
                            String query="select * from ";
                            query=query + tableName;
                            statement=con.createStatement();
                            resultSet=statement.executeQuery(query);
                            while(resultSet.next()){

                            }
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }while(exitCode==0);

    }
    public void insertInto(String url,String username,String password){
        System.out.println("ENTER THE NAME OF TABLE");
        BufferedReader bufferedReader=null;
        try{
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String tableName=bufferedReader.readLine();
            String query;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
