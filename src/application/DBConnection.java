package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	private static final String user = "c##scott";
	private static final String pw = "tiger";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);
		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB 접속실패 : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
		}
		return conn;
	}
	/*
	public static ResultSet executeQuery(String query) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }
        return null;
    }

    private static void closeResources(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    */
	/** 쿼리 전달 */
	/*
	public ResultSet executeQuery(String query) throws SQLException {
	    try (Connection conn = getConnection();
	         PreparedStatement pstm = conn.prepareStatement(query);
	         ResultSet rs = pstm.executeQuery()) {
	        return rs;
	    } catch (SQLException e) {
	        // SQLException을 다시 던지기 전에 로깅하거나 예외처리 로직을 추가할 수 있습니다.
	        e.printStackTrace();
	        throw e;
	    }
	}
	*/
}
