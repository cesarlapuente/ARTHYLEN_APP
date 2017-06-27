package ffscreens.arthylene.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ffscreens.arthylene.R;

/**
 * Created by Thibault on 27/06/2017.
 */

public class ItemAdapter extends ArrayAdapter<Item> {

    public ItemAdapter(@NonNull Context context, @NonNull List<Item> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_cheklist, parent, false);
        }
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
        itemViewOlder.att.setImageResource(R.drawable.dot_atencion3x);
        //itemViewOlder.att.setVisibility(/*(item.isImportant())? */View.VISIBLE /*: View.INVISIBLE*/);
        itemViewOlder.check.setChecked(item.isChecked());

        return convertView;
    }

    public class ItemViewOlder {
        public ImageView image;
        public TextView title;
        public TextView conten;
        public ImageView att;
        public CheckBox check;
    }
}
