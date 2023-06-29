package com.example.sampleoraclespring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    private String id;
    private String name;
}
