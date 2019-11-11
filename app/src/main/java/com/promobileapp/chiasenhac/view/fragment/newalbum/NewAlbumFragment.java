package com.promobileapp.chiasenhac.view.fragment.newalbum;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.promobileapp.chiasenhac.R;
import com.promobileapp.chiasenhac.base.BaseFragment;

import butterknife.ButterKnife;

public class NewAlbumFragment extends BaseFragment {

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
