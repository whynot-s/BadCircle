package com.John.service;

import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by terry on 2017/11/10.
 */

public class Main {
    public static String http_8080="http://123.207.126.172:8080/BadCircle";

    public static HttpURLConnection newConnect(String call, String method, String msg) throws Exception{
        assert method.equals("POST")||method.equals("GET");
        HttpURLConnection connection=null;
        try {
            URL url=null;
            if(method.equals("POST") || msg.equals("")){
                url = new URL(String.format("%s/%s", Main.http_8080,call));
            }
            else{
                url = new URL(String.format("%s/%s?%s", Main.http_8080,call,msg));
            }
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setReadTimeout(8000);
            connection.setConnectTimeout(8000);
            connection.connect();
        }catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
        Log.v("newConnect",String.format("call = %s  method = %s  msg = %s",call,method,msg));
        return connection;
    }
}
