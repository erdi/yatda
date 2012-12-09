package ggx.yatda

import geb.spock.GebSpec
import ggx.yatda.page.LandingPage
import ggx.yatda.page.TodosPage
import groovyx.gaelyk.functional.geb.LoginCategory
import spock.util.mop.Use

@Use(LoginCategory)
class TodosPageSpec extends GebSpec {

	void 'logged in user state'() {
		when:
		loginTo 'user@ggx.org', false, TodosPage

		then:
		with navbar, {
			userGreeting
			userName == 'user@ggx.org'

			logout
		}
	}

	void 'user can log out'() {
		given:
		loginTo 'user@ggx.org', false, TodosPage

		when:
		navbar.logout.click()

		then:
		at LandingPage
	}
}
