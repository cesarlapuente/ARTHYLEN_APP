package ffscreens.arthylene.database;

import android.provider.BaseColumns;

/**
 * Created by Thibault on 21/06/2017.
 */

public final class ChecklistContract {

    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS" + ChecklistEntry.TABLE_NAME;

    private static final String TEXT = " TEXT, ";

    private static final String INTEGER = " INTEGER, ";

    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ChecklistEntry.TABLE_NAME + "(" +
                    ChecklistEntry._ID + " INTEGER PRIMARY KEY," +
                    ChecklistEntry.COLUM_NAME_TITLE + TEXT +
                    ChecklistEntry.COLUM_NAME_CONTENU + TEXT +
                    ChecklistEntry.COLUM_NAME_IMPORTANT + INTEGER +
                    ChecklistEntry.COLUM_NAME_CHECKED + " INTEGER)";

    private ChecklistContract() {

    }

    static class ChecklistEntry implements BaseColumns {

        static final String TABLE_NAME = "checklist";

        static final String COLUM_NAME_TITLE = "title";

        static final String COLUM_NAME_CONTENU = "contenu";

        static final String COLUM_NAME_IMPORTANT = "important";

        static final String COLUM_NAME_CHECKED = "checked";

        private ChecklistEntry() {
        }
    }
}
