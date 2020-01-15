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
public class LojaTO implements Serializable {
    private static final long serialVersionUID = -7200598440016241916L;

    private String codigo = "";
    private String cnpj = "";
    private String nome = "";
    private String arquivo = "";    
    private String data_cadastro = ""; 
    private String hora_cadastro = ""; 
        
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    @Override
    public String toString() {
        return "LojaTO{" + "codigo=" + codigo + ", cnpj=" + cnpj + ", nome=" + nome + ", arquivo=" + arquivo + ", data_cadastro=" + data_cadastro + ", hora_cadastro=" + hora_cadastro + '}';
    }
    
}
