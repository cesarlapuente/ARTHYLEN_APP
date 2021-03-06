package ffscreens.arthylene.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import ffscreens.arthylene.R;
import ffscreens.arthylene.adapter.ItemAdapter;
import ffscreens.arthylene.database.ChecklistDAO;
import ffscreens.arthylene.objects.Item;

/**
 * Arthylene
 * Created by Thibault on 21/06/2017.
 */

public class CheckListFragment extends Fragment {

    private ItemAdapter adapter;
    private ChecklistDAO dao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_check_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button back = getActivity().findViewById(R.id.buttonReturn);
        back.setVisibility(View.INVISIBLE);

        dao = new ChecklistDAO(getActivity());

        view.getParent().requestDisallowInterceptTouchEvent(true);


        ListView listView = view.findViewById(R.id.listchecklist);

        List<Item> itemsDao = dao.getAllItems();

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

}
