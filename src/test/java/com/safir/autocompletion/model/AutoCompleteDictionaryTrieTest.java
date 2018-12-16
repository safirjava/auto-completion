package com.safir.autocompletion.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith (SpringRunner.class)
public class AutoCompleteDictionaryTrieTest {

    @Autowired
    private AutoCompleteDictionaryTrie autoCompleteDictionaryTrie;

    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public void checkEmptyDictionarySizeIsZero() {
        Assert.assertEquals (autoCompleteDictionaryTrie.size (), 0);
    }

    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public void checkAddWordFuncation(){
        autoCompleteDictionaryTrie.addWord ("Kolkata");
        Assert.assertEquals (autoCompleteDictionaryTrie.size (), 1);
        autoCompleteDictionaryTrie.addWord ("Delhi");
        Assert.assertEquals (autoCompleteDictionaryTrie.size (), 2);
        autoCompleteDictionaryTrie.addWord ("Hyderabad");
        Assert.assertEquals (autoCompleteDictionaryTrie.size (), 3);
        autoCompleteDictionaryTrie.addWord ("Kota");
        Assert.assertEquals (autoCompleteDictionaryTrie.size (), 4);
    }

    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public void checkIsWordFuncation(){
        Assert.assertEquals (autoCompleteDictionaryTrie.isWord ("Kolkata"),false);
        autoCompleteDictionaryTrie.addWord ("Kolkata");
        Assert.assertEquals (autoCompleteDictionaryTrie.isWord ("Kolkata"),true);
        Assert.assertEquals (autoCompleteDictionaryTrie.isWord ("Kota"),false);
    }

    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public void checkPredictCompletionsFuncation(){
        autoCompleteDictionaryTrie.addWord ("Kota");
        autoCompleteDictionaryTrie.addWord ("Kolkata");
        List<String> words = new ArrayList<> ();
        words.add ("kota");
        words.add ("kolkata");
        List<String> autoCompletedWords = autoCompleteDictionaryTrie.predictCompletions ("ko", 5);
        Assert.assertEquals (words, autoCompletedWords);

        List<String> autoCompletedWordsZero = autoCompleteDictionaryTrie.predictCompletions ("ko", 0);
        List<String> wordsZero = new ArrayList<> ();
        Assert.assertEquals (wordsZero, autoCompletedWordsZero);

        List<String> autoCompletedWordsOne = autoCompleteDictionaryTrie.predictCompletions ("ko", 1);
        List<String> wordsOne = new ArrayList<> ();
        wordsOne.add ("kota");
        Assert.assertEquals (wordsOne, autoCompletedWordsOne);
    }
}
