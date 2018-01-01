package com.example.yuquan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.yuquan.R.id.user;

public class Login extends AppCompatActivity {
    private Button login;
    private Button signup;
    EditText username;
    EditText password;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(Button)findViewById(R.id.login);
        signup=(Button)findViewById(R.id.signup);
        username=(EditText)super.findViewById(user);//获取用户输入的用户名
        password=(EditText)super.findViewById(R.id.pwd);//获取用户密码
        login.setOnClickListener(new View.OnClickListener()//侦听登录点击事件
        {
            public void onClick(View v)
            {//验证用户名密码是否符合要
                String userid,key;
                userid=username.getText().toString();
                key=password.getText().toString();
                if(MainActivity.User.login(userid,key))
                {
                    Log.v("LoginActivity","login() finished");
                    Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();//提示用户登陆成功
                    Intent t1=new Intent(Login.this,MainActivity.class);//从login页面跳转到index界面
                    Log.v("LoginActivity","login() finished");
                    startActivity(t1);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "用户名或密码错误", Toast.LENGTH_SHORT).show();//提示用户用户名或密码错误
                }
            }
        }
        );
        signup.setOnClickListener(new View.OnClickListener() {//侦听注册点击事件
            public void onClick(View view) {
                Intent t2=new Intent(Login.this, Signup.class);//从login页面跳转到signup界面
                startActivity(t2);
            }
        });
    }
}
