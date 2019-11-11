package com.promobileapp.chiasenhac.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.promobileapp.chiasenhac.bus.MessageEvent;
import com.promobileapp.chiasenhac.utils.AppUtils;
import com.promobileapp.chiasenhac.utils.PreferenceUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public abstract class BaseFragment extends Fragment implements MvpView {

    private BaseActivity mActivity;
    private ProgressDialog mProgressDialog;
    public static PreferenceUtils preferenceUtils;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        preferenceUtils = PreferenceUtils.getInstance(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this.getContext());
    }


    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onError(String message) {
        if (mActivity != null) {
            mActivity.onError(message);
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        if (mActivity != null) {
            mActivity.onError(resId);
        }
    }

    public void hideKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public void marginStatusbar(View view) {
        view.setPadding(8, AppUtils.getHeightStatusBar(getContext()), 8, 8);
    }

    public void transitionBG(View view) {
        AnimationDrawable animationDrawable = (AnimationDrawable) view.getBackground();
        animationDrawable.setEnterFadeDuration(7500);
        animationDrawable.setExitFadeDuration(10000);
        animationDrawable.start();
    }

    public void marginStb_trasitionBG(View view) {
        view.setPadding(8, AppUtils.getHeightStatusBar(getContext()), 8, 8);
        transitionBG(view);
    }

    public void marginStatusbarNormalView(View view) {
        view.setPadding(AppUtils.dpToPixels(16), AppUtils.getHeightStatusBar(getContext()) + AppUtils.dpToPixels(16), AppUtils.dpToPixels(16), AppUtils.dpToPixels(16));
        transitionBG(view);
    }

    @Override
    public void showMessage(String message) {
        if (mActivity != null) {
            mActivity.showMessage(message);
        }
    }

    @Override
    public void showMessage(@StringRes int resId) {
        if (mActivity != null) {
            mActivity.showMessage(resId);
        }
    }

    @Override
    public boolean isNetworkConnected() {
        if (mActivity != null) {
            return mActivity.isNetworkConnected();
        }
        return false;
    }

    @Override
    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
        EventBus.getDefault().unregister(this);

    }
    protected abstract void init(View view);

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public interface Callback {
        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void event(MessageEvent event){

    }
}
