package demo.zjj.wellingtonnews.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import demo.zjj.wellingtonnews.R;
import demo.zjj.wellingtonnews.adapters.CustomListAdapter;
import demo.zjj.wellingtonnews.adapters.MenuAdapter;


public class MenuFragment extends BaseFragment {
    private View fragment_menu;
    private ListView lv_menu;
    private CustomListAdapter adapter;


    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }

    @Override
    public View initView(LayoutInflater inflater) {
        fragment_menu = inflater.inflate(R.layout.fragment_menu, null);
        lv_menu = (ListView) fragment_menu.findViewById(R.id.lv_menu);


        return fragment_menu;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        //Do nothing
    }

    public void initMenu(List<String> titleList){
        //stored position

        if(adapter == null){
            adapter = new MenuAdapter(context, titleList);
            lv_menu.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }
        //TODO 暂时选中第一个 以后再改
        lv_menu.setItemChecked(0,true);
        lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"position: "+position, Toast.LENGTH_SHORT).show();
                //TODO change pager
//                slidingMenu.toggle();
            }
        });

    }

}
