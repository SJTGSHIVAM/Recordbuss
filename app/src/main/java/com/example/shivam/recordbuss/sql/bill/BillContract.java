package com.example.shivam.recordbuss.sql.bill;

import android.provider.BaseColumns;

/**
 * Created by shivam on 23/3/19.
 */

public class BillContract {

    public static final class BillEntry implements BaseColumns {

        public static final String TABLE_NAME = "Bill";
        public static final String BILL_NO = "BILL_NO";
        public static final String CUST_NAME = "CUST_NAME";
        public static final String CUST_CON = "CUST_CON";
        public static final String PROD= "PROD";
        public static final String COST= "COST";
    }
}
