package com.kritter.autojdbc.implautojdbc;
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
    int dataTypeInt=0;
    String dataTypeString="";
    char dataTypeChar=' ';
    float dataTypeFLoat=0;
    double dataTypeDouble=0;
    Date dataTypeDate=null;
    ResultSet resultSet=null;
    Statement statement=null;
    Connection con=null;
    BufferedReader bufferedReader=null;
    int counter=0;
    String dataTypeArray[][]=new String[10][2];
    String globalTableName="";
    int dataSetCounter=0;

    public void checkTable(String url,String username,String password){
        int exitCode=0,choice=0;
        boolean isTableStructureKnown=false;
        String tableName="";
        //String dataFromTAble[]= new String[50];
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
                    if(dataSetCounter>0){
                        for(int count=0;count<dataSetCounter;count++){
                            for(int columns=0;columns<2;columns++){
                                dataTypeArray[count][columns]="";
                            }
                        }
                    }
                    counter=0;
                    dataSetCounter=0;
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
                            dataSetCounter++;
                        }
                        for(int count=0;count<dataSetCounter;count++){
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
                        for(int count=0;count<3;count++){
                            System.out.println(dataTypeArray[count][0] + ":" + dataTypeArray[count][1]);
                        }
                        try{
                            //String tableName=bufferedReader.readLine();
                            con=DriverManager.getConnection(url,username,password);
                            String query="select * from ";
                            query=query + tableName;
                            statement=con.createStatement();
                            resultSet=statement.executeQuery(query);
                            counter=0;

                            while(resultSet.next()){
                                for(int temp=0;temp<dataSetCounter;temp++){
                                    if(dataTypeArray[temp][1].equals("1")){
                                        dataTypeInt=resultSet.getInt(dataTypeArray[temp][0]);
                                    }
                                    else if(dataTypeArray[temp][1].equals("2")){
                                        dataTypeFLoat=resultSet.getFloat(dataTypeArray[temp][0]);
                                    }
                                    else if(dataTypeArray[temp][1].equals("3")){
                                        dataTypeDouble=resultSet.getDouble(dataTypeArray[temp][0]);
                                    }
                                    else if(dataTypeArray[temp][1].equals("4")){
                                        dataTypeString=resultSet.getString(dataTypeArray[temp][0]);
                                    }
                                    //else if(dataTypeArray[temp][1].equals("5")){
                                    //dataTypeChar=resultSet.getString(dataTypeArray[temp][0]);
                                    //}
                                    else if(dataTypeArray[temp][1].equals("6")){
                                        dataTypeDate=resultSet.getDate(dataTypeArray[temp][0]);
                                    }
                                    System.out.println("\n"+dataTypeInt+" "+dataTypeFLoat+" "+dataTypeDouble+" "+dataTypeString+" "+dataTypeChar+" "+dataTypeDate);
                                }
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
