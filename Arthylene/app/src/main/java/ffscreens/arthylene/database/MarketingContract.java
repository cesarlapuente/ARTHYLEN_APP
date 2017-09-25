package ffscreens.arthylene.database;


import android.provider.BaseColumns;

public class MarketingContract
{
    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MarketingContract.MarketingEntry.TABLE_NAME;

    private static final String TEXT = " TEXT, ";

    private static final String INTEGER = " INTEGER, ";

    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MarketingContract.MarketingEntry.TABLE_NAME + "(" +
                    MarketingContract.MarketingEntry.COLUM_NAME_IDMARKETING + " INTEGER PRIMARY KEY," +
                    MarketingContract.MarketingEntry.COLUM_NAME_IDPRODUIT + INTEGER +
                    MarketingContract.MarketingEntry.COLUM_NAME_MARKETING1 + TEXT +
                    MarketingContract.MarketingEntry.COLUM_NAME_MARKETING2 + " TEXT)";

    private MarketingContract() {

    }

    static class MarketingEntry implements BaseColumns {

        static final String TABLE_NAME = "marketing";

        static final String COLUM_NAME_IDMARKETING = "idMarketing";

        static final String COLUM_NAME_IDPRODUIT = "idProduit";

        static final String COLUM_NAME_MARKETING1 = "marketing1";

        static final String COLUM_NAME_MARKETING2 = "marketing2";

        private MarketingEntry() {
        }
    }
}
