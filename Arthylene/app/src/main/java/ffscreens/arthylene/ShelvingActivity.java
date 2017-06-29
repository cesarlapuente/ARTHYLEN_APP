package ffscreens.arthylene;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import ffscreens.arthylene.enumeration.EtatEnum;
import ffscreens.arthylene.fragment.CheckListFragment;
import ffscreens.arthylene.fragment.PictureFragment;

public class ShelvingActivity extends Activity {

    private Button home;
    private Activity me;
    private RadioGroup rg;
    private RadioButton assistance;
    private RadioButton condition;
    private RadioButton presentation;
    private RadioButton checklist;
    private RadioButton placement;
    private EtatEnum etatEnum;

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
        etatEnum = EtatEnum.ASSISTANCE;
        getFragmentManager().beginTransaction()
                .replace(R.id.fr, PictureFragment.newInstance(etatEnum)).commit();



        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                switch (id) {
                    case R.id.assistance:
                    case R.id.condition:
                    case R.id.presentation:
                        etatEnum = getEtat(id);
                        getFragmentManager().beginTransaction()
                                .replace(R.id.fr, PictureFragment.newInstance(getEtat(id))).commit();
                        break;
                    case R.id.checklist:
                        etatEnum = EtatEnum.CHECKLIST;
                        getFragmentManager().beginTransaction()
                                .replace(R.id.fr, new CheckListFragment()).commit();
                        break;
                    default:
                        break;
                }


                Log.e("+++", String.valueOf(radioGroup.getCheckedRadioButtonId() + " " + R.id.condition));
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
            case R.id.assistance:
                return EtatEnum.ASSISTANCE;
            case R.id.condition:
                return EtatEnum.CONDITION;
            case R.id.presentation:
                return EtatEnum.PRESENTATION;
            default:
                return EtatEnum.ASSISTANCE;
        }
    }
}
