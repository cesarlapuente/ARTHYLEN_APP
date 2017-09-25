package ffscreens.arthylene.database;


import android.provider.BaseColumns;

public class CaracteristiqueContract
{

    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CaracteristiqueContract.CaracteristiqueEntry.TABLE_NAME;

    private static final String TEXT = " TEXT, ";

    private static final String INTEGER = " INTEGER, ";

    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CaracteristiqueContract.CaracteristiqueEntry.TABLE_NAME + "(" +
                    CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_IDCARACTERISTIQUE + " INTEGER PRIMARY KEY," +
                    CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_IDPRODUIT + INTEGER +
                    CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_FAMILLE + TEXT +
                    CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_ESPECE + TEXT +
                    CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_TAILLEPOIDS + TEXT +
                    CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_COULEURTEXTURE + TEXT +
                    CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_SAVEUR + TEXT +
                    CaracteristiqueContract.CaracteristiqueEntry.COLUM_NAME_PRINCIPAUXPRODUCTEUR + " TEXT)";

    private CaracteristiqueContract() {

    }


    static class CaracteristiqueEntry implements BaseColumns {

        static final String TABLE_NAME = "caracteristique";

        static final String COLUM_NAME_IDCARACTERISTIQUE = "idCaracteristique";

        static final String COLUM_NAME_IDPRODUIT = "idProduit";

        static final String COLUM_NAME_FAMILLE = "famille";

        static final String COLUM_NAME_ESPECE = "espece";

        static final String COLUM_NAME_TAILLEPOIDS = "taillePoids";

        static final String COLUM_NAME_COULEURTEXTURE = "couleurTexture";

        static final String COLUM_NAME_SAVEUR = "saveur";

        static final String COLUM_NAME_PRINCIPAUXPRODUCTEUR = "principauxProducteur";

        private CaracteristiqueEntry() {
        }
    }
}
