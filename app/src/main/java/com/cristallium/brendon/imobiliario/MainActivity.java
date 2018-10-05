package com.cristallium.brendon.imobiliario;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.cristallium.brendon.imobiliario.Registro.ControleRegistro;
import com.cristallium.brendon.imobiliario.Registro.ControleRegistroFlags;
import com.cristallium.brendon.imobiliario.Registro.ControleRegistroSalvar;
import com.cristallium.brendon.imobiliario.Registro.ControleRegistroSalvarFlags;
import com.cristallium.brendon.imobiliario.Registro.IControleRegistro;
import com.cristallium.brendon.imobiliario.Registro.IControleRegistroSalvar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IControleRegistro, IControleRegistroSalvar {

    private EditText txtValor;
    private EditText txtEndereco;
    private EditText txtInformacoes;
    private EditText txtProprietario;
    private ArrayList<Imovel> imoveis;
    private Integer activeIndex = 0;
    private boolean isControleRegistro = false;
    private boolean isControleRegistroSalvar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imoveis = new ArrayList<>();
        txtValor = findViewById(R.id.txt_valor);
        txtEndereco = findViewById(R.id.txt_endereco);
        txtInformacoes = findViewById(R.id.txt_informacoes);
        txtProprietario = findViewById(R.id.txt_proprietario);
        addControleRegistro();
    }

    public void createMode(View view) {
        clearFields();
        enableDisableFields(true);
        addControleRegistroSalvar();
    }

    private boolean verifyFields() {
        return txtValor.getText().toString().trim().isEmpty() &&
                txtEndereco.getText().toString().trim().isEmpty() &&
                txtInformacoes.getText().toString().trim().isEmpty() &&
                txtProprietario.getText().toString().trim().isEmpty();
    }

    private void addControleRegistro() {
        if(!isControleRegistro) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container_control, ControleRegistro.newInstance());
            transaction.addToBackStack(null);
            transaction.commit();
            isControleRegistro = true;
            isControleRegistroSalvar = false;
        }
    }

    private void addControleRegistroSalvar() {
        if(!isControleRegistroSalvar) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container_control, ControleRegistroSalvar.newInstance());
            transaction.addToBackStack(null);
            transaction.commit();
            isControleRegistro = false;
            isControleRegistroSalvar = true;
        }
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

    private void setActiveIndex(int index) {
        if(index < imoveis.size() - 1 && index >= 0) {
            activeIndex = index;
            txtValor.setText(imoveis.get(index).getValor());
            txtEndereco.setText(imoveis.get(index).getEndereco());
            txtInformacoes.setText(imoveis.get(index).getInformacoes());
            txtProprietario.setText(imoveis.get(index).getProprietario());
        }
    }

    private void addImovel() {
        if(verifyFields()) {
            imoveis.add(new Imovel(
                    txtEndereco.getText().toString(),
                    txtProprietario.getText().toString(),
                    txtInformacoes.getText().toString(),
                    Integer.getInteger(txtValor.getText().toString())
            ));
            activeIndex = imoveis.size() - 1;
        }
    }

    @Override
    public void onControleRegistro(ControleRegistroFlags flag) {
        switch (flag) {
            case ULTIMO_REGISTRO:
                setActiveIndex(imoveis.size() - 1);
                break;
            case AVANCAR_REGISTRO:
                setActiveIndex(activeIndex + 1);
                break;
            case PRIMEIRO_REGISTRO:
                setActiveIndex(0);
                break;
            case RETROCEDER_REGISTRO:
                setActiveIndex(activeIndex - 1);
                break;
        }
    }

    @Override
    public void onControleRegistroSalvar(ControleRegistroSalvarFlags flag) {
        switch (flag) {
            case SALVAR_REGISTRO:
                addImovel();
                break;
            case CANCELAR_REGISTRO:
                clearFields();
                enableDisableFields(false);
                addControleRegistro();
                break;
        }
    }

}
