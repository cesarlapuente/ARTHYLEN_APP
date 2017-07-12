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
 * Arthylene
 * Created by Thibault on 21/06/2017.
 */

public abstract class ApiRequest extends AsyncTask<String, Void, String> {

    private AsyncDelegate asyncDelegate;

    public void setAsyncDelegate(AsyncDelegate asyncDelegate) {
        this.asyncDelegate = asyncDelegate;
    }

    public abstract void setContext(Context context);

    @Override
    protected String doInBackground(String... params) {
        URL siteUrl;
        URLConnection siteConnection;
        BufferedReader in;
        StringBuilder codeHtml = null;
        String rl;
        InputStreamReader isr;

        try {
            siteUrl = new URL(params[0]);
            siteConnection = siteUrl.openConnection();
            siteConnection.setConnectTimeout(1000 * 10);
            try {
                isr = new InputStreamReader(siteConnection.getInputStream());
            } catch (Exception e) {
                return null;
            }
            in = new BufferedReader(isr);

            codeHtml = new StringBuilder();
            // Récupération du code HTML du site ligne par ligne
            while ((rl = in.readLine()) != null) {
                codeHtml.append(rl);

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

        assert codeHtml != null;
        return codeHtml.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        // result contient le JSON
        if (result == null) {
            asyncDelegate.execFinished(this, false);
        } else {
            Log.e("----------------------", "------------------------");
            try {
                JSONArray rep = new JSONArray(result);
                for (int i = 0; i < rep.length(); i++) {
                    JSONObject o = (JSONObject) rep.get(i);
                    addEntity(o);
                }
                daoDeleteRemoved();
                daoInsert();
                //Thread.sleep(1000);
                asyncDelegate.execFinished(this, true);
            } catch (JSONException e) {
                Log.e("log", "onPostExecute: ", e);
            }/* catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }

    abstract void addEntity(JSONObject o) throws JSONException;

    abstract void daoInsert();

    abstract void daoDeleteRemoved();

}