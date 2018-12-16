package com.safir.autocompletion.model;

/**
 * Dictionary1 interface, representing dictionary
 * @author Sk Safiruddin
 */
public interface Dictionary {

    /** Add this word to the dictionary.
     * @param word The word to add
     * @return true if the word was added to the dictionary(it wasn't already there).
     */
    boolean addWord(String word);

    /**
     * Is this a word according to this dictionary?
     * @param s The word to be check
     * @return true if input in valid word as per Dictionary, otherwise false.
     */
    boolean isWord(String s);

    /**
     * Return the number of words in the dictionary
     */
    public abstract int size();
}
