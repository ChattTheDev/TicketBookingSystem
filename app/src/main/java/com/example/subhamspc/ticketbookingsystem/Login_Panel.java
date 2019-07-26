package com.example.subhamspc.ticketbookingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Panel extends AppCompatActivity {
    Button backbutt, signinbutt;

    EditText email2, pass2;



    DatabaseHelper mydb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_login__panel);
        mydb1 = new DatabaseHelper(this);



        email2 = (EditText)findViewById(R.id.editText3);
        pass2 = (EditText)findViewById(R.id.editText6);
        signinbutt = (Button)findViewById(R.id.button5);

        backbutt = (Button)findViewById(R.id.button6);



        signinbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a = email2.getText().toString();
                String b = pass2.getText().toString();
                String storedPassword=mydb1.getSingleEnt(a);
                if(b.equals(storedPassword)) {
                    Toast.makeText(Login_Panel.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(Login_Panel.this, Booking_Panel.class);
                    startActivity(i);
                }
                else
                {

                    Toast.makeText(Login_Panel.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }

                email2.setText("");
                pass2.setText("");




            }
        });


        backbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mydb1.close();
    }
}
