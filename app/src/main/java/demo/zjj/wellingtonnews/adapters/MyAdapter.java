package demo.zjj.wellingtonnews.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import demo.zjj.wellingtonnews.R;
import demo.zjj.wellingtonnews.activities.DetailsActivity;
import demo.zjj.wellingtonnews.activities.MainActivity;
import demo.zjj.wellingtonnews.domain.NewsInfo;
import demo.zjj.wellingtonnews.utils.DateUtils;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final List<NewsInfo> newsList;
    private LayoutInflater inflater;
    private Context context;
    private ImageLoader loader;

    public MyAdapter(Context context, List<NewsInfo> newsList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.newsList = newsList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_list_item, parent,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_item_text.setText(newsList.get(position).name);
        holder.tv_pub_date.setText(DateUtils.longToDateString(Long.parseLong(newsList.get(position).date)));

        loader = ((MainActivity) context).getWellyNewsClient().getImageLoader();
        String imageUrl = newsList.get(position).imageUrl;
        if(!TextUtils.isEmpty(imageUrl)&& !"".equals(imageUrl)){
            holder.iv_item_pic.setImageUrl(imageUrl,loader);
        }else{
            holder.iv_item_pic.setDefaultImageResId(R.drawable.wordel);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
//                intent.putExtra("name",newsList.get(position).name);
                intent.putExtra("newsitem", newsList.get(position));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public final TextView tv_pub_date;
        public final TextView tv_item_text;
        public final NetworkImageView iv_item_pic;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_item_text = (TextView) itemView.findViewById(R.id.tv_item_text);
            iv_item_pic = (NetworkImageView) itemView.findViewById(R.id.iv_item_pic);
            tv_pub_date = (TextView) itemView.findViewById(R.id.tv_pub_date);

        }
    }

}
