package a44screens.arthylene;

import android.app.Activity;
import android.os.Bundle;

import a44screens.arthylene.Api.ApiRequest;
import a44screens.arthylene.Api.EtiquetteRequest;
import a44screens.arthylene.Api.MaturiyRequest;
import a44screens.arthylene.Api.PresentationRequest;
import a44screens.arthylene.Api.ProductRequest;
import a44screens.arthylene.Api.StateRequest;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*ApiRequest task;

        for (ApiAdress aa : ApiAdress.values()){
            switch (aa){
                case product:
                    task = new ProductRequest(getApplicationContext());
                    break;
                case
            }
        }*/

        ApiRequest task = new ProductRequest(getApplicationContext());
        task.setContext(getApplicationContext());
        task.execute("http://192.168.1.114/api/product");
        task = new MaturiyRequest(getApplicationContext());
        task.setContext(getApplicationContext());
        task.execute("http://192.168.1.114/api/maturity");
        task = new StateRequest(getApplicationContext());
        task.setContext(getApplicationContext());
        task.execute("http://192.168.1.114/api/state");
        task = new PresentationRequest(getApplicationContext());
        task.setContext(getApplicationContext());
        task.execute("http://192.168.1.114/api/presentation");
        task = new EtiquetteRequest(getApplicationContext());
        task.setContext(getApplicationContext());
        task.execute("http://192.168.1.114/api/label");


        try {
            Class c = Class.forName("a44screens.arthylene.Api.ProductRequest");
            task = (ApiRequest) c.newInstance();
            task.setContext(getApplicationContext());
            task.setContext(getApplicationContext());
            task.execute("http://192.168.1.114/api/product");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
