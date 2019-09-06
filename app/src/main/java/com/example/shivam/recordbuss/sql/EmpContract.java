package com.example.shivam.recordbuss.sql;

import android.provider.BaseColumns;

/**
 * Created by shivam on 23/3/19.
 */

public class EmpContract {

    public static final class EmpEntry implements BaseColumns {

        public static final String TABLE_NAME = "Emp";
        public static final String EMP_ADHAR = "EMP_ADHAR";
        public static final String EMP_NAME = "EMP_NAME";
        public static final String EMP_CON = "EMP_CON";
        public static final String EMP_ADDRESS= "EMP_ADDRESS";
        public static final String EMP_SAL= "EMP_SAL";
    }
}
