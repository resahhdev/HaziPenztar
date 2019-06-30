/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myClasses;

import control.GuiController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author resahh
 */
public class RDataView extends BorderPane {

    private GuiController FGuiCtr;
    private HBox FHbox;
    private GridPane FGridpane;
    private Label FValueLbl;
    private TextField FValueTf;
    private TextField FNoteTf;
    private Label FMessageLbl;
    private Label FCategoryLbl;
    private Label FDirectionLbl;
    private Label FNoteLbl;
    private ComboBox<String> FCategoryCb;
    private ComboBox<String> FDirectionCb;
    private Button FOkBtn;
    private Button FCancelBtn;
    private String FText;
    private ObservableList<String> FCatList;
    private ObservableList<String> FDirList;
    
    public RDataView( String AText, ObservableList<String> ACatList, ObservableList<String> ADirList ) {
        
        FText = AText;
        FCatList = ACatList;
        FDirList = ADirList;
        FHbox = new HBox();
        FGridpane = new GridPane();
        FValueLbl = new Label();
        FMessageLbl = new Label();
        FCategoryLbl = new Label();
        FDirectionLbl = new Label();
        FNoteLbl = new Label();
        FValueTf = new TextField();
        FNoteTf = new TextField();
        FOkBtn = new Button();
        FCancelBtn = new Button();
        FCategoryCb = new ComboBox();
        FDirectionCb = new ComboBox();
        initialize();
    }
    
    private void initialize() {
        
        this.setPadding( new Insets( 10, 50, 50, 50 ));
        FHbox.setPadding( new Insets( 20, 20, 20, 20 ));
        FGridpane.setVgap( 5 );
        FGridpane.setHgap( 5 );
        FGridpane.setPadding( new Insets( 20, 20, 20, 20 ));
        FMessageLbl.setText( "" );
        FNoteTf.setText( "" );
        FNoteLbl.setText( "Megjegyzés:" );
        FCategoryLbl.setText( "Kategória:" );
        FCategoryCb.setMinWidth( 200 );
        FDirectionLbl.setText( "Irány:" );
        FValueLbl.setText( "Érték:" );
        FCancelBtn.setText( "Mégsem" );
        FOkBtn.setText( "OK" );
        FOkBtn.setMinWidth( 75 );
        FCategoryCb.setPromptText( "Válassz" );
        FCategoryCb.setItems( FCatList );
        FDirectionCb.setMinWidth( 200 );
        FDirectionCb.setItems( FDirList );
        FDirectionCb.setPromptText( "Válassz" );
        setDataPane();
    }
    
    private void setDataPane() {
        
        FGridpane.add( FCategoryLbl, 0, 0 );
        FGridpane.add( FCategoryCb, 1, 0 );
        FGridpane.add( FValueLbl, 0, 1 );
        FGridpane.add( FValueTf, 1, 1 );
        FGridpane.add( FNoteLbl, 0, 2 );
        FGridpane.add( FNoteTf, 1, 2 );
        FGridpane.add( FCancelBtn, 2, 2 );
        FGridpane.add( FDirectionLbl, 0, 3 );
        FGridpane.add( FDirectionCb, 1, 3 );
        FGridpane.add( FOkBtn, 2, 3 );
        FGridpane.add( FMessageLbl, 1, 4 );
        
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
        FOkBtn.setId( "okbtn" );
        FCancelBtn.setId( "cancelbtn" );
        FCategoryCb.setId( "textfield" );
        FDirectionCb.setId( "textfield" );
        FValueTf.setId( "textfield" );
        FNoteTf.setId( "textfield" );
        xText.setId( "text" );
        
        this.setTop( FHbox );
        this.setCenter( FGridpane );
    }
    
    public String[] getData() {
        
        String[] data = new String[ 4 ];
        data[ 0 ] = FCategoryCb.getSelectionModel().getSelectedItem();
        data [ 1 ] = FValueTf.getText();
        data [ 2 ] = FNoteTf.getText();
        data [ 3 ] = FDirectionCb.getSelectionModel().getSelectedItem();
        return data;
    }
    
    public Button getOkBtn() { return FOkBtn; }
    public Button getCancelBtn() { return FCancelBtn; }
    public Label getMessageLbl() { return FMessageLbl; }
    public void setMessageLbl( String AMessage ) { FMessageLbl.setText( AMessage ); }
}
