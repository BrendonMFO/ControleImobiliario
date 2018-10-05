package com.cristallium.brendon.imobiliario.Registro;

import android.os.Bundle;
import android.view.View;
import com.cristallium.brendon.imobiliario.R;

public class ControleRegistroSalvar extends ControleRegistro<ControleRegistroSalvarInterface> {

    public static ControleRegistroSalvar newInstance() {
        ControleRegistroSalvar fragment = new ControleRegistroSalvar();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_controle_registro_salvar;
    }

    @Override
    protected void initControls() {
        getView().findViewById(R.id.btn_salvar_registro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onControleRegistroSalvar(ControleRegistroSalvarFlags.SALVAR_REGISTRO);
            }
        });
        getView().findViewById(R.id.btn_cancelar_registro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onControleRegistroSalvar(ControleRegistroSalvarFlags.CANCELAR_REGISTRO);
            }
        });
    }

}
