package com.shenrs.elastic;

import com.shenrs.elastic.bean.Artcle;
import com.shenrs.elastic.bean.Book;
import com.shenrs.elastic.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {


	// 测试jest
	@Autowired
	private JestClient jestClient;

	@Test
	public void contextLoads() {
		// 1.给Es中索引（保存）一个文档
		Artcle artcle = new Artcle();
		artcle.setId(1);
		artcle.setAuthor("lisi");
		artcle.setContent("dkdkkd");
		artcle.setTitle("消息");

		// 构建一个索引功能
		Index index = new Index.Builder(artcle).index("shenrs").type("artcle").build();

		try {
			// 执行
			jestClient.execute(index);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	// 测试搜索
	@Test
	public void search() throws IOException {
		// 查询表达式
		String json  = "{" +
				"	\"query\" : {" +
				"		\"match\" : {" +
				"			\"content\" :  \"dkdkkd\"" +
				"		}" +
				"	}" +
				"}";
		// 构建搜索功能
		Search search = new Search.Builder(json).addIndex("shenrs").addType("artcle").build();

		// 执行
		SearchResult execute = jestClient.execute(search);
		System.out.println(execute.getJsonString());
	}


	// 测试spring data elasticsearch

	@Autowired
	private BookRepository bookRepository;


	@Test
	public void add(){
		Book book = new Book();
		book.setId(1);
		book.setBookName("西游记");
		book.setAuthor("吴承恩");
		bookRepository.index(book);
	}

	@Test
	public void dataSearch(){
		for (Book book : bookRepository.findByBookNameLike("游")) {
			System.out.println(book);
		}

	}

}
