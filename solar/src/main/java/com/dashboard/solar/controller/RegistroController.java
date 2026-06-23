package com.dashboard.solar.controller;

import com.dashboard.solar.model.RegistroEnergia;
import com.dashboard.solar.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/registros")
@CrossOrigin(origins = "*")
public class RegistroController {

    @Autowired
    private RegistroRepository repository;

    @GetMapping
    public List<RegistroEnergia> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public RegistroEnergia criarNova(@RequestBody RegistroEnergia novaUsina) {
        novaUsina.setStatus("ATIVO"); // Cadastros manuais já entram como clientes
        return repository.save(novaUsina);
    }

    @PostMapping("/cnpj/{cnpj}")
    public RegistroEnergia capturarLeadPorCnpj(@PathVariable String cnpj) {
        String url = "https://brasilapi.com.br/api/cnpj/v1/" + cnpj;
        RestTemplate restTemplate = new RestTemplate();
        try {
            Map<String, Object> dadosEmpresa = restTemplate.getForObject(url, Map.class);
            if (dadosEmpresa != null) {
                RegistroEnergia novoLead = new RegistroEnergia();
                novoLead.setNomeUsina((String) dadosEmpresa.get("razao_social"));
                novoLead.setNumeroContato((String) dadosEmpresa.get("ddd_telefone_1"));
                novoLead.setStatus("LEAD"); // O Segredo da separação visual!
                return repository.save(novoLead);
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar CNPJ: " + e.getMessage());
        }
        return null;
    }

    // Método de deletar!
    @DeleteMapping("/{id}")
    public void deletarRegistro(@PathVariable Long id) {
        repository.deleteById(id);
    }

    //  Método de atualizar!
    @PutMapping("/{id}")
    public RegistroEnergia atualizar(@PathVariable Long id, @RequestBody RegistroEnergia registroAtualizado) {
        // Garantimos que o Java saiba exatamente qual ID ele vai sobrescrever
        registroAtualizado.setId(id);
        return repository.save(registroAtualizado);
    }
}