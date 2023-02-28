package com.fredo.tx;

import com.fredo.tx.controller.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "classpath:beans.xml")
public class TxTest {

    @Autowired
    private BookController bookController;

    /**
     * 余额不足时买书失败案例
     *  1.不加事务注解，失败，但是库存会减一
     *  2.加上事务注解，失败，库存不减一
     */
    @Test
    public void testBuyBook(){
        bookController.buyBook(1,1);
        System.out.println("执行成功···");
    }
}
