package com.aswdc_chemistryformula.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aswdc_chemistryformula.Been.Bean_SubTopic;
import com.aswdc_chemistryformula.Design.Activity_Formula;
import com.aswdc_chemistryformula.Design.Activity_SubTopic;
import com.aswdc_chemistryformula.R;

import java.util.List;

import io.github.kexanie.library.MathView;

/**
 * Created by SAGAR on 15-01-2018.
 */

public class Adapter_Search extends RecyclerView.Adapter<Adapter_Search.MyViewHolder> {

    Context ctx;
    Typeface tf;

    private List<Bean_SubTopic> subTopicList;


    public Adapter_Search(List<Bean_SubTopic> subChapterList, Activity act) {
        this.subTopicList = subChapterList;
        this.ctx=act;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lst_design_subtopic, parent, false);

        return new Adapter_Search.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Bean_SubTopic beanSubTopic = subTopicList.get(position);
        holder.tvSubtopicID.setText(beanSubTopic.getSubTopicID() + "");
        holder.tvSubtopicName.setText(Html.fromHtml(subTopicList.get(position).getSubTopicName()));
        holder.imgSerach.setImageResource(R.mipmap.ic_search2);

        if(position %2==0)
            holder.linearLayout.setBackgroundResource(R.color.white);
        else
            holder.linearLayout.setBackgroundResource(R.color.gray);

        tf=Typeface.createFromAsset(ctx.getAssets(),"fontawesome-webfont.ttf");
        Adapter_SubTopic.MyViewHolder.txtFont.setTypeface(tf);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String id = ((TextView) v.findViewById(R.id.subtopic_rv_id)).getText().toString();
                String title= ((TextView) v.findViewById(R.id.subtopic_rv_name)).getText().toString();
                Intent in = new Intent(ctx, Activity_Formula.class);
                in.putExtra("SubTopicID", id);
                in.putExtra("SubTopicName", title);
                ctx.startActivity(in);
            }
        });

    }

    @Override
    public int getItemCount() {
        return subTopicList.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{

        public TextView tvSubtopicName;
        public TextView tvSubtopicID;
        public LinearLayout linearLayout;
        public LinearLayout mainLinearLayout;
        public ImageView imgSerach;
        TextView txtFont;

        public MyViewHolder(View itemView) {
            super(itemView);
            init(itemView);
            Adapter_SubTopic.MyViewHolder.txtFont=(TextView)itemView.findViewById(R.id.recsubtopic_ll_arrow);
            itemView.setTag(itemView);
        }

        public void init(View view)
        {
            tvSubtopicName = (TextView) itemView.findViewById(R.id.subtopic_rv_name);
            tvSubtopicID = (TextView) itemView.findViewById(R.id.subtopic_rv_id);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.subtopic_linearLayout);
            Adapter_SubTopic.MyViewHolder.txtFont = (TextView) itemView.findViewById(R.id.recsubtopic_ll_arrow);
            mainLinearLayout = (LinearLayout) itemView.findViewById(R.id.subtopic_main_linearLayout);
            imgSerach=(ImageView)itemView.findViewById(R.id.subtopic_img_icon);
        }
    }
}
