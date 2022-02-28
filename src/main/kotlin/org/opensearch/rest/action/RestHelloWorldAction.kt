package org.opensearch.rest.action

import org.apache.logging.log4j.LogManager
import org.opensearch.client.node.NodeClient
import org.opensearch.rest.BaseRestHandler
import org.opensearch.rest.BytesRestResponse
import org.opensearch.rest.RestHandler
import org.opensearch.rest.RestRequest
import org.opensearch.search.fetch.subphase.FetchSourceContext
import java.util.Collections.unmodifiableList
import java.util.Arrays.asList

internal class RestHelloWorldAction: BaseRestHandler() {
    private val loggerNew = LogManager.getLogger(RestHelloWorldAction::class.java)

    override fun getName(): String {
        return "rest_handler_hello_world"
    }

    override fun routes(): MutableList<RestHandler.Route> {
        return unmodifiableList(
            listOf(RestHandler.Route(RestRequest.Method.GET, "/_plugins/hello_world"),
            RestHandler.Route(RestRequest.Method.POST, "/_plugins/hello_world")))
    }

    override fun prepareRequest(p0: RestRequest, p1: NodeClient): RestChannelConsumer {
        loggerNew.info("hello-world-plugin")
        var name: String? = ""
        if (p0.hasContent()) {
            name = p0.contentParser().mapStrings()["name"]
        }

        val srcContext = FetchSourceContext(true, null, null)
        val handleHelloWorldRequest = HandleHelloWorldRequest(name, RestActions.parseVersion(p0), p0.method(), srcContext)

        return RestChannelConsumer {
                channel ->
            p1.execute(HandleHelloWorldAction.INSTANCE, handleHelloWorldRequest, RestToXContentListener(channel))
        }
    }
}