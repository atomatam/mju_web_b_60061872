<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

 
<html lang="ko">
<head>
	<meta charset="UTF-8" />
	<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<meta HTTP-EQUIV="Expires" CONTENT="0">
	<title>Wiki ComGong</title>
	<link rel="stylesheet" href="stylesheets/basic-style.css" />
<style>
	margin-bottom: 40px;
	font-family: 'Lato', 'Sans-Serief';
	color:#fff;
</style>

</head>
<body>
	<h1>Welcome to the Computer Engineering Education Service</h1>
	<ul class="gallery-nav">
		<li>
			<a class = "tooltip photo1" href="PageServlet?op=login">
				<div class="tooltip-box">
					<h4 class="tooltip-title">Login Page</h4>
					<p class="tooltip-desc">Login Page로 이동합니다.</p>
				</div>
			</a>
		</li>	
		
		<li>
			<a class="tooltip photo2" href="PageServlet?op=join">
				<div class="tooltip-box">
					<h4 class="tooltip-title">회원가입 페이지</h4>
					<p class="tooltip-desc">비 회원인 분은 여기를 클릭해 주세요.</p>
				</div>
			</a>
		</li>
		
		<li>
			<a class="tooltip photo3" href="PageServlet?op=searchInfo">
				<div class="tooltip-box">
					<h4 class="tooltip-title">정보 검색</h4>
					<p class="tooltip-desc">정보 검색 창으로 이동.</p>
				</div>
			</a>
		</li>
	</ul>
</body>
</html>	
