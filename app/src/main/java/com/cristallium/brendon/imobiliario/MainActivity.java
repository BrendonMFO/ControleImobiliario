package com.cristallium.brendon.imobiliario;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.cristallium.brendon.imobiliario.Imovel.Imovel;
import com.cristallium.brendon.imobiliario.Registro.ControleRegistroSalvar;
import com.cristallium.brendon.imobiliario.Registro.ControleRegistroSalvarFlags;
import com.cristallium.brendon.imobiliario.Registro.ControleRegistroVisualizacao;
import com.cristallium.brendon.imobiliario.Registro.ControleRegistroSalvarInterface;
import com.cristallium.brendon.imobiliario.Registro.ControleRegistroVisualizacaoFlags;
import com.cristallium.brendon.imobiliario.Registro.ControleRegistroVisualizacaoInterface;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ControleRegistroVisualizacaoInterface, ControleRegistroSalvarInterface {

    private enum TIPO {
        VISUALIZAR_IMOVEL,
        CADASTRAR_IMOVEL,
        EDITAR_IMOVEL
    }

    private TIPO tipo;
    private EditText txtId;
    private EditText txtValor;
    private Integer activeIndex;
    private EditText txtEndereco;
    private EditText txtInformacoes;
    private EditText txtProprietario;
    private ArrayList<Imovel> imoveis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activeIndex = 0;
        imoveis = new ArrayList<>();
        tipo = TIPO.VISUALIZAR_IMOVEL;
        txtId = findViewById(R.id.txtId);
        txtValor = findViewById(R.id.txt_valor);
        txtEndereco = findViewById(R.id.txt_endereco);
        txtInformacoes = findViewById(R.id.txt_informacoes);
        txtProprietario = findViewById(R.id.txt_proprietario);
        addControlFragment(ControleRegistroVisualizacao.newInstance());
    }

    @Override
    public void onControleRegistro(ControleRegistroVisualizacaoFlags flag) {
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
                addEditImovel();
                clearFields();
                enableDisableFields(false);
                setActiveIndex(imoveis.size() - 1);
                break;
            case CANCELAR_REGISTRO:
                clearFields();
                enableDisableFields(false);
                setActiveIndex(activeIndex);
                tipo = TIPO.VISUALIZAR_IMOVEL;
                break;
        }
        addControlFragment(ControleRegistroVisualizacao.newInstance());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cadastrar_registro:
                createMode();
                return true;
            case R.id.editar_registro:
                editMode();
                return true;
            case R.id.excluir_registro:
                removeMode();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createMode() {
        clearFields();
        enableDisableFields(true);
        addControlFragment(ControleRegistroSalvar.newInstance());
        tipo = TIPO.CADASTRAR_IMOVEL;
    }

    private void editMode() {
        if (!imoveis.isEmpty()) {
            enableDisableFields(true);
            addControlFragment(ControleRegistroSalvar.newInstance());
            tipo = TIPO.EDITAR_IMOVEL;
        } else {
            showMessage("Nenhum Imovel cadastrado");
        }
    }

    private void removeMode() {
        if (!imoveis.isEmpty()) {
            showDialog(
                    "Excluir Imovel ?",
                    "Deseja realmente excluir este Imovel",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            clearFields();
                            imoveis.remove(activeIndex.intValue());
                            setActiveIndex(0);
                        }
                    }
            );
        } else {
            showMessage("Nenhum Imovel para excluir.");
        }
    }

    private boolean verifyFields() {
        return !txtValor.getText().toString().trim().isEmpty() &&
                !txtEndereco.getText().toString().trim().isEmpty() &&
                !txtInformacoes.getText().toString().trim().isEmpty() &&
                !txtProprietario.getText().toString().trim().isEmpty();
    }

    private void clearFields() {
        txtId.setText("");
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

    private void addControlFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_control, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void setActiveIndex(int index) {
        if (index <= imoveis.size() - 1 && index >= 0) {
            activeIndex = index;
            txtId.setText(String.valueOf(index));
            txtEndereco.setText(imoveis.get(index).getEndereco());
            txtInformacoes.setText(imoveis.get(index).getInformacoes());
            txtProprietario.setText(imoveis.get(index).getProprietario());
            txtValor.setText(String.valueOf(imoveis.get(index).getValor()));
        }
    }

    private void addEditImovel() {
        if (verifyFields()) {
            switch (tipo) {
                case CADASTRAR_IMOVEL:
                    addImovel();
                    break;
                case EDITAR_IMOVEL:
                    setImovelValues();
                    break;
            }
        }
    }

    private void addImovel() {
        imoveis.add(new Imovel());
        activeIndex = imoveis.size() - 1;
        setImovelValues();
    }

    private void setImovelValues() {
        Imovel imovel = imoveis.get(activeIndex);
        imovel.setValues(
                txtEndereco.getText().toString(),
                txtProprietario.getText().toString(),
                txtInformacoes.getText().toString(),
                Integer.parseInt(txtValor.getText().toString())
        );
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private void showDialog(String title, String Message, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(title)
                .setMessage(Message)
                .setPositiveButton("Sim", listener)
                .setNegativeButton("Nao", null)
                .show();
    }

}
