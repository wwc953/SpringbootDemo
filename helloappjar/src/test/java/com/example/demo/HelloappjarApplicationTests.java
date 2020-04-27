package com.example.demo;

import com.example.demo.dao.MyTestMapper;
import com.example.demo.model.MyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes={HelloappjarApplication.class})// 指定启动类
@SpringBootTest
public class HelloappjarApplicationTests {

	@Autowired
    MyTestMapper myTestMapper;

	@Test
	public void insert() {
		MyTest m = new MyTest();
		m.setName("hahhah");
		m.setDatas(new Date());
		m.setDescs("desc");
        int insert = myTestMapper.insert(m);
        System.out.println("insert:"+insert+",,,"+m.getId());
    }

}
