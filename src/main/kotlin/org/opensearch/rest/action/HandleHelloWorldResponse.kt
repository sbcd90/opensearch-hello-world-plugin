package org.opensearch.rest.action

import org.opensearch.action.ActionResponse
import org.opensearch.common.io.stream.StreamInput
import org.opensearch.common.io.stream.StreamOutput
import org.opensearch.common.xcontent.ToXContent
import org.opensearch.common.xcontent.ToXContentObject
import org.opensearch.common.xcontent.XContentBuilder
import org.opensearch.index.get.GetResult._ID
import java.io.IOException

class HandleHelloWorldResponse: ActionResponse, ToXContentObject {
    var id: String

    constructor(
        id: String
    ) : super() {
        this.id = id
    }

    @Throws(IOException::class)
    constructor(sin: StreamInput): this(
        sin.readString())

    @Throws(IOException::class)
    override fun writeTo(out: StreamOutput) {
        out.writeString(id)
    }

    @Throws(IOException::class)
    override fun toXContent(builder: XContentBuilder, params: ToXContent.Params): XContentBuilder {
        builder.startObject()
            .field(_ID, id)

        return builder.endObject()
    }
}