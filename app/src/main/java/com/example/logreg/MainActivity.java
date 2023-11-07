package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUserName;
    private EditText editTextPassword;
    private AppCompatButton buttonLogin;
    private AppCompatButton buttonRegister;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextUserName.getText().toString().trim().isEmpty() || editTextPassword.getText().toString().trim().isEmpty()){
                    Toast.makeText(MainActivity.this,
                            "Hibás felhasználónév vagy jelszó!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    bejelentkezes();
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void bejelentkezes(){
        String felhnev = editTextUserName.getText().toString();
        String jelszo = editTextPassword.getText().toString();
        if (dbHelper.bejelentkezes(felhnev, jelszo)){
            Intent intent = new Intent(MainActivity.this, LoggedIn.class);
            startActivity(intent);
            finish();
        }
    }
    public void init(){
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        dbHelper = new DBHelper(MainActivity.this);
    }
}