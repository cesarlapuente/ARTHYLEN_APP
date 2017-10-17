package ffscreens.arthylene.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import ffscreens.arthylene.R;
import ffscreens.arthylene.database.PhotoDAO;
import ffscreens.arthylene.database.PresentationDAO;
import ffscreens.arthylene.objects.Photo;
import ffscreens.arthylene.objects.Presentation;
import ffscreens.arthylene.objects.Produit;
import ffscreens.arthylene.utils.ImageLoader;

public class AssistanceAdapter extends BaseAdapter
{
    private List<Produit> listProduit;
    private Context context;
    private ImageLoader imageLoader;

    public AssistanceAdapter(@NonNull Context context, @NonNull List<Produit> listProduit) {
        this.listProduit = listProduit;
        this.context = context;
        imageLoader = new ImageLoader(context);
    }

    @Override
    public int getCount() {
        return listProduit.size();
    }

    @Override
    public Object getItem(int i) {
        return listProduit.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i,  @NonNull View convertView,  @NonNull ViewGroup parent)
    {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.item_assistance, parent, false);

        RelativeLayout relativeLayout = convertView.findViewById(R.id.itemAssitance);
//        relativeLayout.setFocusable(false);

        AssitanceViewOlder assitanceViewOlder = (AssitanceViewOlder) convertView.getTag();
        if (assitanceViewOlder == null) {
            assitanceViewOlder = new AssitanceViewOlder();
            assitanceViewOlder.nomProduit = convertView.findViewById(R.id.nomProduit);
            assitanceViewOlder.varieteProduit = convertView.findViewById(R.id.varieteProduit);
            assitanceViewOlder.imageProduit = convertView.findViewById(R.id.imageProduit);

            convertView.setTag(assitanceViewOlder);
        }

        //this get the product name in the native language
        int idNomProduit = context.getResources().getIdentifier(listProduit.get(i).getNomProduit().toLowerCase(), "string", context.getPackageName());

        if(idNomProduit != 0)
            assitanceViewOlder.nomProduit.setText(context.getString(idNomProduit));
        else
            assitanceViewOlder.nomProduit.setText(listProduit.get(i).getNomProduit());

        assitanceViewOlder.varieteProduit.setText(listProduit.get(i).getVarieteProduit());

        //photo
        if(getPhoto(listProduit.get(i)).getChemin() != null)
            imageLoader.DisplayImage(getPhoto(listProduit.get(i)).getChemin(), assitanceViewOlder.imageProduit);

        return convertView;
    }

    private Photo getPhoto(Produit produit)
    {
        PresentationDAO presentationDAO = new PresentationDAO(context);
        List<Presentation> allPresentations = presentationDAO.getAllPresentations();

        PhotoDAO photoDAO = new PhotoDAO(context);
        List<Photo> allPhotos = photoDAO.getAllPicture();

        Photo photo= null;

        if(produit != null && produit.getIdPresentation() >= 1)
        {
            mainloop:
            for(Presentation pres : allPresentations)
            {
                if((pres.getIdProduit().equals(produit.getIdProduit())))
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
        }
        return photo;
    }

    private class AssitanceViewOlder {
        ImageView imageProduit;
        TextView nomProduit;
        TextView varieteProduit;
    }
}
