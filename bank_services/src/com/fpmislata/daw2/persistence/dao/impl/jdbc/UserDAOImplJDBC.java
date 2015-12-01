
package com.fpmislata.daw2.persistence.dao.impl.jdbc;

import com.fpmislata.daw2.business.domain.Role;
import com.fpmislata.daw2.business.domain.User;
import com.fpmislata.daw2.core.exception.BusinessException;
import com.fpmislata.daw2.persistence.dao.UserDAO;
import com.fpmislata.daw2.persistence.dao.impl.jdbc.connectionfactory.ConnectionFactory;
import com.fpmislata.daw2.security.PasswordManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class UserDAOImplJDBC implements UserDAO {
    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    private PasswordManager passwordManager;

    @Override
    public User get(Integer userID) throws BusinessException {
        try {
            Connection connection = connectionFactory.getConnection();
            User user;

            String sql = "SELECT * FROM user " +
                    "WHERE userID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userID);

            ResultSet result = statement.executeQuery();
            if(result.next()) {
                user = new User(
                        result.getInt("userID"),
                        result.getString("name"),
                        result.getString("password"),
                        result.getString("email"),
                        Role.valueOf(result.getString("role"))
                );
                
                if(result.next()) {
                    throw new RuntimeException("SQL ERROR WHERE ID = " + userID);
                }
            } else {
                user = null;
            }

            statement.close();
            connectionFactory.close(connection);

            return user;
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User insert(User user) throws BusinessException {
        try {
            Connection connection = connectionFactory.getConnection();
            
            user.setPassword(passwordManager.encrypt(user.getPassword()));

            String sql = "INSERT INTO user (name, password, email, role) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getRole().name());

            int rowsInserted = statement.executeUpdate();

            statement.close();
            connectionFactory.close(connection);
            
            if(rowsInserted == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    int returnedGeneratedKey = resultSet.getInt(1);
                    user.setUserID(returnedGeneratedKey);
                } else {
                    throw new RuntimeException("Error: No ID returned.");
                }
            } else {
                throw new RuntimeException("Error: More than 1 row inserted (" + rowsInserted + ").");
            }
            
            return user;
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public User update(User user) throws BusinessException {
        try {
            Connection connection = connectionFactory.getConnection();

            user.setPassword(passwordManager.encrypt(user.getPassword()));
            
            String sql = "UPDATE user SET name=?, password=?, email=?, role=? " +
                    "WHERE userID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getRole().name());
            statement.setInt(5, user.getUserID());

            int rowsUpdated = statement.executeUpdate();

            statement.close();
            connectionFactory.close(connection);
            
            if(rowsUpdated != 0 && rowsUpdated != 1) {
                throw new RuntimeException("Error: More than 1 row updated (" + rowsUpdated + ").");
            }
            
            return user;
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean delete(Integer userID) throws BusinessException {
        try {
            Connection connection = connectionFactory.getConnection();

            String sql = "DELETE FROM user " +
                    "WHERE userID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userID);

            int rowsDeleted = statement.executeUpdate();

            statement.close();
            connectionFactory.close(connection);

            if(rowsDeleted == 0) {
                return false;
            } else if(rowsDeleted == 1) {
                return true;
            } else {
                throw new RuntimeException("Error: More than 1 row deleted (" + rowsDeleted + ").");
            }
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<User> findAll() throws BusinessException {
        try {
            Connection connection = connectionFactory.getConnection();

            String sql = "SELECT * FROM user";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            List<User> users = new ArrayList<>();
            while(result.next()) {
                User user = new User(
                        result.getInt("userID"),
                        result.getString("name"),
                        result.getString("password"),
                        result.getString("email"),
                        Role.valueOf(result.getString("role"))
                );

                users.add(user);
            }
            
            statement.close();
            connectionFactory.close(connection);

            return users;
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<User> findByNameEquals(String name) throws BusinessException {
        try {
            Connection connection = connectionFactory.getConnection();

            String sql = "SELECT * FROM user " +
                    "WHERE name=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);

            ResultSet result = statement.executeQuery();

            List<User> users = new ArrayList<>();
            while(result.next()) {
                User user = new User(
                        result.getInt("userID"),
                        result.getString("name"),
                        result.getString("password"),
                        result.getString("email"),
                        Role.valueOf(result.getString("role"))
                );

                users.add(user);

            }
 
            statement.close();
            connectionFactory.close(connection);

            return users;
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<User> findByNameLike(String name) throws BusinessException {
        try {
            Connection connection = connectionFactory.getConnection();

            String sql = "SELECT * FROM user " +
                    "WHERE name LIKE '%?%'";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);

            ResultSet result = statement.executeQuery();

            List<User> users = new ArrayList<>();
            while(result.next()) {
                User user = new User(
                        result.getInt("userID"),
                        result.getString("name"),
                        result.getString("password"),
                        result.getString("email"),
                        Role.valueOf(result.getString("role"))
                );

                users.add(user);

            }
 
            statement.close();
            connectionFactory.close(connection);

            return users;
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
