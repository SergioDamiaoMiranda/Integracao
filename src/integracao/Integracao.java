/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integracao;

import dao.ParametroDAO;
import db.DaoException;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author Sérgio Damião Miranda
 */
public class Integracao {


    public static void main(String[] args) {
        // Verifica a estrutura dos arquivos
        if (!ApplicationInstanceManager.registerInstance()) {
            // instance already running.
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("ARIAL", Font.PLAIN, 18)));
            UIManager.put("OptionPane.messageForeground", Color.blue);
            UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL", Font.PLAIN, 18)));
            JOptionPane.showMessageDialog(null, "O Sistema Integração já está ativo!");
            System.out.println("APContas já foi ativado! Abandonando....");
            System.exit(0);
        }
        
        // Verifica estrutura dos diretórios e cria se não existir  
        try {
            String localPathErros       = ParametroDAO.getDiretorio() + File.separator  + "erros" + File.separator;
            String localPathProcessados = ParametroDAO.getDiretorio() + File.separator  + "processados" + File.separator;            
            File diretorio = new File(localPathErros);
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            } 
            diretorio = new File(localPathProcessados);
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }             
        } catch (DaoException ex) {
                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("ARIAL", Font.PLAIN, 18)));
                UIManager.put("OptionPane.messageForeground", Color.blue);
                UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL", Font.PLAIN, 18)));
                JOptionPane.showMessageDialog(null, "  Diretório no BD parametro com erro. \n Sistema Integraçao Estoque Cancelado! \n         Ligue suporte (24)2249-3779.");
                System.out.println("Intervalo menor igual a ZERO. Sistema cancelado!"); 
                System.exit(0); 
        }
      
        try {
            int delay = 0;                                // tempo de espera antes da 1ª execução da tarefa.
            int intervalo = ParametroDAO.getIntervalo();  // intervalo no qual a tarefa será executada.
            if (intervalo <= 0) {
                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("ARIAL", Font.PLAIN, 18)));
                UIManager.put("OptionPane.messageForeground", Color.blue);
                UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL", Font.PLAIN, 18)));
                JOptionPane.showMessageDialog(null, "Intervalo no BD parametro esta menor igual a ZERO. \n         Sistema Integraçao Estoque Cancelado! \n              Ligue suporte (24)2249-3779.");
                System.out.println("Intervalo menor igual a ZERO. Sistema cancelado!"); 
                System.exit(0);                
            }
            Timer timer =  new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    processaArquivos();
                }
            }, delay, intervalo);
            
        } catch (DaoException ex) {
            System.out.println("Erro consulta intervalo! \n" + ex.getMessage());
        }
    }
    
    private static void processaArquivos(){
        // pegar arquivo no diretorio padrao
        
        System.out.println("Processando arquivo..");
        
    }
}

