package ffscreens.arthylene.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ffscreens.arthylene.R;

/**
 * Created by Thibault on 29/06/2017.
 */

public class PopupFragment extends Fragment {

    public static PopupFragment newInstance(boolean valid, String popup, String bottom) {
        PopupFragment fragment = new PopupFragment();
        Bundle args = new Bundle();
        args.putBoolean("valid", valid);
        args.putString("popup", popup);
        args.putString("bottom", bottom);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_popup, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView image = view.findViewById(R.id.imageView2);
        TextView popup = view.findViewById(R.id.textPopup);
        TextView bottom = view.findViewById(R.id.textBottom);
        Button back = view.findViewById(R.id.back);
        Button next = view.findViewById(R.id.next);


        if (getArguments() != null) {
            Bundle args = getArguments();
            popup.setText(args.getString("popup", "default"));
            bottom.setText(args.getString("bottom", "default"));
            if (args.containsKey(getString(R.string.key_valid))) {
                if (args.getBoolean(getString(R.string.key_valid))) {
                    image.setImageResource(R.drawable.icon_ok3x);
                } else {
                    image.setImageResource(R.drawable.icon_wrong3x);
                }
            }
        }


    }


}
