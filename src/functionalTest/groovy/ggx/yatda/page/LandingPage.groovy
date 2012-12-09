package ggx.yatda.page

import geb.Page
import groovyx.gaelyk.functional.geb.page.LoginPage
import ggx.yatda.module.NavbarModule

class LandingPage extends Page {

	static at = { title == 'Welcome to YATDA!' }

	static content = {
		navbar { module NavbarModule }

		login(to: LoginPage) { $('a', text: iContains('log in')) }
	}
}
