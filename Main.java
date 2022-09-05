import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.Date;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;

public class Main extends Application{
  int money=100;
  ListView<String> listView = new ListView<String>();
  Dialog<String> dialog = new Dialog<String>();

  public void sub(int value){
    money-=value;
    listView.getItems().add("Money Withdrawn: " + value + "$  " + "Current Balance: " + money + "$  " +  new Date());
  }

  public void add(int value){
    money+=value;
    listView.getItems().add("Money Deposited: " + value + "$  " + "Current Balance: " + money + "$  " + new Date());
  }

  @Override
  public void start(Stage primaryStage) {
    Label label = new Label("Login Details"), balance = new Label("");
    label.setStyle("-fx-font-size: 30;-fx-font-weight: bold");
    balance.setStyle("-fx-font-size: 20;-fx-font-weight: bold");
    TextField username = new TextField("Jack");
    username.setStyle("-fx-font-size: 28");
    Button login = new Button("Login"), addBtn = new Button("Deposit Money"), subBtn = new Button("Withdraw Money"), transaction = new Button("Check Transactions History !"), exit = new Button("EXIT");
    login.setStyle("-fx-font-size: 20");
    addBtn.setStyle("-fx-font-size: 20");
    subBtn.setStyle("-fx-font-size: 20");
    transaction.setStyle("-fx-font-size: 20");
    listView.setStyle("-fx-font-size: 20");
    exit.setStyle("-fx-font-size: 20");
    VBox layoutUI;
    HBox changeBtns;
    Scene scene;
    username.setMaxWidth(200);
    addBtn.setVisible(false);
    subBtn.setVisible(false);
    transaction.setVisible(false);
    listView.setVisible(false);
    listView.setManaged(false);
    listView.getItems().add("Initial Deposit: " + money + "$  " +  new Date());
    exit.setVisible(false);
    dialog.setTitle("Error");
    ButtonType type = new ButtonType("Understood !", ButtonData.OK_DONE);
    dialog.setContentText("Money greated than available balance cannot be withdrawn !!");
    dialog.getDialogPane().getButtonTypes().add(type);

    addBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e){
        add(Integer.parseInt(username.getText()));
        balance.setText("Balance: " + money + "$");
      }
    });

    subBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        if(money-Integer.parseInt(username.getText())<0){
          dialog.showAndWait();
        }
        else{
          sub(Integer.parseInt(username.getText()));
          balance.setText("Balance: " + money + "$");
        }
      }
    });

    transaction.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        if(! listView.isVisible()){
          listView.setVisible(true);
          listView.setManaged(true);
          listView.setMaxSize(610, 300);
          transaction.setText("Hide Transactions History !");
        }
        else{
          listView.setVisible(false);
          listView.setManaged(false);
          transaction.setText("Check Transactions History !");
        }
      }
    });


    exit.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        System.exit(0);
      }
    });

    login.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        primaryStage.setTitle("Welcome " + username.getText());
        label.setText("Logged in User: " + username.getText());
        balance.setText("Available Balance: " + money + "$");
        username.setText("10");
        login.setVisible(false);
        login.setManaged(false);
        addBtn.setVisible(true);
        subBtn.setVisible(true);
        transaction.setVisible(true);
        exit.setVisible(true);
      }
    });
    changeBtns = new HBox(addBtn,subBtn);
    changeBtns.setSpacing(20);
    changeBtns.setAlignment(Pos.CENTER);
    layoutUI = new VBox(label, balance, username, login, changeBtns, transaction, listView, exit);
    layoutUI.setSpacing(20);
    layoutUI.setAlignment(Pos.CENTER);
    layoutUI.setStyle("-fx-background-color: #81c483;");
    scene = new Scene(layoutUI, 400, 400);
    scene.setFill(Color.web("#81c483"));
    primaryStage.setTitle("Wallet");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}

