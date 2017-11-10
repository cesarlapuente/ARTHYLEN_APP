package ffscreens.arthylene.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.objects.Audio;

public class AudioDAO
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
    public AudioDAO(Context context) {
        mDbHandler = new DbHandler(context);
    }

    /**
     * Method that close the connection to the database
     */
    public void destroy() {
        mDbHandler.close();
    }

    public void insertListAudio(List<Audio> audios) {
        db = mDbHandler.getWritableDatabase();
        int update = -1;

        for (Audio a : audios) {
            ContentValues values = new ContentValues();
            values.put(AudioContract.AudioEntry.COLUM_NAME_AUDIO, a.getAudio());
            values.put(AudioContract.AudioEntry.COLUM_NAME_CHEMIN, a.getChemin());
            update = db.update(AudioContract.AudioEntry.TABLE_NAME, values,AudioContract.AudioEntry.COLUM_NAME_IDAUDIO + " = ?",
                    new String[]{String.valueOf(a.getIdAudio())});
            if (update == 0) {
                values.put(AudioContract.AudioEntry.COLUM_NAME_IDAUDIO, a.getIdAudio());
                db.insert(AudioContract.AudioEntry.TABLE_NAME, null, values);
            }
        }
    }

    public List<Audio> getAllAudio() {
        List<Audio> audios = new ArrayList<>();

        db = mDbHandler.getWritableDatabase();

        String[] projection = {
                AudioContract.AudioEntry.COLUM_NAME_IDAUDIO,
                AudioContract.AudioEntry.COLUM_NAME_AUDIO,
                AudioContract.AudioEntry.COLUM_NAME_CHEMIN,
        };

        Cursor cursor = db.query(
                AudioContract.AudioEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            Long idAudio = cursor.getLong(cursor.getColumnIndexOrThrow(AudioContract.AudioEntry.COLUM_NAME_IDAUDIO));
            String audio = cursor.getString(cursor.getColumnIndexOrThrow(AudioContract.AudioEntry.COLUM_NAME_AUDIO));
            String chemin = cursor.getString(cursor.getColumnIndexOrThrow(AudioContract.AudioEntry.COLUM_NAME_CHEMIN));

            Audio a = new Audio(idAudio, audio, chemin);
            audios.add(a);
        }
        cursor.close();

        return audios;
    }

    public Audio getAudio(Long id) {
        db = mDbHandler.getWritableDatabase();
        Audio a = new Audio();

        String[] projection = {
                AudioContract.AudioEntry.COLUM_NAME_IDAUDIO,
                AudioContract.AudioEntry.COLUM_NAME_AUDIO,
                AudioContract.AudioEntry.COLUM_NAME_CHEMIN,
        };

        String selection = AudioContract.AudioEntry.COLUM_NAME_IDAUDIO + " = ?";

        String[] args = {String.valueOf(id)};

        Cursor cursor = db.query(
                AudioContract.AudioEntry.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                null
        );

        if (cursor.moveToNext()) {
            Long idAudio = cursor.getLong(cursor.getColumnIndexOrThrow(AudioContract.AudioEntry.COLUM_NAME_IDAUDIO));
            String audio = cursor.getString(cursor.getColumnIndexOrThrow(AudioContract.AudioEntry.COLUM_NAME_AUDIO));
            String chemin = cursor.getString(cursor.getColumnIndexOrThrow(AudioContract.AudioEntry.COLUM_NAME_CHEMIN));
            a = new Audio(idAudio, audio, chemin);
        }

        return a;
    }

}
