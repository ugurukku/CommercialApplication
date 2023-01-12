package com.ugurukku.secondhand.advertisement.models;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Entity(name = "advertisement")
@Document(indexName = "advertisements")
public class Advertisement implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Field(type = FieldType.Keyword)
    private String title;

    @Field(type = FieldType.Keyword)
    private String description;

    //    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private LocalDateTime lastModified;

    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private LocalDateTime creationDate;

    private Long userId;

    @ElementCollection
    private Set<String> hashtags = new HashSet<>();

    public Advertisement() {
    }


    public Advertisement(String title,
                         String description,
                         Double price,
                         Long userId,
                         Set<String> hashtags) {

        this(title, description, price, LocalDateTime.now(), LocalDateTime.now(), userId, hashtags);

    }

    public Advertisement(String title,
                         String description,
                         Double price,
                         LocalDateTime lastModified,
                         LocalDateTime creationDate,
                         Long userId,
                         Set<String> hashtags) {

        this.title = title;
        this.description = description;
        this.price = price;
        this.lastModified = lastModified;
        this.creationDate = creationDate;
        this.userId = userId;
        this.hashtags = hashtags;
    }

    public Advertisement(String id,
                         String title,
                         String description,
                         Double price,
                         LocalDateTime lastModified,
                         LocalDateTime creationDate,
                         Long userId,
                         Set<String> hashtags) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.lastModified = lastModified;
        this.creationDate = creationDate;
        this.userId = userId;
        this.hashtags = hashtags;
    }

    public String getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }


    public Double getPrice() {
        return price;
    }

    public Long getUserId() {
        return userId;
    }


    public LocalDateTime getLastModified() {
        return lastModified;
    }


    public LocalDateTime
    getCreationDate() {
        return creationDate;
    }


    public Set<String> getHashtags() {
        return hashtags;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", lastModified=" + lastModified +
                ", creationDate=" + creationDate +
                ", userId=" + userId +
                ", hashtags=" + hashtags +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getPrice(), that.getPrice()) && Objects.equals(getLastModified(), that.getLastModified()) && Objects.equals(getCreationDate(), that.getCreationDate()) && Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getHashtags(), that.getHashtags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getPrice(), getLastModified(), getCreationDate(), getUserId(), getHashtags());
    }
}
