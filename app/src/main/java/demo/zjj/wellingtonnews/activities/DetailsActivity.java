package demo.zjj.wellingtonnews.activities;

import android.app.Fragment;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import demo.zjj.wellingtonnews.R;
import demo.zjj.wellingtonnews.application.BaseApplication;
import demo.zjj.wellingtonnews.domain.NewsInfo;
import demo.zjj.wellingtonnews.fragments.WellyNewsClientFragment;

public class DetailsActivity extends BaseActivity {

    private WellyNewsClientFragment client;
    private ImageLoader loader;
    private NetworkImageView iv_detail_pic;
    private TextView tv_detail_name;
    private TextView tv_detail_desc;
    private Toolbar detail_toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        detail_toolbar = (Toolbar) findViewById(R.id.detail_toolbar);

        detail_toolbar.setTitle("News");
//        detail_toolbar.setNavigationIcon(R.drawable.hamilton_pressed);
        detail_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"AAA",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        setSupportActionBar(detail_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


//        client = (WellyNewsClientFragment) getFragmentManager().findFragmentByTag("WellyNews");
//        client = ((MainActivity)getApplicationContext()).getWellyNewsClient();
//        client = (MainActivity(getBaseApplication())).getWellyNewsClient();
//        client = ((MainActivity)context).getWellyNewsClient();
//        loader = client.getImageLoader();

        NewsInfo newsitem = getIntent().getParcelableExtra("newsitem");

        iv_detail_pic = (NetworkImageView) findViewById(R.id.iv_detail_pic);
        tv_detail_name = (TextView) findViewById(R.id.tv_detail_name);
        tv_detail_desc = (TextView) findViewById(R.id.tv_detail_desc);

        String imageUrl = newsitem.imageUrl;
//        if(!TextUtils.isEmpty(imageUrl)&&!"".equals(imageUrl)){
//            iv_detail_pic.setImageUrl(imageUrl,loader);
//        }else{
            iv_detail_pic.setDefaultImageResId(R.drawable.wordel);
//        }
        tv_detail_name.setText(newsitem.name);
        tv_detail_desc.setText(newsitem.description);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Toast.makeText(getApplicationContext(),"AAA",Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
