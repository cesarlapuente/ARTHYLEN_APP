package ffscreens.arthylene.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.objects.BeneficeSante;

public class BeneficeSanteDAO
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
    public BeneficeSanteDAO(Context context) {
        mDbHandler = new DbHandler(context);
    }

    /**
     * Method that close the connection to the database
     */
    public void destroy() {
        mDbHandler.close();
    }

    public void insertListBeneficeSante(List<BeneficeSante> beneficeSante) {
        db = mDbHandler.getWritableDatabase();

        int update = -1;

        for (BeneficeSante benefice : beneficeSante) {
            ContentValues values = new ContentValues();
            values.put(BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_IDBENEFICESANTE, benefice.getIdBeneficeSante());
            values.put(BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_IDPRODUIT, benefice.getIdProduit());
            values.put(BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE1, benefice.getBenefice1());
            values.put(BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE2, benefice.getBenefice2());
            values.put(BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE3, benefice.getBenefice3());
            values.put(BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE4, benefice.getBenefice4());
            values.put(BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE5, benefice.getBenefice5());
            values.put(BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE6, benefice.getBenefice6());
            update = db.update(BeneficeSanteContract.BeneficeSanteEntry.TABLE_NAME, values, BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_IDPRODUIT + " = ?", new String[]{String.valueOf(benefice.getIdProduit())});
            if (update == 0) {
                values.put(BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_IDPRODUIT, benefice.getIdProduit());
                db.insert(BeneficeSanteContract.BeneficeSanteEntry.TABLE_NAME, null, values);
            }
        }
    }

    public List<BeneficeSante> getAllBenefice() {
        List<BeneficeSante> beneficeList = new ArrayList<>();

        db = mDbHandler.getWritableDatabase();

        String[] projection = {
                BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_IDBENEFICESANTE,
                BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_IDPRODUIT,
                BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE1,
                BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE2,
                BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE3,
                BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE4,
                BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE5,
                BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE6,
        };

        Cursor cursor = db.query(
                BeneficeSanteContract.BeneficeSanteEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            Long idBeneficeSante = cursor.getLong(cursor.getColumnIndexOrThrow(BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_IDBENEFICESANTE));
            Long idProduit = cursor.getLong(cursor.getColumnIndexOrThrow(BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_IDPRODUIT));
            String benefice1 = cursor.getString(cursor.getColumnIndexOrThrow(BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE1));
            String benefice2 = cursor.getString(cursor.getColumnIndexOrThrow(BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE2));
            String benefice3 = cursor.getString(cursor.getColumnIndexOrThrow(BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE3));
            String benefice4 = cursor.getString(cursor.getColumnIndexOrThrow(BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE4));
            String benefice5 = cursor.getString(cursor.getColumnIndexOrThrow(BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE5));
            String benefice6 = cursor.getString(cursor.getColumnIndexOrThrow(BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE6));

            BeneficeSante beneficeSante = new BeneficeSante(idBeneficeSante, idProduit, benefice1, benefice2, benefice3, benefice4, benefice5, benefice6);
            beneficeList.add(beneficeSante);
        }
        cursor.close();

        return beneficeList;
    }
}
