package ffscreens.arthylene.api;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.database.EtatDAO;
import ffscreens.arthylene.objects.Etat;

/**
 * Created by Thibault on 23/06/2017.
 */

public class StateRequest extends ApiRequest {

    private List<Etat> etatList;
    private EtatDAO dao;

    @Override
    public void setContext(Context context) {
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
    void daoInsert() {
        dao.insertListStates(etatList);
        /*List<Etat> p2 = dao.getAllStates();
        for (Etat p1 : p2) {
            Log.e("etat --->", p1.toString());
        }*/
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
