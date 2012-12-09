package ggx.yatda.todo

import static com.google.appengine.api.datastore.KeyFactory.createKey
import static java.lang.Long.parseLong
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED

def todo = createKey('todo', parseLong(params.id)).get()
if (todo.userId == user.userId) {
	todo.delete()
	forward '/ggx/yatda/main.groovy'
} else {
	response.status = SC_UNAUTHORIZED
}

