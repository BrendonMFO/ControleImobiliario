package com.cristallium.brendon.imobiliario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText txtValor;
    private EditText txtEndereco;
    private EditText txtInformacoes;
    private EditText txtProprietario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtValor = (EditText)findViewById(R.id.txt_valor);
        txtEndereco = (EditText)findViewById(R.id.txt_endereco);
        txtInformacoes = (EditText)findViewById(R.id.txt_informacoes);
        txtProprietario = (EditText)findViewById(R.id.txt_proprietario);
    }

    private void clearFields() {
        txtValor.setText("");
        txtEndereco.setText("");
        txtInformacoes.setText("");
        txtProprietario.setText("");
    }

    private void enableDisableFields(boolean enable) {
        txtValor.setEnabled(enable);
        txtEndereco.setEnabled(enable);
        txtInformacoes.setEnabled(enable);
        txtProprietario.setEnabled(enable);
    }

}
