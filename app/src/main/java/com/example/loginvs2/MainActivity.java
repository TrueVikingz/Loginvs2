package com.example.loginvs2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button btnsignup,btnsignin;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        repassword=(EditText)findViewById(R.id.repassword);

        btnsignin=(Button) findViewById(R.id.btnsignin);
        btnsignup=(Button) findViewById(R.id.btnsignup);

        db = new DatabaseHelper(this);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (user.equals("") || pass.equals("") || repass.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Fill all the fields.", Toast.LENGTH_SHORT).show();
                } else {
                    if(pass.equals(repass))
                    {
                        Boolean userCheckName = db.checkusername(user);
                        if (userCheckName == false){
                            Boolean regResult = db.insertData(user,pass);
                            if (regResult == true){
                                Toast.makeText(MainActivity.this, "Registration successful.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                            }
                        } else{
                            Toast.makeText(MainActivity.this, "User already exists. \n Please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Password not Matching.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}