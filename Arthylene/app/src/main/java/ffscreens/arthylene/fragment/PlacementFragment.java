package ffscreens.arthylene.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ffscreens.arthylene.R;
import ffscreens.arthylene.adapter.PlacementAdapter;
import ffscreens.arthylene.objects.ItemPlacement;

/**
 * Arthylene
 * Created by Thibault Nougues on 30/06/2017.
 */

public class PlacementFragment extends android.app.Fragment {

    PlacementAdapter p;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_placement, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rv = view.findViewById(R.id.rv);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(lm);
        rv.setNestedScrollingEnabled(false);

        List<ItemPlacement> test = new ArrayList<>();
        test.add(new ItemPlacement("hiver", R.drawable.imagen_invierno3x, false, getActivity()));
        test.add(new ItemPlacement("printemps", R.drawable.imagen_primavera3x, false, getActivity()));
        test.add(new ItemPlacement("ete", R.drawable.imagen_verano3x, true, getActivity()));
        test.add(new ItemPlacement("automne", R.drawable.imagen_otono3x, false, getActivity()));


        p = new PlacementAdapter(test, getActivity());

        rv.setAdapter(p);


    }
}
