package com.cristallium.brendon.imobiliario.Registro;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cristallium.brendon.imobiliario.R;

public class ControleRegistroSalvar extends Fragment {

    private IControleRegistroSalvar mListener;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_controle_registro_salvar, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IControleRegistroSalvar) {
            mListener = (IControleRegistroSalvar) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
