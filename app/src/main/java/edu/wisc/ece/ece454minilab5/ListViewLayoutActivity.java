package edu.wisc.ece.ece454minilab5;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewLayoutActivity extends ListActivity {
    protected static int ID;
    private ArrayAdapter<String> mAdapter;
    ArrayList<String> nameArray = new ArrayList<String>();
    protected SharedPreferences namePrefs;
    //protected ArrayList<String> FOLKS = new ArrayList<String>(Arrays.asList("add","studentA", "studentB", "studentC", "studentD"));
    public static final String PREF_NAME = "NAME";
    String nameString;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Define a new adapter


        namePrefs =getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        //HashMap <Integer, String> nameMap = (HashMap)namePrefs.getAll();
        nameArray.add("add");
        //update student list
/*
        SharedPreferences.Editor editor = namePrefs.edit();
        editor.clear();
        editor.commit();
*/
        nameString = namePrefs.getString("student","");
        int prevIndex = 0;
        for (int i = 0 ; i < nameString.length();i++)
        {
            if(nameString.charAt(i)==',')
            {
                nameArray.add(nameString.substring(prevIndex, i));
                prevIndex=i+1;
            }
            else if (i ==(nameString.length()-1))
                nameArray.add(nameString);

        }





      /*  ID = nameMap.size();
        for (int i = 1; i <= 10;i++)
        {
            if(nameMap.get(Integer.toString(i))!=null)
            nameArray.add(nameMap.get(Integer.toString(i)));
        }*/

        mAdapter = new ArrayAdapter<String>(this,
                R.layout.activity_list_view_layout, nameArray);

        // Assign the adapter to ListView
        setListAdapter(mAdapter);

        // Define the listener interface
        OnItemClickListener mListener = new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Your code here...

                if (id==0)
                {
                    Intent intent = new Intent (ListViewLayoutActivity.this,InputName.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(ListViewLayoutActivity.this,nameArray.get((int)id)+" is removed form the list!",Toast.LENGTH_SHORT).show();
                    nameArray.remove((int) id);
                    SharedPreferences.Editor editor = namePrefs.edit();
                    nameString = "";
                    for (int i = 1; i < nameArray.size();i++)
                    {

                        nameString = nameString + nameArray.get(i)+",";
                    }
                    editor.clear();
                    editor.putString("student",nameString);
                    editor.commit();

                }




                mAdapter.notifyDataSetChanged();

            }

        };

        // Get the ListView and wired the listener
        ListView listView = getListView();
        listView.setOnItemClickListener(mListener);

    }
}