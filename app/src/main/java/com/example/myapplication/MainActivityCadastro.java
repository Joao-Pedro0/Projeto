package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivityCadastro extends AppCompatActivity {

    private EditText txtNome;
    private EditText txtEmail;
    private EditText txtSenha;
    private EditText txtConfirmaSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cadastro);

        txtNome = findViewById(R.id.editTextNomeCad);
        txtEmail = findViewById(R.id.editTextEmailCad);
        txtSenha = findViewById(R.id.editTextSenhaCad1);
        txtConfirmaSenha = findViewById(R.id.editTextSenhaCad2);

    }

    public void AbrirLogin(View view){

        String Nome = txtNome.getText().toString();
        String Email = txtEmail.getText().toString();
        String Senha = txtSenha.getText().toString();
        String ConfirmaSenha = txtConfirmaSenha.getText().toString();

        AppDataBase db = AppDataBase.getInstance(this);
        UsuarioDao dao = db.usuarioDao();
        Usuario u = new Usuario(1,Nome, Email, Senha);
        dao.insert(u);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}