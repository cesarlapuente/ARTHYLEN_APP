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
import ffscreens.arthylene.database.PhotoDAO;
import ffscreens.arthylene.database.PresentationDAO;
import ffscreens.arthylene.database.ProduitDAO;
import ffscreens.arthylene.objects.Photo;
import ffscreens.arthylene.objects.Presentation;
import ffscreens.arthylene.objects.Produit;
import ffscreens.arthylene.objects.ScanResult;

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

        ArrayList<ScanResult> results = new ArrayList<>();
        ScanResult result;

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupCallback.popupOnResult();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if(getDetectedProductJSON() != null)
            {
                Log.i(this.getClass().getName(), "detectedProductJSON : " + getDetectedProductJSON().toString());
                if(getDetectedProductJSON().size() > 0) popupCallback.onNextBtnClicked(true, getDetectedProductJSON().toString());
            }
            else
                popupCallback.onNextBtnClicked(false, "nothing");
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
//                    resBuilder.append("PEACH MAT5").append(" : ").append(object.opt("confidence")).append("\n");

                    //select and put the result result array
                    String name;
                    Integer mat;


                    if(!object.opt("value").toString().equals("UNKNOWN"))
                    {
                        String concatResult = object.opt("value").toString();
                        String[] separated = concatResult.split(" ");

                        name = separated[0];
//                        name = "PEACH";
                        mat = Integer.parseInt(separated[1].replaceAll("[^0-9]", ""));
                    }
                    else
                    {
                        name = "UNKNOWN";
                        mat = 0;
                    }

                    result = new ScanResult(name, mat, Float.parseFloat(object.opt("confidence").toString()));
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
        for (ScanResult detectedFruit : results)
        {
            if(detectedFruit.getConvidence() > 0.80 && !detectedFruit.getName().equals("UNKNOWN"))
                getDetectedFruitInfo(results);
        }
    }

    /**
     * looking for produit about the detected product in databases
     * @param results result of the CVC library formated in list of ScanResult
     */
    private void getDetectedFruitInfo(ArrayList<ScanResult> results)
    {
        ProduitDAO productDAO;
        productDAO = new ProduitDAO(getActivity());
        List<Produit> allProducts = productDAO.getAllProduct();

        ArrayList<Produit> detectedProduct = new ArrayList<>();

        for (ScanResult resultFruit : results)
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

        showNameAndPhoto(detectedProduct); //Once everything is done, show the name and the photo
    }

    private void showNameAndPhoto(ArrayList<Produit> detectedProduct)
    {
        PresentationDAO presentationDAO = new PresentationDAO(getActivity());
        List<Presentation> allPresentations = presentationDAO.getAllPresentations();

        PhotoDAO photoDAO = new PhotoDAO(getActivity());
        List<Photo> allPhotos = photoDAO.getAllPicture();

        Long idPhoto = null;

        //write presentation
        textViewNomProduit.setText(detectedProduct.get(0).getNomProduit());

        for(int i = 0; i < allPresentations.size(); i++)
        {
            if((allPresentations.get(i).getIdProduit().compareTo(detectedProduct.get(0).getIdProduit())) == 0)
            {
                idPhoto = allPresentations.get(i).getIdPhoto();
                break;
            }
        }

        if(idPhoto != null)
        {
            //get la view pour afficher la photo
            //get le chemin de la photo
            //affiche le chemin de la photo dans un premier temps
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

        void onNextBtnClicked(boolean valid, String result);

    }

    private JsonArray getDetectedProductJSON()
    {
        return detectedProductJSON;
    }
}
