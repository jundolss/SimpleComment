<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.test.*" %>
<%
	request.setCharacterEncoding("euc-kr");

	String writer = request.getParameter("writer");
	String content = request.getParameter("content");

	BoardDTO dto = new BoardDTO();
	dto.setWriter(writer);
	dto.setContent(content);
	
	dto.setContent(dto.getContent().replaceAll("'", "''"));
	
	BoardDAO dao = new BoardDAO();
	try {
		dao.connect();
		dao.bbsInsert(dto);
	} catch (Exception e) {
		System.out.println(e.toString());
	} finally {
		try {
			dao.close();
		} catch (Exception e) {
		}
	}
	
	response.sendRedirect(String.format("Board.jsp"));
%>