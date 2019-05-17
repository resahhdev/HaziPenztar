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
                                                      "category VARCHAR(20), " +
                                                      "price VARCHAR(10), " +
                                                      "comment VARCHAR(50), "+
                                                      "direction VARCHAR(5))";
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
    
    public String getIsertUserSql() {
        String sql = "insert into users( username, password ) values ( 'admin', 'admin' )";
        return sql;
    }
    
    public String getInsertCategoriesSql() {
        String sql = "insert into categories( category )" +
                     "values ( 'Fizetés' ),( 'Háztartás' ),( 'Rezsi' )," +
                            "( 'Ajándék' ),( 'Állat' ),( 'Magán' )," +
                            "( 'Élelmiszer' ),( 'Auto' ),( 'Ruha' )";
        return sql;
    }
    
    public String getLoginUserSql( String AText ) {
        String sql = "select username, password from USERS where username = '" + AText + "'";
        return sql;
    }
}
