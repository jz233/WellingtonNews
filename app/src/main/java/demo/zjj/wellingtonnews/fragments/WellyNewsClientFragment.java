package demo.zjj.wellingtonnews.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.List;

import demo.zjj.wellingtonnews.listeners.NewsDataListener;
import demo.zjj.wellingtonnews.domain.NewsInfo;
import demo.zjj.wellingtonnews.utils.BitmapCache;

/**
 * Created by DouJ on 9/7/2015.
 */
public class WellyNewsClientFragment extends Fragment {

    private RequestQueue requestQueue;
    private BitmapCache bitmapCache;
    private ImageLoader imageLoader;
    private NewsDataListener newsDataListener;
    private List<NewsInfo> newsList;

    public WellyNewsClientFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            newsDataListener = (NewsDataListener) context;
        }catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement TradeMeResponseListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        bitmapCache = new BitmapCache();
        imageLoader = new ImageLoader(requestQueue, bitmapCache);


        setRetainInstance(true);

    }

    public ImageLoader getImageLoader(){
        return imageLoader;
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }
    @Override
    public void onStop() {
        super.onStop();
        requestQueue.cancelAll(this);
    }

}
