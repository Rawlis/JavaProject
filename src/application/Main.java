package application;
	
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

//icon- iconmonstr 저작권 미표기 가능

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//메인페이지 로드
			Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
            Scene scene = new Scene(root,1080,720);
            //primaryStage.initStyle(StageStyle.UNDECORATED);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
