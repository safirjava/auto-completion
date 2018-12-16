package com.safir.autocompletion.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * An model data structure that implements the Dictionary and the AutoComplete
 * @author Sk Safiruddin
 */

@Component
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete, Serializable {
    private TrieNode root;
    private int size;

    public AutoCompleteDictionaryTrie(){
        this.root = new TrieNode ();
    }

    /**
     * Insert a word into the model, ignore the word's case.
     * Convert the string to all lower case as we insert it.
     * @param word The word to add
     * @return true if the word was added to the dictionary(it wasn't already there).
     */
    @Override
    public boolean addWord(String word) {
        //Convert to lower case
        word = word.toLowerCase();
        TrieNode currentNode = this.root;
        for(int i = 0; i < word.length(); i++){
            if (currentNode.getChild(word.charAt(i)) != null){
                currentNode = currentNode.getChild(word.charAt(i));
            }
            else{
                currentNode = currentNode.insert(word.charAt(i));
            }
        }
        if(currentNode.endsWord()){
            return false;
        }
        else{
            currentNode.setEndsWord(true);
            this.size++;
            return true;
        }
    }

    /**
     * Return the number of words in the dictionary.
     * This is NOT necessarily the same as the number of TrieNodes in the trie.
     */
    public int size()
    {
        return this.size;
    }

    /**
     * Returns whether the string is a word in the model
     * @param s The word to be check
     * @return true if input in valid word as per Dictionary, otherwise false.
     */
    @Override
    public boolean isWord(String s) {
        s = s.toLowerCase();
        TrieNode currentNode = root;
        for(int i = 0; i < s.length(); i++){
            if (!currentNode.getValidNextCharacters().contains(s.charAt(i))){
                return false;
            }
            else{
                currentNode = currentNode.getChild(s.charAt(i));
            }
        }
        if(currentNode.endsWord() && currentNode.getText().equals(s)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Predict the word from the dictionary base on prefix
     * @param prefix The prefix to use at the word search
     * @param numCompletions The maximum number of predictions desired
     * @return Returns up to the n "best" predictions, including the word itself, in terms of length.
     * If this string is not in the model, it returns null.
     */
    @Override
    public List<String> predictCompletions(String prefix, int numCompletions) {
        prefix = prefix.toLowerCase();
        List<String> completions = new LinkedList<String> ();
        TrieNode currentNode = root;
        for(int i = 0; i < prefix.length(); i++){
            if (currentNode.getValidNextCharacters().contains(prefix.charAt(i))){
                currentNode = currentNode.getChild(prefix.charAt(i));
            }
        }
        if(!currentNode.getText().equals(prefix)){
            return completions;
        }
        LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
        queue.add(currentNode);

        TrieNode possibleCompletion;
        while(!queue.isEmpty() && completions.size() < numCompletions){

            possibleCompletion = queue.removeFirst();

            if(possibleCompletion != null){
                if(possibleCompletion.endsWord()){
                    completions.add(possibleCompletion.getText());
                }
                Set<Character> keySet = possibleCompletion.getValidNextCharacters();
                for (char c : keySet){
                    queue.addLast(possibleCompletion.getChild(c));
                }
            }
        }
        return completions;
    }
}
