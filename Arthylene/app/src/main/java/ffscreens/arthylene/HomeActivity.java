package ffscreens.arthylene;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import ffscreens.arthylene.api.ApiRequest;
import ffscreens.arthylene.enumeration.ApiAdress;

public class HomeActivity extends Activity {

    private static final String URL = "http://192.168.1.114/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ApiRequest task;

        for (ApiAdress api : ApiAdress.values()) {
            String className = className(api);
            try {
                Class c = Class.forName(className);
                task = (ApiRequest) c.newInstance();
                task.setContext(getApplicationContext());
                task.setContext(getApplicationContext());
                task.execute(URL + api.toString());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                Log.e("log", "onCreate: ", e);
            }
        }

    }

    private String className(ApiAdress api) {
        return "ffscreens.arthylene.api." + api.toString().substring(0, 1).toUpperCase() + api.toString().substring(1) + "Request";
    }
}
