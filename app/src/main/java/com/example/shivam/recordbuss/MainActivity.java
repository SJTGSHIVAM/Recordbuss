package com.example.shivam.recordbuss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {



    private AppCompatTextView addEmp,addBill,showEmp,showBill;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            addEmp= (AppCompatTextView) findViewById(R.id.addEmp);
            addBill= (AppCompatTextView) findViewById(R.id.addBill);
            showEmp= (AppCompatTextView) findViewById(R.id.showEmp);
            showBill= (AppCompatTextView) findViewById(R.id.showBill);
            addBill.setOnClickListener(this);
            showEmp.setOnClickListener(this);
            showBill.setOnClickListener(this);
            addEmp.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {


            case R.id.addEmp:
                i = new Intent(MainActivity.this, AddEmp.class);
                startActivity(i);
                break;

            case R.id.addBill:
                i = new Intent(MainActivity.this, AddBill.class);
                startActivity(i);
                break;

            case R.id.showBill:
                i = new Intent(MainActivity.this, RenderList.class);
                startActivity(i);
                break;
            case R.id.showEmp:
                i = new Intent(MainActivity.this, RenderEmpList.class);
                startActivity(i);
                break;

        }
    }
}
