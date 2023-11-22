package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText EditTextEmail;
    private EditText EditTextSenha;
    private TextView TextCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextCadastro = findViewById(R.id.textViewCadastro);
        TextCadastro.setText(Html.fromHtml("<u>Não tenho cadastro</u>",Html.FROM_HTML_MODE_LEGACY));
    }

    public void AbrirCadastro(View view){

        Intent intent = new Intent(this, MainActivityCadastro.class);
        startActivity(intent);
    }

    public void AbrirPrincipal(View view){

        EditTextEmail = findViewById(R.id.editTextEmail);
        EditTextSenha = findViewById(R.id.editTextSenha);

        String email = EditTextEmail.getText().toString();
        String senha =  EditTextSenha.getText().toString();



        if(email.equals("teste") && senha.equals("123")){
            Intent intent = new Intent(this, MainActivityPrincipal.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "Email ou Senha inválidos", Toast.LENGTH_SHORT).show();
        }

    }
}