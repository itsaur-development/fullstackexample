echo '{"name": "test", "lastname": "test1"}' | curl -d @- -XPOST --header "Content-Type:application/json" http://localhost:8080/users

curl -XGET http://localhost:8080/users

echo '{"name": "test1111", "lastname": "test1111111111"}' | curl -d @- -XPUT --header "Content-Type:application/json" http://localhost:8080/users/33339e88-c16f-4454-ba58-4fd9fb979d46