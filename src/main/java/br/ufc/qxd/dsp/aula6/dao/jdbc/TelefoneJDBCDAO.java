package br.ufc.qxd.dsp.aula6.dao.jdbc;

import br.ufc.qxd.dsp.aula6.dao.TelefoneDAO;
import br.ufc.qxd.dsp.aula6.model.Telefone;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TelefoneJDBCDAO extends GenericJDBCDAO<Telefone> implements TelefoneDAO {

    public TelefoneJDBCDAO() {
        super("telefone");
    }

    @Override
    protected Telefone fromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int ddd = rs.getInt("ddd");
        int numero = rs.getInt("numero");
        Telefone telefone = new Telefone(id, ddd, numero, null);
        return telefone;
    }

    @Override
    protected PreparedStatement prepareToInsert(Telefone t) throws SQLException {
        String sql = "INSERT INTO telefone VALUES (default, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, t.getDdd());
        stmt.setInt(2, t.getNumero());
        stmt.setInt(2, t.getCliente().getId());
        return stmt;
    }

    @Override
    protected PreparedStatement prepareToUpdate(Telefone t) throws SQLException {
        String sql = "UPDATE telefone SET ddd=?, numero=?, cliente_id=? WHERE id=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, t.getDdd());
        stmt.setInt(2, t.getNumero());
        stmt.setInt(3, t.getCliente().getId());
        return stmt;
    }

    @Override
    public List<Telefone> findByClientId(int clienteId) {
        try {
            List<Telefone> l = new ArrayList<Telefone>();

            String sql = "select * from telefone where cliente_id=?;";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                l.add(this.fromResultSet(rs));
            }
            return l;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
