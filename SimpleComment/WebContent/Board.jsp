<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="com.test.*" %>
<%
	StringBuilder sb = new StringBuilder();
	BoardDAO dao = new BoardDAO();
	
	try {
 		dao.connect();
 		
 		for(BoardDTO dto: dao.list()){
 			sb.append(String.format("<div class=\"author_wrap\">"));
 			sb.append(String.format("<div class=\"author\">"));
 			sb.append(String.format("<span class=\"seq\">%s</span>.",dto.getBoardId()));
 			sb.append(String.format("<span class=\"name\">%s</span>",dto.getWriter()));
 			sb.append(String.format("</div>"));
 			sb.append(String.format("</div>"));
 			sb.append(String.format("<p class=\"txt\">%s</p>",dto.getContent()));
 			sb.append(String.format("<div class=\"function\">"));
 			sb.append(String.format("<span class=\"date\">�ۼ��� : %s</span>",dto.getWriteDay()));
 			sb.append(String.format("</div>"));
 		}
 	} catch (Exception e) {
		e.printStackTrace();
 	} finally {
		try{
		dao.close();				
		} catch(Exception e2){
		}
 	}	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>JSP Test</title>
<link rel="stylesheet" type="text/css" href="style.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
<script type="text/javascript">

	function getByte(str) {
		var resultSize = 0;
		if (str == null) {
			return 0;
		}
		for (var i = 0; i < str.length; i++) {
			var c = escape(str.charAt(i));
			if (c.length == 1) {
				resultSize++;
			} else if (c.indexOf("%u") != -1) {
				resultSize += 2;
			} else if (c.indexOf("%") != -1) {
				resultSize += c.length / 3;
			}
		}
		return resultSize;
	}

	function cut(str, len) {
		var resultSize = 0;
		for (var i = 0; i < str.length; i++) {
			var c = escape(str.charAt(i));
			if (c.length == 1) {
				resultSize++;
			} else if (c.indexOf("%u") != -1) {
				resultSize += 2;
			} else if (c.indexOf("%") != -1) {
				resultSize += c.length / 3;
			}
			if (resultSize > len)
				return str.substring(0, i);
		}
		return str;
	}

	$(function() {
		var maxLength = $("#countMax").text();

		$("#content").keyup(function() {
			var text = $("#content").val();
			var textBytes = getByte(text);
			$("#count").html(textBytes);
			if (maxLength - textBytes < 0) {
				alert("�ִ� 400Byte ���� �Է��� �����մϴ�.");
				$("#count").html(maxLength);
				$("#content").val(cut(text, maxLength));
			}
			;
		});

		$("#formObj").submit(function() {
			if ($("#writer").val() == "") {
				$("#error").html(" �ۼ��ڸ� �Է����ּ���.");
				$("#error").css("color", "red");
				$("#writer").focus();
				return false;
			} else if ($("#content").val() == "") {
				$("#error").html(" ������ �Է����ּ���.");
				$("#error").css("color", "red");
				$("#content").focus();
				return false;
			} else {
				return true;
			}
			;
		});

	});
</script>
</head>
<body>
	<div id=wrap>
		<div id="comment">
			<div id="commentInsert">
				<form action="BoardInsert.jsp" method="POST" id="formObj">
				<table>
					<tr>
						<td>�ۼ���<span id="error"></span></td>
					</tr>
					<tr>
						<td><input type="text" id="writer" name="writer"></td>
					</tr>
					<tr>
						<td>���� ����� (<span id="count"></span>/<span id="countMax">200</span> Bytes)</td>
					</tr>
					<tr class="commentText">
						<td><textarea name="content" id="content"></textarea></td>
					</tr>
					<tr>
						<td><input type="submit" value="�Է�"></td>
					</tr>
				</table>
				</form>
			</div>
		</div>

		<div id="commentList">
			<div class="commentArea">
				<%= sb.toString() %>
			</div>
		</div>
		<!-- //commentList -->

	</div>
	<!-- //wrap -->
</body>
</html>