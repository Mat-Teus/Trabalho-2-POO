package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cardapio {
    //Atributos
    private Map <String, List<Opcao>> CardapioSemanal;
    
    //Métodos 
    public Cardapio() {
        CardapioSemanal = new HashMap<>();
        for (String dia : getDiasSemana()) {
            CardapioSemanal.put(dia, new ArrayList<>());
        }
    }
    
    public void adicionarOpcaoAlimento(String diaSemana, Opcao opcao) {
        List<Opcao> opcoesDia = CardapioSemanal.get(diaSemana.toLowerCase());
        if (opcoesDia != null) {
            opcoesDia.add(opcao);
        }
    }
    
    public List<Opcao> getOpcoesDia(String diaSemana) {
        return CardapioSemanal.get(diaSemana.toLowerCase());
    }

     private String[] getDiasSemana() {
        return new String[] { "segunda-feira", "terça-feira", "quarta-feira", "quinta-feira", "sexta-feira" };
    }
     
     public void limpaCardapio() {
        for (String dia : getDiasSemana()) {
        List<Opcao> opcoesDia = CardapioSemanal.get(dia.toLowerCase());
        opcoesDia.clear();
        }
    }
}
