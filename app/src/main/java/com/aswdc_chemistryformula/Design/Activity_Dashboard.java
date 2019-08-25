package com.aswdc_chemistryformula.Design;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aswdc_chemistryformula.DBHelper.DB_TopicName;
import com.aswdc_chemistryformula.R;
import com.aswdc_chemistryformula.Utility.Constant;

public class Activity_Dashboard extends AppCompatActivity {

    final String TAG = this.getClass().getName();

    Button btnCF;
    Button btnGL;
    Button btnconcepts;
    Button btnQR;
    Button btndeveloper;
    Button btnShare;
    LinearLayout mainLayout;
    Button btnSearch;
    Button btnFavourite;
    Button btnCalculate;
    EditText etSearch;
    Button btnPeriodicTable;

    DB_TopicName dbtn;
    Activity act;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__dashboard);

        setTitle("Chemistry Formula");
        init();
        btnCF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Activity_Dashboard.this, Activity_SubTopic.class);
                in.putExtra("TopicID", "1");
                in.putExtra("TopicName", "Chemical Formula");

                startActivity(in);
            }
        });
        btnGL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Activity_Dashboard.this, Activity_SubTopic.class);
                in.putExtra("TopicID", "2");
                in.putExtra("TopicName", "Gas Law");
                startActivity(in);

            }
        });
        btnconcepts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Activity_Dashboard.this, Activity_SubTopic.class);
                in.putExtra("TopicID", "3");
                in.putExtra("TopicName", "Concepts");

                startActivity(in);
            }
        });
        btnQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Activity_Dashboard.this, Activity_SubTopic.class);
                in.putExtra("TopicID", "4");
                in.putExtra("TopicName", "Quick Reference");

                startActivity(in);
            }
        });

        btndeveloper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Activity_Dashboard.this, Activity_Developer.class);
                startActivity(in);
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent share = new Intent();
                share.setAction("android.intent.action.SEND");
                share.setType("text/plain");
                share.putExtra("android.intent.extra.TEXT", Constant.SharedMessage+"");
                startActivity(share);
            }
        });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag=0;
                if(etSearch.getText().length()>2) {
                    Intent in = new Intent(Activity_Dashboard.this, Activity_Search.class);
                    in.putExtra("Name", etSearch.getText().toString() + "");
                    startActivity(in);
                }else
                {
                    flag=1;
                    Toast.makeText(Activity_Dashboard.this,"Make sure for valid serach",Toast.LENGTH_LONG).show();

                }
            }
        });

        btnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  in=new Intent(Activity_Dashboard.this,Activity_Favourite.class);
                in.putExtra("From","Favourite");
                in.putExtra("TopicID", "0");
                startActivity(in);

            }
        });
        btnPeriodicTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent periodictable= new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.aswdc_periodictable"));
                startActivity(periodictable);
            }
        });
//        btnCalculate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent  in=new Intent(Activity_Dashboard.this,Activity_Molecular.class);
//                in.putExtra("From","Calculate");
//                in.putExtra("TopicID", "0");
//                startActivity(in);
//
//            }
//        });

    }

    public void init() {

        mainLayout=(LinearLayout)findViewById(R.id.main);
        btnCF = (Button) findViewById(R.id.dashboard_btn_cf);
        btnGL = (Button) findViewById(R.id.dashboard_btn_gl);
        btnconcepts = (Button) findViewById(R.id.dashboard_btn_concepts);
        btnQR = (Button) findViewById(R.id.dashboard_btn_qr);
        btndeveloper = (Button) findViewById(R.id.dashboard_btn_developer);
        btnShare = (Button) findViewById(R.id.dashboard_btn_share);
        btnSearch=(Button) findViewById(R.id.dashboard_btn_searchmain);
        etSearch=(EditText)findViewById(R.id.dashboard_et_search);
        btnFavourite=(Button)findViewById(R.id.dashboard_btn_favourite);
        btnPeriodicTable=(Button)findViewById(R.id.dashboard_btn_periodictable);
       // btnCalculate=(Button)findViewById(R.id.dashboard_btn_molecular);
        act = this;
        dbtn = new DB_TopicName(this);

    }

    boolean twice;

    @Override
    public void onBackPressed() {

        if (twice == true) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);

        }
//        Toast.makeText(Activity_Dashboard.this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        Snackbar snackbar = Snackbar.make(mainLayout, "Please click BACK again to exit", Snackbar.LENGTH_LONG);
        snackbar.show();
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice = false;

            }
        }, 3000);
        twice = true;
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        etSearch.setText("");
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

}

