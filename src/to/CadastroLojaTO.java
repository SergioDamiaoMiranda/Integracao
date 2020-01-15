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
public class CadastroLojaTO implements Serializable {
    private static final long serialVersionUID = -7200598440016241916L;

    private String codigo = "";
    private String cnpj = "";
    private String nome = "";
    private String arquivo = "";    

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

    @Override
    public String toString() {
        return "CadastroLojaTO{" + "codigo=" + codigo + ", cnpj=" + cnpj + ", nome=" + nome + ", arquivo=" + arquivo + '}';
    }

    
    
}
