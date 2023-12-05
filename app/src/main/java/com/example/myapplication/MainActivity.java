package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;


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
        Boolean validaEmail = false;
        Usuario u;
        String Senha = null;

        String email = EditTextEmail.getText().toString();
        String senha =  EditTextSenha.getText().toString();

        AppDataBase db = AppDataBase.getInstance(this);
        UsuarioDao dao = db.usuarioDao();

        List<Usuario> usuarios = dao.getUsuarios();
        for (Usuario usuario : usuarios ) {
            if(email.equals(usuario.getEmail())){
                validaEmail = true;
                u = dao.BuscarPorEmail(email);
                Senha = u.getSenha();
                break;
            }
        }

        if(validaEmail == false ){
            Toast.makeText(getApplicationContext(), "Email não cadastrado", Toast.LENGTH_SHORT).show();
        }
        else if(Senha.equals(senha) == false){
            Toast.makeText(getApplicationContext(), "Senha incorreta", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent = new Intent(this, MainActivityPrincipal.class);
            startActivity(intent);
        }

    }
}