<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<div class="page-breadcrumb">
    <div class="row">
        <div class="col-12 d-flex no-block align-items-center">
           			<h4 class="page-title badge badge-pill badge-info" style="font-size: 17px">사용자 수정</h4>
            <div class="ml-auto text-right">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/main/home">메인</a></li>
                        <li class="breadcrumb-item active" aria-current="page">사용자 수정</li>
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
				           <div class="form-group row">
                               <label for="fname" class="col-sm-1 text-left control-label col-form-label">회사명</label>
                               <div class="col-sm-11">
                                   <input value="${userEntity.company}" type="text" class="form-control" name="company" placeholder="사용자의 회사명을 입력하세요.">
                               </div>
                           </div>
                           <div class="form-group row">
                               <label for="fname" class="col-sm-1 text-left control-label col-form-label">이름</label>
                               <div class="col-sm-11">
                                   <input value="${userEntity.name}" type="text" class="form-control" name="name" placeholder="이름을 입력하세요.">
                               </div>
                           </div>
                           <div class="form-group row">
                               <label for="fname" class="col-sm-1 text-left control-label col-form-label">아이디</label>
                               <div class="col-sm-11">
                                   <input value="${userEntity.account}" type="text" class="form-control" name="account" placeholder="아이디를 입력하세요.">
                               </div>
                           </div>
                           <div class="form-group row">
                               <label for="lname" class="col-sm-1 text-left control-label col-form-label">패스워드</label>
                               <div class="col-sm-11">
                                   <input value="${userEntity.password}" type="text" class="form-control" name="password" placeholder="패스워드를 입력하세요.">
                               </div>
                           </div>
							<div class="form-group row">
                               <label for="lname" class="col-sm-1 text-left control-label col-form-label">패스워드 확인</label>
                               <div class="col-sm-11">
                                   <input type="text" value="${userEntity.password}" class="form-control" name="password_confirm" placeholder="동일한 패스워드를 다시 한번 입력하세요.">
                               </div>
                           </div>
                           <div class="form-group row">
                               <label for="lname" class="col-sm-1 text-left control-label col-form-label">모통슬롯개수</label>
                               <div class="col-sm-11">
                                   <input type="number" value="${userEntity.mobileSearchSlotCnt}" class="form-control" name="mobileSearchSlotCnt"  placeholder="사용자에게 부여할 모통슬롯개수를 입력하세요.">
                               </div>
                           </div>
                           <div class="form-group row">
                               <label for="lname" class="col-sm-1 text-left control-label col-form-label">자동슬롯개수</label>
                               <div class="col-sm-11">
                                   <input type="number" value="${userEntity.autoSlotCnt}" class="form-control" name="autoSlotCnt" placeholder="사용자에게 부여할 자동슬롯개수를 입력하세요.">
                               </div>
                           </div>
                           <div class="form-group row">
                               <label for="lname" class="col-sm-1 text-left control-label col-form-label">연관슬롯개수</label>
                               <div class="col-sm-11">
                                   <input type="number" value="${userEntity.relSlotCnt}" class="form-control" name="relSlotCnt" placeholder="사용자에게 부여할 연관슬롯개수를 입력하세요.">
                               </div>
                           </div>
                           <div class="form-group row">
                               <label for="lname" class="col-sm-1 text-left control-label col-form-label">사용 시작일</label>
                               <div class="col-sm-11">
                                   <div class="input-group">
	                                    <input value="${fn:split(userEntity.startedAt, ' ')[0] }" type="text" class="form-control" id="datepicker-autoclose_1" name="startedAt" placeholder="yyyy-mm-dd" data-date-format="yyyy-mm-dd">
	                                    <div class="input-group-append">
	                                        <span class="input-group-text"><i class="fa fa-calendar"></i></span>
	                                    </div>
                                	</div>
                               </div>
                           </div>
                           	<div class="form-group row">
                               <label for="lname" class="col-sm-1 text-left control-label col-form-label">사용 종료일</label>
                               <div class="col-sm-11">
                                   <div class="input-group">
	                                    <input value="${fn:split(userEntity.endedAt, ' ')[0] }" type="text" class="form-control" id="datepicker-autoclose_2" name="endedAt" placeholder="yyyy-mm-dd" data-date-format="yyyy-mm-dd">
	                                    <div class="input-group-append">
	                                        <span class="input-group-text"><i class="fa fa-calendar"></i></span>
	                                    </div>
                                	</div>
                               </div>
                           </div>
                       </div>
                       <div class="border-top">
                           <div class="card-body text-center">
                           			<button type="button" class="btn btn btn-success" onclick="updateUser();">수정</button>
                           	<button type="submit" class="btn btn-danger" onclick="history.go(-1); return false;">취소</button>
                           </div>
                       </div>
				</form>
			</div>
		</div>
	</div>
</div>


<script>
// 유효성체크
function checkVaildBeforeSubmit(){
	return true;
}

//사용자 수정
function updateUser(){
	
	if(!checkVaildBeforeSubmit()){
		return false;
	}
	
	callAjax("/rest/users", "PUT",
	// 데이터 
	{
		account : getValueByName("account"),
		password : getValueByName("password"),
		name : getValueByName("name"),
		startedAt : getDateValueByName("startedAt"),
		endedAt : getDateValueByName("endedAt"),
		company : getValueByName("company"),
		mobileSearchSlotCnt : getValueByName("mobileSearchSlotCnt"),
		autoSlotCnt : getValueByName("autoSlotCnt"),
		relSlotCnt : getValueByName("relSlotCnt")
	}
	,function(data){ // 성공 콜백
		location.href = "/admin/user/list";
	}
	,function(){ // 실패 콜백
		
	});
}

// 사용자 등록
function registUser(){
	
	if(!checkVaildBeforeSubmit()){
		return false;
	}
	
	callAjax("/rest/users", "POST",
	// 데이터 
	{
		account : getValueByName("account"),
		password : getValueByName("password"),
		name : getValueByName("name"),
		startedAt : getDateValueByName("startedAt"),
		endedAt : getDateValueByName("endedAt"),
		type : '02',
		company : getDateValueByName("company"),
		mobileSearchSlotCnt : getValueByName("mobileSearchSlotCnt"),
		autoSlotCnt : getValueByName("autoSlotCnt"),
		relSlotCnt : getValueByName("relSlotCnt")
	}
	,function(){ // 성공 콜백
		location.href = "/admin/user/list";
	}
	,function(){ // 실패 콜백
		
	});
}


</script>