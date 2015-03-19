package com.objectivelyradical.creepycrawler;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;


public class SearchActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ProgressDialog loading = ProgressDialog.show(this, "Loading",
                "Please wait while we load absolute terror...", true);
        new Thread() {
            public void run() {
                loadCreepyPasta();
                loading.dismiss();
            }
        }.start();
        setContentView(R.layout.activity_search);
        generateCategoryCheckboxes();
    }

    private void loadCreepyPasta() {
        if(Globals.creepyPasta != null) return;
        try {

            InputStream is = getAssets().open("cookedpasta.cc");
            ObjectInputStream ois = new ObjectInputStream(is);
            Globals.creepyPasta = (ArrayList<CreepyPasta>)ois.readObject();

            is.close();
            System.out.println("Loaded pasta successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateCategoryCheckboxes() {
       GridLayout layout = (GridLayout)findViewById(R.id.categoryViewGroup);

        for(String category : Globals.categories) {
            CategoryCheckBox cb = new CategoryCheckBox(getBaseContext(), category);
            layout.addView(cb);
            cb.setTextColor(((TextView) findViewById(R.id.pastaNameLabel)).getCurrentTextColor());
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void searchButtonClicked(View button) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("NAME", ((EditText)findViewById(R.id.pastaName)).getText().toString());
        intent.putExtra("MIN_WORDS", ((EditText)findViewById(R.id.minWordCount)).getText().toString());
        intent.putExtra("MAX_WORDS", ((EditText)findViewById(R.id.maxWordCount)).getText().toString());
        ArrayList<String> selectedCategories = new ArrayList<String>();
        GridLayout layout = (GridLayout)findViewById(R.id.categoryViewGroup);
        for(int i = 0; i < layout.getChildCount(); i++) {
            CheckBox box = (CheckBox)layout.getChildAt(i);
            if(box.isChecked()) {
                selectedCategories.add(box.getText().toString());
            }
        }
        System.out.println("SELECTED CATEGORIES: " + selectedCategories.size());
        intent.putStringArrayListExtra("SELECTED_CATEGORIES", selectedCategories);
        startActivity(intent);
    }
}
