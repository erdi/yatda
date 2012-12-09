package ggx.yatda.todo

import com.google.appengine.api.users.User
import groovyx.gaelyk.functional.datastore.DataBuilder
import groovyx.gaelyk.spock.ConventionalGaelykUnitSpec

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED

class RemoveSpec extends ConventionalGaelykUnitSpec {

	private void setupTodos() {
		new DataBuilder().setupData {
			todo(123) {
				text = 'first'
				userId = '1'
			}
			todo(321) {
				text = 'second'
				userId = '1'
			}
			todo(555) {
				text = 'not mine'
				userId = '2'
			}
		}
	}

	void setup() {
		setupTodos()
		remove.user = new User('', '', '1')
	}

	void 'removing a todo'() {
		given:
		remove.params.id = '321'

		when:
		remove.post()

		then:
		remove.forward == '/ggx/yatda/main.groovy'

		and:
		datastore.execute {
			select all
			from todo
		}*.text == ['first', 'not mine']
	}

	void 'can only remove own todos'() {
		given:
		remove.params.id = '555'

		when:
		remove.post()

		then:
		1 * remove.response.setStatus(SC_UNAUTHORIZED)

		and:
		datastore.execute {
			select count
			from todo
		} == 3
	}
}
