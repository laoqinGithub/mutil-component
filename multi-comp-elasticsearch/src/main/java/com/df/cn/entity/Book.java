package com.df.cn.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author qindongfang
 * @date 2023/9/21
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "book_index", type = "book")
public class Book {

    @Id
    private String id;

    private String title;

    private String author;

    private String releaseDate;

}
