package ffscreens.arthylene.api;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.database.MarketingDAO;
import ffscreens.arthylene.objects.Marketing;

public class MarketingRequest extends ApiRequest
{
    List<Marketing> marketingList;
    MarketingDAO dao;

    @Override
    public void setContext(Context context) {
        marketingList = new ArrayList<>();
        dao = new MarketingDAO(context);
    }

    @Override
    void addEntity(JSONObject newEntity) throws JSONException {
        Marketing marketing = new Marketing(
                newEntity.getLong("idMarketing"),
                newEntity.getLong("idProduit"),
                newEntity.getString("marketing1"),
                newEntity.getString("marketing2")
                );
        marketingList.add(marketing);
    }

    @Override
    void daoInsert() {
        dao.insertListMarketing(marketingList);
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
