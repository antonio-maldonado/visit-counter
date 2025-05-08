# Visit Counter

## Description
The system you work with on a daily basis runs multiple microservices.
You have been asked to prepare an aggregation that represents the number
of user visits to all microservices, for use by data analysts.

The architect you're working with has already designed the API, which consists
of a single class: VisitCounter. VisitCounter has a single method, count().

It returns 
```java 
Map<Long, Long> 
``` this map should contain the number of visits
by the user with a given ID.

This method accepts an array of 
```java 
Map<String, UserStats>...
```
visits. Every map
represents the total number of visits per user to a given microservice.

There are some problems, however:

The map key, which is a String, should be parseable to Long – but it may not be. You must skip any such faulty entries.

For some keys, UserStats may be null. You must skip any such faulty entries.

UserStats has a single field, visitCount, of type 
```java 
Optional<Long>. 
```
A getter for this field is also implemented. This field will never be null; however, it might be empty. You must skip any such faulty entries.

Remember that you may receive some invalid input: null, empty maps, and so on. Handle it all appropriately and return an empty map.

Assumptions

The UserStats class is already implemented. You do not have to write the implementation of this class as a part of your solution.

Available packages and libraries

You may use Java 8 Streams API to implement your solution.