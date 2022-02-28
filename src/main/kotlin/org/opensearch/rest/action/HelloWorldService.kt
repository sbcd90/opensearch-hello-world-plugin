package org.opensearch.rest.action

import org.opensearch.rest.BytesRestResponse
import org.opensearch.rest.RestResponse
import org.opensearch.rest.RestStatus

object HelloWorldService {
    fun buildResponse(name: String?): RestResponse {
        return BytesRestResponse(RestStatus.OK, name);
    }
}