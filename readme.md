# Cardboardbox Application

## How to build
Check out the source code  and run the following command:
```bash
./mwnw clean verify
```

## To run the Application


Start the application
```bash
./mvnw spring-boot:run 
```

and make a request with `curl`

### return matching box no (2)

```bash
curl  -w '\n' --request POST \
  --url http://localhost:8080/api/cardboard/match \
  --header 'Content-Type: application/json' \
  --data '[
    {
        "quantity": 6,
        "articleId": 7
    },
    {
        "quantity": 2,
        "articleId": 4
    },
    {
        "quantity": 4,
        "articleId": 1
    }
]'

2

```

### when no box fits, return message to client

```bash
curl  -w '\n' --request POST \
--url http://localhost:8080/api/cardboard/match \
--header 'Content-Type: application/json' \
--data '[
{
"quantity": 12,
"articleId": 7
},
{
"quantity": 100,
"articleId": 1
}
]'

Upphämtning krävs
```
