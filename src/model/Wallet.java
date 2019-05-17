/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author resahh
 */
public class Wallet {
    
    private SimpleStringProperty date;
    private SimpleStringProperty category;
    private SimpleStringProperty price;
    private SimpleStringProperty comment;
    private SimpleStringProperty direction;

    public Wallet( String ADate, String ACategory, String APrice, String AComment, String ADirection ) {
        
        date = new SimpleStringProperty( ADate );
        category = new SimpleStringProperty( ACategory );
        price = new SimpleStringProperty( APrice );
        comment = new SimpleStringProperty( AComment );
        direction = new SimpleStringProperty( ADirection );
    }
    
    public String getDate() {
        return date.get();
    }
    public void setDate( String ADate ) {
        date.set( ADate );
    }
    public String getCategory() {
        return category.get();
    }
    public void setCategory( String ACategory ) {
        category.set( ACategory );
    }
    public String getPrice() {
        return price.get();
    }
    public void setPrice( String APrice ) {
        price.set( APrice );
    }
    public String getComment() {
        return comment.get();
    }
    public void setComment( String AComment ) {
        comment.set( AComment );
    }
    public String getDirection() {
        return direction.get();
    }
    public void setDirection( String ADirection ) {
        direction.set( ADirection );
    }
}
