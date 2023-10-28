package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText EditTextEmail;
    private EditText EditTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void AbrirCadastro(View view){

        Intent intent = new Intent(this, MainActivityCadastro.class);
        startActivity(intent);
    }

    public void AbrirPrincipal(View view){

        EditTextEmail = findViewById(R.id.editTextEmail);
        EditTextSenha = findViewById(R.id.editTextSenha);

        String email = EditTextEmail.getText().toString();
        String senha = EditTextSenha.getText().toString();

        if(email == "teste" && senha == "123"){
            Intent intent = new Intent(this, MainActivityPrincipal.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "Email ou Senha inválidos", Toast.LENGTH_SHORT).show();
        }

    }
}