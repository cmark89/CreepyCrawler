package com.objectivelyradical.creepycrawler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by corey on 3/19/15.
 */

public class ResultAdapter extends BaseAdapter {
    Context context;
    ArrayList<CreepyPasta> creepyPastaList;
    public ResultAdapter(Context c, ArrayList<CreepyPasta> pasta) {
        creepyPastaList = pasta;
        context = c;
    }

    @Override
    public int getCount() {
        return creepyPastaList.size();
    }

    @Override
    public CreepyPasta getItem(int position) {
        return creepyPastaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listitem_result, parent, false);
        }

        TextView pastaName = (TextView)convertView.findViewById(R.id.title);
        TextView wordCount = (TextView)convertView.findViewById(R.id.wordCounter);
        GridLayout categoryGrid = (GridLayout)convertView.findViewById(R.id.resultCategories);

        CreepyPasta thisPasta = getItem(position);
        pastaName.setText(thisPasta.getTitle());
        if(wordCount == null)
            System.out.println("WORD COUNT IS NULL");
        if(thisPasta == null)
            System.out.println("PASTA IS NULL");
        wordCount.setText(Integer.toString(thisPasta.getWordCount()));

        ArrayList<String> cats = thisPasta.getCategories();
        String temp = "";
        for(int i = 0; i < categoryGrid.getChildCount(); i++) {
            // Don't consider the same category more than once:
            if(temp.length() > 0) cats.remove(temp);

            TextView thisCatText =  ((TextView)categoryGrid.getChildAt(i));
            thisCatText.setText("");
            for(String c : cats) {
                if(Globals.categories.contains(c)) {
                    temp = c;
                    thisCatText.setText(c);
                    break;
                }
            }
        }

        return convertView;
    }
}
