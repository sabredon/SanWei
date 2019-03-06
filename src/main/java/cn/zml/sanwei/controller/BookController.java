package cn.zml.sanwei.controller;

import cn.zml.sanwei.common.SanWeiRes;
import cn.zml.sanwei.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.zml.sanwei.common.Constant.SYSTEM_ERROR;

/**
 * 图书控制层
 * @author  fanyidong
 * @date Created in 2018/11/29
 */
@RestController
public class BookController {

    private static Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bookService;

    @GetMapping("/getBooks")
    public SanWeiRes getBoos() {
        try {
            return SanWeiRes.success(bookService.queryAnyBooks());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return SanWeiRes.failed(SYSTEM_ERROR);
        }
    }

    @PostMapping("/insertBooks")
    @Transactional(rollbackFor = Exception.class)
    public SanWeiRes insertBooks(String filePath) {
        try {
            bookService.insertBooks(filePath);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return SanWeiRes.failed(SYSTEM_ERROR);
        }
        return SanWeiRes.success();
    }

    @GetMapping("/insertOthers")
    @Transactional(rollbackFor = Exception.class)
    public SanWeiRes insertOthers() {
        try {
            return SanWeiRes.success(bookService.insertDetailAndComment());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return SanWeiRes.failed(SYSTEM_ERROR);
        }
    }
}
