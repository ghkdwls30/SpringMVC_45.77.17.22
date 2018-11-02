<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<div class="page-breadcrumb">
    <div class="row">
        <div class="col-12 d-flex no-block align-items-center">
           	<h4 class="page-title badge badge-pill badge-info" style="font-size: 17px">사용자 목록</h4>
           
            <div class="ml-auto text-right">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/main/home">메인</a></li>
                        <li class="breadcrumb-item active" aria-current="page">사용자 목록</li>
                    </ol>
                </nav>
            </div>
        </div>
    </div>
</div>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="card">
				<form name="FORM" class="form-horizontal">
				<div class="card-body">
					<div class="row">
						<div class="col-sm-5  mb-3">
							<div class="input-group">
		                        <input type="text" value="${param.searchKeyword}" class="form-control" id="searchKeyword" placeholder="검색어를 입력하세요." style="position: static !important;">
		                        <div class="input-group-append">
		                            <button type="submit" style="cursor: pointer;" onclick="searchList(); return false;">
			                            <i class="fas fa-search"></i>
			                        </button>  
		                        </div>
		                    </div>
	                    </div>
	                    
	                    <div class="col-sm-7 mb-3 text-right">
	                    	<button type="button" class="btn btn-success" onclick="updateUser(); return false;">수정</button>
	                    	<button type="button" class="btn btn-danger" onclick="deleteUser(); return false;">삭제</button>
	                    </div>
	                    
					</div>
					<table class="table">
                         <thead class="thead-light">
                             <tr>
                                 <th>
                                     <label class="customcheckbox m-b-20">
                                         <input type="checkbox" id="mainCheckbox">
                                         <span class="checkmark"></span>
                                     </label>
                                 </th>
                                 <th class="text-center" scope="col">아이디</th>
                                 <th class="text-center" scope="col">회사명</th>
                                 <th class="text-center" scope="col">이름</th>
                                 <th class="text-center" scope="col">모통 슬롯 개수</th>
                                 <th class="text-center" scope="col">자동 슬롯 개수</th>
                                 <th class="text-center" scope="col">연관 슬롯 개수</th>
                                 <th class="text-center" scope="col">사용 시작일</th>
                                 <th class="text-center" scope="col">사용 종료일</th>
                             </tr>
                         </thead>
                         <tbody class="customtable">
                         	<c:forEach items="${userEntityList}" var="userEntity">
                             <tr data-account="${userEntity.account}">
                                 <th>
                                     <label class="customcheckbox">
                                         <input type="checkbox" class="listCheckbox">
                                         <span class="checkmark"></span>
                                     </label>
                                 </th>
                                 <td class="text-center">${userEntity.account}</td>
                                 <td class="text-center">${userEntity.company}</td>
                                 <td class="text-center">${userEntity.name}</td>
                                 <td class="text-center">${userEntity.mobileSearchSlotCnt}</td>
                                 <td class="text-center">${userEntity.autoSlotCnt}</td>
                                 <td class="text-center">${userEntity.relSlotCnt}</td>
                                 <td class="text-center">${userEntity.startedAt}</td>
                                 <td class="text-center">${userEntity.endedAt}</td>
                             </tr>
                             </c:forEach>
                         </tbody>
                     </table>
                     <input type="hidden" id="page" value="1">
                     <input type="hidden" id="size" value="20">
                     <jsp:include page="../common/inc_pagenavigation.jsp"></jsp:include>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>


<script>

function searchList(){
	var param = "?";
	param += "searchKeyword=" +  $("#searchKeyword").val();
	param += "&page=" +  $("#page").val();
	param += "&size=" +  $("#size").val();
	location.href = "/admin/user/list" + param;
}

function changePage(page){
	$("#page").val( page)
	searchList();
}

//  사용자 수정
function updateUser() {
	
	var accounts  = getCheckedAccountList();
	
	if (accounts.length == 0) {
		alert("수정 할 사용자를 선택하세요"); 
		return false;
	}
	if (accounts.length > 1) {
		alert("한번에 한명의 사용자만 수정 할 수 있습니다."); 
		return false;
	}

	location.href = "/admin/user/edit/" + accounts[0];

}

// 사용자 삭제
function deleteUser(){
	
	var accounts  = getCheckedAccountList();
	
	if (accounts.length == 0) {
		alert("삭제 할 사용자를 선택하세요"); 
		return false;
	}
	
	callAjax("/rest/users", "DELETE",
	// 데이터 
	{
		accounts : accounts
	}
	,function(){ // 성공 콜백
		location.href = "/admin/user/list";
	}
	,function(){ // 실패 콜백
		
	});
	
}

// 체크 박스된 아이디 가져오기
function getCheckedAccountList(){
	
	var accounts = [];
	
	$("tbody .customcheckbox input:checked").each(function(){
		var account = $(this).closest("tr").data("account");
		accounts.push( account + '');
	})
	
	return accounts;
}

</script>


