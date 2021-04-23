package kr.ac.jejunu.userdao;

import java.sql.*;

public class UserDao {
    public User get(Integer id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost/kakao?characterEncoding=utf-8&serverTimezone=UTC", "root", "root");
        PreparedStatement preparedStatement =
                connection.prepareStatement("select * from userinfo where id = ?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        if (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
        return user;
    }
}