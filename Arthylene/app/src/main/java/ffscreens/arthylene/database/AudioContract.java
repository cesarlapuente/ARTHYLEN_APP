package ffscreens.arthylene.database;


import android.provider.BaseColumns;

public class AudioContract
{
    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + AudioEntry.TABLE_NAME;

    private static final String TEXT = " TEXT, ";
    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + AudioEntry.TABLE_NAME + "(" +
                    AudioEntry.COLUM_NAME_IDAUDIO + " INTEGER PRIMARY KEY," +
                    AudioEntry.COLUM_NAME_AUDIO + TEXT +
                    AudioEntry.COLUM_NAME_CHEMIN + " TEXT)";
    private static final String INTEGER = " INTEGER, ";

    public AudioContract() {
    }

    static class AudioEntry implements BaseColumns {

        static final String TABLE_NAME = "audio";

        static final String COLUM_NAME_IDAUDIO = "idAudio";

        static final String COLUM_NAME_AUDIO = "audio";

        static final String COLUM_NAME_CHEMIN = "chemin";

        public AudioEntry() {
        }
    }
}
