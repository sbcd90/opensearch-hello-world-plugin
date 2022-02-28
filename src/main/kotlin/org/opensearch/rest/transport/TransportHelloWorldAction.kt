package org.opensearch.rest.transport

import org.opensearch.OpenSearchStatusException
import org.opensearch.action.ActionListener
import org.opensearch.action.get.GetRequest
import org.opensearch.action.get.GetResponse
import org.opensearch.action.support.ActionFilters
import org.opensearch.action.support.HandledTransportAction
import org.opensearch.client.Client
import org.opensearch.cluster.service.ClusterService
import org.opensearch.common.inject.Inject
import org.opensearch.common.settings.Settings
import org.opensearch.common.xcontent.NamedXContentRegistry
import org.opensearch.rest.RestStatus
import org.opensearch.rest.action.HandleHelloWorldAction
import org.opensearch.rest.action.HandleHelloWorldRequest
import org.opensearch.rest.action.HandleHelloWorldResponse
import org.opensearch.tasks.Task
import org.opensearch.transport.TransportService

class TransportHelloWorldAction @Inject constructor(
    transportService: TransportService,
    val client: Client,
    actionFilters: ActionFilters,
    val xContentRegistry: NamedXContentRegistry,
    val clusterService: ClusterService,
    settings: Settings
) : HandledTransportAction<HandleHelloWorldRequest, HandleHelloWorldResponse>(
    HandleHelloWorldAction.NAME, transportService, actionFilters, ::HandleHelloWorldRequest
) {

    override fun doExecute(task: Task, handleHelloWorldRequest: HandleHelloWorldRequest, actionListener: ActionListener<HandleHelloWorldResponse>) {

        actionListener.onResponse(
            handleHelloWorldRequest.name?.let { HandleHelloWorldResponse(it) }
        )
 /*       val getRequest = GetRequest(".hello-world-plugin", handleHelloWorldRequest.name)
            .version(handleHelloWorldRequest.version)
            .fetchSourceContext(handleHelloWorldRequest.srcContext)

        client.threadPool().threadContext.stashContext().use {
            client.get(
                getRequest,
                object : ActionListener<GetResponse> {
                    override fun onResponse(response: GetResponse) {
                        if (!response.isExists) {
                            actionListener.onFailure(
                                OpenSearchStatusException("Monitor not found.", RestStatus.NOT_FOUND)
                            )
                            return
                        }

                        actionListener.onResponse(
                            HandleHelloWorldResponse(response.id)
                        )
                    }

                    override fun onFailure(t: Exception) {
                        actionListener.onFailure(t)
                    }
                }
            )
        }*/
    }
}