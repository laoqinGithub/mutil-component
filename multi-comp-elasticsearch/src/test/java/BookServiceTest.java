import com.alibaba.fastjson.JSONObject;
import com.df.cn.ElasticsearchApplication;
import com.df.cn.entity.Book;
import com.df.cn.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author qindongfang
 * @date 2023/9/21
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchApplication.class)
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    //新增
    @Test
    public void testSave() {
        Book save = bookService.save(new Book("1003", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017"));
        System.out.println(JSONObject.toJSON(save));
    }

    //删除
    @Test
    public void testDelete() {
        Book book = new Book("1003", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
        bookService.save(book);
        bookService.delete(book);
        Book testBook = bookService.findOne(book.getId());
        System.out.println(JSONObject.toJSON(testBook));
    }

    //查询
    @Test
    public void testFindByTitle() {
        Book book = new Book("1003", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
        bookService.save(book);
        List<Book> byTitle = bookService.findByTitle(book.getTitle());
        System.out.println(JSONObject.toJSON(byTitle));
    }

}
