package ggx.yatda.todo

import com.google.appengine.api.users.User
import groovyx.gaelyk.spock.ConventionalGaelykUnitSpec

class AddSpec extends ConventionalGaelykUnitSpec {

	void 'add a todo'() {
		given:
		add.user = new User('', '', '123')
		add.params.text = 'new todo'

		when:
		add.post()

		then:
		add.forward == '/ggx/yatda/main.groovy'

		and:
		def todo = datastore.execute {
			select single
			from todo
		}

		todo.text == 'new todo'
		todo.userId == '123'
	}
}
