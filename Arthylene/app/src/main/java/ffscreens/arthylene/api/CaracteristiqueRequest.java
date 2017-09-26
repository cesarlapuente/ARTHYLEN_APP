package ffscreens.arthylene.api;


import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.database.CaracteristiqueDAO;
import ffscreens.arthylene.objects.Caracteristique;

public class CaracteristiqueRequest extends ApiRequest
{
    List<Caracteristique> caracteristiqueList;
    CaracteristiqueDAO dao;

    @Override
    public void setContext(Context context) {
        caracteristiqueList = new ArrayList<>();
        dao = new CaracteristiqueDAO(context);
    }

    @Override
    void addEntity(JSONObject newEntity) throws JSONException {
        Caracteristique caracteristique = new Caracteristique(
                newEntity.getLong("idCaracteristique"),
                newEntity.getLong("idProduit"),
                newEntity.getString("famille"),
                newEntity.getString("espece"),
                newEntity.getString("taillePoids"),
                newEntity.getString("couleurTexture"),
                newEntity.getString("saveur"),
                newEntity.getString("principauxProducteurs")
        );
        caracteristiqueList.add(caracteristique);
    }

    @Override
    void daoInsert() {
        dao.insertListCaracteristique(caracteristiqueList);
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
