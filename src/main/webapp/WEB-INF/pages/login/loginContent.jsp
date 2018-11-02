<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html dir="ltr">
    
<head>
        <title>Matrix Admin</title><meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    </head>
    <body>
    
	    <div class="main-wrapper">
	        <!-- ============================================================== -->
	        <!-- Preloader - style you can find in spinners.css -->
	        <!-- ============================================================== -->
	        <div class="preloader">
	            <div class="lds-ripple">
	                <div class="lds-pos"></div>
	                <div class="lds-pos"></div>
	            </div>
	        </div>
	        <!-- ============================================================== -->
	        <!-- Preloader - style you can find in spinners.css -->
	        <!-- ============================================================== -->
	        <!-- ============================================================== -->
	        <!-- Login box.scss -->
	        <!-- ============================================================== -->
	        <div class="auth-wrapper d-flex no-block justify-content-center align-items-center bg-dark">
	            <div class="auth-box bg-dark border-top border-secondary">
	                <div id="loginform">
	                    <div class="text-center p-t-20 p-b-20">
	                        <span class="db"><img src="../../assets/images/logo.png" alt="logo" /></span>
	                    </div>
	                    <!-- Form -->
	                    <form class="form-horizontal m-t-20" id="loginform" action="/login" method="post">
	                        <div class="row p-b-30">
	                            <div class="col-12">
	                                <div class="input-group mb-3">
	                                    <div class="input-group-prepend">
	                                        <span class="input-group-text bg-success text-white" id="basic-addon1"><i class="ti-user"></i></span>
	                                    </div>
	                                    <input type="text" class="form-control form-control-lg" placeholder="Username" aria-label="Username" name="account" aria-describedby="basic-addon1" required="">
	                                </div>
	                                <div class="input-group mb-3">
	                                    <div class="input-group-prepend">
	                                        <span class="input-group-text bg-warning text-white" id="basic-addon2"><i class="ti-pencil"></i></span>
	                                    </div>
	                                    <input type="password" class="form-control form-control-lg" placeholder="Password" aria-label="Password" name="password" aria-describedby="basic-addon1" required="">
	                                </div>
	                            </div>
	                            <div class="col-12">
		                    		<span class="error_msg"></span>
		                    	</div>
	                        </div>
	                        <div class="row border-top border-secondary">
	                            <div class="col-12">
	                                <div class="form-group">
	                                    <div class="p-t-20">
	                                        <button class="btn btn-info" id="to-recover" type="button"><i class="fa fa-lock m-r-5"></i> Lost password?</button>
	                                        <button class="btn btn-success float-right" type="submit" onclick="doLogin()">Login</button>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </form>
	                </div>
	                <div id="recoverform">
	                    <div class="text-center">
	                        <span class="text-white">Enter your e-mail address below and we will send you instructions how to recover a password.</span>
	                    </div>
	                    <div class="row m-t-20">
	                        <!-- Form -->
	                        <form class="col-12" action="index.html">
	                            <!-- email -->
	                            <div class="input-group mb-3">
	                                <div class="input-group-prepend">
	                                    <span class="input-group-text bg-danger text-white" id="basic-addon1"><i class="ti-email"></i></span>
	                                </div>
	                                <input type="text" class="form-control form-control-lg" placeholder="Email Address" aria-label="Username" aria-describedby="basic-addon1">
	                            </div>
	                            <!-- pwd -->
	                            <div class="row m-t-20 p-t-20 border-top border-secondary">
	                                <div class="col-12">
	                                    <a class="btn btn-success" href="#" id="to-login" name="action">Back To Login</a>
	                                    <button class="btn btn-info float-right" type="button" name="action">Recover</button>
	                                </div>
	                            </div>
	                        </form>
	                    </div>
	                </div>
	            </div>
	        </div>
	        <!-- ============================================================== -->
	        <!-- Login box.scss -->
	        <!-- ============================================================== -->
	        <!-- ============================================================== -->
	        <!-- Page wrapper scss in scafholding.scss -->
	        <!-- ============================================================== -->
	        <!-- ============================================================== -->
	        <!-- Page wrapper scss in scafholding.scss -->
	        <!-- ============================================================== -->
	        <!-- ============================================================== -->
	        <!-- Right Sidebar -->
	        <!-- ============================================================== -->
	        <!-- ============================================================== -->
	        <!-- Right Sidebar -->
	        <!-- ============================================================== -->
	    </div>
    
        
    </body>
</html>
