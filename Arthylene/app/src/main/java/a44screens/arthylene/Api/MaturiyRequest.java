package a44screens.arthylene.Api;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import a44screens.arthylene.DataBase.MaturiteDAO;
import a44screens.arthylene.Objects.Maturite;

/**
 * Created by Thibault on 23/06/2017.
 */

public class MaturiyRequest extends ApiRequest {

    private List<Maturite> maturiteList;
    private MaturiteDAO dao;

    public MaturiyRequest(Context context) {
        this.setContext(context);
        maturiteList = new ArrayList<>();
        dao = new MaturiteDAO(context);
    }

    @Override
    void addEntity(JSONObject o) throws JSONException {
        Maturite m = new Maturite(o.getLong("idMaturite"), o.getLong("idProduit"),
                o.getString("contenu"), o.optLong("idPhoto", -1L),
                (o.optInt("maturiteIdeale", -1) == 1), o.getString("textePopup"));
        maturiteList.add(m);
    }

    @Override
    void DAOInsert() {
        dao.insertListMaturity(maturiteList);
        List<Maturite> p2 = dao.getAllMaturity();
        for (Maturite p1 : p2) {
            Log.e("maturite --->", p1.toString());
        }
    }
}
