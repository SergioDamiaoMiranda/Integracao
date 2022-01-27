package to;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;

/**
 *
 * @author Sérgio Damião
 */
public class AlterarProdutoTO implements Serializable {
    private static final long serialVersionUID = -7200598440016241666L;

    private String cnpj = "";
    private String codigo = "";    
    private String novoEan = "";
    private String chave = "";
    private String novaDescricao = "";
    private String novaUnidade = "";    
    private String novaObservacao = ""; 

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getNovaDescricao() {
        return novaDescricao;
    }

    public void setNovaDescricao(String novaDescricao) {
        this.novaDescricao = novaDescricao;
    }

    public String getNovaUnidade() {
        return novaUnidade;
    }

    public void setNovaUnidade(String novaUnidade) {
        this.novaUnidade = novaUnidade;
    }

    public String getNovaObservacao() {
        return novaObservacao;
    }

    public void setNovaObservacao(String novaObservacao) {
        this.novaObservacao = novaObservacao;
    }

    public String getNovoEan() {
        return novoEan;
    }

    public void setNovoEan(String novoEan) {
        this.novoEan = novoEan;
    }

    @Override
    public String toString() {
        return "AlterarProdutoTO{" + "cnpj=" + cnpj + ", codigo=" + codigo + ", novoEan=" + novoEan + ", chave=" + chave + ", novaDescricao=" + novaDescricao + ", novaUnidade=" + novaUnidade + ", novaObservacao=" + novaObservacao + '}';
    }


    
}
