package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Controller implements Initializable {
	// MainPage
	@FXML
	private ImageView close1;
	@FXML
	private ImageView minimize;
	@FXML
	private Button menu;
	@FXML
	private Button user;
	@FXML
	private Button house;
	@FXML
	private Button preset;
	@FXML
	private BorderPane bp;
	
	private ArduinoConnect arduinoConnect;
	private SimpleIntegerProperty tempData = new SimpleIntegerProperty();
	private DBConnection db = new DBConnection();

	public Controller() {
		// arduinoConnect = new ArduinoConnect();
		// arduinoConnect.start();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// text1.textProperty().bind(tempData.asString());
		// 상단바 X 닫기 버튼

		close1.setOnMouseClicked(event -> {
			System.exit(0);
		});
		// 상단바 - 최소화 버튼
		minimize.setOnMouseClicked(event -> {
			Stage stage = (Stage) minimize.getScene().getWindow();
			stage.setIconified(true); // 창 최소화
		});

		/*
		 * // 유저페이지 - 리스트뷰 farmerListView.setCellFactory(param -> new
		 * TextFieldListCell<>(new StringConverter<Pair<String, String>>() {
		 * 
		 * @Override public String toString(Pair<String, String> pair) { return
		 * pair.getFirst() + " - " + pair.getSecond(); }
		 * 
		 * @Override public Pair<String, String> fromString(String string) { String[]
		 * parts = string.split(" - "); return new Pair<>(parts[0], parts[1]); } }));
		 * 
		 * farmerListView.getItems().addAll(new Pair<>("First String", "Second String"),
		 * new Pair<>("Another Pair", "Example Text"), new Pair<>("Hello", "World"));
		 */

		user.setOnMouseClicked(event -> {
			System.out.println("test1");
			loadPage("UserPage");
		});
		house.setOnMouseClicked(event -> {
			System.out.println("test1");
			loadPage("HousePage");
		});
	}
	/*
	 * @FXML private void userButtonClick(MouseEvent event) {
	 * System.out.print("test1"); loadPage("UserPage"); }
	 */

	private void loadPage(String page) {
	    try {
	        URL url = getClass().getResource("/application/" + page + ".fxml");
	        
	        if (url != null) {
	            Parent root = FXMLLoader.load(url);
	            bp.setCenter(root);
	        } else {
	            System.err.println("FXML file not found: " + page);
	        }
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        System.err.println("Error loading FXML file: " + page);
	    }
	}
}
