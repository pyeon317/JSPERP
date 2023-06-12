<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../include/header.jsp" %>     
    

<div align="center" class="container">
	<h3>공 지 사 항</h3>
	
	<table class="table table-bordered">
	 	
	 	<tread>
			<tr>
				<th>글 번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>
		</tread>	
		
		<tbody>
			<c:forEach var="vo" items="${list }" varStatus="x">
				<tr>
					<td>${vo.bno }</td>
					<td>${vo.writer }</td>
					<td><a href="announcement_content.announcement?bno=${vo.bno }">${vo.title }</a></td>
					<td> <fmt:formatDate value = "${vo.regdate }" pattern="yyyy-MM-dd (E) hh시mm분"/> </td>
					<td>${vo.hit }</td>
				</tr>
			</c:forEach>
		</tbody>
			
		<tbody>
			<tr>
				<td colspan="6" align="right">
					<form action="" class="form_inline">
						<div class="form group">
							<input type="text" name="search" placeholder="제목검색" class="form-control">
							<input type="submit" value="검색" class="btn btn-default">
							<input type="button" value="글 작성" class="btn btn-default" onclick="location.href='announcement_write.announcement'">
						</div>
					</form>
				</td>
			</tr>
		</tbody>
			
		
		
	</table>
</div>



<%@ include file="../include/footer.jsp" %>    