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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import com.example.shivam.recordbuss.R;
import com.example.shivam.recordbuss.model.Bill;

import java.util.ArrayList;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder>  {

    private ArrayList<Bill> listBill;
    public ImageView overflow;
    private Context mContext;
    private ArrayList<Bill> mFilteredList;


    public BillAdapter(ArrayList<Bill> listBill, Context mContext) {
        this.listBill = listBill;
        this.mContext = mContext;
        this.mFilteredList = listBill;


    }

    public class BillViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView cardBNo;
        public AppCompatTextView cardName;
        public AppCompatTextView cardCon;
        public AppCompatTextView cardProd;
        public AppCompatTextView cardCost;
        public  ImageView overflow;

        public BillViewHolder(View view) {
            super(view);
            cardBNo = (AppCompatTextView) view.findViewById(R.id.cardBNo);
            cardName = (AppCompatTextView) view.findViewById(R.id.cardName);
            cardCon = (AppCompatTextView) view.findViewById(R.id.cardCon);
            cardProd = (AppCompatTextView) view.findViewById(R.id.cardProd);
            cardCost = (AppCompatTextView) view.findViewById(R.id.cardCost);

        }


    }




    @Override
    public BillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);

        return new BillViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BillViewHolder holder, int position) {
        holder.cardName.setText(listBill.get(position).getName());
        holder.cardCost.setText(String.valueOf(listBill.get(position).getcost()));
        holder.cardProd.setText(listBill.get(position).getprod());
        holder.cardCon.setText(String.valueOf(listBill.get(position).getcontact()));
        holder.cardBNo.setText(String.valueOf(listBill.get(position).getno()));

    }


    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }



}
