package demo.zjj.wellingtonnews.pagers;

import android.content.Context;
import android.view.View;

/**
 * Created by DouJ on 9/2/2015.
 */
public abstract class BasePager {

    private View view;
    public Context context;
    public BasePager(Context context) {
        this.context = context;
        view = initView();
    }

    public abstract View initView();
    public abstract void initData();

    public View getRootView(){
        return view;
    }

    public void retrieveData(){
        //TODO networking
    }

    public void initTitleBar(){
        //TODO init
    }

}
