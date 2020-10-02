<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title> 게시판</title> 
</head> 
<body>  
<div id="contentsArea">  
<section id="titlename">  
<h1> 게시판 내용</h1>  
<p class="formSign">게시판의 글 내용입니다.</p>    
<div id="joinForm"> 
<input type="hidden" name="num" value="<c:out value='${boardDTO.num}'/>">    
<fieldset>    
<legend> 게시판 내용</legend>      
<p>  <label for="m_id"> 작성자 </label> <br/>  <c:out value="${boardDTO.m_id }" />      </p>      
<p>  <label for="subject"> 제목 </label> <br/>  <c:out value="${boardDTO.subject }" />       </p>
 <p>  <label for="content"> 내용 </label> <br/>  <textarea rows="15" cols="74" disabled ><c:out value="${boardDTO.content}" />  </textarea>    </p>
<%--게시판의 첨부 파일에 관해 확인한다.--%>        
<c:choose>          
<c:when test="${!empty boardDTO.attached_file}">      
<p>        <label for="attached_file">파일 첨부</label><br/> 
<c:out value="${boardDTO.attached_file}"/>&nbsp;&nbsp;&nbsp; 
<a href="./boardUpload/<c:out value='${boardDTO.attached_file}'/>"> 파일 다운 </a>
<%--hidden 값으로 기존 파일의 이름인 attached_file를 old_file로 설정한다.--%> 
<input type="hidden" name="old_file" value="
<c:out value='${boardDTO. attached_file}'/>"/> &nbsp;&nbsp;&nbsp;      
</p>          
</c:when>          
<c:otherwise>
 <p>        
 <label for="old_file">파일 첨부</label>        <br/> 첨부 파일이 없습니다.      </p>          
 </c:otherwise>        
 </c:choose>      
 <div class="btnJoinArea">
 
 <%-- <c:set var="m_id" value="${m_id}"/> --%>
 <c:if test ="${m_id eq 'admin'}" >        
 <a href="./BoardReplyMove.qa?num=<c:out value="${boardDTO.num}"/>">          
 <button type="button" class="btnOk"> 답변 </button>        </a>
 </c:if>
          
 <a href="./BoardModify.qa?num=<c:out value="${boardDTO.num}"/>">          
 <button type="button" class="btnOk"> 수정 </button>        </a>         
 
 <a href="./BoardDeleteService.qa?num=<c:out value="${boardDTO.num}"/>">          
 <button type="button" class="btnOk"> 삭제 </button>        </a> 
 <button type="button" value="button" onclick="location.href='./BoardList.qa'" class="btnOk">   목록 </button>      
 </div>    
 </fieldset>    
 </div>  
 </section>  
 </div> 
 </body> 
 </html>