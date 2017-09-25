package ffscreens.arthylene.database;

import android.provider.BaseColumns;


public class BeneficeSanteContract
{

    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + BeneficeSanteContract.BeneficeSanteEntry.TABLE_NAME;

    private static final String TEXT = " TEXT, ";

    private static final String INTEGER = " INTEGER, ";

    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + BeneficeSanteContract.BeneficeSanteEntry.TABLE_NAME + "(" +
                    BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_IDBENEFICESANTE + " INTEGER PRIMARY KEY," +
                    BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_IDPRODUIT + INTEGER +
                    BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE1 + TEXT +
                    BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE2 + TEXT +
                    BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE3 + TEXT +
                    BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE4 + TEXT +
                    BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE5 + TEXT +
                    BeneficeSanteContract.BeneficeSanteEntry.COLUM_NAME_BENEFICE6 + " TEXT)";

    private BeneficeSanteContract() {

    }

    static class BeneficeSanteEntry implements BaseColumns {

        static final String TABLE_NAME = "beneficeSante";

        static final String COLUM_NAME_IDBENEFICESANTE = "idBeneficeSante";

        static final String COLUM_NAME_IDPRODUIT = "idproduit";

        static final String COLUM_NAME_BENEFICE1 = "benefice1";

        static final String COLUM_NAME_BENEFICE2 = "benefice2";

        static final String COLUM_NAME_BENEFICE3 = "benefice3";

        static final String COLUM_NAME_BENEFICE4 = "benefice4";

        static final String COLUM_NAME_BENEFICE5 = "benefice5";

        static final String COLUM_NAME_BENEFICE6 = "benefice6";

        private BeneficeSanteEntry() {
        }
    }
}
