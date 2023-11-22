package com.example.myapplication;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.myapplication.Usuario;
import com.example.myapplication.UsuarioDao;

@Database(entities = {Usuario.class}, version=1)
abstract public class AppDataBase extends RoomDatabase {
    private static AppDataBase sInstance;
    public static final String DATABASE_NAME="DB_App.db";
    public abstract UsuarioDao usuarioDao();
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();
    public static AppDataBase getInstance(final Context context) {
        if ( sInstance == null ) {
            synchronized (AppDataBase.class) {
                if ( sInstance==null ) {
                    sInstance = buildDatabase(context.getApplicationContext());
                    sInstance.updataDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }
    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

    private static AppDataBase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,AppDataBase.class,
                DATABASE_NAME).allowMainThreadQueries().build();
    }

    private void updataDatabaseCreated(final Context context) {
        if( context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }
}