package ttyy.com.commonanno.demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Author: hjq
 * Date  : 2017/06/20 20:13
 * Name  : DemoAdapter
 * Intro : Edit By hjq
 * Version : 1.0
 */
public class DemoAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_text, null);
        }

        return convertView;
    }
}
