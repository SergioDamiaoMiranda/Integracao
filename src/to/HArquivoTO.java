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
public class HArquivoTO implements Serializable {
    private static final long serialVersionUID = -7200598440016241963L;

    private int id;
    private String arquivo = "";
    private String processado = "";
    private String data_cadastro = ""; 
    private String hora_cadastro = "";     

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public String getProcessado() {
        return processado;
    }

    public void setProcessado(String processado) {
        this.processado = processado;
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
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HArquivoTO other = (HArquivoTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HProcessamentoTO{" + "id=" + id + ", arquivo=" + arquivo + ", processado=" + processado + ", data_cadastro=" + data_cadastro + ", hora_cadastro=" + hora_cadastro + '}';
    }
    
}
