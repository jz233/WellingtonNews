package demo.zjj.wellingtonnews.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import demo.zjj.wellingtonnews.R;
import demo.zjj.wellingtonnews.adapters.MyAdapter;
import demo.zjj.wellingtonnews.domain.NewsInfo;

/**
 * Created by DouJ on 9/5/2015.
 */
public class NewsFragment extends BaseFragment {

    private static final String TAG = NewsFragment.class.getSimpleName();
    private RecyclerView rv_news;
    private String[] texts = {"0","1","2","3","4","5","6","7","8","9"};
    private List<NewsInfo> newsList;
    private MyAdapter myAdapter;
    private static final String BASE_URL = "http://wellington.gen.nz/";
    private static final String URL_SUFFIX = "/json";



    public static NewsFragment newInstance(String area, String category){
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString("area",area);
        args.putString("category",category);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_news, null);
        rv_news = (RecyclerView) view.findViewById(R.id.rv_news);

        return view;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        rv_news.setLayoutManager(new LinearLayoutManager(context));

        Bundle args = getArguments();

        //TODO change url
        getNewsList(BASE_URL+args.getString("area")+"+"+args.getString("category")+URL_SUFFIX);

    }

    public void getNewsList(String url){
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//                newsDataListener.onWellyNewsDataListener(response);
                Gson gson = new Gson();
                NewsInfo[] newsInfos = gson.fromJson(response.toString(), NewsInfo[].class);
                newsList = new ArrayList<>();

                for(NewsInfo newsInfo : newsInfos){
                    Log.d(TAG,newsInfo.toString());
                    newsList.add(newsInfo);
                }
                modifyNewsList(newsList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Error",Toast.LENGTH_SHORT).show();
                Log.e(TAG, error.getLocalizedMessage());
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(500000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getMainContext().getWellyNewsClient().getRequestQueue().add(request);
    }

    private void modifyNewsList(final List<NewsInfo> newsList) {
        if (myAdapter == null) {
            myAdapter = new MyAdapter(context, newsList);
            rv_news.setAdapter(myAdapter);
        } else {
            myAdapter.notifyDataSetChanged();
        }

    }
}
