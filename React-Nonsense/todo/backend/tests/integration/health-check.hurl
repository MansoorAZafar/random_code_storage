# Health check
GET http://localhost:8080/
HTTP/1.1 200

# Extra checks to perform against the response
[Asserts]
# We expect to get back a JSON response with { "status": "ok", ... }
jsonpath "$.status" == "ok"