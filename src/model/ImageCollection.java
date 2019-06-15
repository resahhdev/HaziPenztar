package model;

import java.util.HashMap;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ImageCollection {

    private HashMap<String, Node> icons;

    public ImageCollection() {
        
        icons = new HashMap<>();
        fillIconMap();
    }
    
    private void fillIconMap() {
        
        icons.put( ":__HOME__" , new ImageView( new Image( getClass().getResourceAsStream( "/images/home.png" ))));
        icons.put( ":__LOGIN__", new ImageView( new Image( getClass().getResourceAsStream( "/images/login.png" ))));
        icons.put( ":__NEW__" , new ImageView( new Image( getClass().getResourceAsStream( "/images/new.png" ))));
        icons.put( ":__DATA__" , new ImageView( new Image( getClass().getResourceAsStream( "/images/modify.png" ))));
        icons.put( ":__USER__" , new ImageView( new Image( getClass().getResourceAsStream( "/images/useradd.png" ))));
        icons.put( ":__CATEGORY__", new ImageView( new Image( getClass().getResourceAsStream( "/images/category.png" ))));
        icons.put( ":__SELECT__" , new ImageView( new Image( getClass().getResourceAsStream( "/images/select.png" ))));
        icons.put( ":__TABLE__", new ImageView( new Image( getClass().getResourceAsStream( "/images/table.png" ))));
        icons.put( ":__DIAGRAM__" , new ImageView( new Image( getClass().getResourceAsStream( "/images/diagram.png" ))));
        icons.put( ":__DATE__", new ImageView( new Image( getClass().getResourceAsStream( "/images/calendar.png" ))));
        icons.put( ":__PROGRAM__", new ImageView( new Image( getClass().getResourceAsStream( "/images/java.png" ))));
        icons.put( ":__ABOUT__" , new ImageView( new Image( getClass().getResourceAsStream( "/images/about.png" ))));
        icons.put( ":__HELP__" , new ImageView( new Image( getClass().getResourceAsStream( "/images/help.png" ))));
        icons.put( ":__LOGOUT__" , new ImageView( new Image( getClass().getResourceAsStream( "/images/logout.png" ))));
        icons.put( ":__DBGREEN__" , new ImageView( new Image( getClass().getResourceAsStream( "/images/databasegreen.png" ))));
        icons.put( ":__DBRED__" , new ImageView( new Image( getClass().getResourceAsStream( "/images/databasered.png" ))));
    }
    
    public Node getValue( String AKey ) {
        return icons.get( AKey );
    }
}
