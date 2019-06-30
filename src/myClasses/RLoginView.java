/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myClasses;

import control.GuiController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author resahh
 */
public class RLoginView extends BorderPane {

    private final HBox FHbox;
    private final GridPane FGridpane;
    private final Label FUserNameLbl;
    private final Label FPassLbl;
    private final Label FMessageLbl;
    private final TextField FUserTf;
    private final PasswordField FPassTf;
    private final Button FLoginBtn;
    private final Button FCancelBtn;
    private final Button FDeleteBtn;
    private final String FText;
    private final String loginData[];
    private final int FMode;
    
    public RLoginView( String AText, int AMode ) {
        
        FHbox = new HBox();
        FGridpane = new GridPane();
        FUserNameLbl = new Label( );
        FPassLbl = new Label();
        FMessageLbl = new Label();
        FUserTf = new TextField();
        FPassTf = new PasswordField();
        FLoginBtn = new Button();
        FCancelBtn = new Button();
        FDeleteBtn = new Button();
        loginData = new String[ 2 ];
        FText = AText;
        FMode = AMode;
        initialize();
        setLoginPane();
    }
    
    private void initialize() {
        
        
            this.setPadding( new Insets( 10, 50, 50, 50 ));
            FHbox.setPadding( new Insets( 20, 20, 20, 20 ));
            FGridpane.setVgap( 5 );
            FGridpane.setHgap( 5 );
            FGridpane.setPadding( new Insets( 20, 20, 20, 20 ));
            FUserNameLbl.setText( "Felhasználónév:" );
            FPassLbl.setText( "Jelszó:" );
            FMessageLbl.setText( "" );
            FLoginBtn.setText( "OK" );
            FLoginBtn.setMinWidth( 75 );
            FCancelBtn.setText( "Mégsem" );
            FDeleteBtn.setText( "Törlés" );
            FDeleteBtn.setMinWidth( 75 );
    }
    
    private void setLoginPane() {
        
        if( FMode == 1 ) {
            
            FGridpane.add( FUserNameLbl, 0, 0 );
            FGridpane.add( FUserTf , 1, 0 );
            FGridpane.add( FPassLbl, 0, 1 );
            FGridpane.add( FPassTf, 1, 1 );
            FGridpane.add( FCancelBtn, 2, 0 );
            FGridpane.add( FLoginBtn, 2, 1 );
            FGridpane.add( FMessageLbl, 1, 2 );
            
        }else {
            
            FGridpane.add( FUserNameLbl, 0, 0 );
            FGridpane.add( FUserTf , 1, 0 );
            FGridpane.add( FPassLbl, 0, 1 );
            FGridpane.add( FDeleteBtn, 0, 2 );
            FGridpane.add( FPassTf, 1, 1 );
            FGridpane.add( FCancelBtn, 2, 0 );
            FGridpane.add( FLoginBtn, 2, 1 );
            FGridpane.add( FMessageLbl, 1, 2 );
        }
            Reflection xReflection = new Reflection();
            xReflection.setFraction( 0.7f );
            FGridpane.setEffect( xReflection );

            DropShadow xDropSdw = new DropShadow();
            xDropSdw.setOffsetX( 5 );
            xDropSdw.setOffsetY( 5 );

            Text xText = new Text( FText );
            xText.setFont( Font.font( "Courier New", FontWeight.BOLD, 28 ));
            xText.setEffect( xDropSdw );
            FHbox.getChildren().add( xText );

            this.setId( "borderpane" );
            FGridpane.setId( "gridpane" );
            FLoginBtn.setId( "loginbtn" );
            FCancelBtn.setId( "cancelbtn" );
            FDeleteBtn.setId( "deletebtn" );
            FUserTf.setId( "textfield" );
            FPassTf.setId( "textfield" );
            xText.setId( "text" );
        
        this.setTop( FHbox );
        this.setCenter( FGridpane );
    }
    
    public void setErrorMessage( String AMessage ) {
        
        FMessageLbl.setText( AMessage );
        FMessageLbl.setTextFill( Color.RED );
    }
    
    public String[] getLoginData() { 
        
        loginData[ 0 ] = FUserTf.getText();
        loginData[ 1 ] = FPassTf.getText();
        return loginData;
    }
    
    public Label getMessageLbl(){ return FMessageLbl; }
    public void setMessageLbl( String AMessage ) { FMessageLbl.setText( AMessage ); }
    public Button getLoginBtn() { return FLoginBtn; }
    public Button getCancelBtn() { return FCancelBtn; }
    public Button getDeleteBtn() { return FDeleteBtn; }
    public TextField getUserTf() { return FUserTf; }
    public TextField getPassTf() { return FPassTf; }
}
   