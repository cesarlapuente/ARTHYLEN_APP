package ffscreens.arthylene;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import ffscreens.arthylene.adapter.ItemAdapter;
import ffscreens.arthylene.database.ChecklistDAO;
import ffscreens.arthylene.objects.Item;

public class CheckListActivity extends Activity {

    private ListView listView;
    private ItemAdapter adapter;
    private ChecklistDAO dao;
    private List<Item> itemsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);
        dao = new ChecklistDAO(getApplicationContext());

        listView = findViewById(R.id.checklist);

       /* final List<Item> items = new ArrayList<>();
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
    }
}
