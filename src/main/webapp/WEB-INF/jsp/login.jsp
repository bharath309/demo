
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OTis : Application : Login Form</title>


<link rel='stylesheet prefetch'
	href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css'>
<link rel="stylesheet" href="../css/style.css">


</head>

<body>
	<div class="wrapper">
		<form method="POST" action="login" class="form-signin">
			<h2 class="form-signin-heading">Please login</h2>
			<input type="text" class="form-control" name="username"
				placeholder="Username" required="" autofocus="" /> <input
				type="password" class="form-control" name="password"
				placeholder="Password" required="" />
			<!--  <label class="checkbox">
        <input type="checkbox" value="remember-me" id="rememberMe" name="rememberMe"> Remember me
      </label> -->
			<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
		</form>
	</div>


</body>
</html>
