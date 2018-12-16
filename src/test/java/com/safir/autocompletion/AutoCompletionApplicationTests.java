package com.safir.autocompletion;

import com.safir.autocompletion.controller.WordController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoCompletionApplicationTests {

	@Autowired
	private WordController wordController;

	@Test
	public void contextLoads() {
		Assert.assertNotNull (wordController);
	}

}

