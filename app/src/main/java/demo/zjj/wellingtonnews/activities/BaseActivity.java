package demo.zjj.wellingtonnews.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by DouJ on 9/9/2015.
 */
public class BaseActivity extends AppCompatActivity{

    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;

    }
}
