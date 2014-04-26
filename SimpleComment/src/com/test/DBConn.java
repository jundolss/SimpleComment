package com.test;

//외부 패키지 연결
//데이터베이스에 연결하기 위한 전용 클래스들을 제공한다.
import java.sql.*;

//오라클 연결 전용 사용자 클래스 생성 
public class DBConn {
	
	//멤버변수
	//Connection 클래스 자료형을 가진
	//변수 선언
	//데이터베이스에 연결된 경우
	//연결 객체를 저장하는 것이 목적.
	//현재 conn 변수에는 null 이 저장된 상태임.
	private static Connection conn;
	
	//연결 전용 메소드
	public static Connection getConnection() 
				throws ClassNotFoundException, SQLException {
		if (conn == null) {
			//ojdbc6.jar에 있는 특정 클래스 호출해서 객체 반환
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//DriverManager 클래스의
			//getConnection() 메소드 호출
			//오라클서버 위치(localhost), 포트번호, sid, id, pw 입력
			//Connection 객체 반환 예정
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","junsuk","1234");
		}
		return conn;
	}
	
	//메소드
	public static void close() 
			throws SQLException {
		//conn 변수에 Connection 객체 저장 여부 확인
		if (conn != null) {
			//오라클 연결 상태 확인
			if (!conn.isClosed()) {
				//오라클 연결 종료 명령
				conn.close();
			}
		}
		//오라클 연결을 닫은 후에는
		//conn 변수를 초기화해야 한다.
		conn = null;
	}

}
