package bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nicolas on 07-07-16.
 */
public class BdSqliteHelper extends SQLiteOpenHelper {

    private String[] tablas, dropTablas;

    public BdSqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        tablas = new String[5];
        tablas[0] = "Create table Cultivo (idCultivo INTEGER PRIMARY KEY, largo FLOAT, ancho FLOAT, profundidad FLOAT, tipoSuelo TEXT)";
        tablas[1] = "Create table CultivoContienePlantas (idCultivo INTEGER, idPlanta INTEGER, cantidad INTEGER, fecha TEXT, nombre TEXT)";
        tablas[2] = "Create table Tarea (idTarea INTEGER PRIMARY KEY, nombre TEXT, estado INTEGER)";
        tablas[3] = "Create table Tutorial (activo INTEGER)";
        tablas[4] = "INSERT INTO Tutorial (activo) VALUES (0)";
        dropTablas = new String[4];
        dropTablas[0] = "Drop table if exists Cultivo";
        dropTablas[1] = "Drop table if exists CultivoContienePlantas";
        dropTablas[2] = "Drop table if exists Tarea";
        dropTablas[3] = "Drop table if exists Tutorial";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (int i = 0; i < tablas.length; i++) {
            db.execSQL(tablas[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int i = 0; i < dropTablas.length; i++) {
            db.execSQL(dropTablas[i]);
        }

        for (int i = 0; i < tablas.length; i++) {
            db.execSQL(tablas[i]);
        }
    }

}
