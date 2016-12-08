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
    String dataTypeArray[][]=new String[10][2];
    String globalTableName="";

    public void checkTable(String url,String username,String password){
        int exitCode=0,choice=0;
        boolean isTableStructureKnown=false;
        String tableName="";

       bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        do{
            System.out.println("1.SHOW TABLE STRUCTURE");
            System.out.println("2.SHOW TABLE DATA");
            System.out.println("3.EXIT");
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
                        tableName=bufferedReader.readLine();
                        globalTableName=tableName;
                        con=DriverManager.getConnection(url,username,password);
                        String query="describe ";
                        query=query + tableName;
                        statement=con.createStatement();
                        resultSet=statement.executeQuery(query);
                        while(resultSet.next()){
                            String field=resultSet.getString("Field");
                            String type=resultSet.getString("Type");
                            String nul=resultSet.getString("Null");
                            String key=resultSet.getString("Key");
                            String Default=resultSet.getString("Default");
                            String extra=resultSet.getString("Extra");
                            System.out.println(field +" "+ type +" "+ nul +" "+ key +" "+ Default +" "+extra );

                            dataTypeArray[counter][0]=field;

                            if(type.startsWith("int")){
                                dataTypeArray[counter][1]="1";
                            }
                            else if(type.startsWith("float")){
                                dataTypeArray[counter][1]="2";
                            }
                            else if(type.startsWith("double")){
                                dataTypeArray[counter][1]="3";
                            }
                            else if(type.startsWith("varchar")){
                                dataTypeArray[counter][1]="4";
                            }
                            else if(type.startsWith("char")){
                                dataTypeArray[counter][1]="5";
                            }
                            else if(type.startsWith("date")){
                                dataTypeArray[counter][1]="6";
                            }
                            counter++;
                        }
                        for(int count=0;count<3;count++){
                            System.out.println(dataTypeArray[count][0] + ":" + dataTypeArray[count][1]);
                        }
                        con.close();
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    isTableStructureKnown=true;

                    break;
                case 2:
                    System.out.println("ENTER THE TABLE NAME");
                    try{
                        tableName=bufferedReader.readLine();
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    if(isTableStructureKnown==true && globalTableName.equals(tableName)==true){
                        counter=0;
                        try{
                            //String tableName=bufferedReader.readLine();
                            con=DriverManager.getConnection(url,username,password);
                            String query="select * from ";
                            query=query + tableName;
                            statement=con.createStatement();
                            resultSet=statement.executeQuery(query);
                            while(resultSet.next()){
                                if(dataTypeArray[counter][])
                                //int id= resultSet.getInt();

                                counter ++;
                            }
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    exitCode=1;
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
