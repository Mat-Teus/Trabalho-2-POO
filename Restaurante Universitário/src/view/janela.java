package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Opcao;
import model.Cardapio;

public class janela extends JFrame implements ActionListener{
    //Atributos
    private JButton btInserir, btMostrar, btModificar, btApagar, btSair;
    private JPanel pnBotoes;
    private Cardapio cardapio = new Cardapio();
    
    //Métodos
    public janela(){
        //Botões
        btInserir = new JButton("Inserir Cardapio");
        btMostrar = new JButton("Mostrar Cardapio");
        btModificar = new JButton("Modificar Cardapio");
        btApagar = new JButton("Apagar Cardapio");
        btSair = new JButton("Sair");
        
        //Paineis
        pnBotoes = new JPanel();
        pnBotoes.setLayout(new GridLayout(1,4));
        pnBotoes.add(btInserir);pnBotoes.add(btMostrar);pnBotoes.add(btModificar);
        pnBotoes.add(btApagar);pnBotoes.add(btSair);
        
        //Layout
        this.setLayout(new BorderLayout());
        this.add(pnBotoes,BorderLayout.SOUTH);
        
        //Janela
        this.setSize(700,65);
        this.setVisible(true);
        this.setTitle("Restaurante Universitario");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //Ações
        btSair.addActionListener(this);
        btInserir.addActionListener(this);
        btMostrar.addActionListener(this);
        btModificar.addActionListener(this);
        btApagar.addActionListener(this);
    }
    
     private void inserirOpCardapio() {
        String[] diasSemana = { "segunda-feira", "terça-feira", "quarta-feira", "quinta-feira", "sexta-feira" };
        String diaSelecionado = (String) JOptionPane.showInputDialog(this, "Selecione o dia da semana:", "Inserir Opção no Cardápio", JOptionPane.QUESTION_MESSAGE, null, diasSemana, diasSemana[0]);

        if (diaSelecionado != null) {
            String nomeAlimento = JOptionPane.showInputDialog(this, "Digite o nome do alimento:");
            String precoString = JOptionPane.showInputDialog(this, "Digite o preço do alimento:");

            try {
                double precoAlimento = Double.parseDouble(precoString);
                Opcao opcao = new Opcao(nomeAlimento, precoAlimento);
                cardapio.adicionarOpcaoAlimento(diaSelecionado, opcao);
                JOptionPane.showMessageDialog(this, "Opção de alimento inserida com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Digite um valor numérico válido para o preço!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
     }
     
     private void mostrarCardapio(){
        StringBuilder cardapioCompleto = new StringBuilder();
        String[] diasSemana = { "segunda-feira", "terça-feira", "quarta-feira", "quinta-feira", "sexta-feira" };

            for (String dia : diasSemana) {
                List<Opcao> opcoesDia = cardapio.getOpcoesDia(dia);
                cardapioCompleto.append(dia).append(":\n");
                if (opcoesDia != null && !opcoesDia.isEmpty()) {
                    for (Opcao opcao : opcoesDia) {
                        cardapioCompleto.append("- ").append(opcao.getNome()).append(" - R$ ").append(opcao.getPreco()).append("\n");
                }   
                } else {
                    cardapioCompleto.append("Nenhuma opção cadastrada.\n");
                }
                    cardapioCompleto.append("\n");
            }

        JOptionPane.showMessageDialog(this, cardapioCompleto.toString(), "Cardápio Completo", JOptionPane.INFORMATION_MESSAGE);
    }
     
    private void modificaCardapio(){
        String[] diasSemana = { "segunda-feira", "terça-feira", "quarta-feira", "quinta-feira", "sexta-feira" };
    String diaSelecionado = (String) JOptionPane.showInputDialog(this, "Selecione o dia da semana:", "Modificar Opção no Cardápio", JOptionPane.QUESTION_MESSAGE, null, diasSemana, diasSemana[0]);

    if (diaSelecionado != null) {
        List<Opcao> opcoesDia = cardapio.getOpcoesDia(diaSelecionado);
        if (opcoesDia != null && !opcoesDia.isEmpty()) {
            String[] nomesOpcoes = new String[opcoesDia.size()];
            for (int i = 0; i < opcoesDia.size(); i++) {
                nomesOpcoes[i] = opcoesDia.get(i).getNome();
            }

            String opcaoSelecionada = (String) JOptionPane.showInputDialog(this, "Selecione a opção a ser modificada:", "Modificar Opção no Cardápio", JOptionPane.QUESTION_MESSAGE, null, nomesOpcoes, nomesOpcoes[0]);

            if (opcaoSelecionada != null) {
                String novoNome = JOptionPane.showInputDialog(this, "Digite o novo nome do alimento:");
                String novoPrecoString = JOptionPane.showInputDialog(this, "Digite o novo preço do alimento:");

                try {
                    double novoPreco = Double.parseDouble(novoPrecoString);
                    for (Opcao opcao : opcoesDia) {
                        if (opcao.getNome().equals(opcaoSelecionada)) {
                            opcao.setNome(novoNome);
                            opcao.setPreco(novoPreco);
                            JOptionPane.showMessageDialog(this, "Opção de alimento modificada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Digite um valor numérico válido para o preço!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nenhuma opção cadastrada para o dia selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
        JOptionPane.showMessageDialog(this, "Cardapio modificado com sucesso");
    }
     
    private void apagaCardapio(){
        cardapio.limpaCardapio();
        JOptionPane.showMessageDialog(this, "Cardápio apagado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void saiApp(){
        System.exit(1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btSair){
            saiApp();
            }
        if(e.getSource()==btInserir){
                inserirOpCardapio();
            }
        if(e.getSource()==btMostrar){
                mostrarCardapio();
            }
        if(e.getSource()==btModificar){
            modificaCardapio();
        }
        if(e.getSource()==btApagar){
                apagaCardapio();
            }
        }
    }
