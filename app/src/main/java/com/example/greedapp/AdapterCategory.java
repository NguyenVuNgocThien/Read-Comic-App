package com.example.greedapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.example.greedapp.Model.Category;

import java.util.ArrayList;
import java.util.List;

public class AdapterCategory extends BaseAdapter {
    Context context;
String list_category[];
List<Category> list;
ArrayList<CheckBox> checkBoxes;
LayoutInflater inflater;


    public AdapterCategory(ArrayList<CheckBox> checkBoxes,Context context, String[] list_category, List<Category> list) {
        this.context = context;
        this.list_category = list_category;
        this.list=list;
        this.checkBoxes=checkBoxes;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list_category.length/3;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.activity_list_category1,null);
        CheckBox cb1=(CheckBox)view.findViewById(R.id.checkBox1);
        CheckBox cb2=(CheckBox)view.findViewById(R.id.checkBox2);
        CheckBox cb3=(CheckBox)view.findViewById(R.id.checkBox3);
        if(i<=list_category.length/3){
            cb1.setText(list_category[i*3]);
            checkBoxes.add(cb1);
            cb2.setText(list_category[i*3+1]);
            checkBoxes.add(cb2);
            cb3.setText(list_category[i*3+2]);
            checkBoxes.add(cb3);
        }
        return  view;
    }
}