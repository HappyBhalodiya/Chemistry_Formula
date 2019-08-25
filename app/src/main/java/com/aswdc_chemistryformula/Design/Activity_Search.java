package com.aswdc_chemistryformula.Design;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aswdc_chemistryformula.Adapter.Adapter_Search;
import com.aswdc_chemistryformula.Been.Bean_SubTopic;
import com.aswdc_chemistryformula.DBHelper.DB_TopicName;
import com.aswdc_chemistryformula.R;

import java.util.ArrayList;

public class Activity_Search extends AppCompatActivity {

    private Adapter_Search sAdapter;
    EditText etSearchList;
    RecyclerView rvSearchList;
    DB_TopicName db_topicName;
    SearchView svFormula;
    String str;
    DB_TopicName dbtn;
    int id;

    final Context context = this;
    Button button;
    EditText result;


    ArrayList<Bean_SubTopic> arraySubTopic;

    private ArrayList<Bean_SubTopic> SubTopicList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle("Search");

        DB_TopicName db_topicName=new DB_TopicName(this);

        init();
        String name=getIntent().getStringExtra("Name").toString().toLowerCase()+"";
        arraySubTopic = db_topicName.SelectName(name);


        dbtn = new DB_TopicName(this);

//        if(etSearchList.getText().toString().isEmpty()){
//
//            button = (Button) findViewById(R.id.verify);
//
//            // add button listener
//            button.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View arg0) {
//
//                    // get prompts.xml view
//                    LayoutInflater li = LayoutInflater.from(context);
//                    View promptsView = li.inflate(R.layout.design_popup, null);
//
//                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//                            context);
//
//                    // set prompts.xml to alertdialog builder
//                    alertDialogBuilder.setView(promptsView);
//
//
//                    button=(Button)promptsView.findViewById(R.id.verify);
//                    button.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent in=new Intent(Activity_Search.this,Activity_Search.class);
//                            startActivity(in);
//                        }
//                    });
//
//                    // set dialog message
//                    alertDialogBuilder
//                            .setCancelable(false);
//
//                    // create alert dialog
//                    AlertDialog alertDialog = alertDialogBuilder.create();
//
//                    // show it
//                    alertDialog.show();
//
//                }
//            });
//        }






        etSearchList.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String str = etSearchList.getText().toString().toLowerCase();

                arraySubTopic = new ArrayList<Bean_SubTopic>();

                for (int x = 0; x < SubTopicList.size(); x++)
                {
                    if (SubTopicList.get(x).getSubTopicName().toLowerCase().contains(str.toLowerCase()))
                        arraySubTopic.add(SubTopicList.get(x));
                }

                sAdapter = new Adapter_Search(arraySubTopic,Activity_Search.this);
                RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(getApplicationContext());
                rvSearchList.setLayoutManager(rLayoutManager);
                rvSearchList.setItemAnimator(new DefaultItemAnimator());
                rvSearchList.setAdapter(sAdapter);
                sAdapter.notifyDataSetChanged();
            }



            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // To display all sub chapters
        SubTopicList = db_topicName.SelectName(name);
        sAdapter = new Adapter_Search(arraySubTopic, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvSearchList.setLayoutManager(mLayoutManager);
        rvSearchList.setItemAnimator(new DefaultItemAnimator());
        rvSearchList.setAdapter(sAdapter);
        sAdapter.notifyDataSetChanged();
    }
    public void init()
    {
        rvSearchList = (    RecyclerView) findViewById(R.id.search_rv_topicsearch);
        etSearchList = (EditText) findViewById(R.id.search_et_topicsearch);
        db_topicName = new DB_TopicName(this);
        registerForContextMenu(rvSearchList);
    }

}

