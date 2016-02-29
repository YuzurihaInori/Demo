package com.kding.demo;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Toast-pc on 2016/2/25.
 */
public class TableAdapter extends BaseAdapter{

    private final Context context;
    private final List<TableData> data;

    public TableAdapter(Context context, List<TableData> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TableHolder tableHolder = null;
        if (convertView == null){
            tableHolder = new TableHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_table,null);
            tableHolder.tableEt1 = (TextView) convertView.findViewById(R.id.table_et1);
            tableHolder.tableEt2 = (EditText) convertView.findViewById(R.id.table_et2);
            tableHolder.tableEt3 = (EditText) convertView.findViewById(R.id.table_et3);
            convertView.setTag(tableHolder);
        }else {
            tableHolder = (TableHolder) convertView.getTag();
        }

//        tableHolder.tableEt1.setText("123");
//        tableHolder.tableEt2.setText("123");
        tableHolder.tableEt1.setText(data.get(position).text1);
        tableHolder.tableEt2.setText(data.get(position).text2);
        tableHolder.tableEt3.setText(data.get(position).text3);
        tableHolder.tableEt1.addTextChangedListener(new ItemWatcher(tableHolder,data.get(position),position));

        tableHolder.tableEt2.addTextChangedListener(new ItemWatcher(tableHolder,data.get(position),position));


        return convertView;
    }

    private class ItemWatcher implements TextWatcher{
        private final TableHolder tableHolder;
        private final TableData tableData;
        private final int position;

        public ItemWatcher(TableHolder tableHolder,TableData tableData,int position) {
            this.tableHolder =  tableHolder;
            this.tableData = tableData;
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            System.out.println("---------------"+position);
            String num1 ="";
            int num2 = 0;
            String text1 = tableHolder.tableEt1.getText().toString();
            String text2 = tableHolder.tableEt2.getText().toString();
            if (!text1.equals("")){
                num1 = text1;
            }
            if (!text2.equals("")){
                num2 = Integer.valueOf(text2);
            }
            tableHolder.tableEt3.setText(String.valueOf(num1+num2));
        }
        @Override
        public void afterTextChanged(Editable s) {
            tableData.text2 = tableHolder.tableEt2.getText().toString();
            tableData.text3 = tableHolder.tableEt3.getText().toString();
        }
    }

    public static class TableHolder {
        public TextView tableEt1;
        public EditText tableEt2;
        public EditText tableEt3;
    }
}
