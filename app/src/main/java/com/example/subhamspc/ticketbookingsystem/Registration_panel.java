package com.example.subhamspc.ticketbookingsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Registration_panel extends AppCompatActivity {

    Button backbutt, signupbutt1;

    EditText  username1, email1, pass1;
    DatabaseHelper mydb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_registration_panel);


        username1 = (EditText)findViewById(R.id.editText2);

        email1 = (EditText)findViewById(R.id.editText4);
        pass1 = (EditText)findViewById(R.id.editText5);
        mydb = new DatabaseHelper(this);

        backbutt = (Button) findViewById(R.id.button4);
        signupbutt1 = (Button) findViewById(R.id.button3);


        backbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        signupbutt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String b = username1.getText().toString();
                String c = email1.getText().toString();
                String d = pass1.getText().toString();

                if(b.equals("")||c.equals("") || d.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
                }

               boolean isinsert =  mydb.insertdata(b,c,d);

                if(isinsert = true)
                {
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                    finish();

                }
                else {
                    Toast.makeText(getApplicationContext(), "ERROR Creating Account...!!! ", Toast.LENGTH_LONG).show();
                }


                username1.setText("");

                email1.setText("");
                pass1.setText("");


            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        mydb.close();
    }

    @Override
    public void onBackPressed() {

    }
}
