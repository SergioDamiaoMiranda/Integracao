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
public class MovimentacaoTO implements Serializable {
    private static final long serialVersionUID = -7200598440016241966L;


    private String cnpj = "";
    private String codigo = "";    
    private String ean = "";
    private String chave = "";
    private String tipo = "";
    private Double quantidade = 0.0;
    private String observacao = "";    

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

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "MovimentacaoTO{" + "cnpj=" + cnpj + ", codigo=" + codigo + ", ean=" + ean + ", chave=" + chave + ", tipo=" + tipo + ", quantidade=" + quantidade + ", observacao=" + observacao + '}';
    }



    
}
