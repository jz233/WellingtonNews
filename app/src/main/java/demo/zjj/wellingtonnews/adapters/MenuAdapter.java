package demo.zjj.wellingtonnews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import demo.zjj.wellingtonnews.R;

/**
 * Created by DouJ on 9/2/2015.
 */
public class MenuAdapter extends CustomListAdapter<String> {

    private List<String> list = new ArrayList<>();
    private Context context;

    public MenuAdapter(Context context, List<String> titleList) {
        super(titleList);
        this.context = context;
        this.list = titleList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.drawer_list_item, parent, false);
            holder.tv_list_item = (TextView) convertView.findViewById(R.id.tv_list_item);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_list_item.setText(list.get(position));



        return convertView;
    }

    static class ViewHolder{
        TextView tv_list_item;

    }
}
