package com.example.shivam.recordbuss;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;

import android.view.View;
import android.widget.Toast;

import com.example.shivam.recordbuss.model.Emp;
import com.example.shivam.recordbuss.sql.DatabaseHelperEmp;

public class AddEmp extends AppCompatActivity implements View.OnClickListener {


    private final AppCompatActivity activity = AddEmp.this;

    private NestedScrollView nestedScrollView;


    private TextInputEditText empName;
    private TextInputEditText empCon;
    private TextInputEditText empAddress;
    private TextInputEditText empSal;
    private TextInputEditText empAdhar;

    private AppCompatButton reg;


    private DatabaseHelperEmp databaseHelper;
    private Emp emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emp);
        initViews();
        initObjects();
        initListeners();
    }

    //Initialize Views
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView0);


        empName = (TextInputEditText)     findViewById(R.id.empName);
        empCon =  (TextInputEditText)      findViewById(R.id.empCon);
        empAddress   =   (TextInputEditText)       findViewById(R.id.empAddress);
        empSal =     (TextInputEditText)         findViewById(R.id.empSal);
        empAdhar =   (TextInputEditText)       findViewById(R.id.empAdhar);

        reg = (AppCompatButton) findViewById(R.id.reg);


    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        reg.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelperEmp(activity);
        emp = new Emp();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.reg:
                postDataToSQLite();
                break;

        }
    }

    private void postDataToSQLite() {

        if (!databaseHelper.checkUser(empAdhar.getText().toString().trim())) {

            emp.setAdhar(empAdhar.getText().toString().trim());
            emp.setName(empName.getText().toString().trim());
            emp.setContact(Integer.parseInt(empCon.getText().toString().trim()));
            emp.setAddress(empAddress.getText().toString().trim());
            emp.setSal(Integer.parseInt(empSal.getText().toString().trim()));


            databaseHelper.addEmp(emp);

            // Snack Bar to show success message that record saved successfully
            Intent accountsIntent = new Intent(AddEmp.this, RenderEmpList.class);
            Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT)
                    .show();
//            accountsIntent.putExtra("Adhar", empAdhar.getText().toString().trim());
//            accountsIntent.putExtra("NAME", empName.getText().toString().trim());
//            accountsIntent.putExtra("CONTACT", empCon.getText().toString().trim());
//            accountsIntent.putExtra("PRODUCT", empAddress.getText().toString().trim());
//            accountsIntent.putExtra("COST", empSal.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_emp), Snackbar.LENGTH_LONG).show();
        }


    }

    private void emptyInputEditText() {
        empName.setText(null);
        empCon.setText(null);
        empAddress.setText(null);
        empSal.setText(null);
        empAdhar.setText(null);
    }

}
