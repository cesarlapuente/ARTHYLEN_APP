package ffscreens.arthylene;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import ffscreens.arthylene.enumeration.EtatEnum;
import ffscreens.arthylene.fragment.PictureFragment;

public class ReceptionActivity extends Activity {

    private Button home;
    private Activity me;
    private RadioGroup radioGroup;
    private RadioButton scan;
    private RadioButton check;
    private EtatEnum etatEnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception);
        radioGroup = findViewById(R.id.rg);
        scan = findViewById(R.id.scan);
        check = findViewById(R.id.check);

        scan.setChecked(true);
        etatEnum = EtatEnum.SCAN;
        getFragmentManager().beginTransaction()
                .replace(R.id.fr, PictureFragment.newInstance(etatEnum)).commit();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fr, PictureFragment.newInstance(getEtat(id))).commit();
            }
        });

        me = this;

        home = findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                me.finish();
            }
        });
    }

    private EtatEnum getEtat(int id) {
        switch (id) {
            case R.id.check:
                return EtatEnum.CHECK;
            default:
                return EtatEnum.SCAN;
        }
    }
}
