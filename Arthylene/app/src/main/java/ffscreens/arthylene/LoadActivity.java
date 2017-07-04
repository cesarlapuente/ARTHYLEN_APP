package ffscreens.arthylene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
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
    private boolean done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        progress = 0;
        compteur = 0;
        done = true;

        progressBar = findViewById(R.id.progressBar);
        progressBar.getProgressDrawable().setColorFilter(ResourcesCompat.getColor(
                getApplicationContext().getResources(), R.color.load_color, null),
                android.graphics.PorterDuff.Mode.SRC_IN);


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
        } else {
            Toast.makeText(this, getString(R.string.network_fail), Toast.LENGTH_LONG).show();
            startActivity(new Intent(LoadActivity.this, HomeActivity.class));
        }
    }

    @Override
    public void execFinished(ApiRequest apiRequest, boolean done) {
        this.done &= done;
        compteur++;
        progress += 100 / ApiAdress.values().length;
        progressBar.setProgress(progress);
        if (compteur == ApiAdress.values().length) {
            if (!this.done) {
                Toast.makeText(this, getString(R.string.connection_fail), Toast.LENGTH_SHORT).show();
            }
            startActivity(new Intent(LoadActivity.this, HomeActivity.class));
            finish();
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
