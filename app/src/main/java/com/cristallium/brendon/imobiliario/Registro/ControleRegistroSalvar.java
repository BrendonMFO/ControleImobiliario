package com.cristallium.brendon.imobiliario.Registro;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cristallium.brendon.imobiliario.R;

public class ControleRegistroSalvar extends Fragment {

    private Button btnSalvar;
    private Button btnCancelar;
    private ControleRegistroSalvarInterface mListener;

    public ControleRegistroSalvar() {}

    public static ControleRegistroSalvar newInstance() {
        ControleRegistroSalvar fragment = new ControleRegistroSalvar();
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
        return inflater.inflate(R.layout.fragment_controle_registro_salvar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSalvar = getView().findViewById(R.id.btn_salvar_registro);
        btnCancelar = getView().findViewById(R.id.btn_cancelar_registro);
        setClickListeners();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ControleRegistroSalvarInterface) {
            mListener = (ControleRegistroSalvarInterface) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void setClickListeners() {
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onControleRegistroSalvar(ControleRegistroSalvarFlags.SALVAR_REGISTRO);
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onControleRegistroSalvar(ControleRegistroSalvarFlags.CANCELAR_REGISTRO);
            }
        });
    }

}
