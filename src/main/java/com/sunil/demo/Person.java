package com.sunil.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
public class Person {
    String name;
    // Serialize long to UTC ISO 8601 — Date and time STRING
    @JsonSerialize(using = CustomEpochSerializer.class)
    Long created;

    public static void main(String[] args) throws Exception{
        Person p = new Person();
        p.created = 1639010825l;
        p.name ="aaa";

        /*Instant instant = Instant.ofEpochSecond(p.created);
        System.out.println(instant);
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, ZoneOffset.UTC);
        System.out.println(zdt);
        */

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(p);
        System.out.println(json);
    }

}
