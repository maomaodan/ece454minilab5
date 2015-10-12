package edu.wisc.ece.ece454minilab5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InputName extends AppCompatActivity {

    private String nameString;
    SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_name);




    }
    public void onGoBackSave(View v)
    {
        mPrefs = getSharedPreferences(ListViewLayoutActivity.PREF_NAME, MODE_PRIVATE);
        TextView txt = (TextView)findViewById(R.id.editText);


        String txtAdd= txt.getText().toString();


        SharedPreferences.Editor editor = mPrefs.edit();

        nameString = mPrefs.getString("student","");
        nameString = nameString + txtAdd+",";
        editor.clear();
        editor.putString("student", nameString);
        editor.commit();



        Intent intent = new Intent(InputName.this, ListViewLayoutActivity.class);
        startActivity(intent);
    }
    public void onGoBackCancel(View v)
    {
        Intent intent = new Intent(InputName.this, ListViewLayoutActivity.class);
        startActivity(intent);
    }
}