package com.example.shivam.recordbuss;



import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.shivam.recordbuss.adapter.BillAdapter;
import com.example.shivam.recordbuss.model.Bill;
import com.example.shivam.recordbuss.sql.bill.DatabaseHelper;



import java.util.ArrayList;


public class RenderList extends AppCompatActivity {

    private AppCompatActivity activity = RenderList.this;
    private RecyclerView recyclerViewBill;
    private ArrayList<Bill> listBill;
    
    private BillAdapter BillAdapter;
    private DatabaseHelper databaseHelper;
    



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_render_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
        initObjects();

//        Intent intentThatStartedThisActivity = getIntent();
//        if (intentThatStartedThisActivity.hasExtra("NAME")) {
//
//            //get all needed extras intent
//
////            int no = getIntent().getExtras().getInt("NO");
////            int contact = getIntent().getExtras().getInt("CONTACT");
////            String product = getIntent().getExtras().getString("PRODUCT");
////            float cost = getIntent().getExtras().getFloat("COST");
//
//
//
//        }else{
//
//            Toast.makeText(this, "No API Data", Toast.LENGTH_SHORT).show();
//
//        }

    }



    /**
     * This method is to initialize views
     */
    private void initViews() {
        recyclerViewBill = (RecyclerView) findViewById(R.id.rv);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listBill = new ArrayList<>();
        BillAdapter = new BillAdapter(listBill, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewBill.setLayoutManager(mLayoutManager);
        recyclerViewBill.setItemAnimator(new DefaultItemAnimator());
        recyclerViewBill.setHasFixedSize(true);
        recyclerViewBill.setAdapter(BillAdapter);
        databaseHelper = new DatabaseHelper(activity);

        getDataFromSQLite();

    }





    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listBill.clear();
                listBill.addAll(databaseHelper. getAllBill());

                return null;
            }

            @Override
            protected void onPostExecute(Void avoid) {
                super.onPostExecute(avoid);
                BillAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
