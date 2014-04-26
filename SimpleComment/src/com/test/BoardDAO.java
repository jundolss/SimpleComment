package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BoardDAO {
private Connection conn;
	
	//�����ͺ��̽� ����
	public void connect() throws ClassNotFoundException, SQLException{
		conn = DBConn.getConnection();
	}
	
	//������ ���̽� ���� ����
	public void close() throws SQLException{
		DBConn.close();
	}
	
	//�Է� ����
	public int bbsInsert(BoardDTO dto) throws SQLException{
		int result = 0;
		
		String sql = String.format("INSERT INTO board (boardId, writer, content) VALUES (boardSeq.nextval, '%s', '%s')"
				,dto.getWriter()
				,dto.getContent());
		System.out.println(sql);
		
		Statement stmt = conn.createStatement();
		result = stmt.executeUpdate(sql);
		
		return result;
	}
	
	//��ü �Խù� �о���� �޼ҵ�
	public ArrayList<BoardDTO> list() throws SQLException{
		ArrayList<BoardDTO> result = new ArrayList<BoardDTO>();
		
		String sql = String.format("SELECT BOARDID, WRITER, CONTENT, "
				+ "CASE WHEN TO_CHAR(WRITEDAY, 'YYYY-MM-DD') = TO_CHAR(SYSDATE,'YYYY-MM-DD') THEN  TO_CHAR(WRITEDAY, 'HH24:Mi:SS') "
					 + "ELSE TO_CHAR(WRITEDAY, 'YYYY-MM-DD') END AS  WRITEDAY FROM BOARD ORDER BY BOARDID DESC");
		System.out.println(sql);
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			BoardDTO dto = new BoardDTO();
			dto.setBoardId(rs.getString("boardId"));
			dto.setWriter(rs.getString("writer"));
			dto.setContent(rs.getString("content"));
			dto.setWriteDay(rs.getString("writeDay"));
			result.add(dto);
		}
		
		return result;
	}
}
