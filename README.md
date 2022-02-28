opensearch-hello-world-plugin
=============================

This is a simple AWS OpenSearch plugin just for use as a skeleton for kotlin.

Testing
=======

```
curl -XPOST 'http://localhost:9200/_plugins/hello_world' -H 'Content-Type: application/json' -d '{"name":"sbcd90"}' -u 'admin:admin' --insecure

{"_id":"sbcd90"}%  
```