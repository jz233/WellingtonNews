package demo.zjj.wellingtonnews.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import demo.zjj.wellingtonnews.activities.MainActivity;

/**
 * Created by DouJ on 9/2/2015.
 */
public abstract class BaseFragment extends Fragment {


    public Context context;
    public View view;

    public BaseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getActivity();

    }

    public abstract View initView(LayoutInflater inflater);
    public abstract void initData(Bundle savedInstanceState);

    public MainActivity getMainContext(){
        return (MainActivity)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = initView(inflater);
        return view;
    }

    /**
     * 加载数据常放在这里
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initData(savedInstanceState);
        super.onActivityCreated(savedInstanceState);
    }


}
