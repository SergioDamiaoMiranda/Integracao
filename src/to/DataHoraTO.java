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
public class DataHoraTO implements Serializable {
    private static final long serialVersionUID = -7260598440016241916L;
  
    private String dataOperacao; 
    private String horaOperacao; 

    public String getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(String dataOperacao) {
        this.dataOperacao = dataOperacao;
    }

    public String getHoraOperacao() {
        return horaOperacao;
    }

    public void setHoraOperacao(String horaOperacao) {
        this.horaOperacao = horaOperacao;
    }
  
    
    
}
