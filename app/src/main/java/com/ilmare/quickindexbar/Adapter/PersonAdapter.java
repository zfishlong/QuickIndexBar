package com.ilmare.quickindexbar.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ilmare.quickindexbar.Beans.Person;
import com.ilmare.quickindexbar.R;
import com.ilmare.quickindexbar.Utils.Cheeses;
import com.ilmare.quickindexbar.ViewHolder.ListItemHolder;

import java.util.List;

/**
 * Created by zhangchenggeng
 * Time 2016/5/6 9:36.
 * Descripton:
 * History:
 * 版权所有
 */
public class PersonAdapter extends BaseAdapter {


    private Context context;
    List<Person> personList;

    public PersonAdapter( List<Person> personList,Context context) {
        this.context = context;
        this.personList=personList;
    }

    @Override
    public int getCount() {
        return Cheeses.NAMES.length;
    }

    @Override
    public Object getItem(int position) {
        return  Cheeses.NAMES[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemHolder holder=null;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.list_item,null);
            holder=new ListItemHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ListItemHolder) convertView.getTag();
        }


        Person person=personList.get(position);

        String str = null;
        String currentLetter = person.getPingyin().charAt(0) + "";
        // 根据上一个首字母,决定当前是否显示字母
        if(position == 0){
            str = currentLetter;
        }else {
            // 上一个人的拼音的首字母
            String preLetter = personList.get(position - 1).getPingyin().charAt(0) + "";
            if(!TextUtils.equals(preLetter, currentLetter)){
                str = currentLetter;
            }
        }
        // 根据str是否为空,决定是否显示索引栏
        holder.getLetter().setVisibility(str == null ? View.GONE : View.VISIBLE);
        holder.getLetter().setText(currentLetter);
        holder.getTvName().setText(person.getName());
        return convertView;

    }
}
