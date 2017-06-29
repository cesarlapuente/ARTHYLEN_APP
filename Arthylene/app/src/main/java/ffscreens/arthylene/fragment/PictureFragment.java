package ffscreens.arthylene.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ffscreens.arthylene.R;
import ffscreens.arthylene.enumeration.EtatEnum;

/**
 * Created by Thibault on 29/06/2017.
 */

public class PictureFragment extends Fragment {


    private EtatEnum etat;

    public static PictureFragment newInstance(EtatEnum etatEnum) {
        PictureFragment fragment = new PictureFragment();
        Bundle args = new Bundle();
        args.putSerializable("EtatEnum", etatEnum);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_picture, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            Bundle args = getArguments();
            if (args.containsKey(getString(R.string.etat))) {
                etat = (EtatEnum) args.get(getString(R.string.etat));
                Toast.makeText(getActivity(), etat.toString(), Toast.LENGTH_SHORT).show();
            }
        }


    }
}
