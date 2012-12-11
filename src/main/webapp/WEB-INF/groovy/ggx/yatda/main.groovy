package ggx.yatda

if (user) {
	request.title = 'Your todos'
	request.page = 'todos'

	request.todos = datastore.execute {
		select all
		from todo
		where userId == user.userId
	}
} else {
	request.title = 'Welcome to YATDA!'
	request.page = 'landing'
}

forward '/WEB-INF/templates/main.gtpl'