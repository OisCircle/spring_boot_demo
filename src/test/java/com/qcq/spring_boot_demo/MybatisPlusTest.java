package com.qcq.spring_boot_demo;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.qcq.spring_boot_demo.entity.Bird;
import com.qcq.spring_boot_demo.entity.User;
import com.qcq.spring_boot_demo.mapper.BirdMapper;
import com.qcq.spring_boot_demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisPlusTest {
	@Autowired
	BirdMapper birdMapper;
	@Autowired
	UserService userService;
	@Test
	public void wrapper() {
		List<Bird> birdList = birdMapper.selectPage(new Page<Bird>(1, 2), new EntityWrapper<>());
		System.out.println(birdList);
		Page<Bird> birdPage=new Page<>();

		birdPage.setRecords(birdList);
		System.out.println(birdPage);
 	}

	@Test
	public void warpper2() {
		EntityWrapper<User> ew = new EntityWrapper<User>();
		ew.where("user_name={0}", "'zhangsan'").and("id=1")
				.orNew("user_status={0}", "0").or("status=1")
				.notLike("user_nickname", "notvalue")
				.andNew("new=xx").like("hhh", "ddd")
				.andNew("pwd=11").isNotNull("n1,n2").isNull("n3")
				.groupBy("x1").groupBy("x2,x3")
				.having("x1=11").having("x3=433")
				.orderBy("dd").orderBy("d1,d2");
		System.out.println(ew.getSqlSegment());



	}
}
