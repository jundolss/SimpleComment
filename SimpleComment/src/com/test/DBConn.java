package com.test;

//�ܺ� ��Ű�� ����
//�����ͺ��̽��� �����ϱ� ���� ���� Ŭ�������� �����Ѵ�.
import java.sql.*;

//����Ŭ ���� ���� ����� Ŭ���� ���� 
public class DBConn {
	
	//�������
	//Connection Ŭ���� �ڷ����� ����
	//���� ����
	//�����ͺ��̽��� ����� ���
	//���� ��ü�� �����ϴ� ���� ����.
	//���� conn �������� null �� ����� ������.
	private static Connection conn;
	
	//���� ���� �޼ҵ�
	public static Connection getConnection() 
				throws ClassNotFoundException, SQLException {
		if (conn == null) {
			//ojdbc6.jar�� �ִ� Ư�� Ŭ���� ȣ���ؼ� ��ü ��ȯ
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//DriverManager Ŭ������
			//getConnection() �޼ҵ� ȣ��
			//����Ŭ���� ��ġ(localhost), ��Ʈ��ȣ, sid, id, pw �Է�
			//Connection ��ü ��ȯ ����
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","junsuk","1234");
		}
		return conn;
	}
	
	//�޼ҵ�
	public static void close() 
			throws SQLException {
		//conn ������ Connection ��ü ���� ���� Ȯ��
		if (conn != null) {
			//����Ŭ ���� ���� Ȯ��
			if (!conn.isClosed()) {
				//����Ŭ ���� ���� ���
				conn.close();
			}
		}
		//����Ŭ ������ ���� �Ŀ���
		//conn ������ �ʱ�ȭ�ؾ� �Ѵ�.
		conn = null;
	}

}
