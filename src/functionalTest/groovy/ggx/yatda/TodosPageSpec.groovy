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

	private String retrieveUserId() {
		browser.driver.manage().cookies.find { it.name == 'dev_appserver_login' }.value.split(':').last()
	}

	private void setupTodos() {
		def currentUserId = retrieveUserId()
		setupData {
			todo {
				text = 'first'
				userId = currentUserId
			}
			todo {
				text = 'second'
				userId = currentUserId
			}
			todo {
				text = 'not mine'
				userId = currentUserId + 'a'
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

	void 'users see the add todos message if they have none'() {
		when:
		loginTo 'user@ggx.org', false, TodosPage

		then:
		addTodosPromo
		!todos
	}

	@ModifiesDatastore
	void 'users see their todos'() {
		given:
		loginTo 'user@ggx.org', false, TodosPage
		setupTodos()

		when:
		to TodosPage

		then:
		!addTodosPromo
		todos*.text == ['first', 'second']
	}

	@ModifiesDatastore
	void 'users can remove their todos'() {
		given:
		loginTo 'user@ggx.org', false, TodosPage
		setupTodos()
		to TodosPage

		when:
		todos(0).delete.click()

		then:
		todos*.text == ['second']
	}

	@ModifiesDatastore
	void 'can add a todo'() {
		given:
		loginTo 'user@ggx.org', false, TodosPage
		setupTodos()
		to TodosPage

		when:
		newTodo = 'new todo'
		newTodoSubmit.click()

		then:
		todos*.text == ['first', 'second', 'new todo']
	}
}
