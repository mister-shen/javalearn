package com.shenrs.elastic.repository;

import com.shenrs.elastic.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author shenrs
 * @Description
 * @create 2019-04-25 15:40
 **/
public interface BookRepository extends ElasticsearchRepository<Book, Integer> {

    List<Book> findByBookNameLike(String bookName);
}
