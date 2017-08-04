package ffscreens.arthylene.api;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.database.PhotoDAO;
import ffscreens.arthylene.objects.Photo;

/**
 * Created by Thibault on 23/06/2017.
 */

public class PictureRequest extends ApiRequest {

    private List<Photo> photoList;
    private PhotoDAO dao;

    @Override
    public void setContext(Context context) {
        photoList = new ArrayList<>();
        dao = new PhotoDAO(context);
    }

    @Override
    void addEntity(JSONObject o) throws JSONException {
        Photo e = new Photo(o.getLong("idPhoto"), o.getString("photo"),
                o.getString("chemin"));
        photoList.add(e);
    }

    @Override
    void daoInsert() {
        dao.insertListPicture(photoList);
        List<Photo> p2 = dao.getAllPicture();
        /*for (Photo p1 : p2) {
            Log.e("chemin --->", p1.toString());
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
