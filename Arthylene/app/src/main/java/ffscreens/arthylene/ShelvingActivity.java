package ffscreens.arthylene;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ShelvingActivity extends Activity {

    private Button home;
    private Activity me;
    private RadioGroup rg;
    private RadioButton assistance;
    private RadioButton condition;
    private RadioButton presentation;
    private RadioButton checklist;
    private RadioButton placement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelving);
        rg = findViewById(R.id.rg);
        assistance = findViewById(R.id.assistance);
        condition = findViewById(R.id.condition);
        presentation = findViewById(R.id.presentation);
        checklist = findViewById(R.id.checklist);
        placement = findViewById(R.id.placement);

        assistance.setChecked(true);


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
