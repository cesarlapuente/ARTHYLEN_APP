package ffscreens.arthylene;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ReceptionActivity extends Activity {

    private Button home;
    private Activity me;
    private RadioGroup rg;
    private RadioButton scan;
    private RadioButton check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception);
        rg = findViewById(R.id.rg);
        scan = findViewById(R.id.scan);
        check = findViewById(R.id.check);

        scan.setChecked(true);


        me = this;

        home = findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                me.finish();
            }
        });
    }
}
