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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class HousePageController implements Initializable {
	DBConnection dbConn = new DBConnection();
	Connection conn = null; // DB연결된 상태(세션)을 담은 객체
    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
    ObservableList<HouseData> houseBuffer=FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> farmerCB;

    @FXML
    private TableView<HouseData> houseList;
    
    @FXML
    private ListView<String> infoList;

    @FXML
    private Button viewButton;

    @FXML
    private ListView<String> warningList;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	//house tableview setting
    	TableView<HouseData> tableView = new TableView<>();
        TableColumn<HouseData, Integer> houseIdColumn = new TableColumn<>("하우스ID");
        TableColumn<HouseData, String> nameColumn = new TableColumn<>("작물");
        TableColumn<HouseData, String> regionColumn = new TableColumn<>("지역");
    	
        houseIdColumn.setCellValueFactory(new PropertyValueFactory<>("houseId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        regionColumn.setCellValueFactory(new PropertyValueFactory<>("region"));
        houseList.getColumns().addAll(houseIdColumn,nameColumn,regionColumn);
        
    	//combobox db 
    	conn = dbConn.getConnection();
    	String farmerQuery = "SELECT * FROM FARMER";
    	try {
            conn = DBConnection.getConnection();
            pstm = conn.prepareStatement(farmerQuery);
            rs = pstm.executeQuery();
            while(rs.next()){
                int farmerId = rs.getInt(1);
                //int empno = rs.getInt("empno"); 숫자 대신 컬럼 이름을 적어도 된다.
                String name = rs.getString(2);
                farmerCB.getItems().add(farmerId+" "+name);
            }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // ResultSet을 명시적으로 닫아주는 것이 좋습니다.
			try{
			    if ( rs != null ){rs.close();}   
			    if ( pstm != null ){pstm.close();}   
			    if ( conn != null ){conn.close(); }
			}catch(Exception e){
			    throw new RuntimeException(e.getMessage());
			}
	    }
	}
    @FXML
	void viewRequest(ActionEvent event) { 
    	
    	//123123
    	System.out.println("test111");
    	String selectedItem = farmerCB.getSelectionModel().getSelectedItem();
    	if(selectedItem==null)
    		return;
    	else {
    		houseBuffer = FXCollections.observableArrayList();
    		
    		String selectFarmer = selectedItem.split(" ")[0];
    		conn = dbConn.getConnection();
        	String farmerQuery = "SELECT * FROM HOUSE";
        	try {
                conn = DBConnection.getConnection();
                pstm = conn.prepareStatement(farmerQuery);
                rs = pstm.executeQuery();
                while(rs.next()){
                    int houseId = rs.getInt(1);
                    //int empno = rs.getInt("empno"); 숫자 대신 컬럼 이름을 적어도 된다.
                    String name = rs.getString(2);
                    String region = rs.getString(3);
                    int farmerId=rs.getInt(4);
                    if(Integer.toString(farmerId).equals(selectFarmer)) {
                    	houseBuffer.add(new HouseData(houseId,name,region));
                    	System.out.println("test444");
                    }
                }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    } finally {
    	        // ResultSet을 명시적으로 닫아주는 것이 좋습니다.
    			try{
    			    if ( rs != null ){rs.close();}   
    			    if ( pstm != null ){pstm.close();}   
    			    if ( conn != null ){conn.close(); }
    			}catch(Exception e){
    			    throw new RuntimeException(e.getMessage());
    			}
    	    }
        	houseList.setItems(houseBuffer);
    	}
	}
    
}
