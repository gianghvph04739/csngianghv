package com.promobileapp.chiasenhac.view.fragment.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.promobileapp.chiasenhac.R;
import com.promobileapp.chiasenhac.adapter.BannerAdapter;
import com.promobileapp.chiasenhac.adapter.TopChartAdaper;
import com.promobileapp.chiasenhac.base.BaseActivity;
import com.promobileapp.chiasenhac.base.BaseFragment;
import com.promobileapp.chiasenhac.model.Banner;
import com.promobileapp.chiasenhac.net.SearchUtils;
import com.promobileapp.chiasenhac.net.listener.OnSearchResult;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment implements OnSearchResult, BannerAdapter.BannerOnClickListener, TopChartAdaper.OnTopChartClickListener {
    @BindView(R.id.rv_Banner)
    RecyclerView rvBanner;

    @BindView(R.id.rv_TopChart)
    RecyclerView rvTopChart;

    private CarouselLayoutManager bannerLayoutManager;
    private BannerAdapter bannerAdapter;
    private ArrayList<Banner> lstBanner = new ArrayList<>();

    private TopChartAdaper chartAdaper;
    private GridLayoutManager gridLayoutManager;

    private SearchUtils searchUtils;

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
    public void onDetach() {
        super.onDetach();
    }

    @Override
    protected void init(View view) {
        ButterKnife.bind(this, view);
        initChart();
        searchUtils = new SearchUtils(getContext(), this);
        searchUtils.bannerContent();
        showLoading();
    }

    private void initChart() {
        chartAdaper = new TopChartAdaper(getContext(), this);
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rvTopChart.setHasFixedSize(true);
        rvTopChart.setLayoutManager(gridLayoutManager);
        rvTopChart.setAdapter(chartAdaper);
    }

    @Override
    public void onSearchSuccessful(ArrayList<Banner> listBanner) {
        hideLoading();
        lstBanner.clear();
        lstBanner.addAll(listBanner);
        bannerAdapter = new BannerAdapter(getContext(), lstBanner, this);
        bannerLayoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, true);
        bannerLayoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        bannerLayoutManager.setMaxVisibleItems(2);
        rvBanner.setLayoutManager(bannerLayoutManager);
        rvBanner.setHasFixedSize(true);
        rvBanner.setAdapter(bannerAdapter);
        rvBanner.addOnScrollListener(new CenterScrollListener());
    }

    @Override
    public void onSearchFailed(String message) {
        Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBannerItemClick(int position) {

    }

}
