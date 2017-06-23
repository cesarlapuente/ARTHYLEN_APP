package a44screens.arthylene;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import a44screens.arthylene.DataBase.ProduitDAO;
import a44screens.arthylene.Objects.Produit;

/**
 * Created by Thibault on 21/06/2017.
 */

public class ApiRequest extends AsyncTask<String, Void, String> {

    Context context;
    ProduitDAO dao;


    public void setContext(Context context) {
        Log.e("/*/*/*/*/*", "/*/*/*/*/*/*/");
        this.context = context;
        this.dao = new ProduitDAO(context);
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
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return codeHtml;
    }

    @Override
    protected void onPostExecute(String result) {
        // result contient le code html

        /*String t[] = result.split(",");
        for(String s : t){
            Log.e( "--> ", s );
        }*/
        try {
            JSONArray rep = new JSONArray(result);
            List<Produit> produits = new ArrayList<>();
            for (int i = 0; i < rep.length(); i++) {
                JSONObject o = (JSONObject) rep.get(i);
                int niveauMaturite;
                Long idMaturite;
                int niveauEtat;
                Long idEtat;

                if (!o.getString("niveauMaturite").equals("null"))
                    niveauMaturite = o.getInt("niveauMaturite");
                else
                    niveauMaturite = -1;

                if (!o.getString("idMaturite").equals("null"))
                    idMaturite = o.getLong("idMaturite");
                else
                    idMaturite = -1L;

                if (o.getString("niveauEtat") != "null")
                    niveauEtat = o.getInt("niveauEtat");
                else
                    niveauEtat = -1;

                if (o.getString("idEtat") != "null")
                    idEtat = o.getLong("idEtat");
                else
                    idEtat = -1L;

                Produit p = new Produit(o.getLong("idProduit"), o.getString("nomProduit"),
                        o.getString("varieteProduit"), niveauMaturite,
                        idMaturite, niveauEtat,
                        idEtat, o.getLong("idPresentation"));
                produits.add(p);
            }
            dao.insertListProduit(produits);
            List<Produit> p2 = dao.getAllProduct();
            for (Produit p1 : p2) {
                Log.e("thib", p1.getNomProduit());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}