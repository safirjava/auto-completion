package com.safir.autocompletion.controller;

import com.safir.autocompletion.Utils.Converter;
import com.safir.autocompletion.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Word rest controller to expose end point for the client.
 * @author Sk Safiruddin
 */
@RestController
public class WordController {

    @Autowired
    private WordService wordService;

    /**
     * Publish end point for getting auto completed / suggested word from the dictionary.
     * @param start This parameter gives the characters that the word starts with.
     * @param atmost This parameter gives the maximum number of suggestions needed.
     * @return Returned multi line string, each line containing one suggestion word.
     */
    @GetMapping(value = "${wordservice.suggestion_url}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String suggestWord(@RequestParam(name = "start") String start,
                                    @RequestParam(name = "atmost") Integer atmost){
        List<String> suggestion= wordService.suggestWord(start, atmost);
        return Converter.listToMultiLineString(suggestion);

    }

    /**
     * Publish end point for inserting new word into dictionary.
     * @param word Put the word which will insert into dictionary.
     * @return true if the word successfully added to the dictionary other wise return false.
     * If the word already exist it will return false.
     */
    @PostMapping(value = "${wordservice.add_word_url}", consumes = MediaType.TEXT_PLAIN_VALUE)
    public boolean insert(@RequestBody String word){
        return wordService.addWord (word);
    }

}
