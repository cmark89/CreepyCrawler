package com.objectivelyradical.creepycrawler;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;


public class SearchActivity extends ActionBarActivity {

    private static ArrayList<CreepyPasta> creepyPasta;
    private static ArrayList<String> categories = new ArrayList<String>() {
        {
            add("Animals");
            add("Beings");
            add("Books");
            add("Computers and Internet");
            add("Cryptids");
            add("Demon/Devil");
            add("Diary/Journal");
            add("Disappearances");
            add("Dismemberment");
            add("Dreams/Sleep");
            add("Ghosts");
            add("Gods");
            add("Halloween");
            add("History");
            add("Holders");
            add("Items/Objects");
            add("Lost Episodes");
            add("Lovecraftian");
            add("Memes");
            add("Mental Illness");
            add("Military");
            add("Mirrors");
            add("Monsters");
            add("Music");
            add("NSFW");
            add("Nature");
            add("Photography");
            add("Places");
            add("Poetry");
            add("Pokemon");
            add("Reality");
            add("Ritual");
            add("Science Pastas");
            add("Space");
            add("Television");
            add("Theory");
            add("Troll Pasta");
            add("Video Games");
            add("Videos");
            add("Weird");
            add("Zelda");
        }
    };

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
        try {

            InputStream is = getAssets().open("cookedpasta.cc");
            ObjectInputStream ois = new ObjectInputStream(is);
            creepyPasta = (ArrayList<CreepyPasta>)ois.readObject();

            is.close();
            System.out.println("Loaded pasta successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateCategoryCheckboxes() {
       LinearLayout layout = (LinearLayout)findViewById(R.id.categoryViewGroup);

        for(String category : categories) {
            CategoryCheckBox cb = new CategoryCheckBox(getBaseContext(), category);
            layout.addView(cb);
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
}
