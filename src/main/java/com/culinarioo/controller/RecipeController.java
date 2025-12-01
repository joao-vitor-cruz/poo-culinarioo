package com.culinarioo.controller;

import com.culinarioo.model.*;
import com.culinarioo.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.UUID;

@Controller
public class RecipeController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping("/recipes")
    public String recipes(Model model) {
        model.addAttribute("listaReceitas", receitaService.listarTodas());
        return "recipes";
    }

    @GetMapping("/recipes/new")
    public String newRecipeForm(Model model) {
        model.addAttribute("receita", null);
        return "recipe-form";
    }

    // Rota de Edição
    @GetMapping("/recipes/edit/{id}")
    public String editRecipeForm(@PathVariable UUID id, Model model) {
        Receita receita = receitaService.buscarPorId(id);
        model.addAttribute("receita", receita);
        return "recipe-form";
    }

    @GetMapping("/recipes/delete/{id}")
    public String deleteRecipe(@PathVariable UUID id) {
        receitaService.excluir(id);
        return "redirect:/recipes";
    }

    @PostMapping("/recipes/save")
    public String saveRecipe(
            @RequestParam(required = false) UUID id,
            @RequestParam String nome,
            @RequestParam String modoPreparo,
            @RequestParam Categoria categoria,
            @RequestParam String tipoReceita,
            @RequestParam String ingredientesTexto
    ) {
        Receita receita;

        if (id != null) {
            receita = receitaService.buscarPorId(id);

            receita.setNome(nome);
            receita.setModoPreparo(modoPreparo);
            receita.setCategoria(categoria);

            receita.getIngredientes().clear();

        } else {
            if (tipoReceita.equals("DOCE")) {
                receita = new ReceitaDoce(nome, modoPreparo, categoria);
            } else {
                receita = new ReceitaSalgada(nome, modoPreparo, categoria);
            }
        }

        if (ingredientesTexto != null && !ingredientesTexto.isEmpty()) {
            Arrays.stream(ingredientesTexto.split(","))
                    .map(String::trim)
                    .filter(nomeIng -> !nomeIng.isEmpty()) // Proteção contra strings vazias
                    .forEach(nomeIng ->
                            receita.getIngredientes().add(new Ingrediente(nomeIng, "a gosto"))
                    );
        }

        receitaService.salvar(receita);

        return "redirect:/recipes";
    }
}