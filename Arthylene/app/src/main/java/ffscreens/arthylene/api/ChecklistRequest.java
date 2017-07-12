package ffscreens.arthylene.api;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.database.ChecklistDAO;
import ffscreens.arthylene.objects.Item;

/**
 * Arthylene
 * Created by Thibault on 23/06/2017.
 */

public class ChecklistRequest extends ApiRequest {

    private List<Item> itemList;
    private ChecklistDAO dao;

    @Override
    public void setContext(Context context) {
        itemList = new ArrayList<>();
        dao = new ChecklistDAO(context);
    }

    @Override
    void addEntity(JSONObject o) throws JSONException {
        Item e = new Item(o.getLong("id"), o.getString("titre"),
                o.getString("contenu"), (o.optInt("isImportant", -1) == 1)
                , o.optLong("idPhoto", -1L));
        itemList.add(e);
    }

    @Override
    void daoInsert() {
        dao.insertListItem(itemList);
        List<Item> p2 = dao.getAllItems();
        for (Item p1 : p2) {
            Log.e("item --->", p1.toString());
        }
    }

    @Override
    void daoDeleteRemoved() {
        List<Item> local = dao.getAllItems();
        ArrayList<Item> remove = new ArrayList<>();
        Log.e("local", local.toString());
        Log.e("online", itemList.toString());
        for (Item item : local) {
            if (!itemList.contains(item)) {
                Log.e("thib", "daoDeleteRemoved: ");
                remove.add(item);
            }
        }
        dao.deleteListItem(remove);
    }
}
