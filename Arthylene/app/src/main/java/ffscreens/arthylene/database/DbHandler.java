package ffscreens.arthylene.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Thibault on 21/06/2017.
 */

class DbHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    /**
     * The database name
     */
    private static final String DATABASE_NAME = "Arthylene.db";


    DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ProduitContract.SQL_CREATE_ENTRIES);
        db.execSQL(PresentationContract.SQL_CREATE_ENTRIES);
        db.execSQL(MaturiteContract.SQL_CREATE_ENTRIES);
        db.execSQL(EtatContract.SQL_CREATE_ENTRIES);
        db.execSQL(EtiquetteContract.SQL_CREATE_ENTRIES);
        db.execSQL(ChecklistContract.SQL_CREATE_ENTRIES);
        db.execSQL(PhotoContract.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ProduitContract.SQL_DELETE_ENTRIES);
        db.execSQL(PresentationContract.SQL_DELETE_ENTRIES);
        db.execSQL(MaturiteContract.SQL_DELETE_ENTRIES);
        db.execSQL(EtatContract.SQL_DELETE_ENTRIES);
        db.execSQL(EtiquetteContract.SQL_DELETE_ENTRIES);
        db.execSQL(ChecklistContract.SQL_DELETE_ENTRIES);
        db.execSQL(PhotoContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
