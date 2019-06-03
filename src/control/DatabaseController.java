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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Wallet;

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
    private ObservableList<String> categoryList;
    private ObservableList<String> directionList;
    
    public DatabaseController( GuiController AGuiCtr ) {
        
        categoryList = FXCollections.observableArrayList();
        directionList = FXCollections.observableArrayList();
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
    
    public void setCategoryList() {
        
        String sql = sqlQueries.getComboItemsSql();
        conn = dbConn.connect();
        createStatement = null;
        resultset = null;
        
        try {
            
            createStatement = conn.createStatement();
            resultset = createStatement.executeQuery( sql );
            while( resultset.next() ) {
                
                categoryList.add( resultset.getString( "category" ));
            }
            
        }catch( SQLException AEx ) {
            Logger.getLogger( DatabaseController.class.getName()).log(Level.SEVERE, null, AEx );
        }
    }
    
    public void setDirectionList() {
        
    }
    
    public ArrayList<Wallet> setWalletData() {
        
        //Image green = new Image( getClass().getResourceAsStream( "/images/greenlamp.png" ));
        //Image red = new Image( getClass().getResourceAsStream( "/images/redlamp.png" ));
        Image image = null;
        String sql = sqlQueries.getWalletDataSql();
        conn = null;
        createStatement = null;
        resultset = null;
        ArrayList<Wallet> walletData = new ArrayList<>();
        
        try {
            
            conn = dbConn.connect();
            createStatement = conn.createStatement();
            resultset = createStatement.executeQuery( sql );
            
            while( resultset.next() ) {
            String direction = resultset.getString( "direction" );
                if( direction.equals( "Ki" )) {
                
                    image = new Image( getClass().getResourceAsStream( "/images/redlamp.png" ));
                    
                }else {
                
                    image = new Image( getClass().getResourceAsStream( "/images/greenlamp.png" ));
                }
            
                Wallet actualWallet = new Wallet(
                                      resultset.getString( "date" ),
                                      resultset.getString( "category" ),
                                      resultset.getString( "price" ),
                                      resultset.getString( "comment" ),
                                      new ImageView( image )
                                      );
                walletData.add( actualWallet );
            }
            
        }catch( SQLException ex ) {
            ex.printStackTrace();
        }
        return walletData;
    }
    
    public void insertData( String ADate, String ACategory, String APrice, String AComment, String ADirection ) {
/*
"INSERT INTO wallet (date, categoryId, price, comment, directionId) VALUES " +
                   "( ?, (SELECT id FROM categories WHERE category = ?), ?, " +
                   "?, (SELECT id FROM directions WHERE direction = ?))";
*/      
        //String sql = sqlqueries.getInsertDataSql();
        String sql = sqlQueries.getInsertDataSql( ADate, ACategory, APrice, AComment, ADirection );
        try {
            
            conn = dbConn.connect();
            createStatement = conn.createStatement();
            createStatement.execute( sql );
        
        } catch ( SQLException AEx ) {
            Logger.getLogger( DatabaseController.class.getName()).log(Level.SEVERE, null, AEx );
        }
        
    }
    
    public ObservableList getCategoryList() { return categoryList; }
    public ObservableList getDirectionList() { return directionList; }
    //public ObservableList getWalletData() { return walletData; }
}
