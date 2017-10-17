package ffscreens.arthylene.fragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.R;
import ffscreens.arthylene.api.DownloadImageRequest;
import ffscreens.arthylene.database.PhotoDAO;
import ffscreens.arthylene.database.PresentationDAO;
import ffscreens.arthylene.database.ProduitDAO;
import ffscreens.arthylene.objects.Photo;
import ffscreens.arthylene.objects.Presentation;
import ffscreens.arthylene.objects.Produit;
import ffscreens.arthylene.objects.ScanResult;
import ffscreens.arthylene.utils.ResultFormat;

/**
 * Arthylene
 * Created by Thibault Nougues on 29/06/2017.
 */

public class PopupFragment extends Fragment implements DownloadImageRequest.Listener {

    private PopupCallback popupCallback;
    private TextView textViewDescriptionProduit;
    private ImageView imageViewProduct;
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
        imageViewProduct = view.findViewById(R.id.imageViewProduct);

        TextView popup = view.findViewById(R.id.textPopup);
//        TextView bottom = view.findViewById(R.id.textBottom);

        textViewDescriptionProduit = view.findViewById(R.id.textViewDescriptionProduit);

//        Button back = view.findViewById(R.id.back); //old button
        Button back = getActivity().findViewById(R.id.buttonReturn);
        back.setVisibility(View.VISIBLE);
        Button next = view.findViewById(R.id.next);

        popupCallback = (PopupCallback) getActivity();

        ArrayList<ScanResult> results = new ArrayList<>();
        ScanResult result;

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupCallback.popupOnResult();
            }
        });

        if (getArguments() != null)
        {
            Bundle args = getArguments(); //get data about the scan
            Log.i(this.getClass().getName(), "args : " + args);

            //format the string from CVC lib to an ScanResult array
            results = ResultFormat.stringToScanResultArray(args.getString("popup", "default"));
        }

        StringBuilder allScanResult = new StringBuilder();
        int idNomProduit;

        //checking the detected fruit and set listener on next button + background image and precise name
        for (ScanResult detectedFruit : results)
        {
            idNomProduit = getActivity().getResources().getIdentifier(detectedFruit.getName().toLowerCase(), "string", getActivity().getPackageName());
            if(idNomProduit != 0)
                allScanResult.append(getActivity().getString(idNomProduit) + " " + getActivity().getString(R.string.maturity) + " " + detectedFruit.getMaturity() + " : " + ((int)(detectedFruit.getConvidence() * 100)) + "%" + "\n");
            else
                allScanResult.append(detectedFruit.getName() + " " + getActivity().getString(R.string.maturity) + " " + detectedFruit.getMaturity() + " : " + ((int)(detectedFruit.getConvidence() * 100)) + "%" + "\n");

            //classic entry
            if(detectedFruit.getConvidence() > 0.80 && !detectedFruit.getName().equals("UNKNOWN"))
            {
                //write precise product name
                idNomProduit = getActivity().getResources().getIdentifier(detectedFruit.getName().toLowerCase(), "string", getActivity().getPackageName());

                if(idNomProduit != 0)
                    popup.setText(getActivity().getString(idNomProduit) + " " + getActivity().getString(R.string.maturity) + " " + detectedFruit.getMaturity() + " : " + ((int)(detectedFruit.getConvidence() * 100)) + "% " + "\n");
                else
                    popup.setText(detectedFruit.getName() + " " + getActivity().getString(R.string.maturity) + " " + detectedFruit.getMaturity() + " : " + ((int)(detectedFruit.getConvidence() * 100)) + "% " + "\n");


                //get fruit info and populate detectedProductJSON
                getDetectedFruitInfo(results);

                //set ok image
                image.setImageResource(R.drawable.icon_ok3x);

                //set onclick
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(getDetectedProductJSON() != null)
                        {
                            Log.i(this.getClass().getName(), "detectedProductJSON : " + getDetectedProductJSON().toString());

                            if(getDetectedProductJSON().size() > 0)
                                popupCallback.onNextBtnClicked(true, getDetectedProductJSON().toString());
                            else
                                Toast.makeText(getActivity(), getActivity().getString(R.string.no_detail_available), Toast.LENGTH_LONG).show();
                        }
                        else
                            popupCallback.onNextBtnClicked(false, "nothing");
                    }
                });
            }

            //if the product is unknown
            else if (detectedFruit.getConvidence() > 0.80 && detectedFruit.getName().equals("UNKNOWN"))
            {
                //write precise product name
                popup.setText(detectedFruit.getName() + " : " + ((int)(detectedFruit.getConvidence() * 100)) + "%");

                //set image and text about unknown product
                image.setImageResource(R.drawable.icon_wrong3x);
                textViewDescriptionProduit.setText("Product unknown");

                //set onclick
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), getActivity().getString(R.string.no_detail_available), Toast.LENGTH_LONG).show();
                    }
                });
            }

            //if the most convidence product is between 0.80 and 0.51
            else if(detectedFruit.getConvidence() < 0.80 && detectedFruit.getConvidence() > 0.51)
            {
                //show all result
//                if(popup.getText().length() == 0)
//                    popup.setText(allScanResult);
                //set image and text about unknown product
                image.setImageResource(R.drawable.icon_wrong3x);
                textViewDescriptionProduit.setText("Confidence too low");

                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), getActivity().getString(R.string.no_detail_available), Toast.LENGTH_LONG).show();
                    }
                });
            }
            else if (detectedFruit.getConvidence() < 0.50 && popup.getText().length() == 0)
            {
                //If all less than 50% show the correct image
                image.setImageResource(R.drawable.icon_wrong3x);
                textViewDescriptionProduit.setText("Confidence too low");

                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), getActivity().getString(R.string.no_detail_available), Toast.LENGTH_LONG).show();
                    }
                });
            }
        }

        //we never know
        if(popup.getText().length() == 0)
            popup.setText(allScanResult);
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

        if(!detectedProduct.isEmpty())
            showPhoto(detectedProduct); //Once everything is done, show the photo
        else
            textViewDescriptionProduit.setText("Product detected but not available in database");

        Gson gson = new GsonBuilder().create();
        detectedProductJSON = gson.toJsonTree(detectedProduct).getAsJsonArray();

    }

    private void showPhoto(ArrayList<Produit> detectedProduct)
    {
        PresentationDAO presentationDAO = new PresentationDAO(getActivity());
        List<Presentation> allPresentations = presentationDAO.getAllPresentations();

        PhotoDAO photoDAO = new PhotoDAO(getActivity());
        List<Photo> allPhotos = photoDAO.getAllPicture();

        Photo photo= null;

        if(!detectedProduct.isEmpty())
        {
            mainloop:
            for(Presentation pres : allPresentations)
            {
                if((pres.getIdProduit().equals(detectedProduct.get(0).getIdProduit())))
                {
                    for(Photo dbPhoto : allPhotos)
                    {
                        if(dbPhoto.getIdPhoto().equals(pres.getIdPhoto()))
                        {
                            photo = dbPhoto;
                            break mainloop;
                        }
                    }
                }
            }
            if(photo.getChemin() != null)
                new DownloadImageRequest(this).execute(photo.getChemin());
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

    @Override
    public void onImageLoaded(Bitmap bitmap)
    {
        imageViewProduct.setImageBitmap(bitmap);
    }

    @Override
    public void onError() {
        Log.e(this.getClass().getName(), "Error on load image");
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
