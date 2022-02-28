package org.opensearch.rest.action

import org.opensearch.action.ActionRequest
import org.opensearch.action.ActionRequestValidationException
import org.opensearch.common.io.stream.StreamInput
import org.opensearch.common.io.stream.StreamOutput
import org.opensearch.rest.RestRequest
import org.opensearch.rest.RestRequest.Method
import org.opensearch.search.fetch.subphase.FetchSourceContext
import java.io.IOException

class HandleHelloWorldRequest: ActionRequest {
    val name: String?
    val version: Long
    val method: Method
    val srcContext: FetchSourceContext?

    constructor(name: String?,
                version: Long,
                method: Method,
                srcContext: FetchSourceContext?
    ): super() {
        this.name = name
        this.version = version
        this.method = method
        this.srcContext = srcContext
    }

    @Throws(IOException::class)
    constructor(sin: StreamInput) : this(
        sin.readString(), // monitorId
        sin.readLong(), // version
        sin.readEnum(Method::class.java), // method
        if (sin.readBoolean()) {
            FetchSourceContext(sin) // srcContext
        } else null
    )

    override fun validate(): ActionRequestValidationException? {
        return null
    }

    override fun writeTo(out: StreamOutput) {
        out.writeString(name)
        out.writeLong(version)
        out.writeEnum(method)
        out.writeBoolean(srcContext != null)
        srcContext?.writeTo(out)
    }
}