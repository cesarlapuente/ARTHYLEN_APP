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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ffscreens.arthylene.R;

/**
 * Arthylene
 * Created by Thibault Nougues on 11/07/2017.
 */

public class SheetFragment extends Fragment {

    private SheetCallback sheetCallback;
    private JSONArray detectedProductJSON;

    public static SheetFragment newInstance(boolean valid, String data) {
        SheetFragment fragment = new SheetFragment();
        Bundle args = new Bundle();
        args.putBoolean("valid", valid);
        args.putString("data", data);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sheet, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        TableLayout tableMaturity = view.findViewById(R.id.tableMaturity);
        TableLayout tableState = view.findViewById(R.id.tableState);

        Button back = view.findViewById(R.id.backSheet);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sheetCallback.onResult();
            }
        });

        if (getArguments() != null)
        {
            Log.i(this.getClass().getName(), "sheet parameter : " + getArguments());


            try
            {
                detectedProductJSON = new JSONArray(getArguments().getString("data"));
                Log.i(this.getClass().getName(), "array : " + detectedProductJSON);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }

            if(detectedProductJSON.length() > 0)
            {
                int indexMaturity = 0;
                int indexSate = 0;

                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);

                for(int i = 0; detectedProductJSON.length() > i; i++) //populate the tables
                {
                    try
                    {
                        JSONObject jsonProduct = detectedProductJSON.getJSONObject(i);
                        Log.i(this.getClass().getName(), "l'object : " + detectedProductJSON.getJSONObject(i));

                        if(jsonProduct.getInt("niveauMaturite") >= 0)
                        {
                            TableRow rowMaturity = new TableRow(getActivity());
                            rowMaturity.setLayoutParams(layoutParams);

                            TextView name = new TextView(getActivity());
                            TextView maturityLvl = new TextView(getActivity());
                            TextView maturityIdeal = new TextView(getActivity());

                            name.setText(jsonProduct.getString("nomProduit"));
                            maturityLvl.setText(jsonProduct.getString("niveauMaturite"));
                            maturityIdeal.setText(jsonProduct.getString("idMaturite"));

                            rowMaturity.addView(name);
                            rowMaturity.addView(maturityLvl);
                            rowMaturity.addView(maturityIdeal);

                            tableMaturity.addView(rowMaturity, indexMaturity);
                            indexMaturity++;
                        }

                        if(jsonProduct.getInt("niveauEtat") >= 0)
                        {
                            TableRow rowState = new TableRow(getActivity());

                            rowState.setLayoutParams(layoutParams);

                            TextView state = new TextView(getActivity());

                            state.setText(jsonProduct.getString("niveauEtat"));

                            rowState.addView(state);

                            tableState.addView(rowState, indexSate);
                            indexSate++;
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
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
