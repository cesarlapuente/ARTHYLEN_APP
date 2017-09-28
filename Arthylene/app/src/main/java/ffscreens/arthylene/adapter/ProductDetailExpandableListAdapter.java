package ffscreens.arthylene.adapter;

import android.app.Activity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
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
            convertView = inflater.inflate(R.layout.list_header, null);
        }
        Group group = (Group) getGroup(groupPosition);
        ((CheckedTextView) convertView).setText(group.string);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        convertView = inflater.inflate(R.layout.details_table, parent, false);


        if(groupPosition == InfoEnum.presentation.ordinal())
        {

        }
        else if(groupPosition == InfoEnum.caracteristique.ordinal())
        {
            TableLayout tableCaracteristique = convertView.findViewById(R.id.tableLayout);

            TableRow tableRowFamille = new TableRow(activity.getApplicationContext());
            TableRow tableRowEspece = new TableRow(activity.getApplicationContext());
            TableRow tableRowTaillePoids = new TableRow(activity.getApplicationContext());
            TableRow tableRowCouleurTexture = new TableRow(activity.getApplicationContext());
            TableRow tableRowSaveur = new TableRow(activity.getApplicationContext());
            TableRow tableRowPrincipauxProducteur = new TableRow(activity.getApplicationContext());


            TextView famille = new TextView(activity.getApplicationContext());
            TextView contenuFamille = new TextView(activity.getApplicationContext());

            TextView espece = new TextView(activity.getApplicationContext());
            TextView contenuEspece = new TextView(activity.getApplicationContext());

            TextView taillePoids = new TextView(activity.getApplicationContext());
            TextView contenuTaillePoids = new TextView(activity.getApplicationContext());

            TextView couleurTexture = new TextView(activity.getApplicationContext());
            TextView contenuCouleurTexture= new TextView(activity.getApplicationContext());

            TextView saveur = new TextView(activity.getApplicationContext());
            TextView contenuSaveur = new TextView(activity.getApplicationContext());

            TextView principauxProducteur = new TextView(activity.getApplicationContext());
            TextView contenuPrincipauxProducteur = new TextView(activity.getApplicationContext());

            tableRowFamille.setLayoutParams(layoutParams);
            tableRowEspece.setLayoutParams(layoutParams);
            tableRowTaillePoids.setLayoutParams(layoutParams);
            tableRowCouleurTexture.setLayoutParams(layoutParams);
            tableRowSaveur.setLayoutParams(layoutParams);
            tableRowPrincipauxProducteur.setLayoutParams(layoutParams);


            famille.setText("Famille");
            contenuFamille.setText(caracteristique.getFamille());
            tableRowFamille.addView(famille);
            tableRowFamille.addView(contenuFamille);
            tableCaracteristique.addView(tableRowFamille, 0);

            espece.setText("Espece");
            contenuEspece.setText(caracteristique.getEspece());
            tableRowEspece.addView(espece);
            tableRowEspece.addView(contenuEspece);
            tableCaracteristique.addView(tableRowEspece, 1);

            taillePoids.setText("Taille et poids");
            contenuTaillePoids.setText(caracteristique.getTaillePoids());
            tableRowTaillePoids.addView(taillePoids);
            tableRowTaillePoids.addView(contenuTaillePoids);
            tableCaracteristique.addView(tableRowTaillePoids, 2);

            couleurTexture.setText("Couleur et texture");
            contenuCouleurTexture.setText(caracteristique.getCouleurTexture());
            tableRowCouleurTexture.addView(couleurTexture);
            tableRowCouleurTexture.addView(contenuCouleurTexture);
            tableCaracteristique.addView(tableRowCouleurTexture, 3);

            saveur.setText("Saveur");
            contenuSaveur.setText(caracteristique.getSaveur());
            tableRowSaveur.addView(saveur);
            tableRowSaveur.addView(contenuSaveur);
            tableCaracteristique.addView(tableRowSaveur, 4);

            principauxProducteur.setText("Principaux producteur");
            contenuPrincipauxProducteur.setText(caracteristique.getPrincipauxProducteur());
            tableRowPrincipauxProducteur.addView(principauxProducteur);
            tableRowPrincipauxProducteur.addView(contenuPrincipauxProducteur);
            tableCaracteristique.addView(tableRowPrincipauxProducteur, 5);

        }
        else if(groupPosition == InfoEnum.conseil.ordinal())
        {
            TableLayout tableConseil = convertView.findViewById(R.id.tableLayout);

            TableRow tableRowConseil1 = new TableRow(activity.getApplicationContext());
            TableRow tableRowConseil2 = new TableRow(activity.getApplicationContext());
            TableRow tableRowConseil3 = new TableRow(activity.getApplicationContext());

            TextView conseil1 = new TextView(activity.getApplicationContext());
            TextView conseil2 = new TextView(activity.getApplicationContext());

            TextView conseil3 = new TextView(activity.getApplicationContext());
            TextView conseil4 = new TextView(activity.getApplicationContext());

            TextView conseil5 = new TextView(activity.getApplicationContext());
            TextView conseil6 = new TextView(activity.getApplicationContext());

            tableRowConseil1.setLayoutParams(layoutParams);
            tableRowConseil2.setLayoutParams(layoutParams);
            tableRowConseil3.setLayoutParams(layoutParams);

            conseil1.setText(conseil.getConseil1());
            conseil2.setText(conseil.getConseil2());
            tableRowConseil1.addView(conseil1);
            tableRowConseil1.addView(conseil2);
            tableConseil.addView(tableRowConseil1, 0);

            conseil3.setText(conseil.getConseil3());
            conseil4.setText(conseil.getConseil4());
            tableRowConseil2.addView(conseil3);
            tableRowConseil2.addView(conseil4);
            tableConseil.addView(tableRowConseil2, 1);

            conseil5.setText(conseil.getConseil5());
            conseil6.setText(conseil.getConseil6());
            tableRowConseil3.addView(conseil5);
            tableRowConseil3.addView(conseil6);
            tableConseil.addView(tableRowConseil3, 2);
        }
        else if(groupPosition == InfoEnum.beneficeSante.ordinal())
        {
            TableLayout tableBeneficeSante = convertView.findViewById(R.id.tableLayout);

            TableRow tableRowBenefice1 = new TableRow(activity.getApplicationContext());
            TableRow tableRowBenefice2 = new TableRow(activity.getApplicationContext());
            TableRow tableRowBenefice3 = new TableRow(activity.getApplicationContext());

            TextView benefice1 = new TextView(activity.getApplicationContext());
            TextView benefice2 = new TextView(activity.getApplicationContext());

            TextView benefice3 = new TextView(activity.getApplicationContext());
            TextView benefice4 = new TextView(activity.getApplicationContext());

            TextView benefice5 = new TextView(activity.getApplicationContext());
            TextView benefice6 = new TextView(activity.getApplicationContext());

            tableRowBenefice1.setLayoutParams(layoutParams);
            tableRowBenefice2.setLayoutParams(layoutParams);
            tableRowBenefice3.setLayoutParams(layoutParams);

            benefice1.setText(beneficeSante.getBenefice1());
            benefice2.setText(beneficeSante.getBenefice2());
            tableRowBenefice1.addView(benefice1);
            tableRowBenefice1.addView(benefice2);
            tableBeneficeSante.addView(tableRowBenefice1, 0);

            benefice3.setText(beneficeSante.getBenefice3());
            benefice4.setText(beneficeSante.getBenefice4());
            tableRowBenefice2.addView(benefice3);
            tableRowBenefice2.addView(benefice4);
            tableBeneficeSante.addView(tableRowBenefice2, 1);

            benefice5.setText(beneficeSante.getBenefice5());
            benefice6.setText(beneficeSante.getBenefice6());
            tableRowBenefice3.addView(benefice5);
            tableRowBenefice3.addView(benefice6);
            tableBeneficeSante.addView(tableRowBenefice3, 2);
        }
        else if(groupPosition == InfoEnum.marketing.ordinal())
        {
            TableLayout tableMarketing = convertView.findViewById(R.id.tableLayout);

            TableRow tableRowMarketing1 = new TableRow(activity.getApplicationContext());

            TextView marketing1 = new TextView(activity.getApplicationContext());
            TextView marketing2 = new TextView(activity.getApplicationContext());

            tableRowMarketing1.setLayoutParams(layoutParams);

            marketing1.setText(marketing.getMarketing1());
            marketing2.setText(marketing.getMarketing2());
            tableRowMarketing1.addView(marketing1);
            tableRowMarketing1.addView(marketing2);
            tableMarketing.addView(tableRowMarketing1, 0);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

}


