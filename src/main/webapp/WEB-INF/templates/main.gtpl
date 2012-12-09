<!DOCTYPE html>
<html>
	<head>
		<title>${request.title}</title>
		<link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="/css/style.css" rel="stylesheet" media="screen">
	</head>
	<body>
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container">
					<a class="brand" href="#">YATDA</a>
					<% if (user) { %>
						<span>Welcome <strong>${user.nickname}</strong>!</span>
                        <a class="btn btn-danger pull-right" href="${users.createLogoutURL('/')}"><i class="icon-off icon-white"></i> Log out</a>
					<% } %>
				</div>
			</div>
		</div>

		<div class="container page-contents">
			<% include "/WEB-INF/pages/${request.page}.gtpl" %>
		</div>
		<script src="/js/jquery.min.js"></script>
		<script src="/js/bootstrap.min.js"></script>
	</body>
</html>