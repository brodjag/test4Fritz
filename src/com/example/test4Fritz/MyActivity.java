package com.example.test4Fritz;

import android.app.Activity;
import android.os.Bundle;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
     tabSwitcher TS;
    settingTab settingTab1;
    MyActivity con=this;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

       TS= new tabSwitcher(con);    TS.showSetting();

        settingTab1=new settingTab(con);

    }




}
