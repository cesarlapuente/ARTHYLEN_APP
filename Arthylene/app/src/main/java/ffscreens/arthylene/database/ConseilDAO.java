package ffscreens.arthylene.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.objects.Conseil;

public class ConseilDAO
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
    public ConseilDAO(Context context) {
        mDbHandler = new DbHandler(context);
    }

    /**
     * Method that close the connection to the database
     */
    public void destroy() {
        mDbHandler.close();
    }

    public void insertListConseil(List<Conseil> conseilList) {
        db = mDbHandler.getWritableDatabase();

        int update = -1;

        for (Conseil conseil : conseilList) {
            ContentValues values = new ContentValues();
            values.put(ConseilContract.ConseilEntry.COLUM_NAME_IDCONSEIL, conseil.getIdConseil());
            values.put(ConseilContract.ConseilEntry.COLUM_NAME_IDPRODUIT, conseil.getIdProduit());
            values.put(ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL1, conseil.getConseil1());
            values.put(ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL2, conseil.getConseil2());
            values.put(ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL3, conseil.getConseil3());
            values.put(ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL4, conseil.getConseil4());
            values.put(ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL5, conseil.getConseil5());
            values.put(ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL6, conseil.getConseil6());
            update = db.update(ConseilContract.ConseilEntry.TABLE_NAME, values, ConseilContract.ConseilEntry.COLUM_NAME_IDPRODUIT + " = ?", new String[]{String.valueOf(conseil.getIdProduit())});
            if (update == 0) {
                values.put(ConseilContract.ConseilEntry.COLUM_NAME_IDPRODUIT, conseil.getIdProduit());
                db.insert(ConseilContract.ConseilEntry.TABLE_NAME, null, values);
            }
        }
    }

    public List<Conseil> getAllConseil() {
        List<Conseil> conseilList = new ArrayList<>();

        db = mDbHandler.getWritableDatabase();

        String[] projection = {
                ConseilContract.ConseilEntry.COLUM_NAME_IDCONSEIL,
                ConseilContract.ConseilEntry.COLUM_NAME_IDPRODUIT,
                ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL1,
                ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL2,
                ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL3,
                ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL4,
                ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL5,
                ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL6,
        };

        Cursor cursor = db.query(
                ConseilContract.ConseilEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            Long idConseil = cursor.getLong(cursor.getColumnIndexOrThrow(ConseilContract.ConseilEntry.COLUM_NAME_IDCONSEIL));
            Long idProduit = cursor.getLong(cursor.getColumnIndexOrThrow(ConseilContract.ConseilEntry.COLUM_NAME_IDPRODUIT));
            String conseil1 = cursor.getString(cursor.getColumnIndexOrThrow(ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL1));
            String conseil2 = cursor.getString(cursor.getColumnIndexOrThrow(ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL2));
            String conseil3 = cursor.getString(cursor.getColumnIndexOrThrow(ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL3));
            String conseil4 = cursor.getString(cursor.getColumnIndexOrThrow(ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL4));
            String conseil5 = cursor.getString(cursor.getColumnIndexOrThrow(ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL5));
            String conseil6 = cursor.getString(cursor.getColumnIndexOrThrow(ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL6));

            Conseil conseil = new Conseil(idConseil, idProduit, conseil1, conseil2, conseil3, conseil4, conseil5, conseil6);
            conseilList.add(conseil);
        }
        cursor.close();

        return conseilList;
    }
}
