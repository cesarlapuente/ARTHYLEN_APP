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
import android.widget.ExpandableListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.R;
import ffscreens.arthylene.adapter.ProductDetailExpandableListAdapter;
import ffscreens.arthylene.database.AudioDAO;
import ffscreens.arthylene.database.BeneficeSanteDAO;
import ffscreens.arthylene.database.CaracteristiqueDAO;
import ffscreens.arthylene.database.ConseilDAO;
import ffscreens.arthylene.database.MarketingDAO;
import ffscreens.arthylene.database.PhotoDAO;
import ffscreens.arthylene.database.PresentationDAO;
import ffscreens.arthylene.objects.Audio;
import ffscreens.arthylene.objects.BeneficeSante;
import ffscreens.arthylene.objects.Caracteristique;
import ffscreens.arthylene.objects.Conseil;
import ffscreens.arthylene.objects.Marketing;
import ffscreens.arthylene.objects.Photo;
import ffscreens.arthylene.objects.Presentation;
import ffscreens.arthylene.objects.Produit;

/**
 * Arthylene
 * Created by Thibault Nougues on 11/07/2017.
 */

public class SheetFragment extends Fragment {

    private SheetCallback sheetCallback;
    private JSONArray detectedProductJSON;
    private ExpandableListView expandableFicheDetail;

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

//        Button back = view.findViewById(R.id.backSheet); //old button

        Button back = getActivity().findViewById(R.id.buttonReturn);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sheetCallback.onResult();
            }
        });

        List<Produit> produitList = new ArrayList<>();

        expandableFicheDetail = (ExpandableListView) view.findViewById(R.id.expandableFicheDetail);
        expandableFicheDetail.setGroupIndicator(null);


        if (getArguments() != null)
        {
            Log.i(this.getClass().getName(), "sheet parameter : " + getArguments());

            try
            {
                if(!getArguments().getString("data").equals("nothing"))
                    detectedProductJSON = new JSONArray(getArguments().getString("data"));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }

            //transform json to produit object and insert all produit ocjet into produitList
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
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            //search produit information's based on database
            if(produitList.size() > 0)
                getObjectFromDB(produitList);
        }
    }

    /**
     * Get all information stored in database
     * Create needed object based on id and informations
     *
     *  When all object are init, populate the expendable List
     *
     * @param produitList List of selected product in database
     */
    private void getObjectFromDB(List<Produit> produitList)
    {
        PresentationDAO presentationDAO = new PresentationDAO(getActivity());
//        EtatDAO etatDAO = new EtatDAO(getActivity());
//        MaturiteDAO maturiteDAO = new MaturiteDAO(getActivity());
        PhotoDAO photoDAO = new PhotoDAO(getActivity());
        AudioDAO audioDAO = new AudioDAO(getActivity());

        BeneficeSanteDAO beneficeSanteDAO = new BeneficeSanteDAO(getActivity());
        CaracteristiqueDAO caracteristiqueDAO = new CaracteristiqueDAO(getActivity());
        ConseilDAO conseilDAO = new ConseilDAO((getActivity()));
        MarketingDAO marketingDAO = new MarketingDAO(getActivity());


        List<Presentation> allPresentation = presentationDAO.getAllPresentations();
//        List<Etat> allEtat = etatDAO.getAllStates();
//        List<Maturite> allMaturite = maturiteDAO.getAllMaturity();
        List<Photo> allPhoto = photoDAO.getAllPicture();
        List<Audio> allAudio = audioDAO.getAllAudio();
        List<BeneficeSante> allBeneficeSante = beneficeSanteDAO.getAllBenefice();
        List<Caracteristique> allCaracteristique = caracteristiqueDAO.getAllCaracteristique();
        List<Conseil> allConseil = conseilDAO.getAllConseil();
        List<Marketing> allMarketing = marketingDAO.getAllMarketing();

        Long idPresentation = null;
//        Long idEtat = null;
//        Long idMaturite = null;
        Long idPhoto = null;
        Long idAudio = null;
        Long idBeneficeSante = null;
        Long idCaracteristique = null;
        Long idConseil = null;
        Long idMarketing = null;

        Presentation presentation = null;
//        Etat etat = null;
//        Maturite maturite = null;
        Photo photo = null;
        Audio audio = null;
        Caracteristique caracteristique = null;
        BeneficeSante beneficeSante = null;
        Conseil conseil = null;
        Marketing marketing = null;

        //get each id
        for(Produit produit : produitList)
        {
            if(produit.getIdPresentation() != null && idPresentation == null)
                idPresentation = produit.getIdPresentation();

            if(produit.getIdBeneficeSante() != null && idBeneficeSante == null)
                idBeneficeSante = produit.getIdBeneficeSante();

            if(produit.getIdCaracteristique() != null && idCaracteristique == null)
                idCaracteristique = produit.getIdCaracteristique();

            if(produit.getIdConseil() != null && idConseil == null)
                idConseil = produit.getIdConseil();

            if(produit.getIdMarketing() != null && idMarketing == null)
                idMarketing = produit.getIdMarketing();
        }

        //get each associate object
        for(Presentation pres : allPresentation)
        {
            if(pres != null && pres.getIdPresentation().equals(idPresentation) && presentation == null)
                presentation = pres;
        }

        for(Photo dbPhoto : allPhoto)
        {
            if(dbPhoto != null && dbPhoto.getIdPhoto().equals(presentation.getIdPhoto()) && photo == null)
                photo = dbPhoto;
        }

        for(Audio dbAudio : allAudio)
        {
            if(dbAudio != null && dbAudio.getIdAudio().equals(presentation.getIdAudio()) && audio == null)
                audio = dbAudio;
        }

        for(Caracteristique carac : allCaracteristique)
        {
            if(carac != null && carac.getIdCaracteristique().equals(idCaracteristique) && caracteristique == null)
                caracteristique = carac;
        }

        for(BeneficeSante bene : allBeneficeSante)
        {
            if(bene != null && bene.getIdBeneficeSante().equals(idBeneficeSante) && beneficeSante == null)
                beneficeSante = bene;
        }

        for(Conseil con : allConseil)
        {
            if(con != null && con.getIdConseil().equals(idConseil) && conseil == null)
                conseil = con;
        }

        for(Marketing marke : allMarketing)
        {
            if(marke != null && marke.getIdMarketing().equals(idMarketing) && marketing == null)
                marketing = marke;
        }

        populateExpandbleList(produitList.get(0), presentation, photo, caracteristique, beneficeSante, conseil, marketing, audio);
    }

    private void populateExpandbleList(Produit produit, Presentation presentation, Photo photo, Caracteristique caracteristique, BeneficeSante beneficeSante, Conseil conseil, Marketing marketing, Audio audio)
    {
//        Log.i(this.getClass().getName(), "produit : " + produit.toString());
//        Log.i(this.getClass().getName(), "presentation : " + presentation.toString());
//        Log.i(this.getClass().getName(), "photo : " + photo.toString());
//        Log.i(this.getClass().getName(), "caracteristique : " + caracteristique.toString());
//        Log.i(this.getClass().getName(), "beneficeSante : " + beneficeSante.toString());
//        Log.i(this.getClass().getName(), "conseil : " + conseil.toString());
//        Log.i(this.getClass().getName(), "marketing : " + marketing.toString());
//        Log.i(this.getClass().getName(), "audio : " + audio.toString());

        ProductDetailExpandableListAdapter productDetailExpandableListAdapter = new ProductDetailExpandableListAdapter(getActivity(), produit,
                presentation, photo, caracteristique, beneficeSante, conseil, marketing, audio);

        expandableFicheDetail.setAdapter(productDetailExpandableListAdapter);
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
