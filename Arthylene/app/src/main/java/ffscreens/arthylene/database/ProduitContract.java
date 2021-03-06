package ffscreens.arthylene.database;

import android.provider.BaseColumns;

/**
 * Arthylene
 * Created by Thibault Nougues on 21/06/2017.
 */

public final class ProduitContract {

    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ProduitEntry.TABLE_NAME;

    private static final String TEXT = " TEXT, ";

    private static final String INTEGER = " INTEGER, ";

    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ProduitEntry.TABLE_NAME + "(" +
                    ProduitEntry.COLUM_NAME_IDPRODUIT + " INTEGER PRIMARY KEY," +
                    ProduitEntry.COLUM_NAME_NOMPRODUIT + TEXT +
                    ProduitEntry.COLUM_NAME_VARIETEPRODUIT + TEXT +
                    ProduitEntry.COLUM_NAME_NIVEAUMATURITE + INTEGER +
                    ProduitEntry.COLUM_NAME_IDMATURITE + INTEGER +
                    ProduitEntry.COLUM_NAME_NIVEAUETAT + INTEGER +
                    ProduitEntry.COLUM_NAME_IDETAT + INTEGER +
                    ProduitEntry.COLUM_NAME_IDPRESENTATION + INTEGER +
                    ProduitEntry.COLUM_NAME_IDBENEFICESANTE + INTEGER +
                    ProduitEntry.COLUM_NAME_IDCARACTERISTIQUE + INTEGER +
                    ProduitEntry.COLUM_NAME_IDCONSEIL + INTEGER +
                    ProduitEntry.COLUM_NAME_IDMARKETING +" INTEGER)";

    private ProduitContract() {

    }

    static class ProduitEntry implements BaseColumns {

        static final String TABLE_NAME = "produit";

        static final String COLUM_NAME_IDPRODUIT = "idproduit";

        static final String COLUM_NAME_NOMPRODUIT = "nomproduit";

        static final String COLUM_NAME_VARIETEPRODUIT = "varieteproduit";

        static final String COLUM_NAME_NIVEAUMATURITE = "niveaumaturite";

        static final String COLUM_NAME_IDMATURITE = "idmaturite";

        static final String COLUM_NAME_NIVEAUETAT = "niveauetat";

        static final String COLUM_NAME_IDETAT = "idetat";

        static final String COLUM_NAME_IDPRESENTATION = "idpresentation";

        static final String COLUM_NAME_IDBENEFICESANTE = "idBeneficeSante";

        static final String COLUM_NAME_IDCARACTERISTIQUE = "idCaracteristique";

        static final String COLUM_NAME_IDCONSEIL = "idConseil";

        static final String COLUM_NAME_IDMARKETING = "idMarketing";



        private ProduitEntry() {
        }
    }
}
