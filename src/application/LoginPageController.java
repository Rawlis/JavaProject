package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginPageController implements Initializable {
    @FXML
    private TextField IdField;

    @FXML
    private Button LoginButton;

    @FXML
    private TextField PasswordField;
	
	String testId="test";
	String testPw="123";
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	void LoginRequest(ActionEvent event) { 
		if(IdField.getText().equals(testId)&&PasswordField.getText().equals(testPw)) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
	        Parent root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Scene scene = new Scene(root, 1080, 720);
	        Stage secondStage = new Stage();
	        secondStage.setTitle("농장 자동 관리 시스템 T5");
	        secondStage.setScene(scene);
	     // 현재의 로그인 페이지를 닫음
	        Stage currentStage = (Stage) LoginButton.getScene().getWindow();
	        currentStage.close();
	        
	        secondStage.show();
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Login error");
	        alert.setHeaderText(null);
	        alert.setContentText("없는 정보");
	        alert.showAndWait();
		}
	}
}