package com.safir.autocompletion.service;

import com.safir.autocompletion.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementations of dictionary.
 * It use Dictionary repository for making dictionary operation.
 * @author Sk Safiruddin
 */
@Service
public class WordServiceImpl implements WordService{

    @Autowired
    private DictionaryRepository dictionaryRepository;

    /**
     * Provide suggested word from the dictionary base on prefix.
     * @param prefix This parameter gives the characters that the word starts with.
     * @param size This parameter gives the maximum number of suggestions needed.
     * @return Returned list of words which match the prefix.
     */
    @Override
    public List<String> suggestWord(String prefix, Integer size) {
        return dictionaryRepository.suggestWord (prefix, size);
    }

    /**
     * To add new word to existing dictionary.
     * @param word Put the word which will insert into dictionary.
     * @return true if the word successfully added to the dictionary other wise return false.
     */
    @Override
    public boolean addWord(String word) {
        return dictionaryRepository.addWord (word);
    }
}
