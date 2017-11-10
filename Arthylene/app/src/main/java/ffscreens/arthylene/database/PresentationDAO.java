package ffscreens.arthylene.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.objects.Presentation;

/**
 * Arthylene
 * Created by Thibault Nougues on 21/06/2017.
 */

public class PresentationDAO {

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
    public PresentationDAO(Context context) {
        mDbHandler = new DbHandler(context);
    }

    /**
     * Method that close the connection to the database
     */
    public void destroy() {
        mDbHandler.close();
    }

    public void insertListPresentation(List<Presentation> presentations) {
        db = mDbHandler.getWritableDatabase();
        int update = -1;

        for (Presentation p : presentations) {
            ContentValues values = new ContentValues();
            values.put(PresentationContract.PresentationEntry.COLUM_NAME_IDPRODUIT, p.getIdProduit());
            values.put(PresentationContract.PresentationEntry.COLUM_NAME_CONTENU, p.getContenu());
            values.put(PresentationContract.PresentationEntry.COLUM_NAME_IDPHOTO, p.getIdPhoto());
            values.put(PresentationContract.PresentationEntry.COLUM_NAME_IDAUDIO, p.getIdAudio());
            update = db.update(PresentationContract.PresentationEntry.TABLE_NAME, values, PresentationContract.PresentationEntry.COLUM_NAME_IDPRESENTATION + " = ?", new String[]{String.valueOf(p.getIdPresentation())});
            if (update == 0) {
                values.put(PresentationContract.PresentationEntry.COLUM_NAME_IDPRESENTATION, p.getIdPresentation());
                db.insert(PresentationContract.PresentationEntry.TABLE_NAME, null, values);
            }
        }
    }

    public List<Presentation> getAllPresentations() {
        List<Presentation> presentations = new ArrayList<>();

        db = mDbHandler.getWritableDatabase();

        String[] projection = {
                PresentationContract.PresentationEntry.COLUM_NAME_IDPRESENTATION,
                PresentationContract.PresentationEntry.COLUM_NAME_IDPRODUIT,
                PresentationContract.PresentationEntry.COLUM_NAME_CONTENU,
                PresentationContract.PresentationEntry.COLUM_NAME_IDPHOTO,
                PresentationContract.PresentationEntry.COLUM_NAME_IDAUDIO,
        };

        Cursor cursor = db.query(
                PresentationContract.PresentationEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            Long id = cursor.getLong(cursor.getColumnIndexOrThrow(PresentationContract.PresentationEntry.COLUM_NAME_IDPRESENTATION));
            Long idProduit = cursor.getLong(cursor.getColumnIndexOrThrow(PresentationContract.PresentationEntry.COLUM_NAME_IDPRODUIT));
            String contenu = cursor.getString(cursor.getColumnIndexOrThrow(PresentationContract.PresentationEntry.COLUM_NAME_CONTENU));
            Long idPhoto = cursor.getLong(cursor.getColumnIndexOrThrow(PresentationContract.PresentationEntry.COLUM_NAME_IDPHOTO));
            Long idAudio = cursor.getLong(cursor.getColumnIndexOrThrow(PresentationContract.PresentationEntry.COLUM_NAME_IDAUDIO));

            Presentation p = new Presentation(id, idProduit, contenu, idPhoto, idAudio);
            presentations.add(p);
        }
        cursor.close();

        return presentations;
    }

}
