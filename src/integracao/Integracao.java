/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integracao;

import dao.HArquivoDAO;
import dao.LojaDAO;
import dao.ParametroDAO;
import db.DaoException;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import to.LojaTO;

/**
 *
 * @author Sérgio Damião Miranda
 */
public class Integracao {

    static String diretorioPadrao;
    static String localPathEnviar;
    static String localPathErros;
    static String localPathProcessados;
       

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
            diretorioPadrao = ParametroDAO.getDiretorio();
            localPathEnviar      = diretorioPadrao + File.separator  + "enviar" + File.separator;
            localPathErros       = diretorioPadrao + File.separator  + "erros" + File.separator;
            localPathProcessados = diretorioPadrao + File.separator  + "processados" + File.separator;            
            File diretorio = new File(localPathErros);
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            } 
            diretorio = new File(localPathProcessados);
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }    
            diretorio = new File(localPathEnviar);
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

        String linha, parteNome;
        PrintWriter outputStream = null;
        BufferedReader inputStream;        
        
	File file = new File(localPathEnviar);
	File afile[] = file.listFiles();
	int i = 0;
	for (int j = afile.length; i < j; i++) {
            File arquivos = afile[i];                
            System.out.println(" ======================= Processando (1) " + arquivos.getName());
            parteNome = arquivos.getName().substring(0,arquivos.getName().indexOf("_"));            
            try {
                inputStream = new BufferedReader(new FileReader(arquivos));

                if (parteNome.equals("cad-loja")) ProcessaCadastroLoja(inputStream, arquivos);
                 
            } catch (DaoException | IOException ex) {
                System.out.println("Erro processar arquivo - " + arquivos.getName() + ". \n" + ex.getMessage());
            }
            System.out.println("------------------------------ processado arq. " + arquivos.getName());            
	}

        System.out.println("Fim processa arquivos...");
        
    }

    private static void ProcessaCadastroLoja(BufferedReader inputStream, File arquivo) throws IOException, DaoException {
        String linha, proc = "NAO";
        int nLinha = 1;
        LojaTO clTO = new LojaTO();
        while ((linha  = inputStream.readLine()) != null ){
            
            switch(nLinha) {
                case 1:
                    clTO.setCodigo(linha.replace("<codigo>", "").replace("</codigo>", ""));
                    break;
                case 2:
                    clTO.setCnpj(linha.replace("<cnpj>", "").replace("</cnpj>", ""));
                    break;
                case 3:
                    clTO.setNome(linha.replace("<nome>", "").replace("</nome>", ""));
                    break;
            }
           
            nLinha = nLinha + 1;
        }
        clTO.setArquivo(arquivo.getName());   
        inputStream.close();
        
        try {
            // Grava registro na tabela loja
            LojaDAO.inserirLoja(clTO);
            proc = "Sim";
             // move arquivo processado
            geral.Geral.moveArquivos(arquivo, new File(arquivo.getPath().replace("enviar", "processados").replace(".env", ".ok")));            
        } catch (DaoException ex) {
            // move arquivo processado
            File novoArquivo = new File(arquivo.getPath().replace("enviar", "erros").replace("cad-", "Loja_JA_cadastrada_cad-").replace(".env", ".err"));
            geral.Geral.moveArquivos(arquivo, novoArquivo);
        }
         // Grava arquivo log arquivo processado OK
        HArquivoDAO.inserirLoja(arquivo.getName(), proc);

    }
}

