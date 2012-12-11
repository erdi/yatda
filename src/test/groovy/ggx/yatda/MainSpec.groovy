package ggx.yatda

import com.google.appengine.api.users.User
import groovyx.gaelyk.spock.ConventionalGaelykUnitSpec
import spock.lang.Unroll
import groovyx.gaelyk.functional.datastore.DataBuilder

class MainSpec extends ConventionalGaelykUnitSpec {

	DataBuilder dataBuilder = new DataBuilder()

	@Unroll
	void 'should show #page page when user is #scenario in'() {
		given:
		main.user = user

		when:
		main.get()

		then:
		with main, {
			request.title == title
			request.page == page
			forward == '/WEB-INF/templates/main.gtpl'
		}

		where:
		user             | page      | title
		null             | 'landing' | 'Welcome to YATDA!'
		new User('', '') | 'todos'   | 'Your todos'

		scenario = user ? 'logged' : 'not logged'
	}

	void 'todos are retrieved from the datastore'() {
		given:
		main.user = new User('', '', '1')
		dataBuilder.setupData {
			todo {
				text = 'first'
				userId = '1'
			}
			todo {
				text = 'second'
				userId = '1'
			}
			todo {
				text = 'not mine'
				userId = '2'
			}
		}

		when:
		main.get()

		then:
		main.request.todos*.text == ['first', 'second']
	}
}
