package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cardapio {
    //Atributos
    private Map<String, List<Opcao>> cardapioSemanal;

    public Cardapio() {
        cardapioSemanal = new HashMap<>();

        for (String dia : getDiasSemana()) {
            cardapioSemanal.put(dia, new ArrayList<>());
        }
    }

    //Métodos
    public void adicionarOpcao(String diaSemana, Opcao opcao) {
        List<Opcao> opcoesDia = cardapioSemanal.get(diaSemana.toLowerCase());
        if (opcoesDia != null) {
            opcoesDia.add(opcao);
        }
    }

    public List<Opcao> getOpcoesDia(String diaSemana) {
        return cardapioSemanal.get(diaSemana.toLowerCase());
    }

    private String[] getDiasSemana() {
        return new String[] { "segunda-feira", "terça-feira", "quarta-feira", "quinta-feira", "sexta-feira" };
    }

    public void limpaCardapio() {
        for (String dia : getDiasSemana()) {
            List<Opcao> opcoesDia = cardapioSemanal.get(dia.toLowerCase());
            opcoesDia.clear();
        }
    }
}
