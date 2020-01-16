/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integracao;

import dao.AlteraStatusDAO;
import dao.HArquivoDAO;
import dao.LojaDAO;
import dao.MovimentacaoDAO;
import dao.ParametroDAO;
import dao.ProdutoDAO;
import db.DaoException;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import to.AlteraStatusTO;
import to.LojaTO;
import to.MovimentacaoTO;
import to.ProdutoTO;

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
            parteNome = arquivos.getName().substring(0,arquivos.getName().indexOf("_"));            
            try {
                inputStream = new BufferedReader(new FileReader(arquivos));

                if (parteNome.equals("mov-produto")) ProcessaMovimentacao(inputStream, arquivos); 
                else {
                    if (parteNome.equals("alt-status")) ProcessaAlteracaoStatus(inputStream, arquivos); 
                    else {
                        if (parteNome.equals("cad-produto")) ProcessaCadastroProduto(inputStream, arquivos);                
                        else {
                            if (parteNome.equals("cad-loja")) ProcessaCadastroLoja(inputStream, arquivos);
                        }
                    }
                }
                 
            } catch (DaoException | IOException ex) {
                System.out.println("Erro processar arquivo - " + arquivos.getName() + ". \n" + ex.getMessage());
            }      
	}    
    }

    private static void ProcessaCadastroLoja(BufferedReader inputStream, File arquivo) throws IOException, DaoException {
        String linha, proc = "NAO";
        int nLinha = 1;
        LojaTO clTO = new LojaTO();
        while ((linha  = inputStream.readLine()) != null ){
            
            switch(nLinha) {
                case 1:
                    clTO.setCodigo(linha.replace("<codigo>", "").replace("</codigo>", "").trim());
                    break;
                case 2:
                    clTO.setCnpj(linha.replace("<cnpj>", "").replace("</cnpj>", "").trim());
                    break;
                case 3:
                    clTO.setNome(linha.replace("<nome>", "").replace("</nome>", "").trim());
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
        HArquivoDAO.inserirHistorico(arquivo.getName(), proc);

    }
    
    private static void ProcessaCadastroProduto(BufferedReader inputStream, File arquivo) throws IOException, DaoException {
        String linha, proc = "NAO";
        int nLinha = 1;
        ProdutoTO prTO = new ProdutoTO();
        while ((linha  = inputStream.readLine()) != null ){
            
            switch(nLinha) {
                case 1:
                    prTO.setCnpj(linha.replace("<cnpj>", "").replace("</cnpj>", "").trim());
                    break;                    
                case 2:
                    prTO.setCodigo(linha.replace("<codigo>", "").replace("</codigo>", "").trim());
                    break;
                case 3:
                    prTO.setEan(linha.replace("<ean>", "").replace("</ean>", "").trim());
                    break;
                case 4:
                    prTO.setDescricao(linha.replace("<descricao>", "").replace("</descricao>", "").trim());
                    break; 
                case 5:
                    prTO.setUnidade(linha.replace("<unidade>", "").replace("</unidade>", "").trim());
                    break;   
                case 6:
                    prTO.setQuantidade(Double.valueOf(linha.replace("<quantidade>", "").replace("</quantidade>", "")));
                    break; 
                case 7:
                    prTO.setObservacao(linha.replace("<observacao>", "").replace("</observacao>", "").trim());
                    break;                     
            }
           
            nLinha = nLinha + 1;
        }
        prTO.setArquivo(arquivo.getName());   
        inputStream.close();
        try {
            // Grava registro na tabela loja
            ProdutoDAO.inserirProduto(prTO);
            proc = "Sim";
             // move arquivo processado
            geral.Geral.moveArquivos(arquivo, new File(arquivo.getPath().replace("enviar", "processados").replace(".env", ".ok")));            
        } catch (DaoException ex) {
            // move arquivo processado
            File novoArquivo = new File(arquivo.getPath().replace("enviar", "erros").replace("cad-", "Produto_JA_cadastrada_cad-").replace(".env", ".err"));
            geral.Geral.moveArquivos(arquivo, novoArquivo);
        }
         // Grava arquivo log arquivo processado OK
        HArquivoDAO.inserirHistorico(arquivo.getName(), proc);

    }    
    
    private static void ProcessaAlteracaoStatus(BufferedReader inputStream, File arquivo) throws IOException, DaoException {
        String linha, proc = "NAO";
        int nLinha = 1;
        AlteraStatusTO alTO = new AlteraStatusTO();
        while ((linha  = inputStream.readLine()) != null ){
            
            switch(nLinha) {
                case 1:
                    alTO.setCnpj(linha.replace("<cnpj>", "").replace("</cnpj>", "").trim());
                    break;                    
                case 2:
                    alTO.setCodigo(linha.replace("<codigo>", "").replace("</codigo>", "").trim());
                    break;
                case 3:
                    alTO.setEan(linha.replace("<ean>", "").replace("</ean>", "").trim());
                    break;
                case 4:
                    alTO.setChave(linha.replace("<qual_a_chave>", "").replace("</qual_a_chave>", "").trim());
                    break; 
                case 5:
                    alTO.setNovoStatus(linha.replace("<novostatus>", "").replace("</novostatus>", "").trim());
                    break;                     
                case 6:
                    alTO.setObservacao(linha.replace("<observacao>", "").replace("</observacao>", "").trim());
                    break;                     
            }
           
            nLinha = nLinha + 1;
        }
        inputStream.close();
        try {
            // Grava registro na tabela loja
            AlteraStatusDAO.alterarStatusProduto(alTO, arquivo.getName());
            proc = "Sim";
             // move arquivo processado
            geral.Geral.moveArquivos(arquivo, new File(arquivo.getPath().replace("enviar", "processados").replace(".env", ".ok")));            
        } catch (DaoException ex) {
            // move arquivo processado
            File novoArquivo = new File(arquivo.getPath().replace("enviar", "erros").replace("alt-", "Atualizacao_falhou_alt-").replace(".env", ".err"));
            geral.Geral.moveArquivos(arquivo, novoArquivo);
        }
         // Grava arquivo log arquivo processado OK
        HArquivoDAO.inserirHistorico(arquivo.getName(), proc);

    } 
    
    private static void ProcessaMovimentacao(BufferedReader inputStream, File arquivo) throws IOException, DaoException {
        String linha, proc = "NAO";
        int nLinha = 1;
        MovimentacaoTO mvTO = new MovimentacaoTO();
        while ((linha  = inputStream.readLine()) != null ){
            
            switch(nLinha) {
                case 1:
                    mvTO.setCnpj(linha.replace("<cnpj>", "").replace("</cnpj>", "").trim());
                    break;                    
                case 2:
                    mvTO.setCodigo(linha.replace("<codigo>", "").replace("</codigo>", "").trim());
                    break;
                case 3:
                    mvTO.setEan(linha.replace("<ean>", "").replace("</ean>", "").trim());
                    break;
                case 4:
                    mvTO.setChave(linha.replace("<qual_a_chave>", "").replace("</qual_a_chave>", "").trim().toUpperCase());
                    break; 
                case 5:
                    mvTO.setTipo(linha.replace("<tipo>", "").replace("</tipo>", "").trim().toUpperCase());
                    break;                     
                case 6:
                    mvTO.setQuantidade(Double.valueOf(linha.replace("<quantidade>", "").replace("</quantidade>", "")));
                    break;                     
                case 7:
                    mvTO.setObservacao(linha.replace("<observacao>", "").replace("</observacao>", "").trim());
                    break;                     
            }
           
            nLinha = nLinha + 1;
        }
        inputStream.close();
        try {
            // Grava registro na tabela loja
            MovimentacaoDAO.movimentacaoProduto(mvTO, arquivo.getName());
            proc = "Sim";
             // move arquivo processado
            geral.Geral.moveArquivos(arquivo, new File(arquivo.getPath().replace("enviar", "processados").replace(".env", ".ok")));            
        } catch (DaoException ex) {
            // move arquivo processado
            File novoArquivo = new File(arquivo.getPath().replace("enviar", "erros").replace("alt-", "Movimentacao_falhou_alt-").replace(".env", ".err"));
            geral.Geral.moveArquivos(arquivo, novoArquivo);
        }
         // Grava arquivo log arquivo processado OK
        HArquivoDAO.inserirHistorico(arquivo.getName(), proc);

    }    
}

