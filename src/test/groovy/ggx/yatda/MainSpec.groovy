package ggx.yatda

import com.google.appengine.api.users.User
import groovyx.gaelyk.spock.ConventionalGaelykUnitSpec
import spock.lang.Unroll

class MainSpec extends ConventionalGaelykUnitSpec {

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
}
