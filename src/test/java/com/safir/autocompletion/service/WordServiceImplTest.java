package com.safir.autocompletion.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WordServiceImplTest {
    @Autowired
    private WordService wordService;

    @Test
    public void addWordTest() {
        Assert.assertEquals (true,wordService.addWord ("kota"));
    }

    @Test
    public void suggestWordTest(){
        wordService.addWord ("kota");
        List<String> words = new ArrayList<> ();
        words.add ("kota");
        Assert.assertEquals (words, wordService.suggestWord ("k", 2));
        wordService.addWord ("kolkata");
        words.add ("kolkata");
        Assert.assertEquals (words, wordService.suggestWord ("k", 2));
        Assert.assertEquals (new ArrayList<String> (), wordService.suggestWord ("hy", 2));
    }

}
