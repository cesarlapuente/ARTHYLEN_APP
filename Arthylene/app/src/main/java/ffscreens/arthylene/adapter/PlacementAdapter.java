package ffscreens.arthylene.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ffscreens.arthylene.R;
import ffscreens.arthylene.objects.ItemPlacement;

/**
 * Created by Thibault on 30/06/2017.
 */

public class PlacementAdapter extends RecyclerView.Adapter<PlacementAdapter.MyViewHolder> {

    List<ItemPlacement> itemPlacements;
    Context context;

    public PlacementAdapter(List<ItemPlacement> list, Context context) {
        itemPlacements = list;
        this.context = context;
        Log.e("+++", itemPlacements.toString());
    }

    public ItemPlacement getItem(int position) {
        return itemPlacements.get(position);
    }

    @Override
    public PlacementAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_placement, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final PlacementAdapter.MyViewHolder holder, final int position) {
        ItemPlacement itemPlacement = getItem(position);
        holder.title.setText(itemPlacement.getTitle());
        holder.image.setImageResource(itemPlacement.getImage());
        if (itemPlacement.isSelected()) {
            holder.background.setBackgroundColor(context.getResources().getColor(R.color.vertClair));
        } else {
            holder.background.setBackgroundColor(Color.WHITE);
        }

        holder.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!getItem(position).isSelected()) {
                    Toast.makeText(context, holder.title.getText(), Toast.LENGTH_SHORT).show();
                    getItem(position).setSelected(!getItem(position).isSelected());
                    deselectOther(getItem(position));
                    PlacementAdapter.super.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemPlacements.size();
    }

    private void deselectOther(ItemPlacement itemPlacement) {
        for (ItemPlacement placement : itemPlacements) {
            if (!placement.equals(itemPlacement)) {
                placement.setSelected(false);
            }
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView image;
        RelativeLayout background;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewPlacement);
            image = itemView.findViewById(R.id.placementImageView);
            background = itemView.findViewById(R.id.background);
        }
    }
}