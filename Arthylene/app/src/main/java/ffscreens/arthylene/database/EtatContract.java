package ffscreens.arthylene.database;

import android.provider.BaseColumns;

/**
 * Created by Thibault on 21/06/2017.
 */

public final class EtatContract {

    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + EtatEntry.TABLE_NAME;

    private static final String TEXT = " TEXT, ";

    private static final String INTEGER = " INTEGER, ";

    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + EtatEntry.TABLE_NAME + "(" +
                    EtatEntry.COLUM_NAME_IDETAT + " INTEGER PRIMARY KEY," +
                    EtatEntry.COLUM_NAME_IDPRODUIT + INTEGER +
                    EtatEntry.COLUM_NAME_CONTENU + TEXT +
                    EtatEntry.COLUM_NAME_IDPHOTO + INTEGER +
                    EtatEntry.COLUM_NAME_POPUP + " TEXT)";

    private EtatContract() {

    }

    static class EtatEntry implements BaseColumns {

        static final String TABLE_NAME = "etat";

        static final String COLUM_NAME_IDETAT = "idEtat";

        static final String COLUM_NAME_IDPRODUIT = "idProduit";

        static final String COLUM_NAME_CONTENU = "contenu";

        static final String COLUM_NAME_IDPHOTO = "idPhoto";

        static final String COLUM_NAME_POPUP = "popup";

        private EtatEntry() {
        }
    }
}
