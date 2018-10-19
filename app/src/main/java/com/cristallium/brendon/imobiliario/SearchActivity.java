package com.cristallium.brendon.imobiliario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.cristallium.brendon.imobiliario.Imovel.Imovel;
import com.cristallium.brendon.imobiliario.Imovel.ImovelAdapter;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private EditText precoMaximo;
    private ImovelAdapter mAdapter;
    private ArrayList<Imovel> imoveis;
    private RecyclerView mRecyclerView;
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
        precoMaximo = (EditText)findViewById(R.id.txtPrecoMaximo);
        precoMaximo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().isEmpty()) {
                    mAdapter.swapData(imoveis);
                } else {
                    mAdapter.swapData(buscaPrecoMaximo(Integer.getInteger(editable.toString())));
                }
            }
        });
    }

    private ArrayList<Imovel> buscaPrecoMaximo(Integer maxValue) {
        ArrayList<Imovel> resultado = new ArrayList<>();
        for (Imovel imovel: imoveis) {
            if(imovel.getValor() <= maxValue) {
                resultado.add(imovel);
            }
        }
        return resultado;
    }

}
