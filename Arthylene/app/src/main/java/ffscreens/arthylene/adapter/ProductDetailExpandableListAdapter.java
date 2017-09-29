package ffscreens.arthylene.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import ffscreens.arthylene.R;
import ffscreens.arthylene.enumeration.InfoEnum;
import ffscreens.arthylene.objects.BeneficeSante;
import ffscreens.arthylene.objects.Caracteristique;
import ffscreens.arthylene.objects.Conseil;
import ffscreens.arthylene.objects.Group;
import ffscreens.arthylene.objects.Marketing;
import ffscreens.arthylene.objects.Presentation;
import ffscreens.arthylene.objects.Produit;

import static android.R.drawable.arrow_down_float;
import static android.R.drawable.arrow_up_float;
import static ffscreens.arthylene.R.color.load_color;
import static ffscreens.arthylene.R.drawable.avocado;

public class ProductDetailExpandableListAdapter extends BaseExpandableListAdapter
{
    private Activity activity;
    private Produit produit;
    private Presentation presentation;
    private Caracteristique caracteristique;
    private BeneficeSante beneficeSante;
    private Conseil conseil;
    private Marketing marketing;

    private LayoutInflater inflater;

    private SparseArray<Group> groups;

    private TableRow.LayoutParams layoutParams;

    public ProductDetailExpandableListAdapter(Activity activity, Produit produit, Presentation presentation,
                                              Caracteristique caracteristique, BeneficeSante beneficeSante, Conseil conseil, Marketing marketing)
    {
        this.activity = activity;
        this.produit = produit;
        this.presentation = presentation;
        this.caracteristique = caracteristique;
        this.beneficeSante = beneficeSante;
        this.conseil = conseil;
        this.marketing = marketing;

        groups = new SparseArray<Group>();

        for (InfoEnum info : InfoEnum.values())
        {
            Group group = new Group(info.name());
            group.children.add(info.name());
            groups.append(info.ordinal(), group);
        }

        inflater = activity.getLayoutInflater();

        layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(10, 10, 10, 10);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).children.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_header, parent, false);
        }
        Group group = (Group) getGroup(groupPosition);
//        ((CheckedTextView) convertView).setText(group.string);

        if(groupPosition == 0)
        {
            convertView = inflater.inflate(R.layout.list_first_item, parent, false);
            convertView.setClickable(true);
            convertView.setFocusable(false);

            ImageView imageViewPicture = (ImageView) convertView.findViewById(R.id.imageViewPicture);
            imageViewPicture.setImageResource(avocado);

            TextView textViewProductName = (TextView) convertView.findViewById(R.id.textViewProductName);
            textViewProductName.setText("Avocado");
            textViewProductName.setTextColor(activity.getResources().getColor(load_color));
        }
        else
        {
            convertView = inflater.inflate(R.layout.list_header, parent, false);

            TextView header_text = (TextView) convertView.findViewById(R.id.header);
            String groupeTitle = group.string.substring(0,1).toUpperCase() + group.string.substring(1);
            header_text.setText(groupeTitle);
            header_text.setTextColor(Color.BLACK);

            if (isExpanded)
            {
//                header_text.setTypeface(null, Typeface.BOLD);
                header_text.setCompoundDrawablesWithIntrinsicBounds(0, 0,arrow_up_float, 0);

            } else
            {
//                header_text.setTypeface(null, Typeface.NORMAL);
                header_text.setCompoundDrawablesWithIntrinsicBounds(0, 0, arrow_down_float, 0);

            }
        }


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        convertView = inflater.inflate(R.layout.details_table, parent, false);


//        if(groupPosition == InfoEnum.presentation.ordinal())
//        {
//
//        }
        if(groupPosition == InfoEnum.caracteristique.ordinal())
        {
            TextView famille = convertView.findViewById(R.id.textViewTable1);
            TextView contenuFamille = convertView.findViewById(R.id.textViewTable2);
            TextView espece = convertView.findViewById(R.id.textViewTable3);
            TextView contenuEspece = convertView.findViewById(R.id.textViewTable4);
            TextView taillePoids = convertView.findViewById(R.id.textViewTable5);
            TextView contenuTaillePoids = convertView.findViewById(R.id.textViewTable6);
            TextView couleurTexture = convertView.findViewById(R.id.textViewTable7);
            TextView contenuCouleurTexture= convertView.findViewById(R.id.textViewTable8);
            TextView saveur = convertView.findViewById(R.id.textViewTable9);
            TextView contenuSaveur = convertView.findViewById(R.id.textViewTable10);
            TextView principauxProducteur = convertView.findViewById(R.id.textViewTable11);
            TextView contenuPrincipauxProducteur = convertView.findViewById(R.id.textViewTable12);

            famille.setText("Famille");
            famille.setTextColor(Color.parseColor("#7D9F00"));
            contenuFamille.setText(caracteristique.getFamille());

            espece.setText("Espece");
            espece.setTextColor(Color.parseColor("#7D9F00"));
            contenuEspece.setText(caracteristique.getEspece());

            taillePoids.setText("Taille et poids");
            taillePoids.setTextColor(Color.parseColor("#7D9F00"));
            contenuTaillePoids.setText(caracteristique.getTaillePoids());

            couleurTexture.setText("Couleur et texture");
            couleurTexture.setTextColor(Color.parseColor("#7D9F00"));
            contenuCouleurTexture.setText(caracteristique.getCouleurTexture());

            saveur.setText("Saveur");
            saveur.setTextColor(Color.parseColor("#7D9F00"));
            contenuSaveur.setText(caracteristique.getSaveur());

            principauxProducteur.setText("Principaux producteur");
            principauxProducteur.setTextColor(Color.parseColor("#7D9F00"));
            contenuPrincipauxProducteur.setText(caracteristique.getPrincipauxProducteur());

        }
        else if(groupPosition == InfoEnum.conseil.ordinal())
        {
            TableLayout tableLayout = convertView.findViewById(R.id.tableLayout);

            TextView conseil1 = convertView.findViewById(R.id.textViewTable1);
            TextView conseil2 = convertView.findViewById(R.id.textViewTable2);
            TextView conseil3 = convertView.findViewById(R.id.textViewTable3);
            TextView conseil4 = convertView.findViewById(R.id.textViewTable4);
            TextView conseil5 = convertView.findViewById(R.id.textViewTable5);
            TextView conseil6 = convertView.findViewById(R.id.textViewTable6);


            conseil1.setText(conseil.getConseil1());
            conseil2.setText(conseil.getConseil2());

            conseil3.setText(conseil.getConseil3());
            conseil4.setText(conseil.getConseil4());

            conseil5.setText(conseil.getConseil5());
            conseil6.setText(conseil.getConseil6());

            TableRow tableRow4 = convertView.findViewById(R.id.tableRow4);
            TableRow tableRow5 = convertView.findViewById(R.id.tableRow5);
            TableRow tableRow6 = convertView.findViewById(R.id.tableRow6);

            tableLayout.removeView(tableRow4);
            tableLayout.removeView(tableRow5);
            tableLayout.removeView(tableRow6);
        }
        else if(groupPosition == InfoEnum.beneficeSante.ordinal())
        {
            TableLayout tableLayout = convertView.findViewById(R.id.tableLayout);

            TextView benefice1 = convertView.findViewById(R.id.textViewTable1);
            TextView benefice2 = convertView.findViewById(R.id.textViewTable2);
            TextView benefice3 = convertView.findViewById(R.id.textViewTable3);
            TextView benefice4 = convertView.findViewById(R.id.textViewTable4);
            TextView benefice5 = convertView.findViewById(R.id.textViewTable5);
            TextView benefice6 = convertView.findViewById(R.id.textViewTable6);

            benefice1.setText(beneficeSante.getBenefice1());
            benefice2.setText(beneficeSante.getBenefice2());

            benefice3.setText(beneficeSante.getBenefice3());
            benefice4.setText(beneficeSante.getBenefice4());

            benefice5.setText(beneficeSante.getBenefice5());
            benefice6.setText(beneficeSante.getBenefice6());

            TableRow tableRow4 = convertView.findViewById(R.id.tableRow4);
            TableRow tableRow5 = convertView.findViewById(R.id.tableRow5);
            TableRow tableRow6 = convertView.findViewById(R.id.tableRow6);

            tableLayout.removeView(tableRow4);
            tableLayout.removeView(tableRow5);
            tableLayout.removeView(tableRow6);
        }
        else if(groupPosition == InfoEnum.marketing.ordinal())
        {
            TableLayout tableLayout = convertView.findViewById(R.id.tableLayout);

            TextView marketing1 = convertView.findViewById(R.id.textViewTable1);
            TextView marketing2 = convertView.findViewById(R.id.textViewTable2);

            marketing1.setText(marketing.getMarketing1());
            marketing2.setText(marketing.getMarketing2());

            TableRow tableRow2 = convertView.findViewById(R.id.tableRow2);
            TableRow tableRow3 = convertView.findViewById(R.id.tableRow3);
            TableRow tableRow4 = convertView.findViewById(R.id.tableRow4);
            TableRow tableRow5 = convertView.findViewById(R.id.tableRow5);
            TableRow tableRow6 = convertView.findViewById(R.id.tableRow6);

            tableLayout.removeView(tableRow2);
            tableLayout.removeView(tableRow3);
            tableLayout.removeView(tableRow4);
            tableLayout.removeView(tableRow5);
            tableLayout.removeView(tableRow6);

        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        if(groupPosition != 0)
            return false;
        else
            return true;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        if(groupPosition != 0)
            super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        if(groupPosition != 0)
            super.onGroupExpanded(groupPosition);
    }

}


