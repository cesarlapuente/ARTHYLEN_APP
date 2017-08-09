package ffscreens.arthylene.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.objects.Produit;

/**
 * Arthylene
 * Created by Thibault Nougues on 21/06/2017.
 */

public class ProduitDAO {

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
    public ProduitDAO(Context context) {
        mDbHandler = new DbHandler(context);
    }

    /**
     * Method that close the connection to the database
     */
    public void destroy() {
        mDbHandler.close();
    }

    public void insertListProduit(List<Produit> produits) {
        db = mDbHandler.getWritableDatabase();

        int update = -89;

        for (Produit p : produits) {
            ContentValues values = new ContentValues();
            values.put(ProduitContract.ProduitEntry.COLUM_NAME_NOMPRODUIT, p.getNomProduit());
            values.put(ProduitContract.ProduitEntry.COLUM_NAME_VARIETEPRODUIT, p.getVarieteProduit());
            values.put(ProduitContract.ProduitEntry.COLUM_NAME_NIVEAUMATURITE, p.getNiveauMaturite());
            values.put(ProduitContract.ProduitEntry.COLUM_NAME_IDMATURITE, p.getIdMaturite());
            values.put(ProduitContract.ProduitEntry.COLUM_NAME_NIVEAUETAT, p.getNiveauEtat());
            values.put(ProduitContract.ProduitEntry.COLUM_NAME_IDETAT, p.getIdEtat());
            values.put(ProduitContract.ProduitEntry.COLUM_NAME_IDPRESENTATION, p.getIdPresentation());
            update = db.update(ProduitContract.ProduitEntry.TABLE_NAME, values, ProduitContract.ProduitEntry.COLUM_NAME_IDPRODUIT + " = ?", new String[]{String.valueOf(p.getIdProduit())});
            if (update == 0) {
                values.put(ProduitContract.ProduitEntry.COLUM_NAME_IDPRODUIT, p.getIdProduit());
                db.insert(ProduitContract.ProduitEntry.TABLE_NAME, null, values);
            }
        }
    }

    public List<Produit> getAllProduct() {
        List<Produit> produits = new ArrayList<>();

        db = mDbHandler.getWritableDatabase();

        String[] projection = {
                ProduitContract.ProduitEntry.COLUM_NAME_IDPRODUIT,
                ProduitContract.ProduitEntry.COLUM_NAME_NOMPRODUIT,
                ProduitContract.ProduitEntry.COLUM_NAME_VARIETEPRODUIT,
                ProduitContract.ProduitEntry.COLUM_NAME_NIVEAUMATURITE,
                ProduitContract.ProduitEntry.COLUM_NAME_IDMATURITE,
                ProduitContract.ProduitEntry.COLUM_NAME_NIVEAUETAT,
                ProduitContract.ProduitEntry.COLUM_NAME_IDETAT,
                ProduitContract.ProduitEntry.COLUM_NAME_IDPRESENTATION,
        };

        Cursor cursor = db.query(
                ProduitContract.ProduitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            Long id = cursor.getLong(cursor.getColumnIndexOrThrow(ProduitContract.ProduitEntry.COLUM_NAME_IDPRODUIT));
            String nom = cursor.getString(cursor.getColumnIndexOrThrow(ProduitContract.ProduitEntry.COLUM_NAME_NOMPRODUIT));
            String variete = cursor.getString(cursor.getColumnIndexOrThrow(ProduitContract.ProduitEntry.COLUM_NAME_VARIETEPRODUIT));
            int niveauMaturite = cursor.getInt(cursor.getColumnIndexOrThrow(ProduitContract.ProduitEntry.COLUM_NAME_NIVEAUMATURITE));
            Long idmaturite = cursor.getLong(cursor.getColumnIndexOrThrow(ProduitContract.ProduitEntry.COLUM_NAME_IDMATURITE));
            int niveauEtat = cursor.getInt(cursor.getColumnIndexOrThrow(ProduitContract.ProduitEntry.COLUM_NAME_NIVEAUETAT));
            Long idEtat = cursor.getLong(cursor.getColumnIndexOrThrow(ProduitContract.ProduitEntry.COLUM_NAME_IDETAT));
            Long idPresentation = cursor.getLong(cursor.getColumnIndexOrThrow(ProduitContract.ProduitEntry.COLUM_NAME_IDPRESENTATION));

            Produit p = new Produit(id, nom, variete, niveauMaturite, idmaturite, niveauEtat, idEtat, idPresentation);
            produits.add(p);
        }
        cursor.close();

        return produits;
    }

}
