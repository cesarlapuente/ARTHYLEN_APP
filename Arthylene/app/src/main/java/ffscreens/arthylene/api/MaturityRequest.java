package ffscreens.arthylene.api;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.database.MaturiteDAO;
import ffscreens.arthylene.objects.Maturite;

/**
 * Created by Thibault on 23/06/2017.
 */

public class MaturityRequest extends ApiRequest {

    private List<Maturite> maturiteList;
    private MaturiteDAO dao;

    @Override
    public void setContext(Context context) {
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
    void daoInsert() {
        dao.insertListMaturity(maturiteList);
        List<Maturite> p2 = dao.getAllMaturity();
        for (Maturite p1 : p2) {
            Log.e("maturite --->", p1.toString());
        }
    }

    @Override
    void daoDeleteRemoved() {
        /*List<Item> local = dao.getAllItems();
        ArrayList<Item> remove = new ArrayList<>();
        for(Item item : local){
            if(!itemList.contains(item)){
                remove.add(item);
            }
        }
        dao.deleteListItem(remove);*/
    }
}
