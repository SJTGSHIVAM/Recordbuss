package com.example.shivam.recordbuss.adapter;

/**
 * Created by shivam on 23/3/19.
 */

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shivam.recordbuss.R;
import com.example.shivam.recordbuss.model.Emp;

import java.util.ArrayList;

public class EmpAdapter extends RecyclerView.Adapter<EmpAdapter.EmpViewHolder>  {

    private ArrayList<Emp> listEmp;
    public ImageView overflow;
    private Context mContext;
    private ArrayList<Emp> mFilteredList;


    public EmpAdapter(ArrayList<Emp> listEmp, Context mContext) {
        this.listEmp = listEmp;
        this.mContext = mContext;
        this.mFilteredList = listEmp;


    }

    public class EmpViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView empAdhar;
        public AppCompatTextView empName;
        public AppCompatTextView empCon;
        public AppCompatTextView empAddress;
        public AppCompatTextView empSal;
        public  ImageView overflow;

        public EmpViewHolder(View view) {
            super(view);
            empAdhar = (AppCompatTextView) view.findViewById(R.id.empAdhar);
            empName = (AppCompatTextView) view.findViewById(R.id.empName);
            empCon = (AppCompatTextView) view.findViewById(R.id.empCon);
            empAddress = (AppCompatTextView) view.findViewById(R.id.empAddress);
            empSal = (AppCompatTextView) view.findViewById(R.id.empSal);

        }


    }




    @Override
    public EmpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardemp, parent, false);

        return new EmpViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final EmpViewHolder holder, int position) {
        if(holder.empSal==null){
            Toast.makeText(null, "wahhh", Toast.LENGTH_SHORT)
                    .show();}

        holder.empSal.setText(String.valueOf(listEmp.get(position).getSal()));
        holder.empAddress.setText(listEmp.get(position).getAddress());
        holder.empCon.setText(String.valueOf(listEmp.get(position).getContact()));
        holder.empAdhar.setText(String.valueOf(listEmp.get(position).getAdhar()));
        holder.empName.setText(listEmp.get(position).getName());

    }


    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }



}
