package ggx.yatda.page

import geb.Page
import ggx.yatda.module.NavbarModule

class TodosPage extends Page {

	static at = { title == 'Your todos' }

	static content = {
		navbar { module NavbarModule }
	}
}
