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
    ArrayList<String> nameArray = new ArrayList<String>();          //array for names to be stored
    protected SharedPreferences namePrefs;                      //shared preference instance

    public static final String PREF_NAME = "NAME";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Define a new adapter


        namePrefs =getSharedPreferences(PREF_NAME, MODE_PRIVATE);       //get shared preference
        HashMap <Integer, String> nameMap = (HashMap)namePrefs.getAll();    //read from the preference
        nameArray.add("add");                           //"add" option for adding more students

        for (int i = 1; i <= 20;i++)
        {
            if(nameMap.get(Integer.toString(i))!=null)
            nameArray.add(nameMap.get(Integer.toString(i)));        //read from list to reconstruct
        }
        ID = nameMap.size();
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
                    Intent intent = new Intent (ListViewLayoutActivity.this,InputName.class);   //if add is pressed jump to input names activity
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(ListViewLayoutActivity.this,nameArray.get((int)id)+" is removed form the list!",Toast.LENGTH_SHORT).show();
                    nameArray.remove((int) id);
                    SharedPreferences.Editor editor = namePrefs.edit();
                    int i = 0;
                    int cnt = 0;
                    for (i = 0; i < 20; i++)
                    {
                        if (cnt == id-1)
                            break;
                        if (namePrefs.getString(Integer.toString(i), "").length()!=0)
                        {
                            cnt++;
                        }
                    }

                    editor.remove(Long.toString(i));
                    editor.commit();                        //or remove the student from the list and preference

                }
                mAdapter.notifyDataSetChanged();

            }
        };

        // Get the ListView and wired the listener
        ListView listView = getListView();
        listView.setOnItemClickListener(mListener);

    }
}