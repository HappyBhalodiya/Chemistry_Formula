package com.aswdc_chemistryformula.Design;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aswdc_chemistryformula.BuildConfig;
import com.aswdc_chemistryformula.R;
import com.aswdc_chemistryformula.Utility.Constant;

import java.util.Calendar;

public class Activity_Developer extends AppCompatActivity {
    TextView icmail, icweb, icshare, icapp, icrate, iclike, icupdate,iccopy,tvPrivacy;
    TextView appinfo;
    Toolbar tb;
    LinearLayout email, web, share, moreapps, rate, likefb, update;
    WebView wvdetail;
    Typeface tf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__developer);

        setTitle("Developer");
        tvPrivacy= (TextView) findViewById(R.id.dev_tv_privacy);
        icmail = (TextView) findViewById(R.id.dev_ic_mail);
        icweb = (TextView) findViewById(R.id.dev_ic_web);
        icshare = (TextView) findViewById(R.id.dev_ic_share);
        icapp = (TextView) findViewById(R.id.dev_ic_app);
        icrate = (TextView) findViewById(R.id.dev_ic_rate);
        iclike = (TextView) findViewById(R.id.dev_ic_like);
        icupdate = (TextView) findViewById(R.id.dev_ic_update);
        appinfo = (TextView) findViewById(R.id.dev_tv_appinfo);
        iccopy = (TextView) findViewById(R.id.dev_tv_copy);


        tf = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        icmail.setTypeface(tf);
        icweb.setTypeface(tf);
        icshare.setTypeface(tf);
        icapp.setTypeface(tf);
        icrate.setTypeface(tf);
        iclike.setTypeface(tf);
        icupdate.setTypeface(tf);
        appinfo.setTypeface(tf);
        iccopy.setTypeface(tf);

        email = (LinearLayout) findViewById(R.id.dev_email);
        web = (LinearLayout) findViewById(R.id.dev_web);
        share = (LinearLayout) findViewById(R.id.dev_share);
        moreapps = (LinearLayout) findViewById(R.id.dev_more_apps);
        rate = (LinearLayout) findViewById(R.id.dev_rate);
        likefb = (LinearLayout) findViewById(R.id.dev_like_fb);
        update = (LinearLayout) findViewById(R.id.dev_update);
        wvdetail = (WebView) findViewById(R.id.developer_wv_detail);

        wvdetail.loadData("<html><body align=\"justify\" style=\"font-size:15px;color:#747474\">ASWDC is Application, Software and Website Development Center @ Darshan Engineering College run by Students and Staff of Computer Engineering Department.<br><br> Sole purpose of ASWDC is to bridge gap between university curriculum &amp; industry demands. Students learn cutting edge technologies, develop real world application &amp; experiences professional environment @ ASWDC under guidance of industry experts &amp; faculty members", "text/html", "utf-8");

        appinfo.setText(getResources().getString(R.string.app_name) + " (v" + BuildConfig.VERSION_NAME + ")");
        String strInst = getString(R.string.inst_name).toString();
        iccopy.setText("\uf1f9 " + Calendar.getInstance().get(Calendar.YEAR) + " " + strInst);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailintent = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", Constant.ASWDCEmailAddress, null));
                emailintent.putExtra("android.intent.extra.SUBJECT", "Contact from " + getString(R.string.app_name));
                startActivity(Intent.createChooser(emailintent, "Send Email to ASWDC"));
            }
        });


        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webintent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.darshan.ac.in"));
                startActivity(webintent);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent();
                share.setAction("android.intent.action.SEND");
                share.setType("text/plain");
                share.putExtra("android.intent.extra.TEXT", Constant.SharedMessage+"");
                startActivity(share);
            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rateintent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id="+getPackageName()));
                startActivity(rateintent);
            }
        });

        moreapps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moreappsintent = new Intent("android.intent.action.VIEW", Uri.parse("market://sear" +
                        "" +
                        "ch?q=pub:Darshan+Institute+of+Engineering+%26+Technology"));
                startActivity(moreappsintent);
            }
        });

        likefb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fbintent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.facebook.com/DarshanInstitute.Official"));
                startActivity(fbintent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateintent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id="+getPackageName()));
                startActivity(updateintent);
            }
        });
        tvPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webintent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.darshan.ac.in/DIET/ASWDC-Mobile-Apps/Privacy-Policy-General"));
                startActivity(webintent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        wvdetail.destroy();
    }


}
