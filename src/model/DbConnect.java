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
                
                
                DbTeszData dbTest = new DbTeszData();
                
                dbMeta = conn.getMetaData();
                resultset = dbMeta.getTables( null, "APP", "WALLET", null );
                if( !resultset.next() ) {
                    
                    creatStatement.execute( sqlQeries.getCreateWalletSql() );
                    creatStatement.execute( sqlQeries.getCreateCategoriesSql() );
                    creatStatement.execute( sqlQeries.getCreateDirectionSql() );
                    creatStatement.execute( sqlQeries.getCreateUsersSql() );
                    creatStatement.execute( sqlQeries.getInsertDirectionSql() );
                    creatStatement.execute( sqlQeries.getInsertStartUserSql() );
                    creatStatement.execute( sqlQeries.getInsertCategoriesSql() );
                    creatStatement.execute( sqlQeries.getCreateMonthSql() );
                    creatStatement.execute( sqlQeries.getInsertMonthSql() );
                    creatStatement.execute( dbTest.fillTestData() );
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
