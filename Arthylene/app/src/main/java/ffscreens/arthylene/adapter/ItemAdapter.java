package ffscreens.arthylene.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import ffscreens.arthylene.R;
import ffscreens.arthylene.objects.Item;

/**
 * Created by Thibault on 27/06/2017.
 */

public class ItemAdapter extends BaseAdapter {

    private List<Item> itemList;
    private Context context;

    public ItemAdapter(@NonNull Context context, @NonNull List<Item> items) {
        itemList = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Item getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_cheklist, parent, false);
        }
        RelativeLayout l = convertView.findViewById(R.id.itemChecklist);
        l.setFocusable(false);
        ItemViewOlder itemViewOlder = (ItemViewOlder) convertView.getTag();
        if (itemViewOlder == null) {
            itemViewOlder = new ItemViewOlder();
            itemViewOlder.image = convertView.findViewById(R.id.image);
            itemViewOlder.title = convertView.findViewById(R.id.title);
            itemViewOlder.conten = convertView.findViewById(R.id.contenu);
            itemViewOlder.att = convertView.findViewById(R.id.important);
            itemViewOlder.check = convertView.findViewById(R.id.check);

            convertView.setTag(itemViewOlder);
        }

        Item item = getItem(position);
        itemViewOlder.image.setImageDrawable(new ColorDrawable(Color.RED));
        itemViewOlder.title.setText(item.getTitle());
        itemViewOlder.conten.setText(item.getContent());
        if (item.isImportant()) {
            itemViewOlder.att.setImageResource(R.drawable.dot_atencion3x);
            itemViewOlder.att.setVisibility(View.VISIBLE);
        } else {
            itemViewOlder.att.setVisibility(View.INVISIBLE);
        }
        itemViewOlder.check.setChecked(item.isChecked());
        itemViewOlder.check.setFocusable(false);
        itemViewOlder.check.setClickable(false);

        return convertView;
    }

    private class ItemViewOlder {
        ImageView image;
        TextView title;
        TextView conten;
        ImageView att;
        CheckBox check;
    }
}
