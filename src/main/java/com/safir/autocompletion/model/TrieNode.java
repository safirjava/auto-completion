package com.safir.autocompletion.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Represents a node in a Trie
 * @author Sk Safiruddin
 */
public class TrieNode implements Serializable {
    private ConcurrentMap<Character, TrieNode> children;
    private String text;
    private boolean isWord;

    /**
     * Create a new TrieNode
     */
    public TrieNode()
    {
        children = new ConcurrentHashMap<> ();
        text = "";
        isWord = false;
    }

    /**
     * Create a new TrieNode given a text String to store in it
     */
    public TrieNode(String text)
    {
        this();
        this.text = text;
    }

    /**
     * Return the TrieNode that is the child when you follow the link from the given Character
     * @param c The next character in the key
     * @return The TrieNode that character links to, or null if that link is not in the model.
     */
    public TrieNode getChild(Character c)
    {

        return children.get(c);
    }

    /**
     * Inserts this character at this node.
     * Returns the newly created node, if c wasn't already in the model.
     * If it was, it does not modify the model and returns null.
     * @param c The character that will link to the new node
     * @return The newly created TrieNode, or null if the node is already in the model.
     */
    public TrieNode insert(Character c)
    {
        if (children.containsKey(c)) {
            return null;
        }

        TrieNode next = new TrieNode(text + c.toString());
        children.put(c, next);
        return next;
    }

    /**
     * Return the text string at this node
     */
    public String getText()
    {
        return text;
    }

    /**
     * Set whether or not this node ends a word in the model.
     */
    public void setEndsWord(boolean b)
    {
        isWord = b;
    }

    /**
     * Return whether or not this node ends a word in the model.
     */
    public boolean endsWord()
    {
        return isWord;
    }

    /**
     * Return the set of characters that have links from this node
     */
    public Set<Character> getValidNextCharacters()
    {
        return children.keySet();
    }
}
