package ffscreens.arthylene.database;

import android.provider.BaseColumns;

/**
 * Created by Thibault on 21/06/2017.
 */

public final class EtiquetteContract {

    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + EtiquetteEntry.TABLE_NAME;

    private static final String TEXT = " TEXT, ";

    private static final String INTEGER = " INTEGER, ";

    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + EtiquetteEntry.TABLE_NAME + "(" +
                    EtiquetteEntry.COLUM_NAME_IDETIQUETTE + " INTEGER PRIMARY KEY," +
                    EtiquetteEntry.COLUM_NAME_CODE + INTEGER +
                    EtiquetteEntry.COLUM_NAME_IDCAGETTE + INTEGER +
                    EtiquetteEntry.COLUM_NAME_IDPHOTO + INTEGER +
                    EtiquetteEntry.COLUM_NAME_NOMPRODUIT + TEXT +
                    EtiquetteEntry.COLUM_NAME_VARIETEPRODUIT + TEXT +
                    EtiquetteEntry.COLUM_NAME_ETE + INTEGER +
                    EtiquetteEntry.COLUM_NAME_AUTOMNE + INTEGER +
                    EtiquetteEntry.COLUM_NAME_HIVER + INTEGER +
                    EtiquetteEntry.COLUM_NAME_PRINTEMPS + INTEGER +
                    EtiquetteEntry.COLUM_NAME_NBCOUCHE + INTEGER +
                    EtiquetteEntry.COLUM_NAME_MATUMIN + INTEGER +
                    EtiquetteEntry.COLUM_NAME_MATUMAX + INTEGER +
                    EtiquetteEntry.COLUM_NAME_CHARIOT + " INTEGER)";

    private EtiquetteContract() {

    }

    static class EtiquetteEntry implements BaseColumns {

        static final String TABLE_NAME = "etiquette";

        static final String COLUM_NAME_IDETIQUETTE = "idEtiquette";

        static final String COLUM_NAME_CODE = "code";

        static final String COLUM_NAME_IDCAGETTE = "idCagette";

        static final String COLUM_NAME_IDPHOTO = "idPhoto";

        static final String COLUM_NAME_NOMPRODUIT = "nomProduit";

        static final String COLUM_NAME_VARIETEPRODUIT = "varieteProduit";

        static final String COLUM_NAME_ETE = "ete";

        static final String COLUM_NAME_AUTOMNE = "automne";

        static final String COLUM_NAME_HIVER = "hiver";

        static final String COLUM_NAME_PRINTEMPS = "printemps";

        static final String COLUM_NAME_NBCOUCHE = "nbCouche";

        static final String COLUM_NAME_MATUMIN = "matMin";

        static final String COLUM_NAME_MATUMAX = "matMax";

        static final String COLUM_NAME_CHARIOT = "chariot";

        private EtiquetteEntry() {
        }
    }
}
