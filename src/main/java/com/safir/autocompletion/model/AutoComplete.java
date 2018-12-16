package com.safir.autocompletion.model;

import java.util.List;

/**
 * @author Sk Safiruddin
 */
public interface AutoComplete {
    /**
     * Predict the word from the dictionary base on prefix
     * @param prefix The prefix to use at the word search
     * @param numCompletions The maximum number of predictions desired
     * @return A list containing the up to n best predictions
     */
    public List<String> predictCompletions(String prefix, int numCompletions);
}
