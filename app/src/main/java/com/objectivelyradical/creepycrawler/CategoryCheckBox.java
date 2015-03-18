package com.objectivelyradical.creepycrawler;

import android.app.ActionBar;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class CategoryCheckBox extends CheckBox {

    public CategoryCheckBox(Context context, String categoryName) {
        super(context);
        setText(categoryName);
        setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, (float)0.5f));
        setTextAppearance(context, R.style.AppTheme);

        //getLayoutParams().width = 0;
    }
}
