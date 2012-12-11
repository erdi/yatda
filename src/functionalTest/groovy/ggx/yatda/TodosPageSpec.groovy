package ggx.yatda

import geb.spock.GebSpec
import ggx.yatda.page.LandingPage
import ggx.yatda.page.TodosPage
import groovyx.gaelyk.functional.geb.LoginCategory
import spock.util.mop.Use
import groovyx.gaelyk.functional.geb.DataSetupCategory
import groovyx.gaelyk.functional.spock.ModifiesDatastore

@Use([LoginCategory, DataSetupCategory])
class TodosPageSpec extends GebSpec {

	private String retrieveLoggedInUserId() {
		browser.driver.manage().cookies.find { it.name == 'dev_appserver_login' }.value.split(':').last()
	}

	private void setupTodos() {
		def currentId = retrieveLoggedInUserId()
		setupData {
			todo {
				text = 'first'
				userId = currentId
			}
			todo {
				text = 'second'
				userId = currentId
			}
			todo {
				text = 'not mine!'
				userId = currentId + 's'
			}
		}
	}

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

	void 'user sees no todos if they have none'() {
		when:
		loginTo 'user@ggx.org', false, TodosPage

		then:
		addTodosPromo
		!todos
	}

	@ModifiesDatastore
	void 'user sees todos if they have some'() {
		given:
		loginTo 'user@ggx.org', false, TodosPage
		setupTodos()

		when:
		to TodosPage

		then:
		!addTodosPromo
		todos*.text == ['first', 'second']
	}
}
