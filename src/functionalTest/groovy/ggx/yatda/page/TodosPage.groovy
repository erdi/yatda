package ggx.yatda.page

import geb.Page
import ggx.yatda.module.NavbarModule
import ggx.yatda.module.TodoModule

class TodosPage extends Page {

	static at = { title == 'Your todos' }

	static content = {
		navbar { module NavbarModule }

		addTodosPromo(required: false) { $('h2') }
		todos(required: false) { index -> moduleList TodoModule, $('.todo'), index }

		newTodo { $('input', name: 'text') }
		newTodoSubmit{ newTodo.next() }
	}
}
