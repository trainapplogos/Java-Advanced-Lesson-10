<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">

<link rel="stylesheet" href="css/login.css">

<title>i-store</title>
</head>
<body>
	<jsp:include page="welcomeToLogin.jsp"></jsp:include>

	<div class="login-page">
		<div class="form">
			<form class="register-form">
				<input class="firstName" type="text" placeholder="first name" /> 
				<input class="lastName " type="text" placeholder="last name" /> 
				<input class="password" type="password" placeholder="password" /> 
				<input class="cpassword" type="password" placeholder="confirm password" />
				<input class="email" type="text" placeholder="email address" />

				<button class="register">create</button>
				<p class="message"> Already registered? <a href="#">Sign In</a> </p>
			</form>
			<form class="login-form">
				<input class="email" type="text" placeholder="email address" />
				<input class="password" type="password" placeholder="password" />
				<button class="login">login</button>
				<p class="message"> Not registered? <a href="#">Create an account</a></p>
			</form>
		</div>

		<div class="alert alert-success alert-dismissible fade show"
			role="alert">
			<b>Success</b> You are registered!
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
		integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>


	<script src="js/login.js"></script>
</body>
</html>


