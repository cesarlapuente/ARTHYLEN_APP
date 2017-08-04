package ffscreens.arthylene.api;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.database.PresentationDAO;
import ffscreens.arthylene.objects.Presentation;

/**
 * Created by Thibault on 23/06/2017.
 */

public class PresentationRequest extends ApiRequest {

    private List<Presentation> presentationList;
    private PresentationDAO dao;

    @Override
    public void setContext(Context context) {
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
    void daoInsert() {
        dao.insertListPresentation(presentationList);
        /*List<Presentation> p2 = dao.getAllPresentations();
        for (Presentation p1 : p2) {
            Log.e("presentation --->", p1.toString());
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
