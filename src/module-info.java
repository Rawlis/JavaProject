module Smart {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires com.fazecast.jSerialComm;
	requires javafx.graphics;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml, javafx.controls, javafx.base;
}
