package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface UsuarioDao {
  @Query("SELECT * FROM Usuario")
    List<Usuario> getUsuarios();

    @Query("SELECT * FROM Usuario where Email= :email")
    Usuario BuscarPorEmail(String email);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Usuario usuario);

    @Delete
    void delete(Usuario usuario);

    @Update
    void update(Usuario usuario);

}
