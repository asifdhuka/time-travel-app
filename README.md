# Time Travel Machine
This is a rest app exposing a post call to submit the travel details.

## Run and Test Application

### Installation
```mvn clean install```

### Start application
```mvn spring-boot:run```

This will start the application on http://localhost:9090

In order to call the endpoint, please use full path http://localhost:9090/timeTravel/timeTravelMessage
and request body should have following:
```{"pgi": "AS21212", "place": "UK", "date": "2021-03-01"}```

### Run Tests

```mvn test```

Please reach out to me if you need to know more details on implementation.