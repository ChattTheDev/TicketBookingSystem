package com.example.subhamspc.ticketbookingsystem;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Booking_Panel extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Button b1, b2;
    Spinner ssource, sdestination;
    String source, destination;
    int cost = 0;
    TextView t1, mdisplaydate;
    DatePickerDialog.OnDateSetListener mdatesetlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_booking__panel);


        TextView mark = (TextView) findViewById(R.id.textView14);
        b1 = (Button) findViewById(R.id.button7);
        b2 = (Button) findViewById(R.id.button8);

        t1 = (TextView) findViewById(R.id.textView18);
        mdisplaydate = (TextView) findViewById(R.id.textView20);
        mark.setSelected(true);
        mark.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        mark.setSingleLine(true);

        mdisplaydate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Booking_Panel.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mdatesetlist, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mdatesetlist = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayofmonth) {

                month = month + 1;
                String date = dayofmonth + "/" + month + "/" + year;
                mdisplaydate.setText(date);

            }
        };

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a = mdisplaydate.getText().toString();

                if(a.equals("Choose Date"))
                {
                    Toast.makeText(Booking_Panel.this, "Please Choose Travel Date", Toast.LENGTH_LONG).show();
                }
                else
                {
                    addNotification();
                }


            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(Booking_Panel.this);
                alert.setMessage("Are you Sure?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog dialogg = alert.create();
                dialogg.show();
            }
        });


        ssource = (Spinner) findViewById(R.id.spinner);
        sdestination = (Spinner) findViewById(R.id.spinner2);

        List<String> locations = new ArrayList<String>();
        locations.add("Barddhaman");
        locations.add("Khana Jn.");
        locations.add("Mankar");
        locations.add("Panagarh");
        locations.add("Rajbandh");
        locations.add("Durgapur");

        source = locations.get(0);
        destination = locations.get(0);

        ArrayAdapter<String> adapterSpinnerSource = new ArrayAdapter<String>(Booking_Panel.this, android.R.layout.simple_spinner_dropdown_item, locations);
        ArrayAdapter<String> adapterSpinnerDestination = new ArrayAdapter<String>(Booking_Panel.this, android.R.layout.simple_spinner_dropdown_item, locations);
        ssource.setAdapter(adapterSpinnerSource);

        sdestination.setAdapter(adapterSpinnerDestination);

        ssource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                source = parent.getItemAtPosition(position).toString();
                calculatecost();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sdestination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                destination = parent.getItemAtPosition(position).toString();

                calculatecost();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void calculatecost() {
        if ((source.equals("Barddhaman") && destination.equals("Khana Jn.")) || (source.equals("Khana Jn.") && destination.equals("Barddhaman"))) {
            cost = 10;
        } else if ((source.equals("Barddhaman") && destination.equals("Barddhaman")) || (source.equals("Barddhaman") && destination.equals("Barddhaman"))) {
            cost = 0;
        } else if ((source.equals("Barddhaman") && destination.equals("Mankar")) || (source.equals("Mankar") && destination.equals("Barddhaman"))) {
            cost = 20;
        } else if ((source.equals("Barddhaman") && destination.equals("Panagarh")) || (source.equals("Panagarh") && destination.equals("Barddhaman"))) {
            cost = 30;
        } else if ((source.equals("Barddhaman") && destination.equals("Rajbandh")) || (source.equals("Rajbandh") && destination.equals("Barddhaman"))) {
            cost = 40;
        } else if ((source.equals("Barddhaman") && destination.equals("Durgapur")) || (source.equals("Durgapur") && destination.equals("Barddhaman"))) {
            cost = 50;
        } else if ((source.equals("Khana Jn.") && destination.equals("Barddhaman")) || (source.equals("Barddhaman") && destination.equals("Khana Jn."))) {
            cost = 10;
        } else if ((source.equals("Khana Jn.") && destination.equals("Mankar")) || (source.equals("Mankar") && destination.equals("Khana Jn."))) {
            cost = 10;
        } else if ((source.equals("Khana Jn.") && destination.equals("Khana Jn.")) || (source.equals("Khana Jn.") && destination.equals("Khana Jn."))) {
            cost = 0;
        } else if ((source.equals("Khana Jn.") && destination.equals("Panagarh")) || (source.equals("Panagarh") && destination.equals("Khana Jn."))) {
            cost = 20;
        } else if ((source.equals("Khana Jn.") && destination.equals("Rajbandh")) || (source.equals("Rajbandh") && destination.equals("Khana Jn."))) {
            cost = 30;
        } else if ((source.equals("Khana Jn.") && destination.equals("Durgapur")) || (source.equals("Durgapur") && destination.equals("Khana Jn."))) {
            cost = 40;
        } else if ((source.equals("Mankar") && destination.equals("Barddhaman")) || (source.equals("Barddhaman") && destination.equals("Mankar"))) {
            cost = 20;
        } else if ((source.equals("Mankar") && destination.equals("Khana Jn.")) || (source.equals("Khana Jn.") && destination.equals("Mankar"))) {
            cost = 10;
        } else if ((source.equals("Mankar") && destination.equals("Mankar")) || (source.equals("Mankar") && destination.equals("Mankar"))) {
            cost = 0;
        } else if ((source.equals("Mankar") && destination.equals("Panagarh")) || (source.equals("Panagarh") && destination.equals("Mankar"))) {
            cost = 10;
        } else if ((source.equals("Mankar") && destination.equals("Rajbandh")) || (source.equals("Rajbandh") && destination.equals("Mankar"))) {
            cost = 20;
        } else if ((source.equals("Mankar") && destination.equals("Durgapur")) || (source.equals("Durgapur") && destination.equals("Mankar"))) {
            cost = 30;
        } else if ((source.equals("Panagarh") && destination.equals("Barddhaman")) || (source.equals("Barddhaman") && destination.equals("Panagarh"))) {
            cost = 30;
        } else if ((source.equals("Panagarh") && destination.equals("Khana Jn.")) || (source.equals("Khana Jn.") && destination.equals("Panagarh"))) {
            cost = 20;
        } else if ((source.equals("Panagarh") && destination.equals("Panagarh")) || (source.equals("Panagarh") && destination.equals("Panagarh"))) {
            cost = 0;
        } else if ((source.equals("Panagarh") && destination.equals("Mankar")) || (source.equals("Mankar") && destination.equals("Panagarh"))) {
            cost = 10;
        } else if ((source.equals("Panagarh") && destination.equals("Rajbandh")) || (source.equals("Rajbandh") && destination.equals("Panagarh"))) {
            cost = 20;
        } else if ((source.equals("Panagarh") && destination.equals("Durgapur")) || (source.equals("Durgapur") && destination.equals("Panagarh"))) {
            cost = 30;
        } else if ((source.equals("Rajbandh") && destination.equals("Durgapur")) || (source.equals("Durgapur") && destination.equals("Rajbandh"))) {
            cost = 10;
        } else if ((source.equals("Rajbandh") && destination.equals("Rajbandh")) || (source.equals("Rajbandh") && destination.equals("Rajbandh"))) {
            cost = 0;
        } else if ((source.equals("Rajbandh") && destination.equals("Panagarh")) || (source.equals("Panagarh") && destination.equals("Rajbandh"))) {
            cost = 10;
        } else if ((source.equals("Rajbandh") && destination.equals("Mankar")) || (source.equals("Mankar") && destination.equals("Rajbandh"))) {
            cost = 20;
        } else if ((source.equals("Rajbandh") && destination.equals("Khana Jn.")) || (source.equals("Khana Jn.") && destination.equals("Rajbandh"))) {
            cost = 30;
        } else if ((source.equals("Rajbandh") && destination.equals("Barddhaman")) || (source.equals("Barddhaman") && destination.equals("Rajbandh"))) {
            cost = 40;
        } else if ((source.equals("Durgapur") && destination.equals("Barddhaman")) || (source.equals("Barddhaman") && destination.equals("Durgapur"))) {
            cost = 50;
        } else if ((source.equals("Durgapur") && destination.equals("Rajbandh")) || (source.equals("Rajbandh") && destination.equals("Durgapur"))) {
            cost = 10;
        } else if ((source.equals("Durgapur") && destination.equals("Durgapur")) || (source.equals("Durgapur") && destination.equals("Durgapur"))) {
            cost = 0;
        } else if ((source.equals("Durgapur") && destination.equals("Panagarh")) || (source.equals("Panagarh") && destination.equals("Durgapur"))) {
            cost = 20;
        } else if ((source.equals("Durgapur") && destination.equals("Mankar")) || (source.equals("Mankar") && destination.equals("Durgapur"))) {
            cost = 30;
        } else if ((source.equals("Durgapur") && destination.equals("Khana Jn.")) || (source.equals("Khana Jn.") && destination.equals("Durgapur"))) {
            cost = 40;
        }
        t1.setText(String.valueOf(cost));

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addNotification() {


        String CHANNEL_ID = "my_channel_01";

        CharSequence name = "my_channel";


        int NOTIFICATION_ID = 234;


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {


            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);

            notificationManager.createNotificationChannel(mChannel);


        }




        Intent inte = new Intent(Booking_Panel.this, Booking_Details_Panel.class);
        inte.putExtra("from", source);
        inte.putExtra("to", destination);
        inte.putExtra("fare", t1.getText().toString());
        inte.putExtra("date", mdisplaydate.getText().toString());
        startActivity(inte);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        stackBuilder.addParentStack(Booking_Details_Panel.class);

        stackBuilder.addNextIntent(inte);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)

                .setContentTitle("Booking Confirmed")

                .setContentText("Your Booking has been Confirmed")


                .setSmallIcon(R.mipmap.ic_launcher)

                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                .setContentIntent(resultPendingIntent)

                .setAutoCancel(true);


        if (notificationManager != null) {


            notificationManager.notify(NOTIFICATION_ID, builder.build());


        }


    }

}