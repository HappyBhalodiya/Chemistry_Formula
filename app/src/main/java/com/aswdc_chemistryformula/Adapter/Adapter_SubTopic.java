package com.aswdc_chemistryformula.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.aswdc_chemistryformula.Design.Activity_Formula;
import com.aswdc_chemistryformula.Been.Bean_SubTopic;
import com.aswdc_chemistryformula.R;

import java.util.ArrayList;

/**
 * Created by SAGAR on 15-12-2017.
 */

public class Adapter_SubTopic extends RecyclerView.Adapter<Adapter_SubTopic.MyViewHolder> {

    Activity act;
    Typeface tf;
    ProgressDialog mProgressDialog;
    ArrayList<Bean_SubTopic> arraysubtopic;





    public Adapter_SubTopic(Activity ct, ArrayList<Bean_SubTopic> arraysubtopic) {
        act = ct;
        this.arraysubtopic = arraysubtopic;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        LayoutInflater myInflater = LayoutInflater.from(act);
        View MyOwnView = myInflater.inflate(R.layout.lst_design_subtopic, parent, false);
        return new Adapter_SubTopic.MyViewHolder(MyOwnView);




    }


    @Override
    public void onBindViewHolder(Adapter_SubTopic.MyViewHolder holder, int position) {
        init();
        String id=act.getIntent().getStringExtra("TopicID").toString()+"".trim();

        if (position % 2 == 0) {
            holder.linearLayout.setBackgroundColor(act.getResources().getColor(R.color.white));
        } else {
            holder.linearLayout.setBackgroundColor(act.getResources().getColor(R.color.gray));
        }

        tf = Typeface.createFromAsset(act.getAssets(), "fontawesome-webfont.ttf");
        MyViewHolder.txtFont.setTypeface(tf);
        switch(id){
            case "1":
                holder.imgIcon.setImageResource(R.drawable.chemical);
                break;
            case "2":
                holder.imgIcon.setImageResource(R.drawable.gl);
                break;
            case "3":
                holder.imgIcon.setImageResource(R.drawable.concepts);
                break;
            case "4":
                holder.imgIcon.setImageResource(R.drawable.quick);
                break;

            default:
                holder.imgIcon.setImageResource(R.drawable.sub);

        }

        holder.tvSubtopicName.setText(Html.fromHtml(arraysubtopic.get(position).getSubTopicName()));
        holder.tvSubtopicID.setText(arraysubtopic.get(position).getSubTopicID() + "");
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = ((TextView) view.findViewById(R.id.subtopic_rv_id)).getText().toString();
                String name= ((TextView) view.findViewById(R.id.subtopic_rv_name)).getText().toString();

                Intent in = new Intent(act, Activity_Formula.class);
                in.putExtra("SubTopicID", id);
                in.putExtra("SubTopicName", name);
                act.startActivity(in);


            }
        });



    }


    @Override
    public int getItemCount() {
        return arraysubtopic.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvSubtopicName;
        public TextView tvSubtopicID;
        public LinearLayout linearLayout;
        public LinearLayout mainLinearLayout;
        static TextView txtFont;
        public ImageView imgIcon;



        public MyViewHolder(View itemView) {
            super(itemView);

            imgIcon=(ImageView) itemView.findViewById(R.id.subtopic_img_icon);
            tvSubtopicName = (TextView) itemView.findViewById(R.id.subtopic_rv_name);
            tvSubtopicID = (TextView) itemView.findViewById(R.id.subtopic_rv_id);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.subtopic_linearLayout);
            MyViewHolder.txtFont = (TextView) itemView.findViewById(R.id.recsubtopic_ll_arrow);
            mainLinearLayout = (LinearLayout) itemView.findViewById(R.id.subtopic_main_linearLayout);

        }

    }



    void init() {

    }

    public void webClient(WebView mv) {
        mProgressDialog = new ProgressDialog(act);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading");
        mProgressDialog.setCancelable(false);
        mv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgressDialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
            }
        });
    }


}
