package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoggedIn extends AppCompatActivity {

    private TextView textViewFullName;
    private AppCompatButton buttonLogOut;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        init();
        teljesnev();

        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoggedIn.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void teljesnev(){
        Cursor adatok = dbHelper.teljesnev();

    }

    public void init(){
        textViewFullName = findViewById(R.id.textViewFullName);
        buttonLogOut = findViewById(R.id.buttonLogOut);
        dbHelper = new DBHelper(LoggedIn.this);
    }
}