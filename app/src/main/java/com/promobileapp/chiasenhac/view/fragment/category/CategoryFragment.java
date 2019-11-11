package com.promobileapp.chiasenhac.view.fragment.category;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.promobileapp.chiasenhac.R;
import com.promobileapp.chiasenhac.adapter.BannerAdapter;
import com.promobileapp.chiasenhac.base.BaseFragment;
import com.promobileapp.chiasenhac.model.Banner;
import com.promobileapp.chiasenhac.net.SearchUtils;
import com.promobileapp.chiasenhac.net.listener.OnSearchResult;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    protected void init(View view) {
        ButterKnife.bind(getActivity());
    }
}
