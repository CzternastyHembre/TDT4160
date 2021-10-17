package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import savehandler.UserSaveHandler;
import user.User;

import java.io.IOException;

public class AddMoneyController {

  @FXML
  AnchorPane anchorPane;
  @FXML
  MenuItem mainMenu, lobby, exit;
  @FXML
  FXMLLoader loader = new FXMLLoader();
  @FXML
  private TextField amountField;
  @FXML
  private Button addButton;
  @FXML
  private Label responseLabel;
  @FXML
  private Label errorLabel;
  @FXML
  private Label nameLabel;
  private User user;

  @FXML
  public void initialize() throws IOException {
    user = UserSaveHandler.getActiveUser();
    nameLabel.setText("Welcome " + user.getUsername());
  }

  @FXML
  private void addMoney() throws NumberFormatException {
    try {
      int amount = Integer.parseInt(amountField.getText());
      user.addMoney(amount);
      UserSaveHandler.updateUser(user);
      errorLabel.setText("");
      responseLabel.setText(amount + " has been added to your account!");

    } catch (NumberFormatException | IOException e) {
      responseLabel.setText("");
      errorLabel.setText("We only accept integers, try again");
    }
  }

  @FXML
  public void exit(ActionEvent actionEvent) {
    System.exit(0);
  }

  @FXML
  public void backToMainMenu(ActionEvent actionEvent) throws IOException {
    // Sets location on the loader by getting the class and then the view file from
    // resources
    loader.setLocation(getClass().getResource("Start.fxml"));
    Parent newGame = loader.load(); // Create a parent class of the loader.load()
    Scene newGameScene = new Scene(newGame); // Create a new Scene from the parent object

    Stage window = (Stage) anchorPane.getScene().getWindow(); // Create new Stage to from the view-file
    window.setScene(newGameScene); // Set the window to the previous chosen scene

    window.show(); // Opens the window
  }

  @FXML
  public void backToLobby(ActionEvent actionEvent) throws IOException {
    // Sets location on the loader by getting the class and then the view file from
    // resources
    loader.setLocation(getClass().getResource("selectGameView.fxml"));
    Parent newGame = loader.load(); // Create a parent class of the loader.load()
    Scene newGameScene = new Scene(newGame); // Create a new Scene from the parent object
    Stage window = (Stage) anchorPane.getScene().getWindow(); // Create new Stage to from the view-file
    window.setScene(newGameScene); // Set the window to the previous chosen scene

    window.show(); // Opens the window
  }
}
