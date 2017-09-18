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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.R;
import ffscreens.arthylene.database.PresentationDAO;
import ffscreens.arthylene.database.ProduitDAO;
import ffscreens.arthylene.objects.Presentation;
import ffscreens.arthylene.objects.Produit;

/**
 * Arthylene
 * Created by Thibault Nougues on 29/06/2017.
 */

public class PopupFragment extends Fragment {

    private PopupCallback popupCallback;
    private TextView textViewNomProduit, textViewDescriptionProduit;
    private  JsonArray detectedProductJSON;

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

        textViewNomProduit = view.findViewById(R.id.textViewNomProduit);
        textViewDescriptionProduit = view.findViewById(R.id.textViewDescriptionProduit);

        Button back = view.findViewById(R.id.back);
        Button next = view.findViewById(R.id.next);

        final Fragment me = this;

        popupCallback = (PopupCallback) getActivity();

        ArrayList<Result> results = new ArrayList<>();
        Result result;

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupCallback.popupOnResult();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i(this.getClass().getName(), "detectedProductJSON : " + getDetectedProductJSON().toString());

                if(getDetectedProductJSON().size() > 0)
                    popupCallback.onNextBtnClicked(true, getDetectedProductJSON().toString());
//todo handle else statement
//                else
//                    popupCallback.onNextBtnClicked(false, "nothing");
            }
        });

        //text on the top of fragment
        if (getArguments() != null)
        {
            Bundle args = getArguments(); //data about the scan
            Log.i(this.getClass().getName(), "args : " + args);

            try {
                JSONArray array = new JSONArray(args.getString("popup", "default"));
                StringBuilder resBuilder = new StringBuilder();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    resBuilder.append(object.opt("value")).append(" : ").append(object.opt("confidence")).append("\n");

                    //select and put the result result array
                    String name;
                    Integer mat;

                    if(!object.opt("value").toString().equals("UNKNOWN"))
                    {
                        String concatResult = object.opt("value").toString();
                        String[] separated = concatResult.split(" ");

                        name = separated[0];
                        mat = Integer.parseInt(separated[1].replaceAll("[^0-9]", ""));
                    }
                    else
                    {
                        name = "UNKNOWN";
                        mat = 0;
                    }

                    result = new Result(name, mat, Float.parseFloat(object.opt("confidence").toString()));
                    results.add(result);
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

        //checking the detected fruit
        for (Result detectedFruit : results)
        {
            if(detectedFruit.getConvidence() > 0.80 && !detectedFruit.getName().equals("UNKNOWN"))
                getDetectedFruitInfo(results);
        }
    }

    //looking for informations about the detected product in databases.
    //get all informations and after use them, optimization = get only useful informations
    private void getDetectedFruitInfo(ArrayList<Result> results)
    {
        ProduitDAO productDAO;
        productDAO = new ProduitDAO(getActivity());
        List<Produit> allProducts = productDAO.getAllProduct();

        ArrayList<Produit> detectedProduct = new ArrayList<>();
//        detectedProductJSON = new JSONArray();

        PresentationDAO presentationDAO;
        presentationDAO = new PresentationDAO(getActivity());
        List<Presentation> allPresentations = presentationDAO.getAllPresentations();

        for (Result resultFruit : results)
        {
            if(resultFruit.getConvidence() > 0.80 && !resultFruit.getName().equals("UNKNOWN"))
            {
                Log.i(this.getClass().getName(), "Fruit : " + resultFruit.getName() + " - Maturity : " + resultFruit.getMaturity() + " - Convidence : " + resultFruit.getConvidence());

                for(int i = 0; i < allProducts.size(); i++)
                {
                    if(resultFruit.getName().equalsIgnoreCase(allProducts.get(i).getNomProduit()))
                    {
                        detectedProduct.add(allProducts.get(i));
                    }
                }
            }
        }

        Gson gson = new GsonBuilder().create();
        detectedProductJSON = gson.toJsonTree(detectedProduct).getAsJsonArray();


        //write presentation
        textViewNomProduit.setText(detectedProduct.get(0).getNomProduit());
        for(int j = 0; j < allPresentations.size(); j++)
        {
            if((allPresentations.get(j).getIdProduit().compareTo(detectedProduct.get(0).getIdProduit())) == 0)
            {
                textViewDescriptionProduit.setText(allPresentations.get(j).getContenu());
                break;
            }
        }

//        for(int j = 0;j < detectedProduct.size(); j++)
//        {
//            Log.i(this.getClass().getName(), "id product : " + detectedProduct.get(j).getIdProduit().toString());
//        }
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

        void onNextBtnClicked(boolean valid, String result);

    }

    private JsonArray getDetectedProductJSON()
    {
        return detectedProductJSON;
    }
}

class Result //can be replace by the Produit object. But need to create another constructor
{
    //todo better implement this
    private final String name;
    private final Float convidence;
    private final Integer maturity;

    public Result(String name, Integer maturity, Float convidence)
    {
        this.name = name;
        this.maturity = maturity;
        this.convidence = convidence;
    }

    public String getName()
    {
     return name;
    }

    public Integer getMaturity()
    {
    return maturity;
    }

    public Float getConvidence()
    {
        return convidence;
    }
}
