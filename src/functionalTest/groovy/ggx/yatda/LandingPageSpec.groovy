package ggx.yatda

import geb.spock.GebSpec
import ggx.yatda.page.LandingPage
import ggx.yatda.page.TodosPage

class LandingPageSpec extends GebSpec {

	void 'landing page can be reached'() {
		when:
		toAt LandingPage

		then:
		with navbar, {
			!userGreeting
			!logout
		}
	}

	void 'user can log in'() {
		given:
		to LandingPage

		when:
		login.click()

		and:
		email = 'user@ggx.org'
		loginButton.click()

		then:
		at TodosPage
	}
}
