package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Usuario {
    @PrimaryKey
    private int Id;
    private String Email;
    private String Nome;
    private String Senha;

    public Usuario(){

    }

    public Usuario(int Id, String Nome, String Email, String Senha){
        this.Id = Id;
        this.Nome = Nome;
        this.Email = Email;
        this.Senha = Senha;
    }

    public int getId(){
        return Id;
    }
    public void setId(int id){
        this.Id = id;
    }
    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        this.Nome = Nome;
    }

    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        this.Email = email;
    }

    public String getSenha() {
        return Senha;
    }
    public void setSenha(String senha) {
        this.Senha = senha;
    }
}
