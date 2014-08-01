package com.example.test4Fritz;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created with IntelliJ IDEA.
 * User: brodjag
 * Date: 01.08.14
 * Time: 21:01
 * To change this template use File | Settings | File Templates.
 */

public class settingTab {
    MyActivity con;

 public static  String[] sortItems = {"popularity","name", "price","brand"};
 public String sortValue=null;

 public static  String[] directionItems = {"desc","asc",};
 public String directionValue=null;

    public settingTab(MyActivity c){
        con=c;

        con.findViewById(R.id.request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new listRequest(con,"https://www.zalora.com.my/mobile-api/women/clothing"+makeParametrs());
            }
        });

        mkSortSpinner(); mkOrderSpinner();

    }

    //sort spinner
    void mkSortSpinner() {
      ArrayAdapter<String> adapter = new ArrayAdapter<String>(con, android.R.layout.simple_spinner_item, sortItems);
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      Spinner spinner = (Spinner) con.findViewById(R.id.sortSpinner);
      spinner.setAdapter(adapter);
      spinner.setPrompt("Sort");
      spinner.setSelection(1);

      spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view,  int position, long id) {
              sortValue=sortItems[position];


              if(position==0){((Spinner) con.findViewById(R.id.dirSpinner)).setSelection(0);}else {((Spinner) con.findViewById(R.id.dirSpinner)).setSelection(1);}
              // показываем позиция нажатого элемента
            //  Toast.makeText(con, "Position = " + sortValue, Toast.LENGTH_SHORT).show();
          }
          @Override
          public void onNothingSelected(AdapterView<?> arg0) {
          }
      });
  }


    //order spinner
    void mkOrderSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(con, android.R.layout.simple_spinner_item, directionItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) con.findViewById(R.id.dirSpinner);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Dir");
        spinner.setSelection(1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,  int position, long id) {
                directionValue=directionItems[position];
                // показываем позиция нажатого элемента
               // Toast.makeText(con, "Position = " + directionValue, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }


  private String makeParametrs(){
         String res="?";

      try {
          Integer maxitems=Integer.parseInt(((EditText) con.findViewById(R.id.maxitems)).getText().toString());
          res=res+ "maxitems="+maxitems+"&";
      }catch (Exception e){}

      try {
          Integer page=Integer.parseInt(((EditText) con.findViewById(R.id.page)).getText().toString());
          res=res+ "page="+page+"&";
      }catch (Exception e){}

      if(sortValue!=null){res=res+"sort="+sortValue+"&";}
      if(directionValue!=null){res=res+"dir="+directionValue;}


        return res;
    }


}
