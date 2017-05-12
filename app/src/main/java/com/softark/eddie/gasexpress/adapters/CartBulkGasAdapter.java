package com.softark.eddie.gasexpress.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.softark.eddie.gasexpress.R;
import com.softark.eddie.gasexpress.helpers.Cart;
import com.softark.eddie.gasexpress.models.BulkGas;
import com.softark.eddie.gasexpress.models.Service;

import java.util.ArrayList;

/**
 * Created by Eddie on 5/7/2017.
 */

public class CartBulkGasAdapter extends RecyclerView.Adapter<CartBulkGasAdapter.ViewHolder> {

    private Context context;
    private ArrayList<BulkGas> items;
    private LayoutInflater inflater;
    private TextView totalPrice;

    public CartBulkGasAdapter(Context context, ArrayList<BulkGas> bulkGases, TextView totalPrice) {
        this.context = context;
        this.items = bulkGases;
        inflater = LayoutInflater.from(context);
        this.totalPrice = totalPrice;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public CartBulkGasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cart_list, null);
        return new CartBulkGasAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartBulkGasAdapter.ViewHolder holder, int position) {
        BulkGas bulkGas = items.get(position);
        String metric;

        if(bulkGas.getMetric() == 1) {
            metric = "Kg";
        }else {
            metric = "Tons";
        }
        holder.name.setText(bulkGas.getName().concat(" ").concat(String.valueOf(bulkGas.getSize())).concat(" ").concat(metric));
        holder.quantitySelect.setValue(bulkGas.getQuantity());
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name, price;
        public ImageButton remove;
        public NumberPicker quantitySelect;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.cart_item_name);
            price = (TextView) itemView.findViewById(R.id.cart_item_price);
            remove = (ImageButton) itemView.findViewById(R.id.remove_from_cart);
            remove.setOnClickListener(this);
            quantitySelect = (NumberPicker) itemView.findViewById(R.id.quantity_select);
            quantitySelect.setMinValue(1);
            quantitySelect.setMaxValue(100);
            quantitySelect.setWrapSelectorWheel(false);
            quantitySelect.setOnScrollListener(new NumberPicker.OnScrollListener() {
                @Override
                public void onScrollStateChange(NumberPicker view, int scrollState) {
                    if(scrollState == SCROLL_STATE_IDLE) {
                        BulkGas gas = items.get(getAdapterPosition());
                        Cart.updateCartItem(gas, Cart.BULK_GAS, view.getValue());
                        gas.setQuantity(view.getValue());
                        totalPrice.setText(String.valueOf(Cart.totalPrice));
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            BulkGas bulkGas = items.get(getAdapterPosition());
            Cart.removeBulkGas(bulkGas);
            totalPrice.setText(String.valueOf(Cart.totalPrice));
            items.remove(getAdapterPosition());
            notifyItemRemoved(getAdapterPosition());
            notifyItemRangeChanged(getAdapterPosition(), items.size());
            notifyDataSetChanged();
        }
    }
}