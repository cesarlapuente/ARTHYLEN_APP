package ffscreens.arthylene;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import ffscreens.arthylene.enumeration.EtatEnum;
import ffscreens.arthylene.fragment.CheckListFragment;
import ffscreens.arthylene.fragment.PictureFragment;
import ffscreens.arthylene.fragment.PlacementFragment;
import ffscreens.arthylene.fragment.PopupFragment;
import ffscreens.arthylene.fragment.SheetFragment;

/**
 * Arthylene
 * Created by Thibault Nougues
 */

public class ShelvingActivity extends Activity
        implements
        PictureFragment.PictureFragmentCallback,
        PopupFragment.PopupCallback,
        SheetFragment.SheetCallback {

    private Button home;
    private Activity activity;
    private RadioGroup radioGroup;
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
        activity = this;

        /* set buttons */
        home = findViewById(R.id.home);
        radioGroup = findViewById(R.id.rg);
        assistance = findViewById(R.id.assistance);
        condition = findViewById(R.id.condition);
//        presentation = findViewById(R.id.presentation);
        checklist = findViewById(R.id.checklist);
        placement = findViewById(R.id.placement);

        /* set the initial state */
        assistance.setChecked(true);
        etatEnum = EtatEnum.ASSISTANCE;
        getFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, PictureFragment.newInstance(etatEnum)).commit();


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                switch (id) {
                    case R.id.assistance:
                    case R.id.condition:
//                    case R.id.presentation:
                        showPictureFragment(getEtat(id));
                        break;
                    case R.id.checklist:
                        etatEnum = EtatEnum.CHECKLIST;
                        getFragmentManager().beginTransaction()
                                .replace(R.id.frameLayout, new CheckListFragment()).commit();
                        break;
                    case R.id.placement:
                        getFragmentManager().beginTransaction()
                                .replace(R.id.frameLayout, new PlacementFragment()).commit();
                        break;
                    default:
                        break;
                }
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
    }

    /* open the picture fragment whith the actual state of the app */
    private void showPictureFragment(EtatEnum etatEnum) {
        this.etatEnum = etatEnum;
        getFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, PictureFragment.newInstance(etatEnum)).commit();
    }

    /* return the state assigned to this button */
    private EtatEnum getEtat(int id) {
        switch (id) {
            case R.id.assistance:
                return EtatEnum.ASSISTANCE;
            case R.id.condition:
                return EtatEnum.CONDITION;
//            case R.id.presentation:
//                return EtatEnum.PRESENTATION;
            default:
                return EtatEnum.ASSISTANCE;
        }
    }

    /* interface of PictureFragment
     *
     * open the popupFragment with the result of the recognition
     *
     * To start PopupFragment use PopupFragment.newInstance(boolean valid, String popup message, String option)
     *
     */
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
                        .replace(R.id.frameLayout, PopupFragment.newInstance(true, result, "Detail")).commit();
                break;
        }
    }

    /* re-open the pictureFragment */
    @Override
    public void popupOnResult() {
        showPictureFragment(etatEnum);
    }

    @Override
    public void onNextBtnClicked(boolean valid, String result)
    {
        getFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, SheetFragment.newInstance(true, result)).commit();
    }

    @Override
    public void onResult() {
        showPictureFragment(etatEnum);
    }
}
