<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- ============================================================== -->
<!-- All Required js -->
<!-- ============================================================== -->
<script src="assets/libs/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap tether Core JavaScript -->
<script src="assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- ============================================================== -->
<!-- This page plugin js -->
<!-- ============================================================== -->
<script>
$('[data-toggle="tooltip"]').tooltip();
$(".preloader").fadeOut();
// ============================================================== 
// Login and Recover Password 
// ============================================================== 
$('#to-recover').on("click", function() {
    $("#loginform").slideUp();
    $("#recoverform").fadeIn();
});
$('#to-login').click(function(){
    
    $("#recoverform").hide();
    $("#loginform").fadeIn();
});
</script>

<script>    


(function init(){
	var isSuccess = "${param.successYn}";
	
	if( isSuccess == 'N'){
		var $error_msg = $(".error_msg");
		$error_msg.text("라이센스가 만료되었거나 아이디 혹은 패스워드가 일치하지 않습니다")
		$error_msg.show();
	}
})();

function doLogin(){
	
	if( !checkVaild()) return false;
	$("#loginform").submit();
	
}

function checkVaild(){
		
	$("#msg").hide();
	
	var isSuccess = true;
	var $error_msg = $(".error_msg");
	
	if( $("[name='account']").val() == '' ||  $("[name='account']").val().trim().length == 0 ||
		$("[name='password']").val() == '' ||  $("[name='password']").val().trim().length == 0){
		$error_msg.text("아이디와 패스워드를 모두 입력해 주세요")
		$error_msg.show();
		isSuccess = false;
	}else{
		$error_msg.hide();
	}

	if( isSuccess){
		return true;	
	}else{
		return false;
	}
}

</script>