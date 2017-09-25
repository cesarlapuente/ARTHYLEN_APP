package ffscreens.arthylene.database;

import android.provider.BaseColumns;

public class ConseilContract
{
    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ConseilContract.ConseilEntry.TABLE_NAME;

    private static final String TEXT = " TEXT, ";

    private static final String INTEGER = " INTEGER, ";

    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ConseilContract.ConseilEntry.TABLE_NAME + "(" +
                    ConseilContract.ConseilEntry.COLUM_NAME_IDCONSEIL + " INTEGER PRIMARY KEY," +
                    ConseilContract.ConseilEntry.COLUM_NAME_IDPRODUIT + INTEGER +
                    ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL1 + TEXT +
                    ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL2 + TEXT +
                    ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL3 + TEXT +
                    ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL4 + TEXT +
                    ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL5 + TEXT +
                    ConseilContract.ConseilEntry.COLUM_NAME_CONSEIL6 + " TEXT)";

    private ConseilContract() {

    }

    private Long idConseil;
    private Long idProduit;
    private String conseil1;
    private String conseil2;
    private String conseil3;
    private String conseil4;
    private String conseil5;
    private String conseil6;


    static class ConseilEntry implements BaseColumns {

        static final String TABLE_NAME = "conseil";

        static final String COLUM_NAME_IDCONSEIL = "idConseil";

        static final String COLUM_NAME_IDPRODUIT = "idProduit";

        static final String COLUM_NAME_CONSEIL1 = "conseil1";

        static final String COLUM_NAME_CONSEIL2 = "conseil2";

        static final String COLUM_NAME_CONSEIL3 = "conseil3";

        static final String COLUM_NAME_CONSEIL4 = "conseil4";

        static final String COLUM_NAME_CONSEIL5 = "conseil5";

        static final String COLUM_NAME_CONSEIL6 = "conseil6";

        private ConseilEntry() {
        }
    }
}
