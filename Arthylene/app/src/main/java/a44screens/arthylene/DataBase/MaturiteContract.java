package a44screens.arthylene.DataBase;

import android.provider.BaseColumns;

/**
 * Created by Thibault on 21/06/2017.
 */

public final class MaturiteContract {

    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS" + MaturiteEntry.TABLE_NAME;

    private static final String TEXT = " TEXT, ";

    private static final String INTEGER = " INTEGER, ";

    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MaturiteEntry.TABLE_NAME + "(" +
                    MaturiteEntry.COLUM_NAME_IDMATURITE + " INTEGER PRIMARY KEY," +
                    MaturiteEntry.COLUM_NAME_IDPRODUIT + INTEGER +
                    MaturiteEntry.COLUM_NAME_CONTENU + TEXT +
                    MaturiteEntry.COLUM_NAME_IDPHOTO + INTEGER +
                    MaturiteEntry.COLUM_NAME_IDEALE + INTEGER +
                    MaturiteEntry.COLUM_NAME_POPUP + " TEXT)";

    private MaturiteContract() {

    }

    static class MaturiteEntry implements BaseColumns {

        static final String TABLE_NAME = "maturite";

        static final String COLUM_NAME_IDMATURITE = "idMaturite";

        static final String COLUM_NAME_IDPRODUIT = "idProduit";

        static final String COLUM_NAME_CONTENU = "contenu";

        static final String COLUM_NAME_IDPHOTO = "idPhoto";

        static final String COLUM_NAME_IDEALE = "ideale";

        static final String COLUM_NAME_POPUP = "popup";

        private MaturiteEntry() {
        }
    }
}
