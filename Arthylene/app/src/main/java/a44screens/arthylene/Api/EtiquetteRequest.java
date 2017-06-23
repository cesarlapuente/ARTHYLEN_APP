package a44screens.arthylene.Api;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import a44screens.arthylene.DataBase.EtiquetteDAO;
import a44screens.arthylene.Objects.Etiquette;

/**
 * Created by Thibault on 23/06/2017.
 */

public class EtiquetteRequest extends ApiRequest {

    private List<Etiquette> etiquetteList;
    private EtiquetteDAO dao;

    public EtiquetteRequest(Context context) {
        this.setContext(context);
        etiquetteList = new ArrayList<>();
        dao = new EtiquetteDAO(context);
    }

    @Override
    void addEntity(JSONObject o) throws JSONException {
        Etiquette e = new Etiquette(o.getLong("idEtiquette"), o.getString("code"),
                o.optLong("idCagette", -1L), o.optLong("idPhoto", -1L),
                o.getString("nomProduit"), o.getString("varieteProduit"),
                o.optInt("ordreEte", -1), o.optInt("ordreAutomne", -1),
                o.optInt("ordreHiver", -1), o.optInt("ordrePrintemps", -1),
                o.optInt("nombreDeCouche", -1), o.optInt("maturiteMin", -1),
                o.optInt("maturiteMax", -1), o.optInt("emplacementChariot", -1));
        etiquetteList.add(e);
    }

    @Override
    void DAOInsert() {
        dao.insertListLabel(etiquetteList);
        List<Etiquette> p2 = dao.getAllLabels();
        for (Etiquette p1 : p2) {
            Log.e("etiquette --->", p1.toString());
        }
    }
}
