package com.example.vivian.projetomoraaqui;

/**
 * Created by Vivian on 04/06/2017.
 */

public class MoreAqui {

    private int id;
    private String telefone;
    private String tamanho;
    private String tipo;
    private String emConstrucao;


    public MoreAqui() {

    }

    public MoreAqui(String telefone, String tamanho, String tipo) {
        this.telefone = telefone;
        this.tamanho = tamanho;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmConstrucao() {
        return emConstrucao;
    }

    public void setEmConstrucao(String emConstrucao) {
        this.emConstrucao = emConstrucao;
    }
}
