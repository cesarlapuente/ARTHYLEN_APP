package ffscreens.arthylene.api;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.database.EtiquetteDAO;
import ffscreens.arthylene.objects.Etiquette;

/**
 * Arthylene
 * Created by Thibault Nougues on 23/06/2017.
 */

public class LabelRequest extends ApiRequest {

    private List<Etiquette> etiquetteList;
    private EtiquetteDAO dao;

    @Override
    public void setContext(Context context) {
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
    void daoInsert() {
        dao.insertListLabel(etiquetteList);
        /*List<Etiquette> p2 = dao.getAllLabels();
        for (Etiquette p1 : p2) {
            Log.e("etiquette --->", p1.toString());
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
