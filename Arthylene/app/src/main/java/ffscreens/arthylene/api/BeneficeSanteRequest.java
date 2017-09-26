package ffscreens.arthylene.api;


import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.database.BeneficeSanteDAO;
import ffscreens.arthylene.objects.BeneficeSante;

public class BeneficeSanteRequest extends ApiRequest
{
    List<BeneficeSante> beneficeSanteList;
    BeneficeSanteDAO dao;

    @Override
    public void setContext(Context context) {
        beneficeSanteList = new ArrayList<>();
        dao = new BeneficeSanteDAO(context);
    }

    @Override
    void addEntity(JSONObject newEntity) throws JSONException {
        BeneficeSante beneficeSante = new BeneficeSante(
                newEntity.getLong("idBeneficeSante"),
                newEntity.getLong("idProduit"),
                newEntity.getString("benefice1"),
                newEntity.getString("benefice2"),
                newEntity.getString("benefice3"),
                newEntity.getString("benefice4"),
                newEntity.getString("benefice5"),
                newEntity.getString("benefice6")
        );
        beneficeSanteList.add(beneficeSante);
    }

    @Override
    void daoInsert() {
        dao.insertListBeneficeSante(beneficeSanteList);
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
