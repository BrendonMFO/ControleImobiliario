package com.cristallium.brendon.imobiliario;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;
import android.view.MenuItem;
import android.widget.EditText;
import android.view.MenuInflater;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentTransaction;

import com.cristallium.brendon.imobiliario.Imovel.Imovel;
import com.cristallium.brendon.imobiliario.Imovel.ImovelDatabaseHelper;
import com.cristallium.brendon.imobiliario.Registro.ControleRegistroSalvar;
import com.cristallium.brendon.imobiliario.Registro.ControleRegistroSalvarFlags;
import com.cristallium.brendon.imobiliario.Registro.ControleRegistroVisualizacao;
import com.cristallium.brendon.imobiliario.Registro.ControleRegistroSalvarInterface;
import com.cristallium.brendon.imobiliario.Registro.ControleRegistroVisualizacaoFlags;
import com.cristallium.brendon.imobiliario.Registro.ControleRegistroVisualizacaoInterface;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ControleRegistroVisualizacaoInterface, ControleRegistroSalvarInterface {

    private enum TIPO {
        EDITAR_IMOVEL,
        CADASTRAR_IMOVEL,
        VISUALIZAR_IMOVEL,
    }

    private TIPO tipo;
    private EditText txtId;
    private EditText txtValor;
    private Integer activeIndex;
    private EditText txtEndereco;
    private EditText txtInformacoes;
    private EditText txtProprietario;
    private ArrayList<Imovel> imoveis;
    private ImovelDatabaseHelper imovelDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activeIndex = 0;
        imovelDatabase = new ImovelDatabaseHelper(this);
        imoveis = imovelDatabase.getAllImoveis();
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
            case R.id.listar_registro:
                listarImoveis();
                return true;
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

    private void listarImoveis() {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putParcelableArrayListExtra("Imoveis", imoveis);
        startActivity(intent);
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
            Toast.makeText(
                    getApplicationContext(),
                    "Nenhum Imovel cadastrado",
                    Toast.LENGTH_LONG
            ).show();
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
            Toast.makeText(
                    getApplicationContext(),
                    "Nenhum Imovel para excluir.",
                    Toast.LENGTH_LONG
            ).show();
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
        Imovel novoImovel = new Imovel();
        imoveis.add(novoImovel);
        imovelDatabase.addImovel(novoImovel);
        activeIndex = imoveis.size() - 1;
        setImovelValues();
    }

    private void setImovelValues() {
        Imovel imovel = imoveis.get(activeIndex);
        imovelDatabase.editImovel(activeIndex, imovel);
        imovel.setValues(
                txtEndereco.getText().toString(),
                txtProprietario.getText().toString(),
                txtInformacoes.getText().toString(),
                Integer.parseInt(txtValor.getText().toString())
        );
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
