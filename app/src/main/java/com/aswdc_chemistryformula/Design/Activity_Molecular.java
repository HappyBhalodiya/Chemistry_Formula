package com.aswdc_chemistryformula.Design;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aswdc_chemistryformula.Been.Bean_Formula;
import com.aswdc_chemistryformula.Been.Bean_Molecular;
import com.aswdc_chemistryformula.DBHelper.DB_TopicName;
import com.aswdc_chemistryformula.R;

import java.util.ArrayList;

public class Activity_Molecular extends AppCompatActivity {

    EditText etCalculate;
    TextView tvCalculate;
    Button btAns;
    DB_TopicName dbtn;
    ArrayList<Bean_Molecular> arrayMoleculer;
    int id;
    Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__molecular);
        id = Integer.parseInt(getIntent().getStringExtra("AtomicID"));
        arrayMoleculer = dbtn.SelectAllElement(id);
        tvCalculate.setText(dbtn.getelement(id));


//        etCalculate=(EditText)findViewById(R.id.Molecular_et_calculate);
//        btAns=(Button)findViewById(R.id.Molecular_bt_calculate);
       // tvCalculate=(TextView)findViewById(R.id.Molecular_tv_ans);

    }
}
