package demo.zjj.wellingtonnews.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;


import org.json.JSONObject;

import java.util.ArrayList;

import demo.zjj.wellingtonnews.listeners.NewsDataListener;
import demo.zjj.wellingtonnews.R;
import demo.zjj.wellingtonnews.domain.NewsInfo;
import demo.zjj.wellingtonnews.fragments.ContentFragment;
import demo.zjj.wellingtonnews.fragments.WellyNewsClientFragment;

public class MainActivity extends BaseActivity implements NewsDataListener {

    public static final int CITY_WLT = 0;
    public static final int CITY_AKL = 1;
    public static final int CITY_CHCH = 2;
    public static final int SETTINGS = 3;
    private ContentFragment contentFragment;
    private DrawerLayout drawer_layout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private FrameLayout fl_content;
    private NavigationView nav_view;
    private FragmentTransaction ft;
    private FragmentManager fm;
    private Fragment wellynews_fragment;
    private ArrayList<NewsInfo> newsList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getFragmentManager();

        addNonUIFragment();

        initView();

        setupView();

//        setSupportActionBar(toolbar);
//        setupActionBar();

    }

    private void setupView() {
        nav_view.inflateHeaderView(R.layout.drawer_header);
        nav_view.inflateMenu(R.menu.drawer_menu);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.wellington:
                        selectFragment(0);
                        break;
                    case R.id.auckland:
                        selectFragment(1);
                        break;
                    case R.id.christchurch:
                        break;
                    case R.id.settings:
                        selectFragment(3);
                        break;
                }
                drawer_layout.closeDrawers();
                return true;
            }
        });
        nav_view.setCheckedItem(R.id.wellington);
        selectFragment(0);

        drawerToggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawer_layout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
//        drawerToggle.syncState();
        drawer_layout.setDrawerListener(drawerToggle);

    }

    private void selectFragment(int i) {
        ft = fm.beginTransaction();
        switch (i){
            case CITY_WLT:
                if(!fragmentExists("Wellington")){
                    ft.add(R.id.fl_content, ContentFragment.newInstance("Wellington"), "Wellington");
                }else {
                    ft.show(fm.findFragmentByTag("Wellington"));
                }
                ft.commit();
                break;
            case CITY_AKL:
//                getSupportFragmentManager().beginTransaction()
//                        .add(R.id.fl_content, null).commit();
                break;
            case CITY_CHCH:
                break;
            case SETTINGS:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;

            default:
                Toast.makeText(this,"Default",Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private boolean fragmentExists(String tag){
        return getFragmentManager().findFragmentByTag(tag) != null;
    }

    private void initView() {
        nav_view = (NavigationView) findViewById(R.id.nav_view);
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);

    }

    public void addNonUIFragment(){
        ft = fm.beginTransaction();
        wellynews_fragment = fm.findFragmentByTag("WellyNews");
        if(wellynews_fragment == null){
            wellynews_fragment = new WellyNewsClientFragment();
            ft.add(wellynews_fragment,"WellyNews");
        }
        ft.commit();
        fm.executePendingTransactions();

    }

    public WellyNewsClientFragment getWellyNewsClient(){
        return (WellyNewsClientFragment) wellynews_fragment;
    }
    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        drawerToggle.syncState();
    }

    public ActionBarDrawerToggle getDrawerToggle(){
        return drawerToggle;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    public FrameLayout getContentFrame(){
        return fl_content;
    }

    public ContentFragment getContentFragment(){
        return (ContentFragment) getFragmentManager().findFragmentByTag("CONTENT");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onWellyNewsDataListener(JSONObject response) {
        //TODO modify UI
//        Gson gson = new Gson();
//        NewsInfo[] newsInfos = gson.fromJson(response.toString(), NewsInfo[].class);
//        newsList = new ArrayList<>();
//
//        for(NewsInfo newsInfo : newsInfos){
//            newsList.add(newsInfo);
//        }



    }
}
