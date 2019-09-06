package com.example.shivam.recordbuss;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.example.shivam.recordbuss.model.Bill;

import com.example.shivam.recordbuss.sql.bill.DatabaseHelper;

public class AddBill extends AppCompatActivity implements View.OnClickListener {


        private final AppCompatActivity activity = AddBill.this;

        private NestedScrollView nestedScrollView;


        private TextInputEditText custName;
        private TextInputEditText custCon;
        private TextInputEditText prod;
        private TextInputEditText cost;
        private TextInputEditText billNo;

        private AppCompatButton gen;


        private DatabaseHelper databaseHelper;
        private Bill bill;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_bill);

            initViews();
            initObjects();
            initListeners();
        }

        //Initialize Views
        private void initViews() {
            nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView0);



            custName = (TextInputEditText)     findViewById(R.id.custName);
            custCon =  (TextInputEditText)      findViewById(R.id.custCon);
            prod   =   (TextInputEditText)       findViewById(R.id.prod);
            cost =     (TextInputEditText)         findViewById(R.id.cost);
            billNo =   (TextInputEditText)       findViewById(R.id.billNo);

            gen = (AppCompatButton) findViewById(R.id.gen);


        }

        /**
         * This method is to initialize listeners
         */
        private void initListeners() {
            gen.setOnClickListener(this);

        }

        /**
         * This method is to initialize objects to be used
         */
        private void initObjects() {
            databaseHelper = new DatabaseHelper(activity);
            bill = new Bill();
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.gen:
                    postDataToSQLite();
                    break;
                
            }
        }

        private void postDataToSQLite() {
            
            if (!databaseHelper.checkUser(billNo.getText().toString().trim())) {

                bill.setno(Integer.parseInt(billNo.getText().toString().trim()));
                bill.setName(custName.getText().toString().trim());
                bill.setcontact(custCon.getText().toString().trim());
                bill.setprod(prod.getText().toString().trim());
                bill.setcost(cost.getText().toString().trim());


                databaseHelper.addBill(bill);

                // Snack Bar to show success message that record saved successfully
                Intent accountsIntent = new Intent(AddBill.this, RenderList.class);
                Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT)
                        .show();
                emptyInputEditText();
                startActivity(accountsIntent);


            } else {
                // Snack Bar to show error message that record already exists
                Snackbar.make(nestedScrollView, getString(R.string.error_bill), Snackbar.LENGTH_LONG).show();
            }


        }

        private void emptyInputEditText() {
            custName.setText(null);
            custCon.setText(null);
            prod.setText(null);
            cost.setText(null);
            billNo.setText(null);
        }
    
}
