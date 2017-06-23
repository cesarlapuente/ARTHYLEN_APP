package a44screens.arthylene.DataBase;

import android.provider.BaseColumns;

/**
 * Created by Thibault on 21/06/2017.
 */

public final class PresentationContract {

    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS" + PresentationEntry.TABLE_NAME;

    private static final String TEXT = " TEXT, ";

    private static final String INTEGER = " INTEGER, ";

    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PresentationEntry.TABLE_NAME + "(" +
                    PresentationEntry.COLUM_NAME_IDPRESENTATION + " INTEGER PRIMARY KEY," +
                    PresentationEntry.COLUM_NAME_IDPRODUIT + INTEGER +
                    PresentationEntry.COLUM_NAME_CONTENU + TEXT +
                    PresentationEntry.COLUM_NAME_IDPHOTO + " INTEGER)";

    private PresentationContract() {

    }

    static class PresentationEntry implements BaseColumns {

        static final String TABLE_NAME = "presentation";

        static final String COLUM_NAME_IDPRESENTATION = "idPresentation";

        static final String COLUM_NAME_IDPRODUIT = "idProduit";

        static final String COLUM_NAME_CONTENU = "contenu";

        static final String COLUM_NAME_IDPHOTO = "idPhoto";

        private PresentationEntry() {
        }
    }
}
