package com.John.service;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Date;

import static com.John.service.Main.newConnect;

/**
 * Created by terry on 2017/11/10.
 */

public class user {
    public static boolean LOG=false;
    public static Boolean runout=false;

    public int userid=-1;
    public String UserName;
    public String Name;
    public String Gender;
    public String City;
    public String Birthday;
    public Integer JoinedClub;
    public String JoinedTime;
    public Integer RateCount;
    public Double Score;

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
                    /*
                    URL url =new URL(String.format("%s/Login",Main.http_8080));
                    connection=(HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(8000);
                    connection.setConnectTimeout(8000);
                    */
                    connection = newConnect("Login","POST","");

                    DataOutputStream out=new DataOutputStream(connection.getOutputStream());

                    out.writeBytes(String.format("UserName=%s&UserPwd=%s",user_name,user_key));

                    InputStream in =connection.getInputStream();
                    reader=new BufferedReader(new InputStreamReader(in));
                    //System.out.println("");
                    //System.out.println("123321");
                    //System.out.println("12344321");
                    //Log.v("test json",String.format("%s",changeInputStream(in)));
                    String result=String.format("%s",reader.readLine());
                    //{status:0,userId:25}
                    Log.v("login readline",result);
                    JSONObject loginJson =new JSONObject(result);
                    if(loginJson.getInt("status")==-1) {
                        user.LOG=false;
                        Log.v("login result","fail");
                    }
                    else{
                        assert loginJson.getInt("status")==-0;
                        user.LOG=true;
                        user.this.userid=loginJson.getInt("userId");
                        Log.v("login result",String.format("success  userId=%d",user.this.userid));
                    }
                    /*
                        try {
                            JSONObject jsonObject = new JSONObject(jsonString);// 通过jsonString创建jsonobject
                            JSONObject personObject = jsonObject.getJSONObject(key);// 通过key获取value对象
                            // 通过key获取的value设置Person对象的各个属性字段
                            person.setId(personObject.getInt("id"));
                            person.setName(personObject.getString("name"));
                            person.setAddress(personObject.getString("address"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    */
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    user.runout = true;
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
                if(user.runout) break;
            }
        }
        Log.v("Login","Login finish");
        if(user.LOG) this.userProfile();
        Log.v("Login","read profile finish");
        return user.LOG;
    }

    private static String changeInputStream(InputStream inputStream) {
        String jsonString = "";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int len = 0;
        byte[] data = new byte[1024];
        try {
            while ((len = inputStream.read(data))!=-1){// 从inputsteam流中读取数据到data数组中
                outputStream.write(data, 0, len);// 将已经写入到data数组中的字节流写入到outputStream流中
            }
            // 利用输入流的toByteArrar()方法转换成字节数组
            // 然后利用String的构造函数
            jsonString = new String(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public boolean register(final String UserName,final String name,final String UserPwd,final String Gender,final String City,final Date Birthday) {
        /*
            UserName (String)
            Name (String)
            UserPwd (String)
            Gender (String)
            City (String)
            Birthday (Date - "YYYY-MM-DD")
        */
        assert user.LOG==false;
        user.runout=false;
        new Thread(new Runnable() {
            public void run() {
                BufferedReader reader = null;
                HttpURLConnection connection = null;
                try {
                    connection = newConnect("Register", "POST","");
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    //"UserName=notebook&Name=command000&UserPwd=000command&Gender=Male&City=北京&Birthday=1997-01-02"
                    out.writeBytes(String.format("UserName=%s&Name=%s&UserPwd=%s&Gender=%s&City=%s&Birthday=%s", UserName, name, UserPwd, Gender, City, Birthday.toString()));
                    //final String UserName,final String name,final String UserPwd,final String Gender,final String City,final Date Birthday
                    InputStream in =connection.getInputStream();
                    reader=new BufferedReader(new InputStreamReader(in));
                    String result=String.format("%s",reader.readLine());
                    // {status:-1}
                    // {status:0,userId:25}
                    JSONObject registerJson=new JSONObject(result);
                    if(registerJson.getInt("status")==-1){
                        user.LOG=false;
                    }
                    else{
                        assert registerJson.getInt("status")==0;
                        user.LOG=true;
                        user.this.userid=registerJson.getInt("userId");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    user.runout = true;
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
        while(true){
            synchronized (user.runout){
                if(user.runout) break;
            }
        }
        return user.LOG;
    }

    public void userProfile(){
        assert this.userid!=-1&&user.LOG==true;
        user.runout=false;
        new Thread(new Runnable() {
            public void run() {
                BufferedReader reader = null;
                HttpURLConnection connection = null;
                String result="";
                try {
                    connection = newConnect("UserProfile", "GET",String.format("UserId=%d",user.this.userid));
                    //"UserName=notebook&Name=command000&UserPwd=000command&Gender=Male&City=北京&Birthday=1997-01-02"
                    //final String UserName,final String name,final String UserPwd,final String Gender,final String City,final Date Birthday
                    InputStream in =connection.getInputStream();
                    reader=new BufferedReader(new InputStreamReader(in));
                    result=String.format("%s",reader.readLine());
                    // {"UserId":15,"UserName":"ld909","Name":"LD","Gender":"Male"
                    // ,"City":"Beijing","Birthday":"1983-01-01","JoinedClub":9
                    // ,"JoinedTime":"2017-12-29 17:13:53.0","RateCount":0,"Score":0.0}
                    JSONObject registerJson=new JSONObject(result);
                    assert registerJson.getInt("UserId")==user.this.userid;

                    //user.this = (user) JSONObject.toBean(registerJson);
                    //System.out.println("");
                    //System.out.println("123321");

                    user.this.UserName =   registerJson.getString("UserName");
                    user.this.Name      =   registerJson.getString("Name");
                    user.this.Gender    =   registerJson.getString("Gender");
                    user.this.City      =   registerJson.getString("City");
                    user.this.Birthday  =   registerJson.getString("Birthday");
                    user.this.JoinedClub=   registerJson.getInt("JoinedClub");

                    user.this.JoinedTime=   registerJson.getString("JoinedTime");

                    //Log.v("JoinedClub : ",String.format("%d",registerJson.getInt("JoinedClub")));
                    user.this.RateCount =   registerJson.getInt("RateCount");
                    user.this.Score     =   registerJson.getDouble("Score");

                } catch (Exception e) {
                    Log.v("UserProfile result",result);
                    e.printStackTrace();
                } finally {
                    user.runout=true;
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
        while(true){
            synchronized (user.runout){
                if(user.runout) break;
            }
        }
    }
}