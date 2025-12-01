package com.culinarioo.model;

import jakarta.persistence.Entity; // Importe isso

@Entity
public class ReceitaSalgada extends Receita {

    public ReceitaSalgada() { super(); }

    public ReceitaSalgada(String nome, String modoPreparo, Categoria categoria) {
        super(nome, modoPreparo, categoria);
    }

    @Override
    public String getTipoEtiqueta() {
        return "Salgado";
    }
}