package com.objectivelyradical.creepycrawler;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.internal.util.Predicate;

import java.util.ArrayList;
import java.util.Iterator;


public class ResultActivity extends ActionBarActivity {
    ArrayList<CreepyPasta> pastas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        //final ProgressDialog loading = ProgressDialog.show(this, "Searching...",
              //  "Please wait while we fetch your results...", true);
        //new Thread() {
           // public void run() {
                Intent intent = getIntent();
                String name = intent.getStringExtra("NAME");
                String minWords = intent.getStringExtra("MIN_WORDS");
                String maxWords = intent.getStringExtra("MAX_WORDS");
                ArrayList<String> selectedCategories =
                        intent.getStringArrayListExtra("SELECTED_CATEGORIES");
                ArrayList<String> cats = Globals.categories;
                if(selectedCategories.size() > 0) cats = selectedCategories;
                pastas = filterResults(name, minWords, maxWords, cats);

                updateText();
               // loading.dismiss();

           // }
      //  }.start();
        ListView lv = (ListView)findViewById(R.id.listView);
        ResultAdapter adapter = new ResultAdapter(getBaseContext(), pastas);
        lv.setAdapter(adapter);
    }

    public void onCreateView() {

    }

    public void updateText() {
        System.out.println(pastas.size() + " pastas found!");
        //((TextView)findViewById(R.id.resultsLabel)).setText(pastas.size() + " pastas found!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
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

    public ArrayList<CreepyPasta> filterResults(final String name, String minWords, String maxWords,
                              final ArrayList<String> categories) {

        System.out.println("CATEGORIES:");
        for(String s : categories) {
            System.out.println(s);
        }
        // Setup values
        int minW = 0;
        if(minWords.length() > 0) minW = Integer.parseInt(minWords);

        int maxW = Integer.MAX_VALUE;
        if(maxWords.length() > 0) maxW = Integer.parseInt(maxWords);

        // Finalize integers
        final int min = minW;
        final int max = maxW;

        // Setup predicates to loop
        ArrayList<Predicate<CreepyPasta>> filters = new ArrayList<Predicate<CreepyPasta>>();
        if(name.length() > 0) {
            filters.add (new Predicate<CreepyPasta>() {
                @Override
                public boolean apply(CreepyPasta creepyPasta) {
                    return creepyPasta.getTitle().matches("(.*)" + "(?i:" + name +")(.*)");
                }
            });
        }
        filters.add(new Predicate<CreepyPasta>() {
            @Override
            public boolean apply(CreepyPasta creepyPasta) {
                int count = creepyPasta.getWordCount();
                return count >= min && count <= max;
            }
        });
        filters.add(new Predicate<CreepyPasta>() {
            @Override
            public boolean apply(CreepyPasta creepyPasta) {
                for(String cat : creepyPasta.getCategories()) {
                    if(categories.contains(cat)) return true;
                    else continue;
                }
                return false;
            }
        });

        ArrayList<CreepyPasta> filtered = new ArrayList<CreepyPasta>();
        for(CreepyPasta cp : Globals.creepyPasta) {
            boolean add = true;
            for(Predicate<CreepyPasta> f : filters) {
                if(!f.apply(cp)) {
                    add = false;
                    break;
                }
            }
            if(!add)
                continue;
            else
                filtered.add(cp);
        }

        return filtered;
    }
}
