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
public class HistoricoTO implements Serializable {
    private static final long serialVersionUID = -7206598440016241966L;


    private String cnpj = "";
    private String tipo = "";    
    private String codigo = "";    
    private String ean = "";
    private Double quantidade = 0.0; 
    private String observacao = "";      
    private String arquivo = "";
    private String qual_foi_chave = "";
    private String dataMovimentacao = ""; 
    private String horaMovimentacao = "";   

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getQual_foi_chave() {
        return qual_foi_chave;
    }

    public void setQual_foi_chave(String qual_foi_chave) {
        this.qual_foi_chave = qual_foi_chave;
    }

    public String getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(String dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public String getHoraMovimentacao() {
        return horaMovimentacao;
    }

    public void setHoraMovimentacao(String horaMovimentacao) {
        this.horaMovimentacao = horaMovimentacao;
    }

    @Override
    public String toString() {
        return "HistoricoTO{" + "cnpj=" + cnpj + ", tipo=" + tipo + ", codigo=" + codigo + ", ean=" + ean + ", quantidade=" + quantidade + ", observacao=" + observacao + ", arquivo=" + arquivo + ", qual_foi_chave=" + qual_foi_chave + ", dataMovimentacao=" + dataMovimentacao + ", horaMovimentacao=" + horaMovimentacao + '}';
    }

    
}
