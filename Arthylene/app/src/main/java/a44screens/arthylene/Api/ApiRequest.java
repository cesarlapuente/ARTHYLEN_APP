package a44screens.arthylene.Api;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import a44screens.arthylene.Enumeration.ApiAdress;

/**
 * Created by Thibault on 21/06/2017.
 */

public abstract class ApiRequest extends AsyncTask<String, Void, String> {

    Context context;
    ApiAdress api;
    List l;


    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        URL siteUrl = null;
        URLConnection siteConnection;
        BufferedReader in;
        String codeHtml = null;
        String rl = null;

        try {
            siteUrl = new URL(params[0]);
            siteConnection = siteUrl.openConnection();
            in = new BufferedReader(new InputStreamReader(siteConnection.getInputStream()));

            codeHtml = "";
            // Récupération du code HTML du site ligne par ligne
            while ((rl = in.readLine()) != null) {
                codeHtml += rl;

                // Test si le l'AsyncTask est cancel pour annuler
                // la lecture du site
                if (isCancelled()) {
                    in.close();
                    return null;
                }
            }

            // Fermeture du BufferReader
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return codeHtml;
    }

    @Override
    protected void onPostExecute(String result) {
        // result contient le JSON
        try {
            JSONArray rep = new JSONArray(result);
            for (int i = 0; i < rep.length(); i++) {
                JSONObject o = (JSONObject) rep.get(i);
                addEntity(o);
            }
            DAOInsert();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    abstract void addEntity(JSONObject o) throws JSONException;

    abstract void DAOInsert();

}