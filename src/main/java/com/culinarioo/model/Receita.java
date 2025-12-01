package com.culinarioo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity // Indica que é uma tabela no banco
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Junta Doce e Salgada na mesma tabela
public abstract class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // O banco gera o ID automaticamente
    private UUID id;

    private String nome;

    // Configura a relação com ingredientes (Salva junto e deleta junto)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingrediente> ingredientes = new ArrayList<>();

    @Column(columnDefinition = "TEXT") // Permite textos longos
    private String modoPreparo;

    @Enumerated(EnumType.STRING) // Salva como texto no banco (ex: "ALMOCO")
    private Categoria categoria;

    // 1. Construtor Vazio (OBRIGATÓRIO para o banco funcionar)
    public Receita() {
    }

    // 2. Construtor normal (usado pelo seu Controller)
    public Receita(String nome, String modoPreparo, Categoria categoria) {
        this.nome = nome;
        this.modoPreparo = modoPreparo;
        this.categoria = categoria;
    }

    public abstract String getTipoEtiqueta();

    // --- GETTERS E SETTERS ---

    public UUID getId() { return id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; } // Deixei apenas um aqui!

    public List<Ingrediente> getIngredientes() { return ingredientes; }
    public void setIngredientes(List<Ingrediente> ingredientes) { this.ingredientes = ingredientes; }

    public Categoria getCategoria() { return categoria; }

    // Faltava este Setter no seu código original:
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public String getModoPreparo() { return modoPreparo; }

    // Faltava este Setter no seu código original:
    public void setModoPreparo(String modoPreparo) { this.modoPreparo = modoPreparo; }
}