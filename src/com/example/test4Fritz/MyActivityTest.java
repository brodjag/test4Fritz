package com.example.test4Fritz;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: brodjag
 * Date: 02.08.14
 * Time: 0:48
 * To change this template use File | Settings | File Templates.
 */
public class MyActivityTest extends ActivityInstrumentationTestCase2 {
    Activity con;
    Spinner sortSpiner, dirSpiner;
    TextView maxitems,page;


    public MyActivityTest() {
        super(MyActivity.class);


    }


    @Override
    protected void setUp() throws Exception {
        // TODO Auto-generated method stub
        super.setUp();
        setActivityInitialTouchMode(false);
        con = getActivity();

        sortSpiner=(Spinner) con.findViewById(R.id.sortSpinner);
        dirSpiner=(Spinner) con.findViewById(R.id.dirSpinner);
        maxitems=(EditText) con.findViewById(R.id.maxitems);
        page=(EditText) con.findViewById(R.id.page);

    }


    public void testControlsCreated() {
        assertNotNull(con);
        assertNotNull(sortSpiner);
        assertNotNull(dirSpiner);
        assertNotNull(maxitems);
        assertNotNull(page);
    }


    public void testDefaultValues(){
        assertEquals(((MyActivity) con).settingTab1.sortValue,"name");
        assertEquals(((MyActivity) con).settingTab1.directionValue, "asc");

        assertEquals(maxitems.getText().toString(),"10");
        assertEquals(page.getText().toString(),"1");

    }




    public void testGetError() {
       // TouchUtils.tapView(this, tab2);
       // sendKeys(KeyEvent.KEYCODE_1, KeyEvent.KEYCODE_0);
         //assertEquals(true,false);
     //   assertEquals("Must be Error!", "e",tab2.getText().toString());
    }
}

