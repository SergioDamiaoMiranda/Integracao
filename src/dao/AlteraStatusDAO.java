package dao;

import db.DaoException;
import db.DataBaseDaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import to.AlterarStatusTO;
import to.HistoricoTO;

public class AlteraStatusDAO {

    java.util.Date hoje = new java.util.Date();
    private static final String UPDATE_SQL_CODIGO
            = "UPDATE estoque  SET ativo = ? WHERE cnpj_loja = ? and codigo = ? ";
    private static final String UPDATE_SQL_EAN
            = "UPDATE estoque  SET ativo = ? WHERE cnpj_loja = ? and ean = ?";
    
    public static Connection getConnection() throws SQLException {
        return DataBaseDaoFactory.getConnection();
    }

    public static void alterarStatusProduto(AlterarStatusTO alTO, String arquivo) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;        // Data Hora Systema
        String data = "yyyy/MM/dd";
        String hora = "HH:mm";
        String dataS, horaS, aux;
        java.util.Date agora = new java.util.Date();
        SimpleDateFormat formata = new SimpleDateFormat(data);
        dataS = formata.format(agora);
        formata = new SimpleDateFormat(hora);
        horaS = formata.format(agora);

        try {
            int i = 1;
            con = getConnection();
            if (alTO.getChave().equals("E")) ps = con.prepareStatement(UPDATE_SQL_EAN);
            else ps = con.prepareStatement(UPDATE_SQL_CODIGO);
            ps.setString(i++, alTO.getNovoStatus());
          
            //Condicao WHERE
            ps.setString(i++, alTO.getCnpj());

            if (alTO.getChave().equals("E")) aux = alTO.getEan();
            else aux = alTO.getCodigo();
            ps.setString(i++, aux);
            ps.executeUpdate();            
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException ex) {
                throw new DaoException(ex);
            }
        }
        // Inserindo historico movimentacao
        HistoricoTO hTO = new HistoricoTO();
        hTO.setCnpj(alTO.getCnpj());
        hTO.setTipo("A");
        hTO.setCodigo(alTO.getCodigo());
        hTO.setEan(alTO.getEan());
        hTO.setQuantidade(0.0);
        hTO.setQual_foi_chave(alTO.getChave());
        hTO.setObservacao(alTO.getObservacao());
        hTO.setArquivo(arquivo);
        hTO.setDataMovimentacao(dataS);
        hTO.setHoraMovimentacao(horaS);
        HistoricoDAO.inserirHistorico(hTO);

    }
    

}
