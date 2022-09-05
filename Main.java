import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class Main extends Application{
  int money=100;
  public int sub(int num){
    return money-=num;
  }

  public int add(int num){
    return money+=num;
  }

  @Override
  public void start(Stage primaryStage) {
    Label label = new Label("Login Details"), balance = new Label("");
    TextField username = new TextField("Jack");
    Button login = new Button("Login"), addBtn = new Button("Add Money"), subBtn = new Button("Withdraw Money"), transaction = new Button("Check Transactions");
    VBox vbox;
    Scene scene;
    username.setMaxWidth(200);
    addBtn.setVisible(false);
    subBtn.setVisible(false);
    transaction.setVisible(false);

    addBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e){
        balance.setText("Balance: " + add(Integer.parseInt(username.getText())) + "$");
      }
    });

    subBtn.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        balance.setText("Balance: " + sub(Integer.parseInt(username.getText())) + "$");
      }
    });

    login.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        primaryStage.setTitle("Welcome " + username.getText());
        label.setText("Logged in User: " + username.getText());
        balance.setText("Balance: " + money + "$");
        username.setText("10");
        login.setVisible(false);
        login.setManaged(false);
        addBtn.setVisible(true);
        subBtn.setVisible(true);
        transaction.setVisible(true);
      }
    });

    vbox = new VBox(label, balance, username, login, addBtn, subBtn, transaction);
    vbox.setSpacing(20);
    vbox.setAlignment(Pos.CENTER);
    scene = new Scene(vbox, 400, 400);

    primaryStage.setTitle("Wallet");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}

