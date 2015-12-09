package com.fpmislata.daw2.persistence.dao.impl.jdbc;

import com.fpmislata.daw2.business.domain.BankEntity;
import com.fpmislata.daw2.core.exception.BusinessException;
import com.fpmislata.daw2.core.exception.BusinessMessage;
import com.fpmislata.daw2.persistence.dao.BankEntityDAO;
import com.fpmislata.daw2.persistence.dao.impl.jdbc.connectionfactory.ConnectionFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class BankEntityDAOImplJDBC implements BankEntityDAO {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Override
    public BankEntity get(Integer bankEntityID) throws BusinessException {
        try {
            Connection connection = connectionFactory.getConnection();
            BankEntity bankEntity;

            String sql = "SELECT * FROM bankentity "
                    + "WHERE bankEntityID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, bankEntityID);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                bankEntity = new BankEntity(
                        result.getInt("bankEntityID"),
                        result.getString("name"),
                        result.getInt("bankCode"),
                        result.getDate("creationDate"),
                        result.getString("address"),
                        result.getString("ctc")
                );

                if (result.next()) {
                    throw new RuntimeException("SQL ERROR WHERE ID = " + bankEntityID);
                }
            } else {
                bankEntity = null;
            }

            statement.close();
            connectionFactory.close(connection);

            return bankEntity;
        } catch (SQLException sqlex) {
            throw new RuntimeException(sqlex);
        } catch (RuntimeException rex) {
            throw rex;
        }
    }

    @Override
    public BankEntity insert(BankEntity bankEntity) throws BusinessException {
        Connection connection = connectionFactory.getConnection();
        try {

            String sql = "INSERT INTO bankentity (name, bankCode, creationDate, address, ctc) "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, bankEntity.getName());
            statement.setInt(2, bankEntity.getBankCode());
            statement.setDate(3, new Date(bankEntity.getCreationDate().getTime()));
            statement.setString(4, bankEntity.getAddress());
            statement.setString(5, bankEntity.getCtc());

            int rowsInserted = statement.executeUpdate();
            if(rowsInserted == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    int returnedGeneratedKey = resultSet.getInt(1);
                    bankEntity.setBankEntityID(returnedGeneratedKey);
                } else {
                    throw new RuntimeException("Error: No ID returned.");
                }
            } else {
                throw new RuntimeException("Error: More than 1 row inserted (" + rowsInserted + ").");
            }
            statement.close();
            return bankEntity;
        } catch (SQLException sqlex) {
            if (sqlex.getSQLState().equals("23000") && sqlex.getErrorCode() == 1062) {
                throw new BusinessException(new BusinessMessage(null, sqlex.getMessage()));
            } else {
                throw new RuntimeException(sqlex);
            }
        } catch (RuntimeException rex) {
            throw rex;
        } finally {
            try {
                connectionFactory.close(connection);
            } catch (Exception e) {
            }
        }
    }

    @Override
    public BankEntity update(BankEntity bankEntity) throws BusinessException {
        try {
            Connection connection = connectionFactory.getConnection();

            String sql = "UPDATE bankentity SET name=?, bankCode=?, creationDate=?, address=?, ctc=? "
                    + "WHERE bankEntityID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, bankEntity.getName());
            statement.setInt(2, bankEntity.getBankCode());
            statement.setDate(3, new Date(bankEntity.getCreationDate().getTime()));
            statement.setString(4, bankEntity.getAddress());
            statement.setString(5, bankEntity.getCtc());
            statement.setInt(6, bankEntity.getBankEntityID());

            int rowsUpdated = statement.executeUpdate();

            if(rowsUpdated != 0 && rowsUpdated != 1) {
                throw new RuntimeException("Error: More than 1 row updated (" + rowsUpdated + ").");
            }
            statement.close();
            connectionFactory.close(connection);
            return bankEntity;
        } catch (SQLException sqlex) {
            throw new RuntimeException(sqlex);
        } catch (RuntimeException rex) {
            throw rex;
        }
    }

    @Override
    public boolean delete(Integer bankEntityID) throws BusinessException {
        try {
            Connection connection = connectionFactory.getConnection();

            String sql = "DELETE FROM bankentity "
                    + "WHERE bankEntityID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, bankEntityID);

            int rowsDeleted = statement.executeUpdate();

            statement.close();
            connectionFactory.close(connection);

            if (rowsDeleted == 0) {

                return false;
            } else if (rowsDeleted == 1) {
                return true;
            } else {
                throw new RuntimeException("Error: More than 1 row deleted (" + rowsDeleted + ").");
            }
        } catch (SQLException sqlex) {
            throw new RuntimeException(sqlex);
        } catch (RuntimeException rex) {
            throw rex;
        }
    }

    @Override
    public List<BankEntity> findAll() throws BusinessException {
        try {
            Connection connection = connectionFactory.getConnection();

            String sql = "SELECT * FROM bankentity";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            List<BankEntity> bankEntitys = new ArrayList<>();
            while (result.next()) {
                BankEntity bankEntity = new BankEntity(
                        result.getInt("bankEntityID"),
                        result.getString("name"),
                        result.getInt("bankCode"),
                        result.getDate("creationDate"),
                        result.getString("address"),
                        result.getString("ctc")
                );

                bankEntitys.add(bankEntity);
            }

            statement.close();
            connectionFactory.close(connection);

            return bankEntitys;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<BankEntity> findByNameEquals(String name) throws BusinessException {
        try {
            Connection connection = connectionFactory.getConnection();

            String sql = "SELECT * FROM bankentity "
                    + "WHERE name=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);

            ResultSet result = statement.executeQuery();

            List<BankEntity> bankEntitys = new ArrayList<>();
            while (result.next()) {
                BankEntity bankEntity = new BankEntity(
                        result.getInt("bankEntityID"),
                        result.getString("name"),
                        result.getInt("bankCode"),
                        result.getDate("creationDate"),
                        result.getString("address"),
                        result.getString("ctc")
                );

                bankEntitys.add(bankEntity);

            }

            statement.close();
            connectionFactory.close(connection);

            return bankEntitys;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<BankEntity> findByNameLike(String name) throws BusinessException {
        try {
            Connection connection = connectionFactory.getConnection();

            String sql = "SELECT * FROM bankentity "
                    + "WHERE name LIKE ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");

            ResultSet result = statement.executeQuery();

            List<BankEntity> bankEntitys = new ArrayList<>();
            while (result.next()) {
                BankEntity bankEntity = new BankEntity(
                        result.getInt("bankEntityID"),
                        result.getString("name"),
                        result.getInt("bankCode"),
                        result.getDate("creationDate"),
                        result.getString("address"),
                        result.getString("ctc")
                );

                bankEntitys.add(bankEntity);

            }

            statement.close();
            connectionFactory.close(connection);

            return bankEntitys;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
