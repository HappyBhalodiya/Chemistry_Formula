package com.aswdc_chemistryformula.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aswdc_chemistryformula.Been.Bean_Formula;
import com.aswdc_chemistryformula.Been.Bean_Molecular;
import com.aswdc_chemistryformula.Been.Bean_SubTopic;
import com.aswdc_chemistryformula.Been.Bean_TopicName;
import com.aswdc_chemistryformula.Utility.Constant;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

/**
 * Created by SAGAR on 15-12-2017.
 */

public class DB_TopicName extends SQLiteAssetHelper {
    public DB_TopicName(Context context) {
        super(context, Constant.DB_Name, null, Constant.DB_Version);
    }

    public ArrayList<Bean_TopicName> SelectAllTopicName()
    {
        ArrayList<Bean_TopicName> arrayTopicName=new ArrayList<Bean_TopicName>();
        SQLiteDatabase db=getReadableDatabase();
        String strQuery="Select * from MST_Topic order by TopicID";
        Cursor cur=db.rawQuery(strQuery,null);
        if (cur.moveToFirst()) {
            do {
                Bean_TopicName bc = new Bean_TopicName();
                bc.setTopicID(cur.getInt(cur.getColumnIndex("TopicID")));
              //  Log.d("TopicID",bc.getTopicID()+"");
                bc.setTopicName(cur.getString(cur.getColumnIndex("TopicName")));
                bc.setTopicDisplayName(cur.getString(cur.getColumnIndex("TopicDisplayName")));
                arrayTopicName.add(bc);
            } while (cur.moveToNext());
        }

        db.close();
        return arrayTopicName;
    }
    public ArrayList<Bean_SubTopic> SelectAllSubTopicByID(int id)
    {
        ArrayList<Bean_SubTopic> arrayTopicName=new ArrayList<Bean_SubTopic>();
        SQLiteDatabase db=getReadableDatabase();
        String strQuery="Select TopicID,SubTopicID,SubTopicName,SubTopicDisplayName  from MST_SubTopic where TopicID="+id+" order by SubTopicDisplayName ASC";

        Cursor cur=db.rawQuery(strQuery,null);
        if (cur.moveToFirst()) {
            do {
                Bean_SubTopic bc = new Bean_SubTopic();
                bc.setSubTopicID(cur.getInt(cur.getColumnIndex("SubTopicID")));
                bc.setTopicID(cur.getInt(cur.getColumnIndex("TopicID")));
                bc.setSubTopicName(cur.getString(cur.getColumnIndex("SubTopicName")));
                bc.setSubTopicDisplayName(cur.getString(cur.getColumnIndex("SubTopicDisplayName")));
                arrayTopicName.add(bc);
            } while (cur.moveToNext());
        }

        db.close();
        return arrayTopicName;
    }


    public String getDescription(int id)

    {
        SQLiteDatabase db=getReadableDatabase();
        String strQuery="Select * from MST_SubTopic where SubTopicID="+id;
        Cursor cur=db.rawQuery(strQuery,null);
        cur.moveToFirst();
        return (cur.getString(cur.getColumnIndex("SubTopicName")));

    }

    public ArrayList<Bean_Formula>SelectAllFormulaByID(int id)
    {
        ArrayList<Bean_Formula> arrayformula=new ArrayList<Bean_Formula>();
        SQLiteDatabase db=getReadableDatabase();
        String strQuery="Select FormulaID,SubTopicID,DescTable,Formula,TopicDescription from MST_Formula where SubTopicID='"+id+"'";
        Cursor cur=db.rawQuery(strQuery,null);

        if (cur.moveToFirst()) {
            do {
                Bean_Formula bc = new Bean_Formula();
                bc.setSubTopicID(cur.getInt(cur.getColumnIndex("SubTopicID")));
                bc.setFormulaID(cur.getInt(cur.getColumnIndex("FormulaID")));
                bc.setTopicDescription(cur.getString(cur.getColumnIndex("TopicDescription")));
                bc.setFormula(cur.getString(cur.getColumnIndex("Formula")));
                bc.setDescTable(cur.getString(cur.getColumnIndex("DescTable")));
                arrayformula.add(bc);
            } while (cur.moveToNext());
        }

        db.close();
        return arrayformula;
    }

    public String getdes(int id)

    {
        SQLiteDatabase db=getReadableDatabase();
        String strQuery="Select * from MST_Formula where FormulaID="+id;
        Cursor cur=db.rawQuery(strQuery,null);
        cur.moveToFirst();
        return (cur.getString(cur.getColumnIndex("Formula")));

    }

    public String getdesc(int id)

    {
        SQLiteDatabase db=getReadableDatabase();
        String strQuery="Select * from MST_Formula where FormulaID="+id;
        Cursor cur=db.rawQuery(strQuery,null);
        cur.moveToFirst();
        return (cur.getString(cur.getColumnIndex("TopicDescription")));

    }
    public String getdescr(int id)

    {
        SQLiteDatabase db=getReadableDatabase();
        String strQuery="Select * from MST_Formula where FormulaID="+id;
        Cursor cur=db.rawQuery(strQuery,null);
        cur.moveToFirst();
        return (cur.getString(cur.getColumnIndex("DescTable")));

    }

    public ArrayList<Bean_SubTopic> SelectName(String name) {
        ArrayList<Bean_SubTopic> arrayChapter = new ArrayList<Bean_SubTopic>();
        SQLiteDatabase db = getReadableDatabase();
        String strQuery = "Select * from MST_SubTopic where SubTopicName like '%"+name+"%'";
        Cursor cur = db.rawQuery(strQuery, null);
        if (cur.moveToFirst()) {
            do {
                Bean_SubTopic bc = new Bean_SubTopic();
                bc.setSubTopicID(cur.getInt(cur.getColumnIndex("SubTopicID")));
                bc.setSubTopicName(cur.getString(cur.getColumnIndex("SubTopicName")));
                arrayChapter.add(bc);

            } while (cur.moveToNext());
        }
        db.close();
        return arrayChapter;
    }

    public ArrayList<Bean_SubTopic>SelectAllFav()
    {
        ArrayList<Bean_SubTopic> arrayformula=new ArrayList<Bean_SubTopic>();
        SQLiteDatabase db=getReadableDatabase();
        String strQuery="Select * from MST_SubTopic where IsFavourite=1 order by SubTopicID";
        Cursor cur=db.rawQuery(strQuery,null);

        if (cur.moveToFirst()) {
            do {
                Bean_SubTopic bc = new Bean_SubTopic();
                bc.setSubTopicID(cur.getInt(cur.getColumnIndex("SubTopicID")));
                bc.setSubTopicName(cur.getString(cur.getColumnIndex("SubTopicName")));
                bc.setIsFavourite(cur.getInt(cur.getColumnIndex("IsFavourite")));
                arrayformula.add(bc);
            } while (cur.moveToNext());
        }

        db.close();
        return arrayformula;
    }


    public void updateFavourite(int id ,int a)
    {
        SQLiteDatabase db =getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("IsFavourite",a);
        db.update("MST_SubTopic",cv,"SubTopicID="+id,null);
        db.close();
    }
    public int isfavourit(int id){
        SQLiteDatabase db=getReadableDatabase();
        String strQuery="Select IsFavourite from MST_SubTopic where SubTopicID="+id+"";
        Cursor cur=db.rawQuery(strQuery,null);
        cur.moveToNext();
        return cur.getInt(cur.getColumnIndex("IsFavourite"));
    }


    public ArrayList<Bean_Molecular> SelectAllElement(int id)
    {
        ArrayList<Bean_Molecular> arrayElement=new ArrayList<Bean_Molecular>();
        SQLiteDatabase db=getReadableDatabase();
        String strQuery="Select * from MST_Molecular order by AtomicID";
        Cursor cur=db.rawQuery(strQuery,null);
        if (cur.moveToFirst()) {
            do {
                Bean_Molecular be = new Bean_Molecular();
                be.setAtomicID(cur.getInt(cur.getColumnIndex("AtomicID")));
                be.setAtomicMass(cur.getString(cur.getColumnIndex("AtomicMass")));
                be.setElementSymbol(cur.getString(cur.getColumnIndex("ElementSymbol")));
                arrayElement.add(be);
            } while (cur.moveToNext());
        }

        db.close();
        return arrayElement;
    }
    public String getelement(int id)

    {
        SQLiteDatabase db=getReadableDatabase();
        String strQuery="Select * from MST_Molecular where AtomicID="+id;
        Cursor cur=db.rawQuery(strQuery,null);
        cur.moveToFirst();
        return (cur.getString(cur.getColumnIndex("AtomicMass")));

    }
}