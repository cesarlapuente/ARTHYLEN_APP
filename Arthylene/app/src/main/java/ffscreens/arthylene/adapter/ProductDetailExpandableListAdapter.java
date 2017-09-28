package ffscreens.arthylene.adapter;

import android.app.Activity;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    private TableLayout tableCaracteristique = null;
    private TableLayout tableBeneficeSante = null;
    private TableLayout tableConseil = null;
    private TableLayout tableMarketing = null;

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

        initTables();
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
//        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listrow_table, null);
//        }

        //generer tout les tab et les choisir dans les if

        TableLayout tableLayout = null;

        //dans le constructeur init les table
        //if tablePres == null
        //then tabPref = getTabPref()
        //else - rien
        //uniquement jouer avec le table layout et le convertView
        TableRow tableRowPresentation = null;
        TableRow tableRowCaracteristique = null;


        if(groupPosition == InfoEnum.presentation.ordinal())
        {
            tableCaracteristique = convertView.findViewById(R.id.tableLayout);

            tableRowPresentation = new TableRow(activity.getApplicationContext());
            TextView nom = new TextView(activity.getApplicationContext());
            TextView contenu = new TextView(activity.getApplicationContext());

            nom.setText(presentation.getIdPhoto().toString());
            contenu.setText(" Le tableau !");

            tableRowPresentation.addView(nom);
            tableRowPresentation.addView(contenu);
            tableCaracteristique.addView(tableRowPresentation);
        }
        else if(groupPosition == InfoEnum.caracteristique.ordinal())
        {
            tableLayout = convertView.findViewById(R.id.tableLayout);

            tableRowCaracteristique = new TableRow(activity.getApplicationContext());
            TextView nom = new TextView(activity.getApplicationContext());
            TextView contenu = new TextView(activity.getApplicationContext());

            nom.setText("Famille :");
            contenu.setText(caracteristique.getFamille());

            tableRowCaracteristique.addView(nom);
            tableRowCaracteristique.addView(contenu);
            tableLayout.addView(tableRowCaracteristique);
        }
        else if(groupPosition == InfoEnum.conseil.ordinal())
        {

        }
        else if(groupPosition == InfoEnum.beneficeSante.ordinal())
        {

        }
        else if(groupPosition == InfoEnum.marketing.ordinal())
        {

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

    private void initTables()
    {

    }
}
