


package com.aswdc_chemistryformula.Design;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aswdc_chemistryformula.Adapter.Adapter_SubTopic;
import com.aswdc_chemistryformula.Been.Bean_SubTopic;
import com.aswdc_chemistryformula.DBHelper.DB_TopicName;
import com.aswdc_chemistryformula.R;

import java.util.ArrayList;

public class Activity_SubTopic extends AppCompatActivity {
    RecyclerView rvSubTopicList;
    DB_TopicName dbtn;
    Adapter_SubTopic rAdapter;
    ArrayList<Bean_SubTopic> arraysubtopicName;
    Activity act;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__sub_topic);
        setTitle(getIntent().getStringExtra("TopicName"));
        init();
        arraysubtopicName = dbtn.SelectAllSubTopicByID(Integer.parseInt(getIntent().getStringExtra("TopicID")));
        rAdapter = new Adapter_SubTopic(this, arraysubtopicName);
        RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvSubTopicList.setLayoutManager(rLayoutManager);
        rvSubTopicList.setItemAnimator(new DefaultItemAnimator());
        rvSubTopicList.setAdapter(rAdapter);

    }
    public void init()
    {
        act = this;
        rvSubTopicList = (RecyclerView) findViewById(R.id.subtopic_rv_list);
        dbtn = new DB_TopicName(this);

    }

}

