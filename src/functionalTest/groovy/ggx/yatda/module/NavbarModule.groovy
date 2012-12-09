package ggx.yatda.module

import geb.Module

class NavbarModule extends Module {

	static base = { $('.navbar') }

	static content = {
		userGreeting(required: false) { $('span') }
		userName { userGreeting.find('strong').text() }

		logout(required: false) { $('a', text: iContains('log out')) }
	}
}
