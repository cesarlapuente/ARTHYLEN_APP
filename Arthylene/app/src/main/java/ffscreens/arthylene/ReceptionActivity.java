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
import ffscreens.arthylene.fragment.PopupFragment;
import ffscreens.arthylene.fragment.SheetFragment;

/**
 * Arthylene
 * Created by Thibault Nougues on 29/06/2017.
 */


public class ReceptionActivity extends Activity implements PictureFragment.PictureFragmentCallback, SheetFragment.SheetCallback, PopupFragment.PopupCallback {

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
                .replace(R.id.frameLayout, PictureFragment.newInstance(etatEnum)).commit(); //fr = frameLayout

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, PictureFragment.newInstance(getEtat(id))).commit();
            }
        });

        me = this; //ReceptionActivity

        home = findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                me.finish(); //Stop ReceptionActivity; HomeActivity is still running in background
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

    @Override
    public void onPictureResult(boolean valid, String result) {
        switch (etatEnum) {
            case CONDITION:
                getFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, PopupFragment.newInstance(true, "Check condition detail", "Product info sheet")).commit();
                break;
            case PRESENTATION:
                getFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, PopupFragment.newInstance(false, "Check presentation detail", "Detail")).commit();
                break;
            default:
                getFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, new PopupFragment()).commit();
                break;
        }
    }

    private void showPictureFragment(EtatEnum etatEnum) {
        this.etatEnum = etatEnum;
        getFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, PictureFragment.newInstance(etatEnum)).commit();
    }


    @Override
    public void onResult() {
        showPictureFragment(etatEnum);
    }

    @Override
    public void popupOnResult() {
        showPictureFragment(etatEnum);
    }
}
