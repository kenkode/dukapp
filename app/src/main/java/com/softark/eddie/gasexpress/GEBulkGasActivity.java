package com.softark.eddie.gasexpress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.softark.eddie.gasexpress.adapters.BulkGasAdapter;
import com.softark.eddie.gasexpress.data.BulkGasData;
import com.softark.eddie.gasexpress.decorators.RecyclerDecorator;

public class GEBulkGasActivity extends AppCompatActivity {

    private RecyclerView bulkGasList;
    private ProgressBar loader;
    private LinearLayout errorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gebulk_gas);

        errorLayout = (LinearLayout) findViewById(R.id.error_layout_bulk);
        errorLayout.setVisibility(View.GONE);
        loader = (ProgressBar) findViewById(R.id.load_bulk);

        RecyclerDecorator decorator = new RecyclerDecorator(this, 2, 8, true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        bulkGasList = (RecyclerView) findViewById(R.id.bulk_gas_list);
        bulkGasList.setLayoutManager(gridLayoutManager);
        bulkGasList.addItemDecoration(decorator);
        BulkGasData data = new BulkGasData(this);
        data.getBulkGases(bulkGasList, errorLayout, loader);
    }
}
