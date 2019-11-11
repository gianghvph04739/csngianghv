package com.promobileapp.chiasenhac.net.listener;

import com.promobileapp.chiasenhac.model.Banner;

import java.util.ArrayList;

public interface OnSearchResult {
    void onSearchSuccessful(ArrayList<Banner> lstBan);

    void onSearchFailed(String message);
}
