package com.example.aleksi.h03t01;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    Context context = this;
    Button button;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btnHäly);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Hauskaa dialogeilla");

                alertDialogBuilder
                        .setMessage("Klikkaa kyllä!")
                        .setCancelable(false)
                        .setPositiveButton("Kyllä",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                MainActivity.this.finish();
                            }
                        })
                        .setNegativeButton("Nope",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }
}