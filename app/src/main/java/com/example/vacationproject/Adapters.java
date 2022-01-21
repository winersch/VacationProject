package com.example.vacationproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapters extends BaseAdapter {
    private Context mContext;
    private ArrayList<NewsData> arrayList;

    public Adapters(Context context, ArrayList<NewsData> arrayList) {
        this.arrayList = arrayList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.xml_list_item_layout,parent,false);
        }

        if(arrayList.get(position).isHasImg() && arrayList.get(position).getImg() != null){

            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView1);
            ImageLoadTask task = new ImageLoadTask("https:"+arrayList.get(position).getImg(),imageView);
            task.execute();

        }

        TextView textView1 = (TextView) convertView.findViewById(R.id.textView1);
        TextView textView2 = (TextView) convertView.findViewById(R.id.textView2);

        textView1.setText((String) arrayList.get(position).getTitle());
        textView2.setText((String) arrayList.get(position).getDescription());

        return convertView;
    }
}
