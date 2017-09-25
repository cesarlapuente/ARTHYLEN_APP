package ffscreens.arthylene.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.objects.Caracteristique;

public class CaracteristiqueDAO
{
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
    public CaracteristiqueDAO(Context context) {
        mDbHandler = new DbHandler(context);
    }

    /**
     * Method that close the connection to the database
     */
    public void destroy() {
        mDbHandler.close();
    }

    public void insertListCaracteristique(List<Caracteristique> caracteristiques) {
        db = mDbHandler.getWritableDatabase();

        int update = -1;

        for (Caracteristique c : caracteristiques) {
            ContentValues values = new ContentValues();
            values.put(CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_IDCARACTERISTIQUE, c.getIdCaracteristique());
            values.put(CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_IDPRODUIT, c.getIdProduit());
            values.put(CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_FAMILLE, c.getFamille());
            values.put(CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_ESPECE, c.getEspece());
            values.put(CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_TAILLEPOIDS, c.getTaillePoids());
            values.put(CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_COULEURTEXTURE, c.getCouleurTexture());
            values.put(CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_SAVEUR, c.getSaveur());
            values.put(CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_PRINCIPAUXPRODUCTEUR, c.getPrincipauxProducteur());
            update = db.update(CaracteristiqueContract.CaracteristiqueEntry.TABLE_NAME, values, CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_IDPRODUIT + " = ?", new String[]{String.valueOf(c.getIdProduit())});
            if (update == 0) {
                values.put(CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_IDPRODUIT, c.getIdProduit());
                db.insert(CaracteristiqueContract.CaracteristiqueEntry.TABLE_NAME, null, values);
            }
        }
    }

    public List<Caracteristique> getAllCaracteristique() {
        List<Caracteristique> caracteristiques = new ArrayList<>();

        db = mDbHandler.getWritableDatabase();

        String[] projection = {
                CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_IDCARACTERISTIQUE,
                CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_IDPRODUIT,
                CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_FAMILLE,
                CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_ESPECE,
                CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_TAILLEPOIDS,
                CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_COULEURTEXTURE,
                CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_SAVEUR,
                CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_PRINCIPAUXPRODUCTEUR,
        };

        Cursor cursor = db.query(
                CaracteristiqueContract.CaracteristiqueEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext())
        {
            Long idCaracteristique = cursor.getLong(cursor.getColumnIndexOrThrow(CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_IDCARACTERISTIQUE));
            Long idProduit = cursor.getLong(cursor.getColumnIndexOrThrow(CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_IDPRODUIT));
            String famille = cursor.getString(cursor.getColumnIndexOrThrow(CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_FAMILLE));
            String espece = cursor.getString(cursor.getColumnIndexOrThrow(CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_ESPECE));
            String taillePoids = cursor.getString(cursor.getColumnIndexOrThrow(CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_TAILLEPOIDS));
            String couleurTexture = cursor.getString(cursor.getColumnIndexOrThrow(CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_COULEURTEXTURE));
            String saveur = cursor.getString(cursor.getColumnIndexOrThrow(CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_SAVEUR));
            String principauxProducteur = cursor.getString(cursor.getColumnIndexOrThrow(CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_PRINCIPAUXPRODUCTEUR));

            Caracteristique c = new Caracteristique(idCaracteristique, idProduit, famille, espece, taillePoids, couleurTexture, saveur, principauxProducteur);
            caracteristiques.add(c);
        }
        cursor.close();

        return caracteristiques;
    }

}
