package org.opensearch.rest

import org.opensearch.action.ActionRequest
import org.opensearch.action.ActionResponse
import org.opensearch.cluster.metadata.IndexNameExpressionResolver
import org.opensearch.cluster.node.DiscoveryNodes
import org.opensearch.common.settings.ClusterSettings
import org.opensearch.common.settings.IndexScopedSettings
import org.opensearch.common.settings.Settings
import org.opensearch.common.settings.SettingsFilter
import org.opensearch.plugins.ActionPlugin
import org.opensearch.plugins.Plugin
import org.opensearch.rest.action.HandleHelloWorldAction
import org.opensearch.rest.action.RestHelloWorldAction
import org.opensearch.rest.transport.TransportHelloWorldAction
import java.util.Collections.singletonList
import java.util.function.Supplier

class HelloWorldPlugin: ActionPlugin, Plugin() {

    override fun getRestHandlers(
        settings: Settings?,
        restController: RestController?,
        clusterSettings: ClusterSettings?,
        indexScopedSettings: IndexScopedSettings?,
        settingsFilter: SettingsFilter?,
        indexNameExpressionResolver: IndexNameExpressionResolver?,
        nodesInCluster: Supplier<DiscoveryNodes>?
    ): MutableList<RestHandler> {
        return singletonList(RestHelloWorldAction());
    }

    override fun getActions(): MutableList<ActionPlugin.ActionHandler<out ActionRequest, out ActionResponse>> {
        return singletonList(
            ActionPlugin.ActionHandler(HandleHelloWorldAction.INSTANCE, TransportHelloWorldAction::class.java)
        )
    }
}