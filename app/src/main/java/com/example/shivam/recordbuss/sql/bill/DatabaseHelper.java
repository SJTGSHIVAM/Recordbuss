package com.example.shivam.recordbuss.sql.bill;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.shivam.recordbuss.model.Bill;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shivam on 23/3/19.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

   

        private DatabaseHelper DBHelper;
        private SQLiteDatabase db;

        // Database Version
        private static final int DATABASE_VERSION = 2;

        // Database Name
        private static final String DATABASE_NAME = "BillManager.db";

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + BillContract.BillEntry.TABLE_NAME + " (" +
                        BillContract.BillEntry.BILL_NO + " UNSIGNED BIG INT NOT NULL," +
                    BillContract.BillEntry.CUST_NAME + " TEXT NOT NULL, " +
                    BillContract.BillEntry.CUST_CON + " UNSIGNED BIG INT, " +
                    BillContract.BillEntry.PROD + " TEXT NOT NULL, " +
                    BillContract.BillEntry.COST + " DOUBLE NOT NULL " +
                    "); ";

            sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
        }
        //drop Bill table
        private String DROP_Bill_TABLE = "DROP TABLE IF EXISTS " + BillContract.BillEntry.TABLE_NAME;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        //---opens the database---
        public DatabaseHelper open() throws SQLException
        {
            db = DBHelper.getWritableDatabase();
            return this;
        }


        //---closes the database---
        public void close()
        {
            DBHelper.close();
        }


        @Override
        public void onUpgrade(SQLiteDatabase db1, int oldVersion, int newVersion) {

            //Drop User Table if exist

            db1.execSQL(DROP_Bill_TABLE);

            // Create tables again
            onCreate(db1);

        }


        //Method to create Bill records

        public void addBill(Bill bill) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(BillContract.BillEntry.BILL_NO, bill.getno());
            values.put(BillContract.BillEntry.CUST_NAME, bill.getName());
            values.put(BillContract.BillEntry.CUST_CON, bill.getcontact());
            values.put(BillContract.BillEntry.PROD, bill.getprod());
            values.put(BillContract.BillEntry.COST, bill.getcost());

            db.insert(BillContract.BillEntry.TABLE_NAME, null, values);
            db.close();
        }

        public boolean checkUser(String email) {

            // array of columns to fetch
            String[] columns = {
                    BillContract.BillEntry.BILL_NO
            };
            SQLiteDatabase db = this.getReadableDatabase();

            // selection criteria
            String selection = BillContract.BillEntry.BILL_NO + " = ?";

            // selection argument
            String[] selectionArgs = {email};

            // query user table with condition
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT userBILL_NO FROM user WHERE user_email = 'jack@androidtutorialshub.com';
             */
            Cursor cursor = db.query(BillContract.BillEntry.TABLE_NAME, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                      //filter by row groups
                    null);                      //The sort order
            int cursorCount = cursor.getCount();
            cursor.close();
            db.close();

            if (cursorCount > 0) {
                return true;
            }

            return false;
        }





        public List<Bill> getAllBill() {
            // array of columns to fetch
            String[] columns = {
                    BillContract.BillEntry.BILL_NO,
                    BillContract.BillEntry.CUST_NAME,
                    BillContract.BillEntry.CUST_CON,
                    BillContract.BillEntry.PROD,
                    BillContract.BillEntry.COST
            };
            // sorting orders
            String sortOrder =
                    BillContract.BillEntry.BILL_NO + " ASC";
            List<Bill> BillList = new ArrayList<Bill>();

            SQLiteDatabase db = this.getReadableDatabase();


            Cursor cursor = db.query(BillContract.BillEntry.TABLE_NAME, //Table to query
                    columns,    //columns to return
                    null,        //columns for the WHERE clause
                    null,        //The values for the WHERE clause
                    null,       //group the rows
                    null,       //filter by row groups
                    sortOrder); //The sort order


            // Traversing through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Bill Bill = new Bill();
                    Bill.setno(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BillContract.BillEntry.BILL_NO))));
                    Bill.setName(cursor.getString(cursor.getColumnIndex(BillContract.BillEntry.CUST_NAME)));
                    Bill.setcontact(cursor.getString(cursor.getColumnIndex(BillContract.BillEntry.CUST_CON)));
                    Bill.setprod(cursor.getString(cursor.getColumnIndex(BillContract.BillEntry.PROD)));
                    Bill.setcost(cursor.getString(cursor.getColumnIndex(BillContract.BillEntry.COST)));
                    // Adding user record to list
                    BillList.add(Bill);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

            // return user list
            return BillList;
        }

    
}
