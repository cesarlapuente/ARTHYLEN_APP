package ffscreens.arthylene.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import ffscreens.arthylene.objects.Marketing;

public class MarketingDAO {
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
    public MarketingDAO(Context context) {
        mDbHandler = new DbHandler(context);
    }

    /**
     * Method that close the connection to the database
     */
    public void destroy() {
        mDbHandler.close();
    }

    public void insertListMarketing(List<Marketing> marketingList) {
        db = mDbHandler.getWritableDatabase();

        int update = -1;

        for (Marketing marketing : marketingList) {
            ContentValues values = new ContentValues();
            values.put(MarketingContract.MarketingEntry.COLUM_NAME_IDMARKETING, marketing.getIdMarketing());
            values.put(MarketingContract.MarketingEntry.COLUM_NAME_IDPRODUIT, marketing.getIdProduit());
            values.put(MarketingContract.MarketingEntry.COLUM_NAME_MARKETING1, marketing.getMarketing1());
            values.put(MarketingContract.MarketingEntry.COLUM_NAME_MARKETING2, marketing.getMarketing2());
            update = db.update(MarketingContract.MarketingEntry.TABLE_NAME, values, MarketingContract.MarketingEntry.COLUM_NAME_IDPRODUIT + " = ?", new String[]{String.valueOf(marketing.getIdProduit())});
            if (update == 0) {
                values.put(MarketingContract.MarketingEntry.COLUM_NAME_IDPRODUIT, marketing.getIdProduit());
                db.insert(MarketingContract.MarketingEntry.TABLE_NAME, null, values);
            }
        }
    }

    public List<Marketing> getAllMarketing() {
        List<Marketing> marketingList = new ArrayList<>();

        db = mDbHandler.getWritableDatabase();

        String[] projection = {
                MarketingContract.MarketingEntry.COLUM_NAME_IDMARKETING,
                MarketingContract.MarketingEntry.COLUM_NAME_IDPRODUIT,
                MarketingContract.MarketingEntry.COLUM_NAME_MARKETING1,
                MarketingContract.MarketingEntry.COLUM_NAME_MARKETING2,
        };

        Cursor cursor = db.query(
                MarketingContract.MarketingEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            Long idMarketing = cursor.getLong(cursor.getColumnIndexOrThrow(MarketingContract.MarketingEntry.COLUM_NAME_IDMARKETING));
            Long idProduit = cursor.getLong(cursor.getColumnIndexOrThrow(MarketingContract.MarketingEntry.COLUM_NAME_IDPRODUIT));
            String marketing1 = cursor.getString(cursor.getColumnIndexOrThrow(MarketingContract.MarketingEntry.COLUM_NAME_MARKETING1));
            String marketing2 = cursor.getString(cursor.getColumnIndexOrThrow(MarketingContract.MarketingEntry.COLUM_NAME_MARKETING2));

            Marketing marketing = new Marketing(idMarketing, idProduit, marketing1, marketing2);
            marketingList.add(marketing);
        }
        cursor.close();

        return marketingList;
    }
}
