package com.example.h8951.h02t02autocompletelogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoCompleteTextView actv = (AutoCompleteTextView)
                findViewById(R.id.userName);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                new String[]
                        {"Pasi","Juha","Kari","Jouni","Esa","Hannu"});
        actv.setAdapter(aa);

    }

    public void toastCredentials(View view){

        EditText text = (EditText)findViewById(R.id.userName);
        String uname = text.getText().toString();
        text = (EditText)findViewById(R.id.userPassword);
        String pwd = text.getText().toString();

        Toast.makeText(this, "Username: " + uname + "\nPassword: " + pwd, Toast.LENGTH_SHORT ).show();
    }
}
