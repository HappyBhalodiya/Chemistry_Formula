package com.aswdc_chemistryformula.Design;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.aswdc_chemistryformula.Been.Bean_Formula;
import com.aswdc_chemistryformula.Been.Bean_SubTopic;
import com.aswdc_chemistryformula.DBHelper.DB_TopicName;
import com.aswdc_chemistryformula.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import io.github.kexanie.library.MathView;

public class Activity_Formula extends AppCompatActivity {

    MathView mvFormula;
    MathView mvDesc;
    CardView cvFor;
    TextView tvFormula;
    DB_TopicName dbtn;
    ArrayList<Bean_Formula> arrayFormula;
    Activity act;
    File picFile;
    Bean_SubTopic bst;
    int id, isFav;
    MenuItem miFav;
    WebView wvTable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__formula);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getIntent().getStringExtra("SubTopicName"));
        id = Integer.parseInt(getIntent().getStringExtra("SubTopicID"));
        init();
        webConfig();
        isFav = dbtn.isfavourit(id);
        arrayFormula = dbtn.SelectAllFormulaByID(id);
        mvFormula.setText(dbtn.getdes(id));
        //  mvDesc.setText(dbtn.getdesc(id));
        if (dbtn.getdes(id).toString().equals("-99")) {
            cvFor.setVisibility(View.GONE);
            tvFormula.setVisibility(View.GONE);

        }

       /* strTable = "";
        strTable = formula.getDescTable();
*/
        String text;
        text = "<html><body  style=\"text-align:justify;\">";
        text += (dbtn.getdescr(id));
        text += "</body></html>";

//        wvTable.getSettings().setUseWideViewPort(true);
//        wvTable.getSettings().setSupportZoom(true);
        wvTable.loadData(dbtn.getdesc(id), "text/html", "UTF-8");
       /*if (strTable.length() < 10) {
            wvTable.setVisibility(View.GONE);
        } else {
            wvTable.setVisibility(View.VISIBLE);
            AssetManager assetManager = getAssets();
            InputStream is = null;
            try {
                is = assetManager.open(String.valueOf(strTable));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/


    }


    void init() {

        mvFormula = (MathView) findViewById(R.id.formula_mv_formula);
        mvDesc = (MathView) findViewById(R.id.formula_mv_content);
      //  cvFor = (CardView) findViewById(R.id.formula_cv_formula);
        tvFormula = (TextView) findViewById(R.id.formula_tv_for);
        wvTable=(WebView)findViewById(R.id.formula_wv_id);

        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Formula Loading");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        act = this;
        dbtn = new DB_TopicName(this);
        Runnable progressRunnable = new Runnable() {
            @Override
            public void run() {
                mProgressDialog.cancel();
            }
        };
        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 5000);
    }

    void webConfig() {

        mvFormula.config("MathJax.Hub.Config({\n CommonHTML: { linebreaks: { automatic: true } },\n" +
                "  \"HTML-CSS\": { linebreaks: { automatic: true } },\n" +
                "         SVG: { linebreaks: { automatic: true } }\n" +
                "});");
        mvDesc.config("MathJax.Hub.Config({\n CommonHTML: { linebreaks: { automatic: true } },\n" +
                "  \"HTML-CSS\": { linebreaks: { automatic: true } },\n" +
                "         SVG: { linebreaks: { automatic: true } }\n" +
                "});");

        mvFormula.getSettings().setJavaScriptEnabled(true);
        mvFormula.getSettings().setLoadWithOverviewMode(true);
        mvDesc.getSettings().setJavaScriptEnabled(true);
        mvDesc.getSettings().setLoadWithOverviewMode(true);
        webClient(mvFormula);
        webClient(mvDesc);
    }

    public void webClient(MathView mv) {
        mv.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.share:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 123);
                } else {
                    Bitmap bitmap = takeScreenshot();
                    saveBitmap(bitmap);
                    shareIt();
                }
                break;
            case R.id.fav:
                if (isFav == 1) {
                    miFav.setIcon(R.mipmap.ic_fav_light);
                    dbtn.updateFavourite(id, 0);
                    isFav = 0;
                    Toast.makeText(getApplicationContext(), "Remove From Favourite List", Toast.LENGTH_SHORT).show();

                } else {
                    miFav.setIcon(R.mipmap.ic_favwhite);
                    dbtn.updateFavourite(id, 1);
                    isFav = 1;
                    Toast.makeText(getApplicationContext(), "Add To Favourite List", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);
        miFav=menu.findItem(R.id.fav);
        if (isFav == 1)
            miFav.setIcon(R.mipmap.ic_favwhite);
        else
            miFav.setIcon(R.mipmap.ic_fav_light);
        return super.onCreateOptionsMenu(menu);
    }


   public Bitmap takeScreenshot() {
       View rootView = findViewById(R.id.formula_linearlayout).getRootView();
       rootView.setDrawingCacheEnabled(true);
       return rootView.getDrawingCache();
   }
   public void saveBitmap(Bitmap bitmap) {
       picFile = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
       FileOutputStream fos;
       try {
           fos = new FileOutputStream(picFile);
           bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
           boolean saved = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
           if (saved) {
               Toast.makeText(getApplicationContext(), "Image saved to your device Pictures " + "directory!", Toast.LENGTH_SHORT).show();
           } else {
               Toast.makeText(getApplicationContext(), "Image saved Error " + "directory!", Toast.LENGTH_SHORT).show();
           }

           fos.flush();
           fos.close();
       } catch (FileNotFoundException e) {
           Log.e("GREC", e.getMessage(), e);
       } catch (IOException e) {
           Log.e("GREC", e.getMessage(), e);
       }
   }

    private void shareIt() {
        Uri uri = Uri.fromFile(picFile);
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("image/*");

        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);

        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mvDesc.destroy();
        mvFormula.destroy();

    }
}




