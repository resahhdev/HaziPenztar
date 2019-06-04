/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author resahh
 */
public class SqlQueries {
    
    public String getCreateWalletSql() {
        String sql = "create table wallet( id INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY ( START WITH 1, INCREMENT BY 1 ), " +
                                          "date VARCHAR(10), " +
                                          "price VARCHAR(10), " +
                                          "comment VARCHAR(50), "+
                                          "categoryId INTEGER, " +
                                          "directionId INTEGER )";
        return sql;
    }
    
    public String getCreateUsersSql() {
        String sql = "create table users( id INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                                         "username VARCHAR(50), " +
                                         "password VARCHAR(250))";
        return sql;
    }
    
    public String getCreateCategoriesSql() {
        String sql = "create table categories( id INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                                              "category VARCHAR(20))";
        return sql;
    }
    
    public String getCreateDirectionSql() {
        String sql = "create table directions( id INTEGER PRIMARY KEY NOT NULL, direction char(2) )";
        return sql;
    }
    
    public String getInsertDirectionSql() {
        String sql = "insert into directions( id, direction ) values ( 1, 'Be'), ( 2, 'Ki' )";
        return sql;
    }
    
    public String getInsertStartUserSql() {
        String sql = "insert into users( username, password ) values ( 'admin', 'admin' )";
        return sql;
    }
    
    public String getInsertUserSql( String AUser, String APass ) {
        String sql = "INSERT INTO users ( username, password ) VALUES ( '" + AUser + "', '" + APass + "' )";
        return sql;
    }
    
    public String getDeleteUserSql( String AUser ) {
        String sql = "DELETE FROM users WHERE username = '" + AUser + "'";
        return sql;
    }
    
    public String getInsertCategoriesSql() {
        String sql = "insert into categories( category ) values " +
                     "( 'Fizetés' ),( 'Háztartás' ),( 'Rezsi' )," +
                     "( 'Ajándék' ),( 'Állat' ),( 'Magán' )," +
                     "( 'Élelmiszer' ),( 'Auto' ),( 'Ruha' )";
        return sql;
    }
    
    public String getLoginUserSql( String AText ) {
        String sql = "select username, password from USERS where username = '" + AText + "'";
        return sql;
    }
    
    public String getComboItemsSql() {
        String sql = "select category from categories";
        return sql;
    }
    
    public String getWalletDataSql() {
        String walletSql = "SELECT date, category, price, comment, direction FROM wallet " +
                           "INNER JOIN categories ON categoryId = categories.id " +
                           "INNER JOIN directions ON directionId = directions.id";
        return walletSql;
    }
    
    public String getSelectDataSql( String ACategory ) {
        String selectDataSql = "SELECT date, category, price, comment, direction FROM wallet " +
                               "INNER JOIN categories ON categoryId = categories.id " +
                               "INNER JOIN directions ON directionId = directions.id " +
                               "WHERE categoryId = ( SELECT id FROM categories WHERE category = '" + ACategory + "' )";
        return selectDataSql;
    }
    
    public String getInsertDataSql( String ADate, String ACat, String APrice, String AComm, String ADir ) {
        String insertDataSql = "INSERT INTO wallet (date, categoryId, price, comment, directionId) VALUES " +
                     "( '" + ADate + "', (SELECT id FROM categories WHERE category = '" + ACat + "'), '" + APrice +  "', " +
                     "'" + AComm + "', (SELECT id FROM directions WHERE direction = '" + ADir + "'))";
        return insertDataSql;
    }
    
    public String getIncomeSql() {
        String selectPriceSql = "SELECT price FROM wallet " +
                                "INNER JOIN directions ON directionId = directions.id " +
                                "WHERE direction = 'Be'";
        return selectPriceSql;
    }
    
    public String getOutGoingSql() {
        String selectPriceSql = "SELECT price FROM wallet " +
                                "INNER JOIN directions ON directionId = directions.id " +
                                "WHERE direction = 'Ki'";
        return selectPriceSql;
    }
}
