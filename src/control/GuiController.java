/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import control.DatabaseController;
import model.Wallet;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author resahh
 */
public class GuiController implements Initializable {
    
    private DatabaseController dbCtr;
    @FXML private DatePicker datePicker;
    
//===================== Start menu items declaration ===========================
    @FXML private final String MANAGE_MN = "Kezelés";
    @FXML private final String LOGIN_MN = "Bejelentkezés";
    @FXML private final String CLOSE_MN = "Kilépés";
    @FXML private final String INPUT_MN = "új";
    @FXML private final String DATA_MN = "Adat";
    @FXML private final String USER_MN = "Felhasználó";
    @FXML private final String CATEGORY_MN = "Kategória";
    @FXML private final String SELECT_MN = "Lekérdezés";
    @FXML private final String TABLE_MN = "Táblázat";
    @FXML private final String DIAGRAM_MN = "Diagram";
    @FXML private final String PROGRAM_MN = "Program";
    @FXML private final String ABOUT_MN = "Programról";
    @FXML private final String HELEP_MN = "Segítség";
//======================= End menu items declaration ===========================
    
//======================== Start panes declaration =============================
    @FXML private StackPane menuPane;
    @FXML private StackPane helpPane;
    @FXML private StackPane aboutPane;
    @FXML private StackPane tablePane;
    @FXML private StackPane diagramPane;
    @FXML private StackPane loginPane;
    @FXML private StackPane dataPane;
    @FXML private StackPane userPane;
    @FXML private StackPane categoryPane;
//========================= End panes declaration ==============================
    
//===================== Start textfields declaration ===========================
    @FXML private TableView table;
    @FXML private TreeView treeView;
    @FXML private TextField loginUserNameTf;
    @FXML private PasswordField loginPassTf;
    @FXML private TextField noteTf;
    @FXML private TextField valueTf;
    @FXML private TextField addUserTf;
    @FXML private TextField addPassTf;
    @FXML private TextField categoryTf;
//======================== End textfields declaration ==========================
    
//========================== Start buttons declaration =========================
    @FXML private Button loginBtn;
    @FXML private Button dataSaveBtn;
    @FXML private Button dbBtn;
    @FXML private Button delUserBtn;
    @FXML private Button addUserBtn;
    @FXML private Button addCategoryBtn;
    @FXML private Button delCategoryBtn;
//=========================== End buttons declarations =========================
    
//=========================== Start labels declaration =========================
    @FXML private Label statusLbl;
    @FXML private Label loginStatusLbl;
    @FXML private Label dataStatusLbl;
    @FXML private Label ballanceLbl;
    @FXML private Label addUserLbl;
    @FXML private Label categoryStatusLbl;
//============================ End labels declaration ==========================
    
//========================= Start comboboxes declaration =======================
    @FXML private ComboBox<String> dataCategoryCb;
    @FXML private ComboBox<String> dataDirectionCb;
    @FXML private ComboBox<String> selectDataCb;
    private ObservableList<String> categoryList;
    private ObservableList<String> directionList;
//========================== End comboboxes declaration ========================
    private int mode;
    private Image green = new Image( getClass().getResourceAsStream( "/images/greenlamp.png" ));
    private Image red = new Image( getClass().getResourceAsStream( "/images/redlamp.png" ));
    private ObservableList<Wallet> xData = FXCollections.observableArrayList();
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        directionList = FXCollections.observableArrayList( "Ki", "Be" );
        setStatusPane();
        start();
        menuAction();
    }

    private void start() {
        
        mode = 0;
        setMenu();
        dbCtr = new DatabaseController( this );
        loginStatusLbl.setText( "" );
        statusLbl.setText( "" );
        dataStatusLbl.setText( "" );
        addUserLbl.setText( "" );
        loginStatusLbl.setText( "" );
        categoryStatusLbl.setText( "" );
        loginPane.setVisible( false );
        tablePane.setVisible( false );
        diagramPane.setVisible( false );
        aboutPane.setVisible( false );
        helpPane.setVisible( false );
        userPane.setVisible( false );
        categoryPane.setVisible( false );
        dataPane.setVisible( false );
    }
    
    private void setStatusPane() {
        
        dbBtn.setText( "Kapcsolódás" );
        Image databaseRed = new Image( getClass().getResourceAsStream( "/images/databasered.png" ));
        dbBtn.setGraphic( new ImageView( databaseRed ) );
        ballanceLbl.setTextFill(Color.web( "red" ));
    }
    
    public void setMenu() {
        
        Image xImgHome = new Image( getClass().getResourceAsStream( "/images/home.png" ));
        Node xHomeIcon = new ImageView( xImgHome );
        Image xImgLogin = new Image( getClass().getResourceAsStream( "/images/login.png" ));
        Node xLoginIcon = new ImageView( xImgLogin ); 
        Image xImgNew = new Image( getClass().getResourceAsStream( "/images/new.png" ));
        Node xNewIcon = new ImageView( xImgNew );
        Image xImgData = new Image( getClass().getResourceAsStream( "/images/modify.png" ));
        Node xDataIcon = new ImageView( xImgData );
        Image xImgUser = new Image( getClass().getResourceAsStream( "/images/useradd.png" ));
        Node xUserIcon = new ImageView( xImgUser );
        Image xImgCategory = new Image( getClass().getResourceAsStream( "/images/category.png" ));
        Node xCategoryIcon = new ImageView( xImgCategory );
        Image xImgSelect = new Image( getClass().getResourceAsStream( "/images/select.png" ));
        Node xSelectIcon = new ImageView( xImgSelect );
        Image xImgTable = new Image( getClass().getResourceAsStream( "/images/table.png" ));
        Node xTableIcon = new ImageView( xImgTable );
        Image xImgDiagram = new Image( getClass().getResourceAsStream( "/images/diagram.png" ));
        Node xDiagramIcon = new ImageView( xImgDiagram );
        Image xImgDate = new Image( getClass().getResourceAsStream( "/images/calendar.png" ));
        Node xDateIcon = new ImageView( xImgDate );
        Image xImgProgram = new Image( getClass().getResourceAsStream( "/images/java.png" ));
        Node xProgramIcon = new ImageView( xImgProgram );
        Image xImgAbout = new Image( getClass().getResourceAsStream( "/images/about.png" ));
        Node xAboutIcon = new ImageView( xImgAbout );
        Image xImgHelp = new Image( getClass().getResourceAsStream( "/images/help.png" ));
        Node xHelpIcon = new ImageView( xImgHelp );
        Image xImgClose = new Image( getClass().getResourceAsStream( "/images/logout.png" ));
        Node xCloseIcon = new ImageView( xImgClose );
        
        TreeItem<String> root = new TreeItem<>( "Menü" );
        treeView = new TreeView<>( root );
        treeView.setShowRoot( false );
        
        TreeItem<String> mangeMn = new TreeItem<>( MANAGE_MN, xHomeIcon );
        TreeItem<String> loginMn = new TreeItem<>( LOGIN_MN, xLoginIcon );
        TreeItem<String> closeMn = new TreeItem<>( CLOSE_MN, xCloseIcon );
        mangeMn.getChildren().addAll( loginMn, closeMn );
        mangeMn.setExpanded( true );
        
        TreeItem<String> inputMn = new TreeItem<>( INPUT_MN, xNewIcon );
        TreeItem<String> dataMn = new TreeItem<>( DATA_MN, xDataIcon );
        TreeItem<String> userMn = new TreeItem<>( USER_MN, xUserIcon );
        TreeItem<String> categoryMn = new TreeItem<>( CATEGORY_MN, xCategoryIcon );
        inputMn.getChildren().addAll( dataMn, userMn, categoryMn );
        
        TreeItem<String> selectMn = new TreeItem<>( SELECT_MN, xSelectIcon );
        TreeItem<String> TableMn = new TreeItem<>( TABLE_MN, xTableIcon );
        TreeItem<String> DiagramMn = new TreeItem<>( DIAGRAM_MN, xDiagramIcon );
        selectMn.getChildren().addAll( TableMn, DiagramMn );
        
        TreeItem<String> programMn = new TreeItem<>( PROGRAM_MN, xProgramIcon );
        TreeItem<String> aboutMn = new TreeItem<>( ABOUT_MN, xAboutIcon );
        TreeItem<String> helpMn = new TreeItem<>( HELEP_MN, xHelpIcon );
        programMn.getChildren().addAll( aboutMn, helpMn );
        
        root.getChildren().addAll( mangeMn, inputMn, selectMn, programMn );
        menuPane.getChildren().add( treeView );
    }
    
    private void menuAction() {
        
        treeView.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {
            @Override
            public void changed( ObservableValue observable, Object oldValue, Object newValue ) {
                
                TreeItem<String> selectedItem = ( TreeItem<String> ) newValue;
                String selectedMenu = selectedItem.getValue();
                
                if( selectedMenu != null ) {
                    
                    switch( selectedMenu ) {
                        
                        case LOGIN_MN:
                            if( mode == 1 ) {
                               setLoginPane(); 
                            }
                            break;
                        case CLOSE_MN: exit(); break;
                        case DATA_MN:
                            if( mode == 2 ) {
                               setDataPane(); 
                            }
                            break;
                        case USER_MN:
                            if( mode == 2 ) {
                               setUserPane(); 
                            }
                            break;
                        case CATEGORY_MN:
                            if( mode == 2 ) {
                               setCategoryPane(); 
                            }
                            break;
                        case TABLE_MN:
                            if( mode == 2 ) {
                               setTablePane(); 
                            }
                            break;
                        case DIAGRAM_MN:
                            if( mode == 2 ) {
                               setDiagramPane(); 
                            }
                            break;
                        case ABOUT_MN:
                            if( mode == 2 ) {
                               setAboutPane(); 
                            }
                            break;
                        case HELEP_MN:
                            if( mode == 2 ) {
                               setHelpPane(); 
                            }
                            break;
                    }
                }
            }
        });
    }
    
    private void setTablePane() {
        
        loginPane.setVisible( false );
        tablePane.setVisible( true );
        diagramPane.setVisible( false );
        dataPane.setVisible( false );
        categoryPane.setVisible( false );
        userPane.setVisible( false );
        aboutPane.setVisible( false );
        helpPane.setVisible( false );
        
        if( xData.isEmpty() ) {
            
            setTable();
        }
    }
    
    public void setTable() {
        
        dbCtr.setCategoryList();
        categoryList = dbCtr.getCategoryList();
        selectDataCb.setItems( categoryList );
        
        TableColumn xDateCol = new TableColumn( "Dátum" );
        xDateCol.setMinWidth( 25 );
        xDateCol.setCellValueFactory( new PropertyValueFactory<Wallet, String>( "date" ));
      
        TableColumn xCategoryCol = new TableColumn( "Kategória" );
        xCategoryCol.setMinWidth( 100 );
        xCategoryCol.setCellValueFactory( new PropertyValueFactory<Wallet, String>( "category" ));
        
        TableColumn xPriceCol = new TableColumn( "Összeg" );
        xPriceCol.setMinWidth( 15 );
        xPriceCol.setCellValueFactory( new PropertyValueFactory<Wallet, String>( "price" ));
        
        TableColumn xCommentCol = new TableColumn( "Megjegyzés" );
        xCommentCol.setMinWidth( 200 );
        xCommentCol.setCellValueFactory( new PropertyValueFactory<Wallet, String>( "comment" ));
        
        TableColumn xImageCol = new TableColumn( "Irány" );
        xImageCol.setMinWidth( 20 );
        xImageCol.setCellValueFactory( new PropertyValueFactory<>( "image" ));
        
        table.getColumns().addAll( xDateCol, xCategoryCol, xPriceCol, xCommentCol, xImageCol );
        xData.addAll( dbCtr.setWalletData() );
        table.setItems( xData );
    }
    
    private void setDiagramPane() {
        
        loginPane.setVisible( false );
        tablePane.setVisible( false );
        diagramPane.setVisible( true );
        dataPane.setVisible( false );
        categoryPane.setVisible( false );
        userPane.setVisible( false );
        aboutPane.setVisible( false );
        helpPane.setVisible( false );
    }
    
    public void setDiagram() {
        
    }
    
    private void setLoginPane() {
        
        loginPane.setVisible( true );
        tablePane.setVisible( false );
        diagramPane.setVisible( false );
        dataPane.setVisible( false );
        categoryPane.setVisible( false );
        userPane.setVisible( false );
        aboutPane.setVisible( false );
        helpPane.setVisible( false );
    }
    
    private void setAboutPane() {
        
        loginPane.setVisible( false );
        tablePane.setVisible( false );
        diagramPane.setVisible( false );
        dataPane.setVisible( false );
        categoryPane.setVisible( false );
        userPane.setVisible( false );
        aboutPane.setVisible( true );
        helpPane.setVisible( false );
    }
    
    private void setHelpPane() {
        
        loginPane.setVisible( false );
        tablePane.setVisible( false );
        diagramPane.setVisible( false );
        dataPane.setVisible( false );
        categoryPane.setVisible( false );
        userPane.setVisible( false );
        aboutPane.setVisible( false );
        helpPane.setVisible( true );
    }
    
    private void setUserPane() {
        
        loginPane.setVisible( false );
        tablePane.setVisible( false );
        diagramPane.setVisible( false );
        dataPane.setVisible( false );
        categoryPane.setVisible( false );
        userPane.setVisible( true );
        aboutPane.setVisible( false );
        helpPane.setVisible( false );
    }
    
    private void setCategoryPane() {
        
        loginPane.setVisible( false );
        tablePane.setVisible( false );
        diagramPane.setVisible( false );
        dataPane.setVisible( false );
        categoryPane.setVisible( true );
        userPane.setVisible( false );
        aboutPane.setVisible( false );
        helpPane.setVisible( false );
    }
    
    private void setDataPane() {
        
        loginPane.setVisible( false );
        tablePane.setVisible( false );
        diagramPane.setVisible( false );
        dataPane.setVisible( true );
        categoryPane.setVisible( false );
        userPane.setVisible( false );
        aboutPane.setVisible( false );
        helpPane.setVisible( false );
        
        dbCtr.setCategoryList();
        categoryList = dbCtr.getCategoryList();
        dataCategoryCb.setItems( categoryList );
        dataDirectionCb.setItems( directionList );
        datePicker.setValue( LocalDate.now() );
    }
    
    public void dbBtnAction() {
        
        if( mode == 0 ) {
            
            boolean isConnected = false;
            isConnected = dbCtr.connect();
            if( isConnected == true ) {
                
                mode = 1;
                Image databaseGreen = new Image( getClass().getResourceAsStream( "/images/databasegreen.png" ));
                dbBtn.setText( "Kapcsolat OK" );
                dbBtn.setGraphic( new ImageView( databaseGreen ) );
                statusLbl.setText( "" );
                
            }else {
                
                statusLbl.setTextFill( Color.web( "red" ));
                statusLbl.setText( "Nincs kapcsolat." );
            }
        }
    }
    
    public void loginBtnAction() {
        
        if( mode == 1 ) {
            
            if( !loginUserNameTf.getText().equals( "" )) {
            
                String authentication = dbCtr.getLoginUser( loginUserNameTf.getText() );
                String[] auth = authentication.split( ":" );

                if( auth[ 0 ].equals( loginUserNameTf.getText() ) && auth[ 1 ].equals( loginPassTf.getText() )) {

                    loginStatusLbl.setTextFill( Color.web( "green" ));
                    statusLbl.setText( "Üdv " + auth[ 0]  );
                    mode = 2;

                }else {

                    loginStatusLbl.setTextFill( Color.web( "red" ));
                    loginStatusLbl.setText( "Hibás adatok!" );
                }
            }else {
                
                loginStatusLbl.setTextFill( Color.web( "red" ));
                loginStatusLbl.setText( "Mezők nem lehetnek üresen!" );
            }  
        }else {
            
            loginStatusLbl.setTextFill( Color.web( "red" ));
            loginStatusLbl.setText( "Kapcsolódjon az adatbázishoz!" );
        }
    }
    
    private void exit() {
        System.exit( 0 );
    }
    
    public void inputDataBtnAction() {
        
        String date = String.valueOf( datePicker.getValue() );
        String category = dataCategoryCb.getSelectionModel().getSelectedItem();
        String price = valueTf.getText();
        String comment = noteTf.getText();
        String direction = dataDirectionCb.getSelectionModel().getSelectedItem();
        dbCtr.insertData( date, category, price, comment, direction );
        
        valueTf.setText( "" );
        noteTf.setText( "" );
        datePicker.getEditor().clear();
        dataCategoryCb.getSelectionModel().clearSelection();
        dataDirectionCb.getSelectionModel().clearSelection();
        dataStatusLbl.setText( "Sikeres felírás" );
        ImageView imageView;
        if( direction.equals( "Ki" )) {
            
            imageView = new ImageView( red );
            
        }else {
            
            imageView = new ImageView( green );
        }
        Wallet wallet = new Wallet( date, category, price, comment, imageView );
        table.getItems().add(wallet);
        
    }
    
    public void addUserBtnAction() {
        
        String xUserName = addUserTf.getText();
        String xPassword = addPassTf.getText();
        boolean addUser = dbCtr.insertNewUser( xUserName, xPassword );
        if( addUser ) {
           
            addUserLbl.setTextFill( Color.web( "green" ));
            addUserLbl.setText( "Felhasználó hozzáadva" );
        
        }else {
            
            addUserLbl.setTextFill( Color.web( "red" ));
            addUserLbl.setText( "Írási hiba" );
        }
        
    }
    
    public void delUserBtnAction() {
        
        String xUsername = addUserTf.getText();
        boolean deleteUser = dbCtr.deleteUser( xUsername );
        if( deleteUser ) {
            
            addUserLbl.setTextFill( Color.web( "green" ));
            addUserLbl.setText( "Sikeres törlés" );
            
        }else {
            
            addUserLbl.setTextFill( Color.web( "red" ));
            addUserLbl.setText( "Hiba a törlés során" );
        }
        
        
    }
    
    public void addCategoryBtnAction() {
        
    }
    
    public void delCategoryBtnAction() {
        
    }
    
    public void setErrorMessage( String AMessage ) {
        
        statusLbl.setText( AMessage );
    }
    
    public int getMode() {
        return mode;
    }
    public void setMode( int AMode ) {
        mode = AMode;
    }
}

/*
Állapotok:
    mode 0: program alaphelyzetben, csak az adatbázis kapcsolódás gomb működik
    mode 1: bejelentkezve az adatbázisba, csak a login felület működik
    mode 2: user bejelentkezve, minden funkció működik
*/
