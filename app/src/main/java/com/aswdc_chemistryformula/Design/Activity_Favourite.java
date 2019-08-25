package com.aswdc_chemistryformula.Design;

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

public class Activity_Favourite extends AppCompatActivity {

    private ArrayList<Bean_SubTopic> subTopicList = new ArrayList<>();
    private RecyclerView rvSubTopic;
    private Adapter_SubTopic stAdapter;
    DB_TopicName db_topicname;
    int subtopic;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        setTitle("Favourite");





        db_topicname = new DB_TopicName(this);
        rvSubTopic=(RecyclerView)findViewById(R.id.favourite_rv_list);

        RecyclerView.LayoutManager mLayoutManager= new LinearLayoutManager(getApplicationContext());
        rvSubTopic.setLayoutManager(mLayoutManager);
        rvSubTopic.setItemAnimator(new DefaultItemAnimator());


    }

    @Override
    protected void onResume() {
        super.onResume();
        subTopicList=db_topicname.SelectAllFav();
        stAdapter =new Adapter_SubTopic(this,subTopicList);
        rvSubTopic.setAdapter(stAdapter);
        stAdapter.notifyDataSetChanged();

    }
}
