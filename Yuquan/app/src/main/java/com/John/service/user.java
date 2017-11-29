package com.John.service;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.John.service.*;

/**
 * Created by terry on 2017/11/10.
 */

public class user {
    public static boolean LOG=false;
    public static Boolean runout=false;
    public user(){

    }

    public boolean login(final String user_name, final String user_key){
        user.LOG=false;
        user.runout=false;
        new Thread(new Runnable(){
            public void run(){
                BufferedReader reader=null;
                HttpURLConnection connection=null;
                try{
                    URL url =new URL(String.format("%s/BadCircle/Login",Main.http_8080));
                    connection=(HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(8000);
                    connection.setConnectTimeout(8000);

                    DataOutputStream out=new DataOutputStream(connection.getOutputStream());
                    out.writeBytes(String.format("ID=%s&PW=%s",user_name,user_key));

                    InputStream in =connection.getInputStream();
                    reader=new BufferedReader(new InputStreamReader(in));
                    String result=reader.readLine();
                    Log.v("log:",String.format("ID=%s&PW=%s&result=%s",user_name,user_key,result));
                    user.LOG=result.equals("true");
                    synchronized (user.runout) {
                        user.runout = true;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    if(reader!=null){
                        try{
                            reader.close();
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                    if(connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
        while(true){
            synchronized (user.runout){
                if(user.runout)
                    break;
            }
        }
        return user.LOG;
    }

}
