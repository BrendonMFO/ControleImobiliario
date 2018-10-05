package com.cristallium.brendon.imobiliario.Registro;

import android.os.Bundle;
import android.view.View;
import com.cristallium.brendon.imobiliario.R;

public class ControleRegistroVisualizacao extends ControleRegistro<ControleRegistroVisualizacaoInterface> {

    public static ControleRegistroVisualizacao newInstance() {
        ControleRegistroVisualizacao fragment = new ControleRegistroVisualizacao();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_controle_registro;
    }

    @Override
    protected void initControls() {
        getView().findViewById(R.id.btn_ultimo_registro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onControleRegistro(ControleRegistroVisualizacaoFlags.ULTIMO_REGISTRO);
            }
        });
        getView().findViewById(R.id.btn_avancar_registro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onControleRegistro(ControleRegistroVisualizacaoFlags.AVANCAR_REGISTRO);
            }
        });
        getView().findViewById(R.id.btn_primeiro_registro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onControleRegistro(ControleRegistroVisualizacaoFlags.PRIMEIRO_REGISTRO);
            }
        });
        getView().findViewById(R.id.btn_retroceder_registro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onControleRegistro(ControleRegistroVisualizacaoFlags.RETROCEDER_REGISTRO);
            }
        });
    }

}
