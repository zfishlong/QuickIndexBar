package com.ilmare.quickindexbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ListView;

import com.ilmare.quickindexbar.Adapter.PersonAdapter;
import com.ilmare.quickindexbar.Beans.Person;
import com.ilmare.quickindexbar.Utils.Cheeses;
import com.ilmare.quickindexbar.Utils.Utils;
import com.ilmare.quickindexbar.View.QuickIndexBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private   QuickIndexBar quickIndexBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        quickIndexBar = (QuickIndexBar) findViewById(R.id.quick_index_bar);
        listView = (ListView) findViewById(R.id.lv_list);

        final List<Person> personList=new ArrayList<>();
        for(int i=0;i< Cheeses.NAMES.length;i++){
            personList.add(new Person(Cheeses.NAMES[i]));
        }
        Collections.sort(personList);


        quickIndexBar.setOnLetterChangedListener(new QuickIndexBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                Utils.showToast(MainActivity.this, letter);
                // 根据字母定位ListView, 找到集合中第一个以letter为拼音首字母的对象,得到索引
                for (int i = 0; i < personList.size(); i++) {
                    Person person = personList.get(i);
                    String l = person.getPingyin().charAt(0) + "";
                    if (TextUtils.equals(letter, l)) {
                        // 匹配成功
                        listView.setSelection(i);
                        break;
                    }
                }
            }
        });






        listView.setAdapter(new PersonAdapter(personList,this));
    }
}
