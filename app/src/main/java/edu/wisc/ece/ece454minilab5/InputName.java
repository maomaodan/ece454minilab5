package edu.wisc.ece.ece454minilab5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class InputName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_name);


    }

    public void goBackSave(View v) {





        Intent intent = new Intent(InputName.this, ListViewLayoutActivity.class);
    }

}