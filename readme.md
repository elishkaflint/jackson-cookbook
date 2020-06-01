# Jackson Cookbook

This repo comes about after:
 - tweaking Jackson annotations here and there without really understanding everything that they do 
 - wrestling with its errors more times than is cool
 - an hour or two spent writing a custom deserialiser for functionality already included in the tool (there's an important 
 lesson in there for a mid-level dev, which I've learnt the hard way) 

It leans heavily on:
- https://www.baeldung.com/jackson-annotations

## Overview

Jackson has a databinding API which converts JSON to and from a Java object (POJO).

There are 2 types of databinding:
1. **Simple** -  converts JSON to and from Java Maps, Lists, Strings, Numbers, Booleans and null objects.
2. **Full** - converts JSON to and from any Java type

Simple databinding:

| Json Type         | Java Type                     |
| ----------------- | ----------------------------- |
| object            | LinkedHashMap<String,Object>  |
| array	            | ArrayList<Object>             | 
| string            | String                        | 
| complete number   | Integer, Long or BigInteger   |  
| fractional number | Double / BigDecimal           |
| true or false     | Boolean                       | 
| null              | null                          | 

## Dependency

```
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.10.4</version>
    </dependency>
```

The databind dependency brings in 3 Jackson libraries:
- jackson-annotations
- jackson-core
- jackson-databind

## Deserialisation

aka "destringify" 

(**Json -> Java  Object**)

Examples are in `JacksonDeserializationTest`

###  Simple

When deserialising a straightforward json string into a Java object,
Jackson needs one of 3 things to be in place:

1. An implicit default constructor (available when no other constructor is explicitly defined)
2. An explicit default constructor (if an args constructor is already defined)
3. `@JsonProperty` annotations on the object's fields

```
public MovieSimple(@JsonProperty("title") String title, @JsonProperty("releaseYear") int releaseYear) {
    this.title = title;
    this.releaseYear = releaseYear;
}
```

Otherwise, Jackson will throw an `InvalidDefinitionException`:
```
com.fasterxml.jackson.databind.exc.InvalidDefinitionException: 
Cannot construct instance of `models.MovieSimple` 
(no Creators, like default construct, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
```

### `@JsonProperty`

If the json includes a field with a different key than the Java object field, use `@JsonProperty`:
```
public MovieWithJsonProperty(@JsonProperty("name") String title) {
    this.title = title;
}
``` 
> Note that this appears to work without `@JsonCreator` in later versions of Jackson, despite some Baeldung / Stack Overflow advice.

### `@JsonInject`

If the json is missing a field which we want to set in the deserialised object, use @JsonInject to set a field by type 
or value via the ObjectMapper.



