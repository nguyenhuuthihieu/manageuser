<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ADM002</title>
		<link href="./view/css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<%@include file="Header.jsp" %>
		<form action="listUser.do" method="get" name="mainform">
			<table  class="tbl_input" border="0" width="90%"  cellpadding="0" cellspacing="0" >		
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						会員名称で会員を検索します。検索条件無しの場合は全て表示されます。 
					</td>
				</tr>
				<input type="hidden" value="SEARCH" name="type"/>
				<tr>
					<td width="100%">
						<table class="tbl_input" cellpadding="4" cellspacing="0" >
							<tr>
								<td class="lbl_left">氏名:</td>
								<td align="left">
								<input class="txBox" type="text" name="name" value="${fullName }"
									size="20" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" />
								</td>
								<td></td>
							</tr>
							<tr>
								<td class="lbl_left">グループ:</td>
								<td align="left" width="80px">
									<select name="group_id">
										<option value="0" ${groupId == 0 ? 'selected="selected"' : ''}>全て</option>	
										<c:if test="${not empty listGroup}">
											<c:forEach items="${listGroup}" var="group">
												<option value="${group.getGroupId()}" ${groupId == group.getGroupId() ? 'selected="selected"' : ''} >${group.getGroupName()}</option>
											</c:forEach>
										</c:if>							
									</select>							
								</td>
								<td align="left">
									<input class="btn" type="submit" value="検索" />
									<input class="btn" type="button" onclick="/addUser.do" value="新規追加" />							
								</td>
							</tr>
						</table>
					</td>
				</tr>		
			</table>
			<!-- End vung dieu kien tim kiem -->
		</form>
		<!-- Begin vung hien thi danh sach user -->
		<table class="tbl_list" border="1" cellpadding="4" cellspacing="0" width="80%">
			<tr class="tr2">
				<th align="center" width="20px">
					ID
				</th>
				<th align="left" width="350px" style="word-break: break-all;">
					氏名 
					<c:choose>
				      <c:when test="${sortFullName eq 'ASC'}">
				      	<a href = "/manage_user/listUser.do?type=SORT&type_sort=full_name&full_name=DESC&code_level=ASC&end_date=DESC">▲▽</a>
				      </c:when>
				
				      <c:otherwise>
				      	<a href="/manage_user/listUser.do?type=SORT&type_sort=full_name&full_name=ASC&code_level=ASC&end_date=DESC">△▼</a> &nbsp;
				      </c:otherwise>	
				    </c:choose>
				</th>
				<th align="left">
					生年月日
				</th>
				<th align="left">
					グループ
				</th>
				<th align="left">
					メールアドレス
				</th>
				<th align="left" width="70px">
					電話番号
				</th>
				<th align="left">
					日本語能力 
					<c:choose>
				      <c:when test="${sortCodeLevel eq 'ASC'}">
				      	<a href = "/manage_user/listUser.do?type=SORT&type_sort=code_level&full_name=ASC&code_level=DESC&end_date=DESC">▲▽</a>
				      </c:when>
				
				      <c:otherwise>
				      	<a href = "/manage_user/listUser.do?type=SORT&type_sort=code_level&full_name=ASC&code_level=ASC&end_date=DESC">△▼</a>
				      </c:otherwise>	
				    </c:choose>
				</th>
				<th align="left">
					失効日 
					<c:choose>
				      <c:when test="${sortEndDate eq 'ASC'}">
				      	<a href = "/manage_user/listUser.do?type=SORT&type_sort=end_date&full_name=ASC&code_level=ASC&end_date=DESC">▲▽</a>
				      </c:when>
				
				      <c:otherwise>
				      	<a href = "/manage_user/listUser.do?type=SORT&type_sort=end_date&full_name=ASC&code_level=ASC&end_date=ASC">△▼</a>
				      </c:otherwise>	
				    </c:choose>
				</th>
				<th align="left">
					点数
				</th>
			</tr>
			
			<c:if test="${not empty listUsers}">
				<c:forEach items="${listUsers}" var="user">
					<tr>
						<td align="right">
							<a href="ADM005.html">${user.getUserId()}</a>
						</td>
						<td max-width="350px" style="word-break: break-all;">
							${user.getFullName()}
						</td>
						<td align="center">
							${user.getBirthday()}
						</td>
						<td max-width="100px">
							${user.getGroupName()}
						</td>
						<td max-width="200px" style="word-break: break-all;">
							${user.getEmail()}
						</td>
						<td>
							${user.getTel()}
						</td>
						<td max-width="100px">
							${user.getNameLevel()}
						</td>
						<td align="center">
							<c:if test="${not empty user.getNameLevel()}">
							    ${user.getEndDate()}
							</c:if>
						</td>
						<td align="right">
							<c:if test="${not empty user.getNameLevel()}">
							    ${user.getTotal()}
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<c:if test="${empty listUsers}">
			検索条件に該当するユーザが見つかりません。
		</c:if>
		<!-- End vung hien thi danh sach user -->
		<!-- Begin vung paging -->
		<table>
			<tr>
				<td class = "lbl_paging">
					<c:if test="${offsetPage > 0}">
						<a href = "/manage_user/listUser.do?type=SELECT_PAGE&page=${(offsetPage - 1) * limitPaging + 1}"><<</a>
					</c:if>
					<c:if test="${not empty listPaging}">
						<c:forEach items="${listPaging}" var="page">
							<c:choose>
						      <c:when test="${page eq currentPage}">
						      	<a>${page}</a> &nbsp;
						      </c:when>
						
						      <c:otherwise>
						      	<a href="/manage_user/listUser.do?type=SELECT_PAGE&page=${page}">${page}</a> &nbsp;
						      </c:otherwise>	
						    </c:choose>
						</c:forEach>
					</c:if>
					<c:if test="${totalPage > maxPageOffset}">
						<a href = "/manage_user/listUser.do?type=SELECT_PAGE&page=${maxPageOffset + 1}">>></a>
					</c:if>
				</td>
			</tr>
		</table>
		<!-- End vung paging -->
		<%@include file="Footer.jsp" %>
	</body>
</html>