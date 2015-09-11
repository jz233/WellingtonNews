package demo.zjj.wellingtonnews.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by DouJ on 9/8/2015.
 */
public class BaseApplication extends Application {

    private static BaseApplication context = null;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;

    }

    public static BaseApplication getBaseApplication(){
        return context;
    }
}
