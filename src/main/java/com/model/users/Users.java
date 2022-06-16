package com.model.users;

import com.MVC.Model;

import java.sql.*;
import java.util.ArrayList;

public class Users extends Model {
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String noHp;
    public Users(int id, String name, String email, String password, String role, String noHp
    ) throws SQLException, ClassNotFoundException {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.noHp = noHp;
        this.role = role;
    }

    public int login(String email, String password) throws SQLException, ClassNotFoundException {
        try {
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement statement = this.conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                if(result.getString("role").equals("1")){
                    return 1;
                }else if(result.getString("role").equals("0")){
                    return 2;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<Users> all() throws SQLException, ClassNotFoundException {
        Statement stmt = this.conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM users");
        ArrayList<Users> data = new ArrayList<>();
        while(result.next()){
            Users temp = new Users(
                    result.getInt("id"),
                    result.getString("username"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getString("noHp"),
                    result.getString("role")
            );
            data.add(temp);
        }
        return data;
    }

    public Users find(int id) throws SQLException, ClassNotFoundException {
        Statement stmt = this.conn.createStatement();
        ResultSet result = stmt.executeQuery(String.format("SELECT * FROM users WHERE id = %d", id));
        return new Users(
                result.getInt("id"),
                result.getString("username"),
                result.getString("email"),
                result.getString("password"),
                result.getString("role"),
                result.getString("noHp")
        );
    }

    public ArrayList<Users> filter(String cari) throws SQLException, ClassNotFoundException {
        Statement stmt = this.conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM users WHERE username LIKE '%" + cari + "%'");
        ArrayList<Users> data = new ArrayList<>();
        while(result.next()){
            Users temp = new Users(
                    result.getInt("id"),
                    result.getString("username"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getString("role"),
                    result.getString("noHp")
            );
            data.add(temp);
        }
        return data;
    }

    public void create(Users data) throws SQLException, ClassNotFoundException {
        Statement stmt = this.conn.createStatement();
        stmt.executeUpdate(
                String.format(
                        "INSERT INTO users (username, email, password, role, noHp, createdAt, updatedAt) " +
                                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                        data.name, data.email, data.password, data.role, data.noHp, this.getTimeNow(), this.getTimeNow()
                )
        );
    }

    public void update(Users data, int id) throws SQLException, ClassNotFoundException {
        Statement stmt = this.conn.createStatement();
        stmt.executeUpdate(
                String.format(
                        "UPDATE `users` SET " +
                        "username = '%s', " +
                        "email = '%s', " +
                        "password = '%s', " +
                        "role = '%s', " +
                        "noHp = '%s', " +
                        "updatedAt = '%s' " +
                        "WHERE id=%d",
                        data.name, data.email, data.password, data.role, data.noHp, this.getTimeNow() ,id
                )
        );
    }

    public void delete(int id) throws SQLException, ClassNotFoundException {
        Statement stmt = this.conn.createStatement();
        stmt.executeUpdate(String.format("DELETE FROM users WHERE id = %d", id));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }
}
