echo '{"name": "Franta z CMD line"}' | curl -d @- --header "Content-Type:application/json" http://127.0.0.1:8080/api/dummies

curl --header "Content-Type:application/octet-stream" --data-binary README.md http://127.0.0.1:8080/api/uploads
