POST http://localhost:8080/v1/todo
Content-Type: application/json;

[BasicAuth]
admin:admin
`{"name": "Gnimmy", text: "Holas amigos, I have to finish my spanish lessons, I am gnim gnim"}`

HTTP/1.1 200
[Assets]
jsonpath "$.status" == "ok"
jsonpath "$.id" isString