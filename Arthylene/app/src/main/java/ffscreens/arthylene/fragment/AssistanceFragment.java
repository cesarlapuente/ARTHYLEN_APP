package ffscreens.arthylene.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.R;
import ffscreens.arthylene.adapter.AssistanceAdapter;
import ffscreens.arthylene.database.ProduitDAO;
import ffscreens.arthylene.enumeration.DetectedProduct;
import ffscreens.arthylene.objects.Produit;

public class AssistanceFragment extends Fragment
{
    private AssistanceCallback assistanceCallback;
    private List<Produit> allProducts;
    private List<Produit> availableProduct;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_assistance, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        view.getParent().requestDisallowInterceptTouchEvent(true);

        Button back = getActivity().findViewById(R.id.buttonReturn);
        back.setVisibility(View.INVISIBLE);

        assistanceCallback = (AssistanceCallback) getActivity();

        ProduitDAO produitDAO = new ProduitDAO(getActivity());
        ListView listView = view.findViewById(R.id.listAllFruits);
        allProducts = produitDAO.getAllProduct();
        availableProduct = fillAvailableProduct(allProducts);

        //set the product scannable in the list view
        AssistanceAdapter assistanceAdapter = new AssistanceAdapter(getActivity(), availableProduct);
        listView.setAdapter(assistanceAdapter);

        //On item click, format data then open sheetFragment and send selected product information
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Gson gson = new GsonBuilder().create();
                JsonArray selectedProductInfoJSON = gson.toJsonTree(getSelectedProductInfo(availableProduct.get(position))).getAsJsonArray();

                if(selectedProductInfoJSON != null)
                {
                    Log.i(this.getClass().getName(), "selectedProductInfoJSON : " + selectedProductInfoJSON.toString());

                    if(selectedProductInfoJSON.size() > 0)
                        assistanceCallback.onItemClicked(true, selectedProductInfoJSON.toString());
                    else
                        Toast.makeText(getActivity(), getActivity().getString(R.string.no_detail_available), Toast.LENGTH_LONG).show();
                }
                else
                    assistanceCallback.onItemClicked(false, "nothing");
            }
        });
    }

    //get name, variete and presentation of the product present in DetectedProduct enum
    private List<Produit> fillAvailableProduct(List<Produit> allProducts)
    {
        if(availableProduct == null)
            availableProduct = new ArrayList<>();


        for (DetectedProduct detectedProduct : DetectedProduct.values())
        {
            allProductsLoop:
            for(int i = 0; i < allProducts.size(); i++)
            {
                if(detectedProduct.toString().equalsIgnoreCase(allProducts.get(i).getNomProduit()) && allProducts.get(i).getVarieteProduit() != null && allProducts.get(i).getIdPresentation() != null)
                {
                    availableProduct.add(allProducts.get(i));
                    break allProductsLoop;
                }
            }
        }
        return availableProduct;
    }

    //get all the object from DB about the selected object
    private List<Produit> getSelectedProductInfo(Produit produit)
    {
        List<Produit> selectedProduit = new ArrayList<>();

        ProduitDAO produitDAO = new ProduitDAO(getActivity());
        List<Produit> allProducts = produitDAO.getAllProduct();

        for (Produit productFromDAO : allProducts)
        {
            if(productFromDAO.getNomProduit().equalsIgnoreCase(produit.getNomProduit()) && productFromDAO.getVarieteProduit().equalsIgnoreCase(produit.getVarieteProduit()))
                selectedProduit.add(productFromDAO);
        }

        return selectedProduit;
    }

    public interface AssistanceCallback {
        void onItemClicked(boolean valid, String result);

    }
}
