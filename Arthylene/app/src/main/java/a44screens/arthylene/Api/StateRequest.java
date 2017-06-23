package a44screens.arthylene.Api;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import a44screens.arthylene.DataBase.EtatDAO;
import a44screens.arthylene.Objects.Etat;

/**
 * Created by Thibault on 23/06/2017.
 */

public class StateRequest extends ApiRequest {

    private List<Etat> etatList;
    private EtatDAO dao;

    public StateRequest(Context context) {
        this.setContext(context);
        etatList = new ArrayList<>();
        dao = new EtatDAO(context);
    }

    @Override
    void addEntity(JSONObject o) throws JSONException {
        Etat e = new Etat(o.getLong("idEtat"), o.getLong("idProduit"),
                o.getString("contenu"), o.optLong("idPhoto", -1L),
                o.getString("textePopup"));
        etatList.add(e);
    }

    @Override
    void DAOInsert() {
        dao.insertListStates(etatList);
        List<Etat> p2 = dao.getAllStates();
        for (Etat p1 : p2) {
            Log.e("etat --->", p1.toString());
        }
    }
}
