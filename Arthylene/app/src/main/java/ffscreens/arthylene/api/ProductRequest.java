package ffscreens.arthylene.api;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.database.ProduitDAO;
import ffscreens.arthylene.objects.Produit;

/**
 * Created by Thibault on 23/06/2017.
 */

public class ProductRequest extends ApiRequest {


    List<Produit> produitList;
    ProduitDAO dao;

    @Override
    public void setContext(Context context) {
        produitList = new ArrayList<>();
        dao = new ProduitDAO(context);
    }

    @Override
    void addEntity(JSONObject o) throws JSONException {
        Produit p = new Produit(o.getLong("idProduit"), o.getString("nomProduit"),
                o.getString("varieteProduit"), o.optInt("niveauMaturite", -1),
                o.optLong("idMaturite", -1L), o.optInt("niveauEtat", -1),
                o.optLong("idEtat", -1), o.optLong("idPresentation", -1L));
        produitList.add(p);
    }

    @Override
    void daoInsert() {
        dao.insertListProduit(produitList);
        List<Produit> p2 = dao.getAllProduct();
        for (Produit p1 : p2) {
            Log.e("product -->", p1.toString());
        }
    }
}
