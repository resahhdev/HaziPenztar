/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author resahh
 */
public class DbConnect {

    //private final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private final String URL = "jdbc:derby:homecassaDb;create=true";
    private Connection conn;
    private SqlQueries sqlQeries;
    
    public DbConnect() {
        
        sqlQeries = new SqlQueries();
    }
    
    public void startConnect() {
        
        conn = null;
        Statement creatStatement = null;
        try {
            
            conn = DriverManager.getConnection( URL );
            if( conn != null ) {
                
                creatStatement = conn.createStatement();
                ResultSet resultset = null;
                DatabaseMetaData dbMeta = null;
                
                dbMeta = conn.getMetaData();
                resultset = dbMeta.getTables( null, "APP", "WALLET", null );
                if( !resultset.next() ) {
                    
                    String walletSql = sqlQeries.getCreateWalletSql();
                    creatStatement.execute( walletSql );
                    String categorySql = sqlQeries.getCreateCategoriesSql();
                    creatStatement.execute( categorySql );
                    String directionSql = sqlQeries.getCreateDirectionSql();
                    creatStatement.execute( directionSql );
                    String userSql = sqlQeries.getCreateUsersSql();
                    creatStatement.execute( userSql );
                    String insertDirectionSql = sqlQeries.getInsertDirectionSql();
                    creatStatement.execute( insertDirectionSql );
                    String insertUserSql = sqlQeries.getInsertStartUserSql();
                    creatStatement.execute( insertUserSql );
                    String insertCategoriesSql = sqlQeries.getInsertCategoriesSql();
                    creatStatement.execute( insertCategoriesSql );
                    creatStatement.close();
                }
                
            }
        }catch( SQLException AEx ) {
            
            Logger.getLogger( DbConnect.class.getName()).log(Level.SEVERE, null, AEx );
        }
        
    }
    
    public Connection dBClose() {
        
        try {
           
            if( conn != null ) {
            
            conn.close();
        
            }
        }catch( SQLException AEx ) {
            
            Logger.getLogger( DbConnect.class.getName()).log(Level.SEVERE, null, AEx );
        }
        
        return conn;
    }
    
    public Connection connect() {
        
        try {
            
            conn = conn = DriverManager.getConnection( URL );
            
        }catch( SQLException ex ) {
            
            ex.printStackTrace();
        }
        return conn;
    }
    
    public Connection getConnection() {
        return conn;
    }
}
