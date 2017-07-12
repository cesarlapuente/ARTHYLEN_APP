package ffscreens.arthylene.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.objects.Photo;

/**
 * Created by Thibault on 21/06/2017.
 */

public class PhotoDAO {

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
    public PhotoDAO(Context context) {
        mDbHandler = new DbHandler(context);
    }

    /**
     * Method that close the connection to the database
     */
    public void destroy() {
        mDbHandler.close();
    }

    public void insertListPicture(List<Photo> photos) {
        db = mDbHandler.getWritableDatabase();
        int update = -1;

        for (Photo p : photos) {
            ContentValues values = new ContentValues();
            values.put(PhotoContract.PhotoEntry.COLUM_NAME_PHOTO, p.getPhoto());
            values.put(PhotoContract.PhotoEntry.COLUM_NAME_CHEMIN, p.getChemin());
            update = db.update(PhotoContract.PhotoEntry.TABLE_NAME, values,
                    PhotoContract.PhotoEntry.COLUM_NAME_IDPHOTO + " = ?",
                    new String[]{String.valueOf(p.getIdPhoto())});
            if (update == 0) {
                values.put(PhotoContract.PhotoEntry.COLUM_NAME_IDPHOTO, p.getIdPhoto());
                db.insert(PhotoContract.PhotoEntry.TABLE_NAME, null, values);
            }
        }
    }

    public List<Photo> getAllPicture() {
        List<Photo> photos = new ArrayList<>();

        db = mDbHandler.getWritableDatabase();

        String[] projection = {
                PhotoContract.PhotoEntry.COLUM_NAME_IDPHOTO,
                PhotoContract.PhotoEntry.COLUM_NAME_PHOTO,
                PhotoContract.PhotoEntry.COLUM_NAME_CHEMIN,
        };

        Cursor cursor = db.query(
                PhotoContract.PhotoEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            Long idPhoto = cursor.getLong(cursor.getColumnIndexOrThrow(PhotoContract.PhotoEntry.COLUM_NAME_IDPHOTO));
            String photo = cursor.getString(cursor.getColumnIndexOrThrow(PhotoContract.PhotoEntry.COLUM_NAME_PHOTO));
            String chemin = cursor.getString(cursor.getColumnIndexOrThrow(PhotoContract.PhotoEntry.COLUM_NAME_CHEMIN));

            Photo p = new Photo(idPhoto, photo, chemin);
            photos.add(p);
        }
        cursor.close();

        return photos;
    }

    public Photo getPhoto(Long id) {
        db = mDbHandler.getWritableDatabase();
        Photo p = new Photo();

        String[] projection = {
                PhotoContract.PhotoEntry.COLUM_NAME_IDPHOTO,
                PhotoContract.PhotoEntry.COLUM_NAME_PHOTO,
                PhotoContract.PhotoEntry.COLUM_NAME_CHEMIN,
        };

        String selection = PhotoContract.PhotoEntry.COLUM_NAME_IDPHOTO + " = ?";

        String[] args = {String.valueOf(id)};

        Cursor cursor = db.query(
                PhotoContract.PhotoEntry.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                null
        );

        if (cursor.moveToNext()) {
            final Long idphoto = cursor.getLong(cursor.getColumnIndexOrThrow(PhotoContract.PhotoEntry.COLUM_NAME_IDPHOTO));
            final String photo = cursor.getString(cursor.getColumnIndexOrThrow(PhotoContract.PhotoEntry.COLUM_NAME_PHOTO));
            final String chemin = cursor.getString(cursor.getColumnIndexOrThrow(PhotoContract.PhotoEntry.COLUM_NAME_CHEMIN));
            p = new Photo(idphoto, photo, chemin);
        }

        return p;
    }

}
