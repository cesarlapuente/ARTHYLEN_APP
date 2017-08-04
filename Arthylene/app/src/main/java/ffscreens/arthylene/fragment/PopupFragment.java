package ffscreens.arthylene.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ffscreens.arthylene.R;

/**
 * Created by Thibault on 29/06/2017.
 */

public class PopupFragment extends Fragment {

    private PopupCallback popupCallback;

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

        final Fragment me = this;


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupCallback.popupOnResult();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fr, new SheetFragment()).commit();
            }
        });


        if (getArguments() != null) {
            Bundle args = getArguments();

            try {
                JSONArray array = new JSONArray(args.getString("popup", "default"));
                StringBuilder resBuilder = new StringBuilder();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    resBuilder.append(object.opt("value")).append(" : ").append(object.opt("confidence")).append("\n");
                }

                popup.setText(resBuilder.toString());
                bottom.setText(args.getString("bottom", "default"));
                if (args.containsKey(getString(R.string.key_valid))) {
                    if (args.getBoolean(getString(R.string.key_valid))) {
                        image.setImageResource(R.drawable.icon_ok3x);
                    } else {
                        image.setImageResource(R.drawable.icon_wrong3x);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PopupCallback) {
            popupCallback = (PopupCallback) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        popupCallback = null;
    }

    public interface PopupCallback {

        public void popupOnResult();

    }
}
