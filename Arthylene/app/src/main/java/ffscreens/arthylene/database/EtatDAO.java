package ffscreens.arthylene.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.objects.Etat;

/**
 * Arthylene
 * Created by Thibault Nougues on 21/06/2017.
 */

public class EtatDAO {

    /**
     * DbHandler to access the database
     */
    private DbHandler mDbHandler;

    /**
     * Database object
     */
    private SQLiteDatabase db;

    /**
     * Constructor of the DAO
     */
    public EtatDAO(Context context) {
        mDbHandler = new DbHandler(context);
    }

    /**
     * Method that close the connection to the database
     */
    public void destroy() {
        mDbHandler.close();
    }

    public void insertListStates(List<Etat> etats) {
        db = mDbHandler.getWritableDatabase();
        int update = -1;

        for (Etat e : etats) {
            ContentValues values = new ContentValues();
            values.put(EtatContract.EtatEntry.COLUM_NAME_IDPRODUIT, e.getIdProduit());
            values.put(EtatContract.EtatEntry.COLUM_NAME_CONTENU, e.getContenu());
            values.put(EtatContract.EtatEntry.COLUM_NAME_IDPHOTO, e.getIdPhoto());
            values.put(EtatContract.EtatEntry.COLUM_NAME_POPUP, e.getPopup());
            update = db.update(EtatContract.EtatEntry.TABLE_NAME, values, EtatContract.EtatEntry.COLUM_NAME_IDETAT + " = ?", new String[]{String.valueOf(e.getIdEtat())});
            if (update == 0) {
                values.put(EtatContract.EtatEntry.COLUM_NAME_IDETAT, e.getIdEtat());
                db.insert(EtatContract.EtatEntry.TABLE_NAME, null, values);
            }
        }
    }

    public List<Etat> getAllStates() {
        List<Etat> etats = new ArrayList<>();

        db = mDbHandler.getWritableDatabase();

        String[] projection = {
                EtatContract.EtatEntry.COLUM_NAME_IDETAT,
                EtatContract.EtatEntry.COLUM_NAME_IDPRODUIT,
                EtatContract.EtatEntry.COLUM_NAME_CONTENU,
                EtatContract.EtatEntry.COLUM_NAME_IDPHOTO,
                EtatContract.EtatEntry.COLUM_NAME_POPUP,
        };

        Cursor cursor = db.query(
                EtatContract.EtatEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            Long id = cursor.getLong(cursor.getColumnIndexOrThrow(EtatContract.EtatEntry.COLUM_NAME_IDETAT));
            Long idProduit = cursor.getLong(cursor.getColumnIndexOrThrow(EtatContract.EtatEntry.COLUM_NAME_IDPRODUIT));
            String contenu = cursor.getString(cursor.getColumnIndexOrThrow(EtatContract.EtatEntry.COLUM_NAME_CONTENU));
            Long idPhoto = cursor.getLong(cursor.getColumnIndexOrThrow(EtatContract.EtatEntry.COLUM_NAME_IDPHOTO));
            String popup = cursor.getString(cursor.getColumnIndexOrThrow(EtatContract.EtatEntry.COLUM_NAME_POPUP));

            Etat e = new Etat(id, idProduit, contenu, idPhoto, popup);
            etats.add(e);
        }
        cursor.close();

        return etats;
    }

}
