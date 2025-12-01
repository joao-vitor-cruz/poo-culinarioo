package com.culinarioo.model;

import jakarta.persistence.Entity; // Importe isso

@Entity
public class ReceitaDoce extends Receita {

    public ReceitaDoce() { super(); }

    public ReceitaDoce(String nome, String modoPreparo, Categoria categoria) {
        super(nome, modoPreparo, categoria);
    }

    @Override
    public String getTipoEtiqueta() {
        return "Doce";
    }
}