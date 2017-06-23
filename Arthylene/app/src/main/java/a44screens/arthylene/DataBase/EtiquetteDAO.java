package a44screens.arthylene.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import a44screens.arthylene.Objects.Etiquette;

/**
 * Created by Thibault on 21/06/2017.
 */

public class EtiquetteDAO {

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
    public EtiquetteDAO(Context context) {
        mDbHandler = new DbHandler(context);
    }

    /**
     * Method that close the connection to the database
     */
    public void destroy() {
        mDbHandler.close();
    }

    public void insertListLabel(List<Etiquette> etiquettes) {
        db = mDbHandler.getWritableDatabase();

        for (Etiquette p : etiquettes) {
            ContentValues values = new ContentValues();
            values.put(EtiquetteContract.EtiquetteEntry.COLUM_NAME_IDETIQUETTE, p.getIdEtiquette());
            values.put(EtiquetteContract.EtiquetteEntry.COLUM_NAME_CODE, p.getCode());
            values.put(EtiquetteContract.EtiquetteEntry.COLUM_NAME_IDCAGETTE, p.getIdCagette());
            values.put(EtiquetteContract.EtiquetteEntry.COLUM_NAME_IDPHOTO, p.getIdPhoto());
            values.put(EtiquetteContract.EtiquetteEntry.COLUM_NAME_NOMPRODUIT, p.getNomProduit());
            values.put(EtiquetteContract.EtiquetteEntry.COLUM_NAME_VARIETEPRODUIT, p.getVarieteProduit());
            values.put(EtiquetteContract.EtiquetteEntry.COLUM_NAME_ETE, p.getEte());
            values.put(EtiquetteContract.EtiquetteEntry.COLUM_NAME_AUTOMNE, p.getAutomne());
            values.put(EtiquetteContract.EtiquetteEntry.COLUM_NAME_HIVER, p.getHiver());
            values.put(EtiquetteContract.EtiquetteEntry.COLUM_NAME_PRINTEMPS, p.getPrintems());
            values.put(EtiquetteContract.EtiquetteEntry.COLUM_NAME_NBCOUCHE, p.getNbCouche());
            values.put(EtiquetteContract.EtiquetteEntry.COLUM_NAME_MATUMIN, p.getMaturiteMin());
            values.put(EtiquetteContract.EtiquetteEntry.COLUM_NAME_MATUMAX, p.getMaturiteMax());
            values.put(EtiquetteContract.EtiquetteEntry.COLUM_NAME_CHARIOT, p.getEmplacementChariot());
            db.insert(EtiquetteContract.EtiquetteEntry.TABLE_NAME, null, values);
        }
    }

    public List<Etiquette> getAllLabels() {
        List<Etiquette> etiquettes = new ArrayList<>();

        db = mDbHandler.getWritableDatabase();

        String[] projection = {
                EtiquetteContract.EtiquetteEntry.COLUM_NAME_IDETIQUETTE,
                EtiquetteContract.EtiquetteEntry.COLUM_NAME_CODE,
                EtiquetteContract.EtiquetteEntry.COLUM_NAME_IDCAGETTE,
                EtiquetteContract.EtiquetteEntry.COLUM_NAME_IDPHOTO,
                EtiquetteContract.EtiquetteEntry.COLUM_NAME_NOMPRODUIT,
                EtiquetteContract.EtiquetteEntry.COLUM_NAME_VARIETEPRODUIT,
                EtiquetteContract.EtiquetteEntry.COLUM_NAME_ETE,
                EtiquetteContract.EtiquetteEntry.COLUM_NAME_AUTOMNE,
                EtiquetteContract.EtiquetteEntry.COLUM_NAME_HIVER,
                EtiquetteContract.EtiquetteEntry.COLUM_NAME_PRINTEMPS,
                EtiquetteContract.EtiquetteEntry.COLUM_NAME_NBCOUCHE,
                EtiquetteContract.EtiquetteEntry.COLUM_NAME_MATUMIN,
                EtiquetteContract.EtiquetteEntry.COLUM_NAME_MATUMAX,
                EtiquetteContract.EtiquetteEntry.COLUM_NAME_CHARIOT,
        };

        Cursor cursor = db.query(
                EtiquetteContract.EtiquetteEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            Long id = cursor.getLong(cursor.getColumnIndexOrThrow(EtiquetteContract.EtiquetteEntry.COLUM_NAME_IDETIQUETTE));
            String code = cursor.getString(cursor.getColumnIndexOrThrow(EtiquetteContract.EtiquetteEntry.COLUM_NAME_CODE));
            Long idCagette = cursor.getLong(cursor.getColumnIndexOrThrow(EtiquetteContract.EtiquetteEntry.COLUM_NAME_IDCAGETTE));
            Long idPhoto = cursor.getLong(cursor.getColumnIndexOrThrow(EtiquetteContract.EtiquetteEntry.COLUM_NAME_IDPHOTO));
            String nom = cursor.getString(cursor.getColumnIndexOrThrow(EtiquetteContract.EtiquetteEntry.COLUM_NAME_NOMPRODUIT));
            String variete = cursor.getString(cursor.getColumnIndexOrThrow(EtiquetteContract.EtiquetteEntry.COLUM_NAME_VARIETEPRODUIT));
            int ete = cursor.getInt(cursor.getColumnIndexOrThrow(EtiquetteContract.EtiquetteEntry.COLUM_NAME_ETE));
            int automne = cursor.getInt(cursor.getColumnIndexOrThrow(EtiquetteContract.EtiquetteEntry.COLUM_NAME_AUTOMNE));
            int hiver = cursor.getInt(cursor.getColumnIndexOrThrow(EtiquetteContract.EtiquetteEntry.COLUM_NAME_HIVER));
            int printemps = cursor.getInt(cursor.getColumnIndexOrThrow(EtiquetteContract.EtiquetteEntry.COLUM_NAME_PRINTEMPS));
            int nbCouche = cursor.getInt(cursor.getColumnIndexOrThrow(EtiquetteContract.EtiquetteEntry.COLUM_NAME_NBCOUCHE));
            int min = cursor.getInt(cursor.getColumnIndexOrThrow(EtiquetteContract.EtiquetteEntry.COLUM_NAME_MATUMIN));
            int max = cursor.getInt(cursor.getColumnIndexOrThrow(EtiquetteContract.EtiquetteEntry.COLUM_NAME_MATUMAX));
            int chariot = cursor.getInt(cursor.getColumnIndexOrThrow(EtiquetteContract.EtiquetteEntry.COLUM_NAME_CHARIOT));

            Etiquette p = new Etiquette(id, code, idCagette, idPhoto, nom, variete, ete, automne, hiver, printemps, nbCouche, min, max, chariot);
            etiquettes.add(p);
        }
        cursor.close();

        return etiquettes;
    }

}
