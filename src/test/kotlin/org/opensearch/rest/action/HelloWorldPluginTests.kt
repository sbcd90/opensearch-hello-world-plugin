package org.opensearch.rest.action

import org.hamcrest.CoreMatchers.equalTo
import org.opensearch.test.OpenSearchTestCase

class HelloWorldPluginTests: OpenSearchTestCase() {
    fun `testBuildHelloWorldResponse`() {
        assertThat(HelloWorldService.buildResponse("OpenSearch").content().utf8ToString(),
            equalTo("OpenSearch"))
    }
}