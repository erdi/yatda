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
}
