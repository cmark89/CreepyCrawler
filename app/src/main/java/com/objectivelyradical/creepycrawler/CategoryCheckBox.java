package com.objectivelyradical.creepycrawler;

import android.app.ActionBar;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CategoryCheckBox extends CheckBox {

    public CategoryCheckBox(Context context, String categoryName) {
        super(context);
        setText(categoryName);
        //getLayoutParams().width = 0;
    }
}
