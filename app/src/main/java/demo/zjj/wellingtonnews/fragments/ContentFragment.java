package demo.zjj.wellingtonnews.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import demo.zjj.wellingtonnews.R;
import demo.zjj.wellingtonnews.activities.MainActivity;

public class ContentFragment extends BaseFragment {

    private static final String TAG = ContentFragment.class.getSimpleName();
    private View fragment_content;
    private ViewPager viewpager;
    private TabLayout tablayout;
    private AppBarLayout appbar;
    private Toolbar toolbar;
    private Spinner spinner;
    private FrameLayout fl_incl_pager;
    private String[] categories = {"Business","Education","Events","Government"};
    private List<String> cityList;
    private String[] cities;
    private String[] areas = {"Brooklyn","Kilbirnie","Miramar","Newtown"};
    private int current_selected_area_position = 0;
    private CustomPagerAdapter adapter;
    private List<Fragment> pagerList = new ArrayList<>();

    public static ContentFragment newInstance(String city) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString("city",city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View initView(LayoutInflater inflater) {
        fragment_content = inflater.inflate(R.layout.fragment_content, null);

        appbar = (AppBarLayout) fragment_content.findViewById(R.id.appbar);
        tablayout = (TabLayout) fragment_content.findViewById(R.id.tablayout);
        toolbar = (Toolbar) fragment_content.findViewById(R.id.toolbar);
        spinner = (Spinner) fragment_content.findViewById(R.id.spinner);
//        fl_incl_pager = (FrameLayout)fragment_content.findViewById(R.id.fl_incl_pager);
        viewpager = (ViewPager) fragment_content.findViewById(R.id.viewpager);


        setupView();


        return fragment_content;
    }

    private void setupView() {
        getMainContext().setSupportActionBar(toolbar);

        setupToolBar();

    }
    private void setupToolBar() {
        ActionBar actionBar = getMainContext().getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        spinner.setAdapter(new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                areas));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                current_selected_area_position = position;
                //重新刷新adapter更新数据
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(context, "Nothing selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        cities = getResources().getStringArray(R.array.cities);
        cityList = new ArrayList<>();
        for (String city: cities){
            cityList.add(city);
        }
       /* for(int i=0; i<categories.length; i++){
            pagerList.add(NewsFragment.newInstance(areas[current_selected_area_position],categories[i]));
        }*/

        if(adapter == null){
            adapter = new CustomPagerAdapter(((MainActivity) context).getFragmentManager(),"Blah");
            viewpager.setAdapter(adapter);
        }else {

            adapter.notifyDataSetChanged();
        }

        //滑动ViewPager时tab才会跟着滑动
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tablayout.setupWithViewPager(viewpager);
        tablayout.setTabsFromPagerAdapter(adapter);
    }


    public class CustomPagerAdapter extends FragmentStatePagerAdapter {

        public CustomPagerAdapter(FragmentManager fm, String blah) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.d(TAG,"getItem: "+position);
            NewsFragment fragment = NewsFragment.newInstance(areas[current_selected_area_position], categories[position]);
            pagerList.add(fragment);
            return fragment;
        }

        @Override
        public int getCount() {
            return categories.length;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.d(TAG, "instantiateItem: " + position);

            return super.instantiateItem(container,position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.d(TAG, "destroyItem: " + position);
            super.destroyItem(container, position, object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return categories[position];
        }

        //强制刷新
        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    public View getRootView(){
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMainContext().getDrawerToggle().syncState();
    }
}
