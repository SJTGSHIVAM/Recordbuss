package com.example.shivam.recordbuss.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.shivam.recordbuss.model.Emp;
import com.example.shivam.recordbuss.sql.EmpContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shivam on 23/3/19.
 */

public class DatabaseHelperEmp extends SQLiteOpenHelper {



        private DatabaseHelperEmp DBHelper;
        private SQLiteDatabase db;

        // Database Version
        private static final int DATABASE_VERSION = 2;

        // Database Name
        private static final String DATABASE_NAME = "EmpManager.db";

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            final String SQL_CREATE_FAVORITE_TABLE = "CREATE TABLE " + EmpContract.EmpEntry.TABLE_NAME + " (" +
                        EmpContract.EmpEntry.EMP_ADHAR + " UNSIGNED BIG INT NOT NULL," +
                    EmpContract.EmpEntry.EMP_NAME+ " TEXT NOT NULL, " +
                    EmpContract.EmpEntry.EMP_CON + " UNSIGNED BIG INT NOT NULL, " +
                    EmpContract.EmpEntry.EMP_ADDRESS+ " TEXT NOT NULL, " +
                    EmpContract.EmpEntry.EMP_SAL+ " DOUBLE NOT NULL " +
                    "); ";

            sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);
        }
        //drop Emp table
        private String DROP_Emp_TABLE = "DROP TABLE IF EXISTS " + EmpContract.EmpEntry.TABLE_NAME;

        public DatabaseHelperEmp(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        //---opens the database---
        public DatabaseHelperEmp open() throws SQLException
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

            db1.execSQL(DROP_Emp_TABLE);

            // Create tables again
            onCreate(db1);

        }


        //Method to create Emp records

        public void addEmp(Emp Emp) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(EmpContract.EmpEntry.EMP_ADHAR, Emp.getAdhar());
            values.put(EmpContract.EmpEntry.EMP_NAME, Emp.getName());
            values.put(EmpContract.EmpEntry.EMP_CON, Emp.getContact());
            values.put(EmpContract.EmpEntry.EMP_ADDRESS, Emp.getAddress());
            values.put(EmpContract.EmpEntry.EMP_SAL, Emp.getSal());

            db.insert(EmpContract.EmpEntry.TABLE_NAME, null, values);
            db.close();
        }

        public boolean checkUser(String email) {

            // array of columns to fetch
            String[] columns = {
                    EmpContract.EmpEntry.EMP_ADHAR
            };
            SQLiteDatabase db = this.getReadableDatabase();

            // selection criteria
            String selection = EmpContract.EmpEntry.EMP_ADHAR + " = ?";

            // selection argument
            String[] selectionArgs = {email};

            // query user table with condition
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT userEMP_ADHAR FROM user WHERE user_email = 'jack@androidtutorialshub.com';
             */
            Cursor cursor = db.query(EmpContract.EmpEntry.TABLE_NAME, //Table to query
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





        public List<Emp> getAllEmp() {
            // array of columns to fetch
            String[] columns = {
                    EmpContract.EmpEntry.EMP_ADHAR,
                    EmpContract.EmpEntry.EMP_NAME,
                    EmpContract.EmpEntry.EMP_CON,
                    EmpContract.EmpEntry.EMP_ADDRESS,
                    EmpContract.EmpEntry.EMP_SAL
            };
            // sorting orders
            String sortOrder =
                    EmpContract.EmpEntry.EMP_ADHAR + " ASC";
            List<Emp> EmpList = new ArrayList<Emp>();

            SQLiteDatabase db = this.getReadableDatabase();


            Cursor cursor = db.query(EmpContract.EmpEntry.TABLE_NAME, //Table to query
                    columns,    //columns to return
                    null,        //columns for the WHERE clause
                    null,        //The values for the WHERE clause
                    null,       //group the rows
                    null,       //filter by row groups
                    sortOrder); //The sort order


            // Traversing through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Emp Emp = new Emp();
                    Emp.setAdhar(cursor.getString(cursor.getColumnIndex(EmpContract.EmpEntry.EMP_ADHAR)));
                    Emp.setName(cursor.getString(cursor.getColumnIndex(EmpContract.EmpEntry.EMP_NAME)));
                    Emp.setContact(Integer.parseInt(cursor.getString(cursor.getColumnIndex(EmpContract.EmpEntry.EMP_CON))));
                    Emp.setAddress(cursor.getString(cursor.getColumnIndex(EmpContract.EmpEntry.EMP_ADDRESS)));
                    Emp.setSal(Integer.parseInt(cursor.getString(cursor.getColumnIndex(EmpContract.EmpEntry.EMP_SAL))));
                    // Adding user record to list
                    EmpList.add(Emp);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

            // return user list
            return EmpList;
        }

    
}
