package ffscreens.arthylene.api;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.database.AudioDAO;
import ffscreens.arthylene.objects.Audio;

public class AudioRequest extends ApiRequest
{
    private List<Audio> audioList;
    private AudioDAO dao;

    @Override
    public void setContext(Context context) {
        audioList = new ArrayList<>();
        dao = new AudioDAO(context);
    }

    @Override
    void addEntity(JSONObject o) throws JSONException {
        Audio audio = new Audio(o.getLong("idAudio"), o.getString("audio"), o.getString("chemin"));

        audioList.add(audio);
    }

    @Override
    void daoInsert() {
        dao.insertListAudio(audioList);
        List<Audio> p2 = dao.getAllAudio();
        /*for (Audio p1 : p2) {
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
