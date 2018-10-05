package com.cristallium.brendon.imobiliario.Registro;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cristallium.brendon.imobiliario.R;

public class ControleRegistroVisualizacao extends Fragment {

    private ImageButton btnUltimoRegistro;
    private ImageButton btnAvancarRegistro;
    private ImageButton btnPrimeiroRegistro;
    private ImageButton btnRetrocederRegistro;
    private ControleRegistroVisualizacaoInterface iControleRegistro;

    public ControleRegistroVisualizacao() {}

    public static ControleRegistroVisualizacao newInstance() {
        ControleRegistroVisualizacao fragment = new ControleRegistroVisualizacao();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private void setClickListeners() {
        btnUltimoRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iControleRegistro.onControleRegistro(ControleRegistroVisualizacaoFlags.ULTIMO_REGISTRO);
            }
        });
        btnAvancarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iControleRegistro.onControleRegistro(ControleRegistroVisualizacaoFlags.AVANCAR_REGISTRO);
            }
        });
        btnPrimeiroRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iControleRegistro.onControleRegistro(ControleRegistroVisualizacaoFlags.PRIMEIRO_REGISTRO);
            }
        });
        btnRetrocederRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iControleRegistro.onControleRegistro(ControleRegistroVisualizacaoFlags.RETROCEDER_REGISTRO);
            }
        });
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
        btnUltimoRegistro = getView().findViewById(R.id.btn_ultimo_registro);
        btnAvancarRegistro = getView().findViewById(R.id.btn_avancar_registro);
        btnPrimeiroRegistro = getView().findViewById(R.id.btn_primeiro_registro);
        btnRetrocederRegistro = getView().findViewById(R.id.btn_retroceder_registro);
        setClickListeners();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ControleRegistroVisualizacaoInterface) {
            iControleRegistro = (ControleRegistroVisualizacaoInterface) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        iControleRegistro = null;
    }

}
