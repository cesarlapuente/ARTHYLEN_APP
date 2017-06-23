package a44screens.arthylene.Api;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import a44screens.arthylene.DataBase.PresentationDAO;
import a44screens.arthylene.Objects.Presentation;

/**
 * Created by Thibault on 23/06/2017.
 */

public class PresentationRequest extends ApiRequest {

    private List<Presentation> presentationList;
    private PresentationDAO dao;

    public PresentationRequest(Context context) {
        this.setContext(context);
        presentationList = new ArrayList<>();
        dao = new PresentationDAO(context);
    }

    @Override
    void addEntity(JSONObject o) throws JSONException {
        Presentation p = new Presentation(o.getLong("idPresentation"), o.getLong("idProduit"),
                o.getString("contenu"), o.optLong("idPhoto", -1L));
        presentationList.add(p);
    }

    @Override
    void DAOInsert() {
        dao.insertListPresentation(presentationList);
        List<Presentation> p2 = dao.getAllPresentations();
        for (Presentation p1 : p2) {
            Log.e("presentation --->", p1.toString());
        }
    }
}
