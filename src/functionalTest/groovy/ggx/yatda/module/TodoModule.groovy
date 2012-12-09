package ggx.yatda.module

import geb.Module

class TodoModule extends Module {
	static content = {
		text { $().text() }
		
		delete { $('button') }
	}
}
