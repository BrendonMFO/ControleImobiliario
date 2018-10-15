package com.cristallium.brendon.imobiliario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cristallium.brendon.imobiliario.Imovel.Imovel;
import com.cristallium.brendon.imobiliario.Imovel.ImovelAdapter;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private ArrayList<Imovel> imoveis;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        imoveis = getIntent().getParcelableArrayListExtra("Imoveis");
        mRecyclerView = findViewById(R.id.imoveisRecyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ImovelAdapter(imoveis);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

}
