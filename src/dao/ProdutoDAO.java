package dao;

import db.DaoException;
import db.DataBaseDaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import to.ProdutoTO;

public class ProdutoDAO {

    java.util.Date hoje = new java.util.Date();
    private static final String INSERT_SQL
            = "INSERT INTO estoque "
            + "(cnpj_loja, codigo, ean, descricao, unidade, "
            + " saldo, observacao, nome_arquivo, data_cadastro, hora_cadastro, "
            + " ativo) "
            + " VALUES(?,?,?,?,?, "
            + "       ?,?,?,?,?, "
            + "       ?)";

    public static Connection getConnection() throws SQLException {
        return DataBaseDaoFactory.getConnection();
    }

    public static void inserirProduto(ProdutoTO prTO) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;        // Data Hora Systema
        String data = "yyyy/MM/dd";
        String hora = "HH:mm";
        String dataS;
        String horaS;
        java.util.Date agora = new java.util.Date();
        SimpleDateFormat formata = new SimpleDateFormat(data);
        dataS = formata.format(agora);
        formata = new SimpleDateFormat(hora);
        horaS = formata.format(agora);

        try {
            int i = 1;
            con = getConnection();
            ps = con.prepareStatement(INSERT_SQL);
            ps.setString(i++, prTO.getCnpj());
            ps.setString(i++, prTO.getCodigo());
            ps.setString(i++, prTO.getEan());
            ps.setString(i++, prTO.getDescricao());
            ps.setString(i++, prTO.getUnidade());
            
            ps.setDouble(i++, prTO.getQuantidade());
            ps.setString(i++, prTO.getObservacao());
            ps.setString(i++, prTO.getArquivo());
            ps.setString(i++, dataS);
            ps.setString(i++, horaS);
            
            ps.setString(i++, "Sim");
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
    }

}
