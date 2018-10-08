package com.cristallium.brendon.imobiliario.Imovel;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.support.annotation.NonNull;

import com.cristallium.brendon.imobiliario.R;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ImovelAdapter extends RecyclerView.Adapter<ImovelHolder> {

    private final ArrayList<Imovel> imoveis;

    public ImovelAdapter(ArrayList<Imovel> imoveis) {
        this.imoveis = imoveis;
    }

    @NonNull
    @Override
    public ImovelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImovelHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.imovel_list_item,
                parent,
                false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ImovelHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return imoveis.size();
    }

}
