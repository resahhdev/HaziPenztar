/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import control.DatabaseController;
import model.Wallet;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.ImageCollection;

/**
 *
 * @author resahh
 */
public class GuiController implements Initializable {
    
    private DatabaseController dbCtr;
    private ImageCollection imgCol;
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
    @FXML private BarChart<String, Number> barChart;
    private int mode;
    private Image green = new Image( getClass().getResourceAsStream( "/images/greenlamp.png" ));
    private Image red = new Image( getClass().getResourceAsStream( "/images/redlamp.png" ));
    private ObservableList<Wallet> xData = FXCollections.observableArrayList();
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        directionList = FXCollections.observableArrayList( "Ki", "Be" );
        imgCol = new ImageCollection();
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
        dbBtn.setGraphic( imgCol.getValue( ":__DBRED__" ));
        ballanceLbl.setTextFill(Color.web( "red" ));
    }
    
    public void setMenu() {
        
        TreeItem<String> root = new TreeItem<>( "Menü" );
        treeView = new TreeView<>( root );
        treeView.setShowRoot( false );
        
        TreeItem<String> mangeMn = new TreeItem<>( MANAGE_MN, imgCol.getValue( ":__HOME__" ));
        TreeItem<String> loginMn = new TreeItem<>( LOGIN_MN, imgCol.getValue( ":__LOGIN__" ));
        TreeItem<String> closeMn = new TreeItem<>( CLOSE_MN, imgCol.getValue( ":__LOGOUT__" ));
        mangeMn.getChildren().addAll( loginMn, closeMn );
        mangeMn.setExpanded( true );
        
        TreeItem<String> inputMn = new TreeItem<>( INPUT_MN, imgCol.getValue( ":__NEW__" ));
        TreeItem<String> dataMn = new TreeItem<>( DATA_MN, imgCol.getValue( ":__DATA__" ));
        TreeItem<String> userMn = new TreeItem<>( USER_MN, imgCol.getValue( ":__USER__" ));
        TreeItem<String> categoryMn = new TreeItem<>( CATEGORY_MN, imgCol.getValue( ":__CATEGORY__" ));
        inputMn.getChildren().addAll( dataMn, userMn, categoryMn );
        
        TreeItem<String> selectMn = new TreeItem<>( SELECT_MN, imgCol.getValue( ":__SELECT__" ));
        TreeItem<String> TableMn = new TreeItem<>( TABLE_MN, imgCol.getValue( ":__TABLE__" ));
        TreeItem<String> DiagramMn = new TreeItem<>( DIAGRAM_MN, imgCol.getValue( ":__DIAGRAM__" ));
        selectMn.getChildren().addAll( TableMn, DiagramMn );
        
        TreeItem<String> programMn = new TreeItem<>( PROGRAM_MN, imgCol.getValue( ":__PROGRAM__" ));
        TreeItem<String> aboutMn = new TreeItem<>( ABOUT_MN, imgCol.getValue( ":__ABOUT__" ));
        TreeItem<String> helpMn = new TreeItem<>( HELEP_MN, imgCol.getValue( ":__HELP__" ));
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
        setBallaceLbl();
    }
    
    public void setBallaceLbl() {
        
        int ballance = dbCtr.getBallance();
        if( ballance >= 100000 ) {
            
            ballanceLbl.setTextFill( Color.web( "green" ));
            
        }else if( ballance < 100000 && ballance > 50000 ) {
            
            ballanceLbl.setTextFill( Color.web( "orange" ));
            
        }else {
            
            ballanceLbl.setTextFill( Color.web( "red" ));
        }
        ballanceLbl.setText( String.valueOf( ballance ));
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
        setDiagram();
    }
    
    public void setDiagram() {
        
        final String income = "Bevétel";
        final String outgoing = "Kiadás";
        
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        barChart = new BarChart<String, Number>( xAxis, yAxis );
        barChart.setTitle( "Statisztika (6 hónap)" );
        xAxis.setLabel( "Hónap" );
        yAxis.setLabel( "Összeg" );
        
        XYChart.Series incomeSeries = new XYChart.Series<>();
        incomeSeries.setName( income );
        incomeSeries.getData().add( new XYChart.Data( "Január", 352000 ));
        incomeSeries.getData().add( new XYChart.Data( "Február", 321000 ));
        incomeSeries.getData().add( new XYChart.Data( "Március", 359000 ));
        incomeSeries.getData().add( new XYChart.Data( "Április", 267000 ));
        incomeSeries.getData().add( new XYChart.Data( "Május", 387000 ));
        incomeSeries.getData().add( new XYChart.Data( "Június", 255000 ));
        
        XYChart.Series outgoingSeries = new XYChart.Series<>();
        outgoingSeries.setName( outgoing );
        outgoingSeries.getData().add( new XYChart.Data( "Január", 298000 ));
        outgoingSeries.getData().add( new XYChart.Data( "Február", 325000 ));
        outgoingSeries.getData().add( new XYChart.Data( "Március", 215000 ));
        outgoingSeries.getData().add( new XYChart.Data( "Április", 267000 ));
        outgoingSeries.getData().add( new XYChart.Data( "Május", 367000 ));
        outgoingSeries.getData().add( new XYChart.Data( "Június", 199000 ));
        
        barChart.getData().addAll( incomeSeries, outgoingSeries );
        diagramPane.getChildren().add( barChart );
        /*
        Node n = barChart.lookup(".data0.chart-bar");
        n.setStyle("-fx-bar-fill: green");
        n = barChart.lookup(".data1.chart-bar");
        n.setStyle("-fx-bar-fill: red");
        n = barChart.lookup(".data2.chart-bar");
        n.setStyle("-fx-bar-fill: green");
        n = barChart.lookup(".data3.chart-bar");
        n.setStyle("-fx-bar-fill: red");
        */
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
        statusLbl.setText( "Kapcsolódás ..." );
        if( mode == 0 ) {
            
            boolean isConnected = false;
            isConnected = dbCtr.connect();
            
            if( isConnected ) {
                
                mode = 1;
                //Image databaseGreen = new Image( getClass().getResourceAsStream( "/images/databasegreen.png" ));
                dbBtn.setText( "Kapcsolat OK" );
                dbBtn.setGraphic( imgCol.getValue( ":__DBGREEN__" ));
                //dbBtn.setGraphic( new ImageView( databaseGreen ) );
                statusLbl.setText( "Jelentkezzen be!" );
                
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
        setBallaceLbl();
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
