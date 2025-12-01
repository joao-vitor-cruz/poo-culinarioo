package com.culinarioo.service;

import com.culinarioo.model.Receita;
import com.culinarioo.repository.ReceitaRepository; // Importe o novo repositório
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository; // Agora usamos o Repository, não o DAO

    public List<Receita> listarTodas() {
        return receitaRepository.findAll();
    }

    public void salvar(Receita receita) {
        receitaRepository.save(receita);
    }

    public Receita buscarPorId(UUID id) {
        // O findById retorna um Optional, por isso usamos o .orElse(null) se não achar
        return receitaRepository.findById(id).orElse(null);
    }

    public void excluir(UUID id) {
        receitaRepository.deleteById(id);
    }
}