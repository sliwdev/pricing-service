## Pricing Service Assessment Task

### Running the app

To create native image and run it in a Docker container:
1) Have Docker daemon running on your host.
                                      
2) Execute
```
./gradlew clean dockerBuildNative
```
(might take a few minutes)
or
```
./gradlew clean dockerBuild
```
3) Execute
```
docker run -p 8080:8080 -d pricing-service
```

You can also run the app without a container by executing:
```
./gradlew clean run
```

In both cases after that you'll be able to play with the API, example:
```
curl --request GET 'http://localhost:8080/quotes?productId=490f7b77-ecc5-44df-9474-5b119654b65f&amount=3'
```

### Running the tests
Execute
```
./gradlew clean test
```

### Assumptions
Price is a base amount of money in a supported currency per single unit (piece) of product at given point in time.

Price management (creation, validation, scheduling, ...) is out of scope as the point of the interest is calculation of 
a selling price ("quote") out of base price, amount and available discounts, and exposure via REST.

Discount policies configuration is possible via properties. For brevity I came up with some sample logic based on given
requirements, but I can imagine a whole variety of criteria to base discounts upon, closer to real life scenarios, which 
I can discuss later.

I tried to fulfill requirements with minimal amount of effort hence a number of elements necessary for production-readiness 
is purposely omitted (that I can also discuss later).

Enjoy :)

