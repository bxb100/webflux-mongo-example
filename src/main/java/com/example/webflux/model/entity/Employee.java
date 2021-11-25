package com.example.webflux.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Xiaobo Bi (869384236@qq.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "employee")
public class Employee {

    @Id
    private int id;
    private String name;
    private String role;
}
