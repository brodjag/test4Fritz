package com.example.test4Fritz;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: brodjag
 * Date: 01.08.14
 * Time: 20:39
 * To change this template use File | Settings | File Templates.
 */
public class tabSwitcher {
    MyActivity con;

    public tabSwitcher(MyActivity c){
        con=c;

        con.findViewById(R.id.setting_tab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSetting();
            }
        });

        con.findViewById(R.id.list_tab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             showList();
            }
        });
    }

    public void showList() {
        con.findViewById(R.id.setting_view).setVisibility(View.GONE);
        con.findViewById(R.id.list_view).setVisibility(View.VISIBLE);

        ((TextView) con.findViewById(R.id.setting_tab)).setTypeface(null, Typeface.NORMAL);
        ((TextView) con.findViewById(R.id.list_tab)).setTypeface(null, Typeface.BOLD);
    }


    public void showSetting(){
        con.findViewById(R.id.setting_view).setVisibility(View.VISIBLE);
        con.findViewById(R.id.list_view).setVisibility(View.GONE);

        ((TextView) con.findViewById(R.id.setting_tab)).setTypeface(null, Typeface.BOLD);
        ((TextView) con.findViewById(R.id.list_tab)).setTypeface(null, Typeface.NORMAL);
    }

}
