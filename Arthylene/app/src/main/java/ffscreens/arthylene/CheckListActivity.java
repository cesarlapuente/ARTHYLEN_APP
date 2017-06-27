package ffscreens.arthylene;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.adapter.Item;
import ffscreens.arthylene.adapter.ItemAdapter;

public class CheckListActivity extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);

        listView = findViewById(R.id.checklist);

        List<Item> items = new ArrayList<>();
        items.add(new Item("titre", "contenu", true, true));
        items.add(new Item("titre2", "contenu2", true, false));
        items.add(new Item("titre3", "contenu3", false, false));
        items.add(new Item("titre4", "contenu4", false, true));

        ItemAdapter adapter = new ItemAdapter(this, items);
        listView.setAdapter(adapter);
    }
}
