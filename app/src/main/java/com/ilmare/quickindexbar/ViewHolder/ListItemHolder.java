package com.ilmare.quickindexbar.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ilmare.quickindexbar.R;

public class ListItemHolder {
    private TextView letter;
    private ImageView icon;
    private TextView tvName;

    public ListItemHolder(View view) {
        letter = (TextView) view.findViewById(R.id.letter);
        icon = (ImageView) view.findViewById(R.id.icon);
        tvName = (TextView) view.findViewById(R.id.tv_name);
    }

    public ImageView getIcon() {
        return icon;
    }

    public TextView getLetter() {
        return letter;
    }

    public TextView getTvName() {
        return tvName;
    }
}
