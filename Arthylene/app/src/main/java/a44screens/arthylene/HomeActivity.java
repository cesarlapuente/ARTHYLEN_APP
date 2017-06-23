package a44screens.arthylene;

import android.app.Activity;
import android.os.Bundle;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ApiRequest task = new ApiRequest();
        task.setContext(getApplicationContext());
        task.execute("http://192.168.1.114/api/product");

    }
}
