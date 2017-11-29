package com.example.yuquan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by terry on 2017/11/9.
 */

public class Signup extends AppCompatActivity {
    private Button signup;
    EditText username;
    EditText phonenum;
    EditText password1;
    EditText password2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup=(Button)findViewById(R.id.signup);
        username=(EditText)super.findViewById(R.id.user);//获取用户输入的用户名
        phonenum=(EditText)super.findViewById(R.id.phnum);//获取用户输入的用户名
        password1=(EditText)super.findViewById(R.id.pwd1);//获取用户密码
        password2=(EditText)super.findViewById(R.id.pwd2);//获取用户确认密码
        signup.setOnClickListener(new View.OnClickListener()//侦听登录点击事件
                                 {
                                     public void onClick(View v)
                                     {//验证用户名密码是否符合要求
                                         if(password1.getText().toString().equals(password2.getText().toString())) {
                                             if (username.getText().toString().equals("admin") && password1.getText().toString().equals("888888")) {
                                                 Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();//提示用户登陆成功
                                                 Intent t1 = new Intent(Signup.this, MainActivity.class);//从login页面跳转到index界面
                                                 startActivity(t1);
                                             }
                                             if (!username.getText().toString().equals("admin") || !password1.getText().toString().equals("888888")) {
                                                 Toast.makeText(getApplicationContext(), "用户名已存在", Toast.LENGTH_SHORT).show();//提示用户用户名或密码错误
                                             }
                                         }else{
                                             Toast.makeText(getApplicationContext(), "两次密码不一致", Toast.LENGTH_SHORT).show();//提示用户用户名或密码错误
                                         }
                                     }
                                 }
        );
    }
}
