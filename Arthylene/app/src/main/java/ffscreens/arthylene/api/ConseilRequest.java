package ffscreens.arthylene.api;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.database.ConseilDAO;
import ffscreens.arthylene.objects.Conseil;

public class ConseilRequest extends ApiRequest
{
    List<Conseil> conseilList;
    ConseilDAO dao;

    @Override
    public void setContext(Context context) {
        conseilList = new ArrayList<>();
        dao = new ConseilDAO(context);
    }

    @Override
    void addEntity(JSONObject newEntity) throws JSONException {
        Conseil conseil = new Conseil(
                newEntity.getLong("idConseil"),
                newEntity.getLong("idProduit"),
                newEntity.getString("conseil1"),
                newEntity.getString("conseil2"),
                newEntity.getString("conseil3"),
                newEntity.getString("conseil4"),
                newEntity.getString("conseil5"),
                newEntity.getString("conseil6")
        );
        conseilList.add(conseil);
    }

    @Override
    void daoInsert() {
        dao.insertListConseil(conseilList);
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
