package ffscreens.arthylene.api;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Thibault on 21/06/2017.
 */

public abstract class ApiRequest extends AsyncTask<String, Void, String> {

    public abstract void setContext(Context context);

    @Override
    protected String doInBackground(String... params) {
        URL siteUrl;
        URLConnection siteConnection;
        BufferedReader in;
        String codeHtml = null;
        String rl;
        InputStreamReader isr = null;

        try {
            siteUrl = new URL(params[0]);
            siteConnection = siteUrl.openConnection();
            siteConnection.setConnectTimeout(1000 * 10);
            try {
                isr = new InputStreamReader(siteConnection.getInputStream());
            } catch (Exception e) {
                super.cancel(true);
            }
            in = new BufferedReader(isr);

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
            Log.e("log", "doInBackground: ", e);
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
            daoInsert();
        } catch (JSONException e) {
            Log.e("log", "onPostExecute: ", e);
        }
    }

    abstract void addEntity(JSONObject o) throws JSONException;

    abstract void daoInsert();

}