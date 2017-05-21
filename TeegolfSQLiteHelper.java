package version1.teegolf;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by julia on 20/5/17.
 */

public class TeegolfSQLiteHelper extends SQLiteOpenHelper{

    // creem una variable que contindra la sentendia SQL de creació de la taula
    String sql = "CREATE TABLE TeeGolf (RFID INTEGER, ClubName TEXT, TeeHeight INTEGER)";

    public TeegolfSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        // Aquest metode s'executa automaticament quan la base de dades no existeix
        // La primera vegada que crida la classe també crea la BBDD

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Aquest metode s'executa quan es detecta que la versió de la BBDD
        // ha canviar i, s'han de definir tots els processos de migració de l'estructura
        // anterior a l'estructura nova. En aquest cas eliminarem la taula i la tornarem a crear

        db.execSQL("DROP TABLE IF EXISTS TeeGolf");
        db.execSQL(sql);
    }

}
