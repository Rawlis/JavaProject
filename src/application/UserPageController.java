package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class UserPageController implements Initializable {
	// UserPage
	DBConnection dbConn = new DBConnection();
	Connection conn = null; // DB연결된 상태(세션)을 담은 객체
    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
    
	private ObservableList<String> items = FXCollections.observableArrayList("Item 1", "Item 2", "Item 3");
	@FXML
	ListView<String> listView;
	/*
		items.set(1, "Updated Item");
		
	    // ListView 업데이트
	    listView.refresh();
    */
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    try {
	    	listView.setItems(items);
	    	String quary = "SELECT * FROM EMP";
            //String quary = "SELECT * FROM FARMER";
            
            conn = DBConnection.getConnection();
            pstm = conn.prepareStatement(quary);
            rs = pstm.executeQuery();
            while(rs.next()){
                int empno = rs.getInt(1);
                //int empno = rs.getInt("empno"); 숫자 대신 컬럼 이름을 적어도 된다.
                String ename = rs.getString(2);
                String job = rs.getString(3);
                int mgr = rs.getInt(4);
                java.sql.Date hiredate = rs.getDate(5); // Date 타입 처리
                int sal = rs.getInt(6);
                int comm = rs.getInt(7);
                int deptno = rs.getInt(8);
                
                items.add(Integer.toString(empno)+" - "+ename);
                String result = empno+" "+ename+" "+job+" "+mgr+" "+hiredate+" "+sal+" "+comm+" "+deptno;
                System.out.println(result);
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
	    listView.refresh();
	}
}
