````
POST: http://localhost:8080/api/task-manager
Request:
{
    "title": "New task",
    "description": "Super task",
    "date_from": "2023-05-26T11:20:13.675+00:00",
    "date_to": "2023-05-26T11:20:13.675+00:00"
}
Response:
{
    "title": "New task",
    "description": "Super task",
    "date_from": "2023-05-26T11:20:13.675+00:00",
    "date_to": "2023-05-26T11:20:13.675+00:00",
    "id": 1
}
````
````
GET: http://localhost:8080/api/task-manager
Response:
[
    {
        "id": 1,
        "date_from": "2023-05-26T11:20:10.675+00:00",
        "date_to": "2023-05-26T11:20:13.675+00:00",
        "title": "New task",
        "description": "Super task"
    },
    {
        "id": 2,
        "date_from": "2023-05-27T11:20:10.675+00:00",
        "date_to": "2023-05-27T11:20:13.675+00:00",
        "title": "New task",
        "description": "Super task"
    }
]
````
````
PUT: http://localhost:8080/api/task-manager/2
Request:
{
    "title": "New task 123",
    "description": "Super task 321",
    "date_from": "2023-05-26T11:20:13.675+00:00",
    "date_to": "2023-05-26T11:20:13.675+00:00"
}
Response:
{
    "id": 2,
    "date_from": "2023-05-26T11:20:13.675+00:00",
    "date_to": "2023-05-26T11:20:13.675+00:00",
    "title": "New task 123",
    "description": "Super task 321"
}
````
````
DELETE: http://localhost:8080/api/task-manager/2
Response:
{
    "result": true
}
````
````
Error Response:
{
    "errorText": "Task not found by id",
    "errorCode": 422
}

{
    "errorText": "Date from and date to - incorrect",
    "errorCode": 422
}

{
    "errorText": "The assignment already exists for this date time",
    "errorCode": 422
}
````