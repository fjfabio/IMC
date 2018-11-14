package com.imc.model;

import java.io.Serializable;

import static java.lang.String.format;

public class HistoricoIMC implements Serializable {

    private String nomeUsuario;
    private Double imc;
    private String resultado;
    private String altura;
    private String peso;
    private String data;

    public HistoricoIMC() {
    }

    public HistoricoIMC(String nomeUsuario, Double imc, String resultado, String altura, String peso, String data) {
        this.nomeUsuario = nomeUsuario;
        this.imc = imc;
        this.resultado = resultado;
        this.altura = altura;
        this.peso = peso;
        this.data = data;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Double getImc() {
        return imc;
    }

    public void setImc(Double imc) {
        this.imc = imc;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return
                "Nome: " + nomeUsuario + "\n" +
                "Altura: " + altura + "\n" +
                "Peso: " + peso + "\n" +
                "IMC: " + format("%.2f", imc) + "\n" +
                "Resultado: " + resultado + "\n" +
                "Data: " + data;
    }


}
