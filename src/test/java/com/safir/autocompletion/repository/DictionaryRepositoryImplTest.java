package com.safir.autocompletion.repository;

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
public class DictionaryRepositoryImplTest {
    @Autowired
    private DictionaryRepository dictionaryRepository;

    @Test
    public void addWordTest() {
        Assert.assertEquals (true,dictionaryRepository.addWord ("kolkata"));
    }

    @Test
    public void suggestWordTest(){
        dictionaryRepository.addWord ("kolkata");
        List<String> words = new ArrayList<> ();
        words.add ("kolkata");
        Assert.assertEquals (words, dictionaryRepository.suggestWord ("k", 2));

        dictionaryRepository.addWord ("kota");
        words.add (0, "kota");
        Assert.assertEquals (words, dictionaryRepository.suggestWord ("k", 2));

        Assert.assertEquals (new ArrayList<String> (), dictionaryRepository.suggestWord ("hy", 2));
    }
}
