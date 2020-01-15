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
public class HProcessamentoTO implements Serializable {
    private static final long serialVersionUID = -7200598440016241963L;

    private int id;
    private String arquivo = "";
    private String processado = "";

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
        final HProcessamentoTO other = (HProcessamentoTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HProcessamentoTO{" + "id=" + id + ", arquivo=" + arquivo + ", processado=" + processado + '}';
    }

    
}
