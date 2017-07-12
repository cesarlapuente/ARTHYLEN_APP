package ffscreens.arthylene.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.objects.Item;

/**
 * Created by Thibault on 21/06/2017.
 */

public class ChecklistDAO {

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
    public ChecklistDAO(Context context) {
        mDbHandler = new DbHandler(context);
    }

    /**
     * Method that close the connection to the database
     */
    public void destroy() {
        mDbHandler.close();
    }

    public void insertListItem(List<Item> items) {
        db = mDbHandler.getWritableDatabase();
        int update = -1;

        for (Item i : items) {
            Log.e("thib", i.toString());
            ContentValues values = new ContentValues();
            values.put(ChecklistContract.ChecklistEntry.COLUM_NAME_TITLE, i.getTitle());
            values.put(ChecklistContract.ChecklistEntry.COLUM_NAME_CONTENU, i.getContent());
            values.put(ChecklistContract.ChecklistEntry.COLUM_NAME_IMPORTANT, i.isImportant());
            values.put(ChecklistContract.ChecklistEntry.COLUM_NAME_IDPHOTO, i.getIdPhoto());
            update = db.update(ChecklistContract.ChecklistEntry.TABLE_NAME, values, ChecklistContract.ChecklistEntry._ID + " = ?", new String[]{String.valueOf(i.getId())});
            if (update == 0) {
                values.put(ChecklistContract.ChecklistEntry._ID, i.getId());
                values.put(ChecklistContract.ChecklistEntry.COLUM_NAME_CHECKED, false);
                db.insert(ChecklistContract.ChecklistEntry.TABLE_NAME, null, values);
            }
        }
    }

    public void updateItem(Item item) {
        db = mDbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ChecklistContract.ChecklistEntry.COLUM_NAME_TITLE, item.getTitle());
        values.put(ChecklistContract.ChecklistEntry.COLUM_NAME_CONTENU, item.getContent());
        values.put(ChecklistContract.ChecklistEntry.COLUM_NAME_IMPORTANT, item.isImportant());
        values.put(ChecklistContract.ChecklistEntry.COLUM_NAME_CHECKED, item.isChecked());
        values.put(ChecklistContract.ChecklistEntry.COLUM_NAME_IDPHOTO, item.getIdPhoto());

        String selection = ChecklistContract.ChecklistEntry._ID + " = ?";
        String[] args = {String.valueOf(item.getId())};

        db.update(ChecklistContract.ChecklistEntry.TABLE_NAME, values, selection, args);
    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();

        db = mDbHandler.getWritableDatabase();

        String[] projection = {
                ChecklistContract.ChecklistEntry._ID,
                ChecklistContract.ChecklistEntry.COLUM_NAME_TITLE,
                ChecklistContract.ChecklistEntry.COLUM_NAME_CONTENU,
                ChecklistContract.ChecklistEntry.COLUM_NAME_IMPORTANT,
                ChecklistContract.ChecklistEntry.COLUM_NAME_CHECKED,
                ChecklistContract.ChecklistEntry.COLUM_NAME_IDPHOTO,
        };

        Cursor cursor = db.query(
                ChecklistContract.ChecklistEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            Long id = cursor.getLong(cursor.getColumnIndexOrThrow(ChecklistContract.ChecklistEntry._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(ChecklistContract.ChecklistEntry.COLUM_NAME_TITLE));
            String contenu = cursor.getString(cursor.getColumnIndexOrThrow(ChecklistContract.ChecklistEntry.COLUM_NAME_CONTENU));
            int important = cursor.getInt(cursor.getColumnIndexOrThrow(ChecklistContract.ChecklistEntry.COLUM_NAME_IMPORTANT));
            int checked = cursor.getInt(cursor.getColumnIndexOrThrow(ChecklistContract.ChecklistEntry.COLUM_NAME_CHECKED));
            Long idPhoto = cursor.getLong(cursor.getColumnIndexOrThrow(ChecklistContract.ChecklistEntry.COLUM_NAME_IDPHOTO));

            Item e = new Item(id, title, contenu, (important == 1), (checked == 1), idPhoto);
            items.add(e);
        }
        cursor.close();

        return items;
    }

    public void deleteListItem(List<Item> items) {
        db = mDbHandler.getWritableDatabase();
        for (Item item : items) {
            String selection = ChecklistContract.ChecklistEntry._ID + " = ?";
            String[] arg = {String.valueOf(item.getId())};

            db.delete(ChecklistContract.ChecklistEntry.TABLE_NAME, selection, arg);
        }
    }

}
