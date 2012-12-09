package ggx.yatda

if (user) {
	request.title = 'Your todos'
	request.page = 'todos'
} else {
	request.title = 'Welcome to YATDA!'
	request.page = 'landing'
}

forward '/WEB-INF/templates/main.gtpl'