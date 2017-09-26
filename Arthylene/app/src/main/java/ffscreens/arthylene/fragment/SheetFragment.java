package ffscreens.arthylene.fragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
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

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.R;
import ffscreens.arthylene.objects.BeneficeSante;
import ffscreens.arthylene.objects.Caracteristique;
import ffscreens.arthylene.objects.Conseil;
import ffscreens.arthylene.objects.Marketing;
import ffscreens.arthylene.objects.Presentation;
import ffscreens.arthylene.objects.Produit;

import static ffscreens.arthylene.R.drawable.tableborder;

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

        Button back = view.findViewById(R.id.backSheet);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sheetCallback.onResult();
            }
        });
        List<Produit> produitList = new ArrayList<>();

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


            if(detectedProductJSON != null && detectedProductJSON.length() > 0)
            {
                for(int i = 0; detectedProductJSON.length() > i; i++)
                {
                    try
                    {
                        JSONObject jsonProduct = detectedProductJSON.getJSONObject(i);

                        Long id = jsonProduct.getLong("idProduit");
                        String nom = jsonProduct.getString("nomProduit");
                        String variete = jsonProduct.getString("varieteProduit");
                        int niveauMaturite = jsonProduct.getInt("niveauMaturite");
                        Long idmaturite = jsonProduct.getLong("idMaturite");
                        int niveauEtat = jsonProduct.getInt("niveauEtat");
                        Long idEtat = jsonProduct.getLong("idEtat");
                        Long idPresentation = jsonProduct.getLong("idPresentation");
                        Long idBeneficeSante = jsonProduct.getLong("idBeneficeSante");
                        Long idCaracteristique = jsonProduct.getLong("idCaracteristique");
                        Long idConseil = jsonProduct.getLong("idConseil");
                        Long idMarketing = jsonProduct.getLong("idMarketing");

                        Produit produit = new Produit(id, nom, variete, niveauMaturite, idmaturite, niveauEtat, idEtat, idPresentation, idBeneficeSante, idCaracteristique, idConseil, idMarketing);
                        produitList.add(produit);

                        Log.i(this.getClass().getName(), "l'object : " + produit.toString());
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }



    private void getObjectFromDB()
    {
        //insert incoming json
        //out = populateExpandableList
    }

    private void populateExpandbleList(Produit produit, Presentation presentation, Caracteristique caracteristique, BeneficeSante beneficeSante, Conseil conseil, Marketing marketing)
    {

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
