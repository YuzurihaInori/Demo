package com.kding.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    private TableAdapter tableAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        List<TableData> data = new ArrayList<>();

        data.add(new TableData("111","",""));
        data.add(new TableData("222","",""));
        data.add(new TableData("333","",""));
        data.add(new TableData("444","",""));
        data.add(new TableData("555","",""));
        data.add(new TableData("666","",""));
        data.add(new TableData("777","",""));
        data.add(new TableData("888","",""));
        data.add(new TableData("999","",""));
        data.add(new TableData("aaa","",""));
        data.add(new TableData("bbb","",""));
        data.add(new TableData("ccc","",""));
        data.add(new TableData("ddd","",""));
        data.add(new TableData("eee","",""));
        data.add(new TableData("fff","",""));
        data.add(new TableData("ggg","",""));
        data.add(new TableData("hhh","",""));
        data.add(new TableData("ttt","",""));
        data.add(new TableData("uuu","",""));
        data.add(new TableData("iii","",""));
        data.add(new TableData("ooo","",""));
        data.add(new TableData("ppp","",""));
        data.add(new TableData("nnn","",""));
        data.add(new TableData("mmm","",""));
        data.add(new TableData("qqq","",""));


        ListView tableList = (ListView) findViewById(R.id.table_list);
         tableAdapter= new TableAdapter(this,data);
        tableList.setAdapter(tableAdapter);
    }
}
