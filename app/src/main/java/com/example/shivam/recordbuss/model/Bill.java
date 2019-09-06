package com.example.shivam.recordbuss.model;

/**
 * Created by shivam on 23/3/19.
 */

public class Bill {
    
        private int no;
        private String name;
        private String contact;
        private String prod;
        private String cost;


        public int getno() {
            return no;
        }

        public void setno(int no) {
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getcontact() {
            return contact;
        }

        public void setcontact(String contact) {
            this.contact = contact;
        }

        public String getprod() {
            return prod;
        }

        public void setprod(String prod) {
            this.prod = prod;
        }

        public String getcost() {
            return cost;
        }

        public void setcost(String cost) {
            this.cost = cost;
        }
 
}
