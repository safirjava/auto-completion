package com.safir.autocompletion.service;

import java.util.List;

/**
 * Service Interface for dictionary Operation.
 * @author Sk Safiruddin
 */
public interface WordService {
    /**
     * Provide suggested word from the dictionary base on prefix.
     * @param prefix This parameter gives the characters that the word starts with.
     * @param size This parameter gives the maximum number of suggestions needed.
     * @return Returned list of words which match the prefix.
     */
    List<String> suggestWord(String prefix, Integer size);

    /**
     * To add new word to existing dictionary.
     * @param word Put the word which will insert into dictionary.
     * @return true if the word successfully added to the dictionary other wise return false.
     */
    boolean addWord(String word);
}
