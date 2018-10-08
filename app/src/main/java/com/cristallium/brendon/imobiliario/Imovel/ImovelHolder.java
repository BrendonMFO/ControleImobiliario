package com.cristallium.brendon.imobiliario.Imovel;

import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.cristallium.brendon.imobiliario.R;

public class ImovelHolder extends RecyclerView.ViewHolder {

    public TextView valor;
    public TextView endereco;
    public TextView informacoes;

    public ImovelHolder(View view) {
        super(view);
        this.valor = view.findViewById(R.id.imovel_list_valor);
        this.endereco = view.findViewById(R.id.imovel_list_endereco);
        this.informacoes = view.findViewById(R.id.imovel_list_informacoes);
    }

}