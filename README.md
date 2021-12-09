### Simple API to test epoch long value to be converted into UTC ISO 8601 — Date and time format

here is simple application where a person object with name and created (epoch long value) attribute is returned from the api
_expectation of the api response is to present person created value in UTC ISO 8601 — Date and time format String value (yyyy-MM-ddTHH:mm:ssZ)_ 

Below is the code involved


```java

@Data
public class Person {
    String name;
    
    // Serialize long to UTC ISO 8601 — Date and time STRING
    @JsonSerialize(using = CustomEpochSerializer.class)
    Long created;
}

public class CustomEpochSerializer extends StdSerializer<Long> {
    public CustomEpochSerializer() {
        this(null);
    }
    public CustomEpochSerializer(Class<Long> t) {
        super(t);
    }

    @Override
    public void serialize (Long value, JsonGenerator jsonGenerator, SerializerProvider arg)
            throws IOException, JsonProcessingException {
        Instant instant = Instant.ofEpochSecond(value);
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, ZoneOffset.UTC);
        jsonGenerator.writeString(zdt.toString());
    }
}

@RestController
public class AppController {
    @GetMapping("/person")
    public Person person() {
        Person person = new Person();
        person.setName("hello");
        person.setCreated(1639010825l); // epoch long value
        return person;
    }
}
```

```json

Output of  API REQUEST GET  /person
        {   
            "name":"hello",
            "created":"2021-12-09T00:47:05Z"
        }
        
note : if there was NO @JsonSerialize for Person object : created property then long value will be returned as-is
        {
            "name":"hello",
            "created":1639010825
        }
```
## Spring would convert the respone of Person java object to JSON API response




