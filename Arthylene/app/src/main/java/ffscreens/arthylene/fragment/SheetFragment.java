package ffscreens.arthylene.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ffscreens.arthylene.R;

/**
 * Arthylene
 * Created by Thibault Nougues on 11/07/2017.
 */

public class SheetFragment extends Fragment {

    private SheetCallback sheetCallback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sheet, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button back = view.findViewById(R.id.backSheet);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sheetCallback.onResult();
            }
        });

//        if (getArguments() != null)
//        {
            Bundle args = getArguments();
            Log.i(this.getClass().getName(), "args : " + args);
//        }
        //todo recevoir l'objet list avec les data
        //todo parcourir l'objet et placer chaque row dans le row d'un des deux tab
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SheetCallback) {
            sheetCallback = (SheetCallback) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface SheetCallback {
        public void onResult();
    }
}
