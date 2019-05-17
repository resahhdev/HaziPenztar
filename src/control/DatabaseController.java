/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.SqlQueries;
import model.DbConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author resahh
 */
public class DatabaseController {

    private GuiController guiCtr;
    private DbConnect  dbConn;
    private SqlQueries sqlQueries;
    private Connection conn;
    private SqlQueries sqlqueries;
    private ResultSet resultset;
    private PreparedStatement prepStm;
    private Statement createStatement;
    
    public DatabaseController( GuiController AGuiCtr ) {
        
        guiCtr = AGuiCtr;
        conn = null;
        sqlQueries = new SqlQueries();
        dbConn = new DbConnect();
    }
    
    public boolean connect() {
        
        boolean connection = false;
        dbConn.startConnect();
        conn = dbConn.getConnection();
        
        if( conn != null ) {
            
            connection = true;
            return connection;
            
        }else {
            return connection;
        }
    }
    
    public String getLoginUser( String AText ) {
        
        String sql = sqlQueries.getLoginUserSql( AText );
        String authentication = "";
        
        if( guiCtr.getMode() == 1 ) {
            
            try {
                conn = dbConn.connect();
                createStatement = conn.createStatement();
                resultset = createStatement.executeQuery( sql );
                    while( resultset.next() ) {

                        String username = resultset.getString( "username" );
                        String password = resultset.getString( "password" );
                        authentication = username + ":" + password;
                    }
            
            } catch ( SQLException AEx ) {
            
                Logger.getLogger( DatabaseController.class.getName()).log(Level.SEVERE, null, AEx );
            }
            
        }
        return authentication;
    }
}
