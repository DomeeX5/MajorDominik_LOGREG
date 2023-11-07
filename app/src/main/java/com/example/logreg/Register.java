package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextUserNameReg;
    private EditText editTextPasswordReg;
    private EditText editTextFullName;
    private AppCompatButton buttonRegister;
    private AppCompatButton buttonBack;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regisztracio);
        init();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regisztracio();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void regisztracio(){
        String email = editTextEmail.getText().toString();
        String felhnev = editTextUserNameReg.getText().toString();
        String jelszo = editTextPasswordReg.getText().toString();
        String teljesnev = editTextFullName.getText().toString();
        if (dbHelper.regisztracio(email, felhnev, jelszo, teljesnev)){
            Toast.makeText(this, "Sikeres regisztráció!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Register.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    public void init(){
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextUserNameReg = findViewById(R.id.editTextUserNameReg);
        editTextPasswordReg = findViewById(R.id.editTextPasswordReg);
        editTextFullName = findViewById(R.id.editTextFullName);
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonBack = findViewById(R.id.buttonBack);
        dbHelper = new DBHelper(Register.this);
    }
}