package edu.wisc.ece.ece454minilab5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InputName extends AppCompatActivity
{

    private String name;
    SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_name);





    }
    public void onGoBackSave(View v)
    {
        mPrefs = getSharedPreferences(ListViewLayoutActivity.PREF_NAME, MODE_PRIVATE);
        TextView txt = (TextView)findViewById(R.id.editText);
        name = txt.getText().toString();
        SharedPreferences.Editor editor = mPrefs.edit();        //editor for shared preference

        if (ListViewLayoutActivity.ID<20)
            ListViewLayoutActivity.ID++;                            //creating index for each name, at most 20
        else
            ListViewLayoutActivity.ID = 0;

        for (int i = 0; i < 20; i ++)
        {
            String tst = mPrefs.getString(Integer.toString(ListViewLayoutActivity.ID),"");
            if (mPrefs.getString(Integer.toString(ListViewLayoutActivity.ID), "").length()==0)
            {
                editor.putString(Integer.toString(ListViewLayoutActivity.ID), name);        //add student
                editor.commit();
                break;
            }
            else
            {
                ListViewLayoutActivity.ID++;
            }
        }

        Intent intent = new Intent(InputName.this, ListViewLayoutActivity.class);

        startActivity(intent);
    }
    public void onGoBackCancel(View v)
    {
        Intent intent = new Intent(InputName.this, ListViewLayoutActivity.class);
        startActivity(intent);
    }
}