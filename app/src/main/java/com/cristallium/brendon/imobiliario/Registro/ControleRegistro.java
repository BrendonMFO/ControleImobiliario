package com.cristallium.brendon.imobiliario.Registro;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

abstract class ControleRegistro<T extends ControleRegistroInterface> extends Fragment {

    protected T listener;

    protected abstract int getLayout();

    protected abstract void initControls();

    public ControleRegistro() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initControls();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (T) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}
