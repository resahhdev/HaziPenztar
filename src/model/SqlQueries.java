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
        String sql =
        "CREATE TABLE wallet(id INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
        "date DATE, " +
        "price VARCHAR(10), " +
        "comment VARCHAR(50), "+
        "categoryId INTEGER, " +
        "directionId INTEGER," +
        "userId INTEGER)";
        return sql;
    }
    
    public String getCreateUsersSql() {
        String sql =
        "CREATE TABLE users(id INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
        "username VARCHAR(50), " +
        "password VARCHAR(250))";
        return sql;
    }
    
    public String getCreateCategoriesSql() {
        String sql =
        "CREATE TABLE categories(id INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1), " +
        "category VARCHAR(20))";
        return sql;
    }
    
    public String getCreateDirectionSql() {
        String sql =
        "CREATE TABLE directions(id INTEGER PRIMARY KEY NOT NULL,direction char(2))";
        return sql;
    }
    
    public String getCreateMonthSql() {
        String sql =
        "CREATE TABLE months(id INTEGER,month varchar(10))";
        return sql;
    }
    
    public String getInsertMonthSql() {
        String sql =
        "INSERT INTO months(id,month) VALUES " +
        "(1,'Január'),(2,'Február'),(3,'Március'), " +
        "(4,'Április'),(5,'Május'),(6,'Június')," +
        "(7,'Július'),(8,'Augusztus'),(9,'Szeptember')," +
        "(10,'Október'),(11,'November'),(12,'December')";
        return sql;
    }
    
    public String getInsertDirectionSql() {
        String sql =
        "INSERT INTO directions(id, direction) VALUES (1, 'Be'), (2, 'Ki')";
        return sql;
    }
    
    public String getInsertStartUserSql() {
        String sql =
        "INSERT INTO users(username, password)values('admin','admin')";
        return sql;
    }
    
    public String getInsertUserSql( String AUser, String APass ) {
        String sql =
        "INSERT INTO users ( username, password ) VALUES ('"+AUser+"', '"+APass+"')";
        return sql;
    }
    
    public String getDeleteUserSql( String AUser ) {
        String sql =
        "DELETE FROM users WHERE username='"+AUser+"'";
        return sql;
    }
    
    public String getInsertCategoriesSql() {
        String sql =
        "INSERT INTO categories( category ) VALUES " +
        "('Fizetés'),('Háztartás'),('Rezsi')," +
        "('Ajándék'),('Állat'),('Magán')," +
        "('Élelmiszer'),('Auto'),('Ruha')";
        return sql;
    }
    
    public String getLoginUserSql( String AText ) {
        String sql =
        "SELECT username, password from USERS where username='"+AText+"'";
        return sql;
    }
    
    public String getComboItemsSql() {
        String sql =
        "SELECT category from categories";
        return sql;
    }
    
    public String getMonthComboItemsSql() {
        String sql =
        "SELECT month FROM months";
        return sql;
    }
    
    public String getWalletDataSql( String AUser ) {
        String walletSql =
        "SELECT date, category, price, comment, direction FROM wallet " +
        "INNER JOIN categories ON categoryId=categories.id " +
        "INNER JOIN directions ON directionId=directions.id " +
        "INNER JOIN users ON userId = users.id " +
        "WHERE users.id = ( SELECT id FROM users WHERE username = '" + AUser + "') " +
        "ORDER BY date";
        return walletSql;
    }
    
    /*
    SELECT date, category, price, comment, direction FROM wallet
    INNER JOIN categories ON categoryId=categories.id
    INNER JOIN directions ON directionId=directions.id
    INNER JOIN users ON userId = users.id
    WHERE users.id = ( SELECT id FROM users WHERE username = 'admin')
    ORDER BY date
    */
    
    public String getSelectDataSql( String ACategory ) {
        String selectDataSql =
        "SELECT date, category, price, comment, direction FROM wallet " +
        "INNER JOIN categories ON categoryId=categories.id " +
        "INNER JOIN directions ON directionId=directions.id " +
        "WHERE categoryId=(SELECT id FROM categories WHERE category='"+ACategory+"')";
        return selectDataSql;
    }
    
    public String getInsertDataSql( String ACat, String APrice, String AComm, String ADir ) {
        String insertDataSql =
        "INSERT INTO wallet (date, categoryId, price, comment, directionId) VALUES " +
        "(CURRENT_DATE,(SELECT id FROM categories WHERE category ='"+ACat+"'),'"+APrice+"'," +
        "'"+AComm+"',(SELECT id FROM directions WHERE direction='"+ADir+"'))";
        return insertDataSql;
    }
    
    public String getIncomeSql() {
        String selectPriceSql =
        "SELECT price FROM wallet " +
        "INNER JOIN directions ON directionId=directions.id " +
        "WHERE direction='Be'";
        return selectPriceSql;
    }
    
    public String getOutGoingSql() {
        String selectPriceSql =
        "SELECT price FROM wallet " +
        "INNER JOIN directions ON directionId=directions.id " +
        "WHERE direction='Ki'";
        return selectPriceSql;
    } 
    
    public String getDiagramPriceInSql( String AStartDate, String AEndDate ) {
        String sql =
        "SELECT price FROM wallet " +
        "INNER JOIN directions ON directionId=directions.id " +
        "WHERE date BETWEEN '"+AStartDate+"' AND '"+AEndDate+"'" +
        "AND direction='Be' ";
        return sql;
    }
    
    public String getDiagramPriceOutSql( String AStartDate, String AEndDate ) {
        String sql =
        "SELECT price FROM wallet " +
        "INNER JOIN directions ON directionId=directions.id " +
        "WHERE date BETWEEN '"+AStartDate+"' AND '"+AEndDate+"'" +
        "AND direction='Ki' ";
        return sql;
    }
}


/*
WHERE booking_date BETWEEN
    DATE('1998-02-26') AND
    DATE('1998-03-01')
*/