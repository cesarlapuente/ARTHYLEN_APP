package ffscreens.arthylene;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.uab.cvc.scanfruits.ScanFruitsSDK;
import ffscreens.arthylene.api.ApiRequest;
import ffscreens.arthylene.api.AsyncDelegate;
import ffscreens.arthylene.enumeration.ApiAdress;

/**
 * Arthylene
 * Created by Thibault Nougues on 21/06/2017.
 */

public class LoadActivity extends Activity implements AsyncDelegate {

    /* code for checkPermission */
    private int permissionCode = 1414;


    private ProgressBar progressBar;
    private int progress;
    private int compteur;
    private boolean done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        progress = 20;
        compteur = 0;
        done = true;

        progressBar = findViewById(R.id.progressBar);
        progressBar.getProgressDrawable().setColorFilter(ResourcesCompat.getColor(
                getApplicationContext().getResources(), R.color.load_color, null),
                android.graphics.PorterDuff.Mode.SRC_IN);

        checkPermissions(permissionCode);

    }

    public void init() {

        LoadCvc loadCvc = new LoadCvc();
        loadCvc.execute(1);

        /* download Json from website and fill the local database */
        ApiRequest task;

        if (isOnline()) {
            /* load all class for api request */
            for (ApiAdress api : ApiAdress.values()) {
                String className = className(api);
                try {
                    Class c = Class.forName(className);
                    task = (ApiRequest) c.newInstance();
                    task.setAsyncDelegate(this);
                    task.setContext(getApplicationContext());
                    task.setContext(getApplicationContext());
                    task.execute(getString(R.string.url_website) + api.toString());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    Log.e("log", "onCreate: ", e);
                }
            }
        } else {
            Toast.makeText(this, getString(R.string.network_fail), Toast.LENGTH_LONG).show();
            startActivity(new Intent(LoadActivity.this, HomeActivity.class));
        }
        /*  */
    }

    /* response of each api request */
    @Override
    public void execFinished(ApiRequest apiRequest, boolean done) {
        this.done &= done;
        compteur++;
        progress += 80 / ApiAdress.values().length;
        progressBar.setProgress(progress);
        if (compteur == ApiAdress.values().length) {
            if (!this.done) { //if any error on http request
                Toast.makeText(this, getString(R.string.connection_fail), Toast.LENGTH_SHORT).show();
            }
            startActivity(new Intent(LoadActivity.this, HomeActivity.class));
            finish();
        }
    }

    /* create the name of the class with the enumeration value */
    private String className(ApiAdress api) {
        return "ffscreens.arthylene.api." + api.toString().substring(0, 1).toUpperCase() + api.toString().substring(1) + "Request";
    }
    /*  */

    /* check if the network is enable on the device */
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    /*  */

    /* check the permission */
    private void checkPermissions(int code) {
        String[] permissionsRequired = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_NETWORK_STATE};
        List permissionsNotGrantedList = new ArrayList<>();
        for (String permission : permissionsRequired) {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsNotGrantedList.add(permission);
            }
        }
        if (!permissionsNotGrantedList.isEmpty()) {
            String[] permissions = new String[permissionsNotGrantedList.size()];
            permissionsNotGrantedList.toArray(permissions);
            ActivityCompat.requestPermissions(this, permissions, code);
        } else {
            init();
        }
    }
    /*  */

    /* result of the permission check */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == permissionCode) {
            boolean ok = true;
            for (int grantResult : grantResults) {
                ok = ok && (grantResult == PackageManager.PERMISSION_GRANTED);
            }
            if (ok) {
                init();
            } else {
                Toast.makeText(this, "Error: required permissions not granted!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
    /* */

    private class LoadCvc extends AsyncTask {

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            progressBar.setProgress((Integer) values[0]);
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            /* config CVC framework */
            String pathConfig = Environment.getExternalStorageDirectory().getPath() +
                    getApplicationContext().getString(R.string.pathCvc);

//            System.out.println("path :: "+pathConfig);
            Log.i(this.getClass().getName(), "path : " + pathConfig);

            publishProgress(10);
            ScanFruitsSDK.processCreate(pathConfig);
            ScanFruitsSDK.processSetNumThreads(2);
            ScanFruitsSDK.processSetTopK(3);
            ScanFruitsSDK.processSetWhiteBalance(1.080, 1.000, 0.900); //best on work

//            ScanFruitsSDK.processSetWhiteBalance(0.985, 1.000, 1.130); //test
            publishProgress(20);
        /*  */
            return 1;
        }
    }
    /*  */
}
