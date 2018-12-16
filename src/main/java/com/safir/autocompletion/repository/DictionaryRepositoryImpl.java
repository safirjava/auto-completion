package com.safir.autocompletion.repository;

import com.safir.autocompletion.model.AutoCompleteDictionaryTrie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Provide the abstraction on dictionary implementation.
 * It use trie data structure base implementation of dictionary to store the word.
 * Which make searching of word with prefix very efficient.
 * @author Sk Safiruddin
 */
@Repository
public class DictionaryRepositoryImpl implements DictionaryRepository {
    @Autowired
    private AutoCompleteDictionaryTrie autoCompleteDictionaryTrie;

    /**
     * Provide suggested word from the dictionary base on prefix.
     * @param prefix This parameter gives the characters that the word starts with.
     * @param size This parameter gives the maximum number of suggestions needed.
     * @return Returned list of words which match the prefix.
     */
    @Override
    public List<String> suggestWord(String prefix, Integer size) {
        return autoCompleteDictionaryTrie.predictCompletions (prefix, size);
    }

    /**
     * To add new word to existing dictionary.
     * @param word Put the word which will insert into dictionary.
     * @return true if the word successfully added to the dictionary other wise return false.
     * If the word already exist it will return false.
     */
    @Override
    public boolean addWord(String word) {
        return autoCompleteDictionaryTrie.addWord (word);
    }
}
