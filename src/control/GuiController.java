/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.Wallet;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.ImageCollection;
import model.ToolTipCollection;
import myClasses.RDataView;
import myClasses.RLoginView;

/**
 *
 * @author resahh
 */
public class GuiController implements Initializable {
    
    private DatabaseController dbCtr;
    private ImageCollection imgCol;
    private ToolTipCollection ttCol;
    private RLoginView FLoginVw;
    private String FUser;
    @FXML private DatePicker newDatePicker;
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;
    
//===================== Start menu items declaration ===========================
    @FXML private TreeView<String> treeview;
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
    @FXML private StackPane innerLoginPane;
    @FXML private StackPane dataPane;
    @FXML private StackPane userPane;
    @FXML private StackPane innerUserPane;
    @FXML private StackPane categoryPane;
    @FXML private StackPane innerDataPane;
    @FXML private VBox loginVbox;
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
    @FXML private Label FDatabaseLbl;
    @FXML private Label ballanceLbl;
    @FXML private Label FBallanceTextLbl;
    @FXML private Label addUserLbl;
//============================ End labels declaration ==========================
    
//========================= Start comboboxes declaration =======================
    @FXML private ComboBox<String> selectDataCb;
    @FXML private ComboBox<String> selectDateCb;
    @FXML private ComboBox<String> dataCategoryCb;
    @FXML private ComboBox<String> dataDirectionCb;
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
        ttCol = new ToolTipCollection();
        setStatusPane();
        start();
        //menuAction();
    }

    private void start() {
        
        mode = 0;
        setMenu();
        dbCtr = new DatabaseController( this );
        
        statusLbl.setText( "" );
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
        
        DropShadow xDropSdw = new DropShadow();
        xDropSdw.setOffsetX( 5 );
        xDropSdw.setOffsetY( 5 );
        FDatabaseLbl.setTextFill( Color.WHITE );
        FDatabaseLbl.setEffect( xDropSdw );
        FBallanceTextLbl.setTextFill( Color.WHITE );
        FBallanceTextLbl.setEffect( xDropSdw );
        dbBtn.setText( "Kapcsolódás" );
        dbBtn.setGraphic( imgCol.getValue( ":__DBRED__" ));
        ballanceLbl.setTextFill( Color.PINK );
    }
    
    public void setMenu() {
        
        TreeItem<String> root = new TreeItem<>( "Menü" );
        
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
        treeview.setRoot( root );
    }
    
    @FXML
    public void mouseClick() {
        
        String selectedMenu = treeview.getSelectionModel().getSelectedItem().getValue();
        
                if( selectedMenu != null ) {
                    
                    switch( selectedMenu ) {
                        
                        case LOGIN_MN: if( mode == 1 ) { setLoginPane(); } break;
                        case CLOSE_MN: exit(); break;
                        case DATA_MN: if( mode == 2 ) { setDataPane(); } break;
                        case USER_MN: if( mode == 2 ) { setUserPane(); } break;
                        case CATEGORY_MN: if( mode == 2 ) { setCategoryPane(); } break;
                        case TABLE_MN: if( mode == 2 ) { setTablePane(); } break;
                        case DIAGRAM_MN: if( mode == 2 ) { setDiagramPane(); } break;
                        case ABOUT_MN: if( mode == 2 ) { setAboutPane(); } break;
                        case HELEP_MN: if( mode == 2 ) { setHelpPane(); } break;
                    }
                }
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
        
        selectDataCb.setItems( dbCtr.getCategoryList() );
        selectDataCb.setPromptText( "Kategória" );
        selectDataCb.setTooltip( new Tooltip( ttCol.getToolTip( ":__SELCATEGORY__" )));
        selectDateCb.setPromptText( "Hónap" );
        selectDateCb.setItems( dbCtr.getMonthList() );
        selectDateCb.setTooltip( new Tooltip( ttCol.getToolTip( ":__SELMONTH__" )));
        
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
        xData.addAll( dbCtr.setWalletData( FUser ) );
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
        /*
        dbCtr.getDiagramSumIn();
        final String income = "Bevétel";
        final String outgoing = "Kiadás";
        
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        barChart = new BarChart<String, Number>( xAxis, yAxis );
        barChart.setTitle( "Statisztika (6 hónap)" );
        xAxis.setLabel( "Hónap" );
        yAxis.setLabel( "Összeg" );
        
        ObservableList<XYChart.Series<String, Double>> answer = FXCollections.observableArrayList();
        Series<String, Double> aSeries = new Series<String, Double>();
        Series<String, Double> cSeries = new Series<String, Double>();
        aSeries.setName("a");
        cSeries.setName("C");
        
        for (int i = 2011; i < 2021; i++) {
            aSeries.getData().add(new XYChart.Data(Integer.toString(i), aValue));
            aValue = aValue + Math.random() - .5;
            cSeries.getData().add(new XYChart.Data(Integer.toString(i), cValue));
            cValue = cValue + Math.random() - .5;
        }
        answer.addAll(aSeries, cSeries);
        
        
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
        
        Node n = barChart.lookup(".data0.chart-bar");
        n.setStyle("-fx-bar-fill: green");
        n = barChart.lookup(".data1.chart-bar");
        n.setStyle("-fx-bar-fill: red");
        n = barChart.lookup(".data2.chart-bar");
        n.setStyle("-fx-bar-fill: green");
        n = barChart.lookup(".data3.chart-bar");
        n.setStyle("-fx-bar-fill: red");
        n = barChart.lookup(".data4.chart-bar");
        n.setStyle("-fx-bar-fill: green");
        n = barChart.lookup(".data4.chart-bar");
        n.setStyle("-fx-bar-fill: red");
        n = barChart.lookup(".data5.chart-bar");
        n.setStyle("-fx-bar-fill: green");
        n = barChart.lookup(".data6.chart-bar");
        n.setStyle("-fx-bar-fill: red");
        */
    }
    
    private void setLoginPane() {
        
        FLoginVw = new RLoginView( "Bejelentkezés", 1 );
        loginPane.setVisible( true );
        tablePane.setVisible( false );
        diagramPane.setVisible( false );
        dataPane.setVisible( false );
        categoryPane.setVisible( false );
        userPane.setVisible( false );
        aboutPane.setVisible( false );
        helpPane.setVisible( false );
        
        innerLoginPane.getChildren().add( FLoginVw );
        FLoginVw.setMessageLbl( "Jelentkezzen be!" );
        login();
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
        
        FLoginVw = new RLoginView( "Új felhasználó", 2 );
        loginPane.setVisible( false );
        tablePane.setVisible( false );
        diagramPane.setVisible( false );
        dataPane.setVisible( false );
        categoryPane.setVisible( false );
        userPane.setVisible( true );
        aboutPane.setVisible( false );
        helpPane.setVisible( false );
        
        innerUserPane.getChildren().add( FLoginVw );
        addUser();
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
        
        
        categoryList = dbCtr.getCategoryList();
        
        RDataView xDataVw = new RDataView( "Új adat", categoryList, directionList );
        innerDataPane.getChildren().add( xDataVw );
        System.out.println("példányosítás");
        inputData( xDataVw );
        
        /*
        dataCategoryCb.setItems( dbCtr.getCategoryList() );
        dataDirectionCb.setItems( directionList );
        newDatePicker.setValue( LocalDate.now() );
        */
    }
    
    public void dbBtnAction() {
        
        if( mode == 0 ) {
            
            statusLbl.setText( "Kapcsolódás ..." );
            boolean isConnected = false;
            isConnected = dbCtr.connect();
            
            if( isConnected ) {
                
                dbBtn.setText( "Kapcsolat OK" );
                dbBtn.setStyle("-fx-text-fill: black");
                dbBtn.setStyle("-fx-background-color: lightgreen");
                //setLoginPane();
                dbBtn.setGraphic( imgCol.getValue( ":__DBGREEN__" ));
                statusLbl.setText( "Jelentkezzen be!" );
                mode = 1;
                
            }else {
                
                statusLbl.setTextFill( Color.web( "red" ));
                statusLbl.setText( "Nincs kapcsolat." );
            }
        }
    }
    
    public void login() { 
        
        FLoginVw.getLoginBtn().setOnAction( new EventHandler() {
            @Override
            public void handle(Event t) {
                
                String[] xLoginData = FLoginVw.getLoginData();
                 if( mode == 1 ) {
            
                    if( !xLoginData[ 0 ].equals( "" )) {

                        String authentication = dbCtr.getLoginUser( xLoginData[ 0 ] );
                        String[] auth = authentication.split( ":" );

                        if( auth[ 0 ].equals( xLoginData[ 0 ] ) && auth[ 1 ].equals( xLoginData[ 1 ] )) {

                            FUser = auth[ 0 ];
                            statusLbl.setText( "Üdv " + auth[ 0]  );
                            FLoginVw.getUserTf().setText( "" );
                            FLoginVw.getPassTf().setText( "" );
                            FLoginVw.setMessageLbl( "" );
                            mode = 2;

                        }else {

                            FLoginVw.setErrorMessage( "Hibás adatok!" );
                        }

                    }else {

                        FLoginVw.setErrorMessage( "Mező nem lehet üres!" );

                    }  
                }else {

                    //loginStatusLbl.setTextFill( Color.web( "red" ));
                    //loginStatusLbl.setText( "Kapcsolódjon az adatbázishoz!" );
                }
            }
    });
        
        
        FLoginVw.getCancelBtn().setOnAction( new EventHandler() {
            @Override
            public void handle(Event t) {
                FLoginVw.getUserTf().setText( "" );
                FLoginVw.getPassTf().setText( "" );
            }
        
        });
        /*
        if( mode == 1 ) {
            
            if( !AUser.equals( "" )) {
            
                String authentication = dbCtr.getLoginUser( AUser );
                String[] auth = authentication.split( ":" );

                if( auth[ 0 ].equals( AUser ) && auth[ 1 ].equals( APass )) {

                    statusLbl.setText( "Üdv " + auth[ 0]  );
                    mode = 2;

                }else {
                    
                    FLoginVw.setErrorMessage( "Hibás adatok!" );
                }
                
            }else {
                
                FLoginVw.setErrorMessage( "Mezők nem lehetnek üresen!" );
                
            }  
        }else {
            
            loginStatusLbl.setTextFill( Color.web( "red" ));
            loginStatusLbl.setText( "Kapcsolódjon az adatbázishoz!" );
        }
        */
    }
    
    private void exit() {
        
        if( mode == 2 ) {
            mode = 1;
            setLoginPane();
            statusLbl.setText( "" );
            ballanceLbl.setTextFill( Color.PINK );
            ballanceLbl.setText( "000000" );
            table.getItems().removeAll( xData );
        }
    }
    public void inputData( RDataView ADataVw) {
        
        RDataView xDataVw = ADataVw;
        
        System.out.println( "getdata" );
        
        xDataVw.getOkBtn().setOnAction( new EventHandler() {
            @Override
            public void handle(Event t) {
                System.out.println( "okgomb");
                
                String[] data = xDataVw.getData();
                if(!( data[ 0 ] == null || data[ 1 ].equals( "" ) ||
                    data[ 2 ].equals( "" ) || data[ 3 ] == null )) {
                    
                    xDataVw.setMessageLbl( "" );
                    dbCtr.insertData( data[ 0 ], data[ 1 ], data[ 2 ], data[ 3 ] );
                    ImageView imageView;
                    if( data[ 3 ].equals( "Ki" )) {

                        imageView = new ImageView( red );

                    }else {

                        imageView = new ImageView( green );
                    }
                    Wallet wallet = new Wallet( "2000-01-01", data[ 0 ], data[ 1 ], data[ 2 ], imageView );
                    table.getItems().add(wallet);
                    setBallaceLbl();
                    xDataVw.getMessageLbl().setTextFill( Color.LIGHTGREEN );
                    xDataVw.setMessageLbl( "Sikeres felírás" );
                    
                }else {
                    
                    xDataVw.setMessageLbl( "Üres mező" );
                    xDataVw.getMessageLbl().setTextFill( Color.RED );
                }
        }
        });
        /*
        String date = String.valueOf( newDatePicker.getValue() );
        String category = dataCategoryCb.getSelectionModel().getSelectedItem();
        String price = valueTf.getText();
        String comment = noteTf.getText();
        String direction = dataDirectionCb.getSelectionModel().getSelectedItem();
        dbCtr.insertData( date, category, price, comment, direction );
        
        valueTf.setText( "" );
        noteTf.setText( "" );
        newDatePicker.getEditor().clear();
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
        */
    }
    
    public void addUser() {
        
        FLoginVw.getLoginBtn().setOnAction( new EventHandler() {
            @Override
            public void handle(Event t) {
                
               String[] xUserData = FLoginVw.getLoginData();
               if(!( xUserData[ 0 ].equals( "" ) || xUserData[ 1 ].equals( "" ))){
                   
                   boolean addUser = dbCtr.insertNewUser( xUserData[ 0 ], xUserData[ 1 ] );
                   if( addUser ) {
                       
                       FLoginVw.getMessageLbl().setTextFill( Color.GREEN );
                       FLoginVw.setMessageLbl( "Felhasználó hozzáadva" );
                       
                   }else {
                       
                       FLoginVw.getMessageLbl().setTextFill( Color.RED );
                       FLoginVw.setMessageLbl( "Írási hiba!" );
                   }
               }else {
                   
                   FLoginVw.getMessageLbl().setTextFill( Color.RED );
                   FLoginVw.setMessageLbl( "Mező nem lehet üres!" );
               }
            }
        });
        
        FLoginVw.getCancelBtn().setOnAction( new EventHandler() {
            @Override
            public void handle(Event t) {
                
                FLoginVw.getUserTf().setText( "" );
                FLoginVw.getPassTf().setText( "" );
            }
            
        });
        
        FLoginVw.getDeleteBtn().setOnAction( new EventHandler() {
            @Override
            public void handle(Event t) {
                
                String userDel = FLoginVw.getUserTf().getText();
                if( !userDel.equals( "" )) {
                    
                    boolean delete = dbCtr.deleteUser( userDel );
                    if( delete ) {
                        
                        FLoginVw.getMessageLbl().setTextFill( Color.GREEN );
                        FLoginVw.setMessageLbl( "Felhasználó törölve" );
                    }
                }else {
                    
                    FLoginVw.getMessageLbl().setTextFill( Color.RED );
                    FLoginVw.setMessageLbl( "Felhasználó mező üres!" );
                }
            }
            
        });
        /*
        boolean addUser = dbCtr.insertNewUser( "", "" );
        if( addUser ) {
           
            addUserLbl.setTextFill( Color.web( "green" ));
            addUserLbl.setText( "Felhasználó hozzáadva" );
        
        }else {
            
            addUserLbl.setTextFill( Color.web( "red" ));
            addUserLbl.setText( "Írási hiba" );
        }
        */
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
