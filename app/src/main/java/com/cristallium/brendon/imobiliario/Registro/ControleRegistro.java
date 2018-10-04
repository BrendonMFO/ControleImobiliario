package com.cristallium.brendon.imobiliario.Registro;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cristallium.brendon.imobiliario.R;

public class ControleRegistro extends Fragment {

    private ImageButton btnUltimoRegistro;
    private ImageButton btnAvancarRegistro;
    private ImageButton btnPrimeiroRegistro;
    private ImageButton btnRetrocederRegistro;

    public ControleRegistro() {}

    public static ControleRegistro newInstance() {
        ControleRegistro fragment = new ControleRegistro();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_controle_registro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnUltimoRegistro = (ImageButton)getView().findViewById(R.id.btn_ultimo_registro);
        btnAvancarRegistro = (ImageButton)getView().findViewById(R.id.btn_avancar_registro);
        btnPrimeiroRegistro = (ImageButton)getView().findViewById(R.id.btn_primeiro_registro);
        btnRetrocederRegistro = (ImageButton)getView().findViewById(R.id.btn_retroceder_registro);
    }

}
