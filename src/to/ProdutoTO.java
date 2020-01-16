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
public class ProdutoTO implements Serializable {
    private static final long serialVersionUID = -7200598440016241916L;

    private String cnpj = "";
    private String codigo = "";
    private String ean = "";
    private String descricao = "";
    private String unidade = "";     
    private Double quantidade = 0.0;
    private String observacao = "";    
    private String arquivo = "";    
    private String data_cadastro = ""; 
    private String hora_cadastro = ""; 

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

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public String getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(String data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public String getHora_cadastro() {
        return hora_cadastro;
    }

    public void setHora_cadastro(String hora_cadastro) {
        this.hora_cadastro = hora_cadastro;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    @Override
    public String toString() {
        return "ProdutoTO{" + "cnpj=" + cnpj + ", codigo=" + codigo + ", ean=" + ean + ", descricao=" + descricao + ", quantidade=" + quantidade + ", observacao=" + observacao + ", arquivo=" + arquivo + ", data_cadastro=" + data_cadastro + ", hora_cadastro=" + hora_cadastro + '}';
    }
 
    
}
