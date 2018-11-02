<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Charts js Files -->

<script src="/assets/libs/select2/dist/js/select2.full.min.js"></script>
<script src="/assets/libs/select2/dist/js/select2.min.js"></script>
<script src="/assets/libs/quill/dist/quill.min.js"></script>
<script>    
$(".select2").select2();


var isAdmin = '${isAdmin}';
// 어드민일 경우
if( isAdmin == 'true'){
	var quill = new Quill('#editor', {
	    theme: 'snow'
	});
	
	$(".ql-editor").html( '${boardEntity.content}')
}
</script>