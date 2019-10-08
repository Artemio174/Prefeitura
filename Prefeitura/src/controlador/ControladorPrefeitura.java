/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.DaoPrefeitura;
import javax.swing.JOptionPane;
import modelo.Prefeitura;
import tela.manutencao.ManutencaoPrefeitura;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author netom
 */
public class ControladorPrefeitura {

    public static void inserir(ManutencaoPrefeitura man){
        Prefeitura objeto = new Prefeitura();
        objeto.setNome(man.jtfnome.getText());
        objeto.setEndereco(man.jtfendereco.getText());
        objeto.setNr_funcionarios(Integer.parseInt(man.jtfnr_funcionarios.getText()));

        
        boolean resultado = DaoPrefeitura.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else 
        if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
}

  public static void alterar(ManutencaoPrefeitura man){
        Prefeitura objeto = new Prefeitura();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfcodigo.getText()));
        objeto.setNome(man.jtfnome.getText());
        objeto.setEndereco(man.jtfendereco.getText());
        objeto.setNr_funcionarios(Integer.parseInt(man.jtfnr_funcionarios.getText()));
        
        
        boolean resultado = DaoPrefeitura.alterar(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } else 
        if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }

    public static void excluir(ManutencaoPrefeitura man){
        Prefeitura objeto = new Prefeitura();
        objeto.setCodigo(Integer.parseInt(man.jtfcodigo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoPrefeitura.excluir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } else 
        if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }

   public static void atualizarTabela(JTable tabela) {
        DefaultTableModel modelo = new DefaultTableModel();
        //definindo o cabeçalho da tabela
        modelo.addColumn("Codigo");
        modelo.addColumn("Nome");
        modelo.addColumn("Endereço");
        modelo.addColumn("Número de funcionários");
        List<Prefeitura> resultados = DaoPrefeitura.consultar();
        for (Prefeitura objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getNome());
            linha.add(objeto.getEndereco());
            linha.add(objeto.getNr_funcionarios());
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
   
   public static void atualizaCampos(ManutencaoPrefeitura man, int pk){ 
        Prefeitura objeto = DaoPrefeitura.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfcodigo.setText(objeto.getCodigo().toString());
        man.jtfnome.setText(objeto.getNome());
        man.jtfendereco.setText(objeto.getEndereco());
        man.jtfnr_funcionarios.setText(objeto.getNr_funcionarios().toString());
        
        man.jtfcodigo.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }

}
