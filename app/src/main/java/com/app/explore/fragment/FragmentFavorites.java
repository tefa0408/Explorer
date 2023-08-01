package com.app.explore.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.explore.ActivityMain;
import com.app.explore.ActivityPlaceDetail;
import com.app.explore.R;
import com.app.explore.adapter.AdapterFavorites;
import com.app.explore.data.AppConfig;
import com.app.explore.data.GDPR;
import com.app.explore.model.PlaceModel;
import com.app.explore.realm.RealmController;
import com.app.explore.utils.Analytics;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

public class FragmentFavorites extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayout lyt_no_item;
    private AdapterFavorites mAdapter;
    private View root_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root_view = inflater.inflate(R.layout.fragment_favorites, container, false);

        lyt_no_item = (LinearLayout) root_view.findViewById(R.id.lyt_no_item);
        recyclerView = (RecyclerView) root_view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Analytics.trackFragmentScreen(this);

        return root_view;
    }

    @Override
    public void onResume() {
        super.onResume();
        List<PlaceModel> items = RealmController.with(this).getPlace();
        displayListData(items);
    }

    private void displayListData(List<PlaceModel> items) {
        //set data and list adapter
        mAdapter = new AdapterFavorites(getActivity(), items);
        recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new AdapterFavorites.OnItemClickListener() {
            @Override
            public void onItemClick(View view, PlaceModel obj, int position) {
                try {
                    ((ActivityMain)getActivity()).showInterstitialAd();
                } catch (Exception e) {

                }
                ActivityPlaceDetail.navigate((ActivityMain) getActivity(), view, obj.getJsonString());
            }
        });

        if (items.size() <= 0) {
            lyt_no_item.setVisibility(View.VISIBLE);
        } else {
            lyt_no_item.setVisibility(View.GONE);
        }
    }

}
