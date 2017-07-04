package ffscreens.arthylene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import ffscreens.arthylene.api.ApiRequest;
import ffscreens.arthylene.api.AsyncDelegate;
import ffscreens.arthylene.enumeration.ApiAdress;

public class LoadActivity extends Activity implements AsyncDelegate {


    private static final String URL = "http://192.168.1.114/api/";

    private ProgressBar progressBar;
    private int progress;
    private int compteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        progress = 0;
        compteur = 0;

        progressBar = findViewById(R.id.progressBar);

        ApiRequest task;

        if (isOnline()) {
            for (ApiAdress api : ApiAdress.values()) {
                String className = className(api);
                try {
                    Class c = Class.forName(className);
                    task = (ApiRequest) c.newInstance();
                    task.setAsyncDelegate(this);
                    task.setContext(getApplicationContext());
                    task.setContext(getApplicationContext());
                    task.execute(URL + api.toString());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    Log.e("log", "onCreate: ", e);
                }
            }
        } else
            Log.e("+++", "hors ligne");
    }

    @Override
    public void execFinished(ApiRequest apiRequest, boolean done) {
        if (done) {
            compteur++;
            progress += 100 / ApiAdress.values().length;
            progressBar.setProgress(progress);
            if (compteur == ApiAdress.values().length)
                startActivity(new Intent(LoadActivity.this, HomeActivity.class));
        } else {
            Toast.makeText(this, "connection fail", Toast.LENGTH_SHORT).show();
        }
    }

    private String className(ApiAdress api) {
        return "ffscreens.arthylene.api." + api.toString().substring(0, 1).toUpperCase() + api.toString().substring(1) + "Request";
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
