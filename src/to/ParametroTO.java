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
public class ParametroTO implements Serializable {
    private static final long serialVersionUID = -7200598440016241913L;

    private int id;
    private String nomeMatriz = "";
    private String diretorio = "";
    private int tempoPesquisa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeMatriz() {
        return nomeMatriz;
    }

    public void setNomeMatriz(String nomeMatriz) {
        this.nomeMatriz = nomeMatriz;
    }

    public String getDiretorio() {
        return diretorio;
    }

    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }

    public int getTempoPesquisa() {
        return tempoPesquisa;
    }

    public void setTempoPesquisa(int tempoPesquisa) {
        this.tempoPesquisa = tempoPesquisa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
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
        final ParametroTO other = (ParametroTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
