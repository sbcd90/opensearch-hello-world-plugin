package org.opensearch.rest.action

import org.opensearch.action.ActionType

class HandleHelloWorldAction private constructor(): ActionType<HandleHelloWorldResponse>(NAME, ::HandleHelloWorldResponse) {
    companion object {
        val INSTANCE = HandleHelloWorldAction()
        val NAME = "cluster:admin/opendistro/helloworld/get"
    }
}