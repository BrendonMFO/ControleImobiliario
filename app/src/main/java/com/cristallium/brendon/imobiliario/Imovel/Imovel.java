package com.cristallium.brendon.imobiliario.Imovel;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class Imovel implements Parcelable {

    private Integer id;
    private Integer valor;
    private String endereco;
    private String informacoes;
    private String proprietario;

    public static final Parcelable.Creator<Imovel> CREATOR = new Parcelable.Creator<Imovel>() {
        @NonNull
        public Imovel createFromParcel(Parcel in) {
            return new Imovel(in);
        }

        @NonNull
        public Imovel[] newArray(int size) {
            return new Imovel[size];
        }
    };

    private Imovel(Parcel from) {
        valor = from.readInt();
        endereco = from.readString();
        informacoes = from.readString();
        proprietario = from.readString();
    }

    private Imovel(String endereco, String proprietario, String informacoes, Integer valor) {
        setValues( endereco, proprietario, informacoes, valor);
    }

    public Imovel() {
        this("", "", "", 0);
    }

    public void setValues(String endereco, String proprietario, String informacoes, Integer valor) {
        this.valor = valor;
        this.endereco = endereco;
        this.informacoes = informacoes;
        this.proprietario = proprietario;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Integer getValor() {
        return valor;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getInformacoes() {
        return informacoes;
    }

    public String getProprietario() {
        return proprietario;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(valor);
        dest.writeString(endereco);
        dest.writeString(informacoes);
        dest.writeString(proprietario);
    }
}
