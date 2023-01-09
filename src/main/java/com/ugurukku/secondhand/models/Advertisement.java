package com.ugurukku.secondhand.models;

import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Document(indexName = "advertisements")
public class Advertisement {

    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Keyword)
    private String title;

    @Field(type = FieldType.Keyword)
    private String description;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date lastModified;

    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date creationDate;

    public Advertisement() {
    }

    public Advertisement(String id, String title, String description, Double price, Date lastModified, Date creationDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.lastModified = lastModified;
        this.creationDate = creationDate;
    }

    public Advertisement(String title, String description, Double price) {

        this(UUID.randomUUID().toString(),title, description, price, Date.from(Instant.now()), Date.from(Instant.now()));

    }

    public Advertisement(String title, String description, Double price, Date lastModified, Date creationDate) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.lastModified = lastModified;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
