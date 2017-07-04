package ffscreens.arthylene.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import ffscreens.arthylene.R;
import ffscreens.arthylene.adapter.ItemAdapter;
import ffscreens.arthylene.database.ChecklistDAO;
import ffscreens.arthylene.objects.Item;

public class CheckListFragment extends Fragment {

    private ListView listView;
    private ItemAdapter adapter;
    private ChecklistDAO dao;
    private List<Item> itemsDao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_check_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dao = new ChecklistDAO(getActivity());


        listView = view.findViewById(R.id.listchecklist);

        /*final List<Item> items = new ArrayList<>();
        items.add(new Item("titre", "contenu", true, true));
        items.add(new Item("titre2", "contenu2", true, false));
        items.add(new Item("titre3", "contenu3", false, false));
        items.add(new Item("titre4", "contenu4", false, true));
        items.add(new Item("titre4", "contenu4", false, true));
        items.add(new Item("titre4", "contenu4", false, true));
        items.add(new Item("titre4", "contenu4", false, true));
        items.add(new Item("titre4", "contenu4", false, true));
        items.add(new Item("titre4", "contenu4", false, true));
        items.add(new Item("titre4", "contenu4", false, true));
        items.add(new Item("titre4", "contenu4", false, true));

        dao.insertListItem(items);*/

        itemsDao = dao.getAllItems();

        adapter = new ItemAdapter(getActivity(), itemsDao);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Item item = adapter.getItem(position);
                item.setChecked(!item.isChecked());
                dao.updateItem(item);
                adapter.notifyDataSetChanged();
            }
        });
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_check_list);
        dao = new ChecklistDAO(getApplicationContext());

        listView = findViewById(R.id.checklist);

       *//* final List<Item> items = new ArrayList<>();
        items.add(new Item("titre", "contenu", true, true));
        items.add(new Item("titre2", "contenu2", true, false));
        items.add(new Item("titre3", "contenu3", false, false));
        items.add(new Item("titre4", "contenu4", false, true));
        items.add(new Item("titre4", "contenu4", false, true));
        items.add(new Item("titre4", "contenu4", false, true));
        items.add(new Item("titre4", "contenu4", false, true));
        items.add(new Item("titre4", "contenu4", false, true));
        items.add(new Item("titre4", "contenu4", false, true));
        items.add(new Item("titre4", "contenu4", false, true));
        items.add(new Item("titre4", "contenu4", false, true));

        dao.insertListItem(items);*//*

        itemsDao = dao.getAllItems();

        adapter = new ItemAdapter(this, itemsDao);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Item item = adapter.getItem(position);
                item.setChecked(!item.isChecked());
                dao.updateItem(item);
                adapter.notifyDataSetChanged();
            }
        });
    }*/
}
