package com.danbro.search.entity;

import java.io.Serializable;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Classname User
 * @Description TODO
 * @Date 2021/2/15 17:53
 * @Created by Administrator
 */
@Data
@Document(indexName = "banks", shards = 1, replicas = 0)
public class Account implements Serializable {
    @Id
    private String id;

    @Field(type = FieldType.Long)
    private Long account_number;

    @Field(type = FieldType.Keyword)
    private String address;

    @Field(type = FieldType.Long)
    private Long age;

    @Field(type = FieldType.Long)
    private Long balance;

    @Field(type = FieldType.Keyword)
    private String city;

    @Field(type = FieldType.Keyword)
    private String email;

    @Field(type = FieldType.Keyword)
    private String employer;

    @Field(type = FieldType.Keyword,value = "firstname")
    private String firstname;

    @Field(type = FieldType.Keyword,value = "lastname")
    private String lastname;

    @Field(type = FieldType.Keyword)
    private String gender;

    @Field(type = FieldType.Keyword)
    private String state;

}
