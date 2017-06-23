package a44screens.arthylene.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import a44screens.arthylene.Objects.Maturite;

/**
 * Created by Thibault on 21/06/2017.
 */

public class MaturiteDAO {

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
    public MaturiteDAO(Context context) {
        mDbHandler = new DbHandler(context);
    }

    /**
     * Method that close the connection to the database
     */
    public void destroy() {
        mDbHandler.close();
    }

    public void insertListMaturity(List<Maturite> maturites) {
        db = mDbHandler.getWritableDatabase();

        for (Maturite m : maturites) {
            ContentValues values = new ContentValues();
            values.put(MaturiteContract.MaturiteEntry.COLUM_NAME_IDMATURITE, m.getIdMaturite());
            values.put(MaturiteContract.MaturiteEntry.COLUM_NAME_IDPRODUIT, m.getIdProduit());
            values.put(MaturiteContract.MaturiteEntry.COLUM_NAME_CONTENU, m.getContenu());
            values.put(MaturiteContract.MaturiteEntry.COLUM_NAME_IDPHOTO, m.getIdPhoto());
            values.put(MaturiteContract.MaturiteEntry.COLUM_NAME_IDEALE, m.isIdeale());
            values.put(MaturiteContract.MaturiteEntry.COLUM_NAME_POPUP, m.getPopup());
            db.insert(ProduitContract.ProduitEntry.TABLE_NAME, null, values);
        }
    }

    public List<Maturite> getAllMaturity() {
        List<Maturite> maturites = new ArrayList<>();

        db = mDbHandler.getWritableDatabase();

        String[] projection = {
                MaturiteContract.MaturiteEntry.COLUM_NAME_IDMATURITE,
                MaturiteContract.MaturiteEntry.COLUM_NAME_IDPRODUIT,
                MaturiteContract.MaturiteEntry.COLUM_NAME_CONTENU,
                MaturiteContract.MaturiteEntry.COLUM_NAME_IDPHOTO,
                MaturiteContract.MaturiteEntry.COLUM_NAME_IDEALE,
                MaturiteContract.MaturiteEntry.COLUM_NAME_POPUP,
        };

        Cursor cursor = db.query(
                MaturiteContract.MaturiteEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            Long id = cursor.getLong(cursor.getColumnIndexOrThrow(MaturiteContract.MaturiteEntry.COLUM_NAME_IDMATURITE));
            Long idProduit = cursor.getLong(cursor.getColumnIndexOrThrow(MaturiteContract.MaturiteEntry.COLUM_NAME_IDPRODUIT));
            String contenu = cursor.getString(cursor.getColumnIndexOrThrow(MaturiteContract.MaturiteEntry.COLUM_NAME_CONTENU));
            Long idPhoto = cursor.getLong(cursor.getColumnIndexOrThrow(MaturiteContract.MaturiteEntry.COLUM_NAME_IDPHOTO));
            boolean ideal = ((cursor.getInt(cursor.getColumnIndexOrThrow(MaturiteContract.MaturiteEntry.COLUM_NAME_IDEALE)) == 1) ? true : false);
            String popup = cursor.getString(cursor.getColumnIndexOrThrow(MaturiteContract.MaturiteEntry.COLUM_NAME_POPUP));

            Maturite p = new Maturite(id, idProduit, contenu, idPhoto, ideal, popup);
            maturites.add(p);
        }
        cursor.close();

        return maturites;
    }

}
