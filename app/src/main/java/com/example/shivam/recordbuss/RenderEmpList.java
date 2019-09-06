package com.example.shivam.recordbuss;

import android.content.Intent;
import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.shivam.recordbuss.adapter.EmpAdapter;
import com.example.shivam.recordbuss.model.Emp;
import com.example.shivam.recordbuss.sql.DatabaseHelperEmp;


import java.util.ArrayList;

public class RenderEmpList extends AppCompatActivity {
    
    private AppCompatActivity activity = com.example.shivam.recordbuss.RenderEmpList.this;
    private RecyclerView recyclerViewEmp;
    private ArrayList<Emp> listEmp;

    private com.example.shivam.recordbuss.adapter.EmpAdapter EmpAdapter;
    private DatabaseHelperEmp databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_render_emp_list);
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarEmp);
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                initViews();
                initObjects();


// the snippet i
// copied from internet and i truly dont know how all this work but just terying to figure it out
//
//        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
//                final int position = viewHolder.getAdapterPosition(); //get position which is swipe
//
//                if (direction == ItemTouchHelper.LEFT) {    //if swipe left
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this); //alert for confirm to delete
//                    builder.setMessage("Are you sure to delete?");    //set message
//
//                    builder.setPositiveButton("REMOVE", new DialogInterface.OnClickListener() { //when click on DELETE
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            adapter.notifyItemRemoved(position);    //item removed from recylcerview
//                            sqldatabase.execSQL("delete from " + TABLE_NAME + " where _id='" + (position + 1) + "'"); //query for delete
//                            list.remove(position);  //then remove item
//
//                            return;
//                        }
//                    }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {  //not removing items if cancel is done
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            adapter.notifyItemRemoved(position + 1);    //notifies the RecyclerView Adapter that data in adapter has been removed at a particular position.
//                            adapter.notifyItemRangeChanged(position, adapter.getItemCount());   //notifies the RecyclerView Adapter that positions of element in adapter has been changed from position(removed element index to end of list), please update it.
//                            return;
//                        }
//                    }).show();  //show alert dialog
//                }
//            }
//        };
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
//        itemTouchHelper.attachToRecyclerView(recyclerView); //set swip
//
//





//                Intent intentThatStartedThisActivity = getIntent();
//                if (intentThatStartedThisActivity.hasExtra("NAME")) {
//
//                    //get all needed extras intent
//
////            int no = getIntent().getExtras().getInt("NO");
////            int contact = getIntent().getExtras().getInt("CONTACT");
////            String product = getIntent().getExtras().getString("PRODUCT");
////            float cost = getIntent().getExtras().getFloat("COST");
//
//
//
//                }else{
//
//                    Toast.makeText(this, "No API Data", Toast.LENGTH_SHORT).show();
//
//                }

            }



            /**
             * This method is to initialize views
             */
            private void initViews() {
                recyclerViewEmp = (RecyclerView) findViewById(R.id.rvEmp);
            }

            /**
             * This method is to initialize objects to be used
             */
            private void initObjects() {
                listEmp = new ArrayList<>();
                EmpAdapter = new EmpAdapter(listEmp, this);

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerViewEmp.setLayoutManager(mLayoutManager);
                recyclerViewEmp.setItemAnimator(new DefaultItemAnimator());
                recyclerViewEmp.setHasFixedSize(true);
                recyclerViewEmp.setAdapter(EmpAdapter);
                databaseHelper = new DatabaseHelperEmp(activity);

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
                        listEmp.clear();
                        listEmp.addAll(databaseHelper. getAllEmp());

                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void avoid) {
                        super.onPostExecute(avoid);
                        EmpAdapter.notifyDataSetChanged();
                    }
                }.execute();



    }
}
