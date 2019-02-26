package com.wwsystems.bomcidadao.model;

public class ItemTelefone {

    private int idTelefone;
    private String nomeTelefone;
    private String telefoneContato;
    private String nomeCidade;

    public void setIdTelefone(int idTelefone) {
        this.idTelefone = idTelefone;
    }

    public void setnomeTelefone(String nomeTelefone) {
        this.nomeTelefone = nomeTelefone;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public void setNomeTelefone(String nomeTelefone) {
        this.nomeTelefone = nomeTelefone;
    }

    public int getIdTelefone() {
        return idTelefone;
    }

    public String getNomeTelefone() {
        return nomeTelefone;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }
}
