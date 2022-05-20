package com.example.loginvs2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button btnLogin;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.usernameLogin);
        password = (EditText) findViewById(R.id.passwordLogin);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        db = new DatabaseHelper(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Please enter the Credentials", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean result = db.checkusernamePassword(user,pass);
                    if (result==true){

                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}