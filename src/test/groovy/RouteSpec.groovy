import groovyx.gaelyk.spock.GaelykRoutingSpec

import static groovyx.gaelyk.routes.RedirectionType.FORWARD

class RouteSpec extends GaelykRoutingSpec {

	void setup() {
		routing 'routes.groovy'
	}

	void 'root forwards to the main groovlet'() {
		expect:
		with get('/'), {
			matches
			redirectionType == FORWARD
			destination == '/ggx/yatda/main.groovy'
		}
	}

	void 'todo removal'() {
		expect:
		with post('/todo/remove/2'), {
			matches
			redirectionType == FORWARD
			destination == '/ggx/yatda/todo/remove.groovy?id=2'
		}
	}

	void 'adding a todo'() {
		expect:
		with post('/todo/add'), {
			matches
			redirectionType == FORWARD
			destination == '/ggx/yatda/todo/add.groovy'
		}
	}
}
