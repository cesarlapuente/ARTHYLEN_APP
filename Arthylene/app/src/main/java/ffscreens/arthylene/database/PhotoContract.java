package ffscreens.arthylene.database;

import android.provider.BaseColumns;

/**
 * Created by Thibault on 21/06/2017.
 */

public final class PhotoContract {

    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PhotoEntry.TABLE_NAME;

    private static final String TEXT = " TEXT, ";
    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PhotoEntry.TABLE_NAME + "(" +
                    PhotoEntry.COLUM_NAME_IDPHOTO + " INTEGER PRIMARY KEY," +
                    PhotoEntry.COLUM_NAME_PHOTO + TEXT +
                    PhotoEntry.COLUM_NAME_CHEMIN + " TEXT)";
    private static final String INTEGER = " INTEGER, ";

    private PhotoContract() {

    }

    static class PhotoEntry implements BaseColumns {

        static final String TABLE_NAME = "photo";

        static final String COLUM_NAME_IDPHOTO = "idPhoto";

        static final String COLUM_NAME_PHOTO = "photo";

        static final String COLUM_NAME_CHEMIN = "chemin";

        private PhotoEntry() {
        }
    }
}
