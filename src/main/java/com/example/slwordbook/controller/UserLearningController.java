package com.example.slwordbook.controller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.slwordbook.model.CustomUserDetails;
import com.example.slwordbook.model.Wordbook;
import com.example.slwordbook.model.WordbookWord;
import com.example.slwordbook.service.WordbookService;
import com.example.slwordbook.service.WordbookWordService;


@Controller
@RequestMapping("/user/learning/wordbooks")
public class UserLearningController {

    @Autowired
    private WordbookService wordbookService;

    @Autowired
    private WordbookWordService wordbookWordService;

    @GetMapping()
    public String learningForm(Model model, @AuthenticationPrincipal CustomUserDetails currentUser) {
        List<Wordbook> listWordbooks = wordbookService.findByUser(currentUser.getUser());
        model.addAttribute("listWordbooks", listWordbooks);
        return "user/learning/learning_select";
    }


    @GetMapping("/{id}")
    public String learningStart(@PathVariable("id") Long id, Model model,
            @RequestParam(name = "nums") int nums) {
        List <WordbookWord> wordbookWords = wordbookWordService.findByWordbookId(id);
        Collections.shuffle(wordbookWords);

        List<List<String>> listWords = wordbookWords.stream()
        .limit(nums) // numsは問題数
        .map(wordbookWord -> {
            // 各WordbookWordをList<String>に変換
            List<String> wordInfo = new ArrayList<>();
            wordInfo.add(wordbookWord.getWord().getName());
            wordInfo.add(wordbookWord.getWord().getDescribe());
            wordInfo.add(wordbookWord.getWord().getDetail_describe());
            return wordInfo; // List<String>として返す
        })
        .collect(Collectors.toList());
        model.addAttribute("listWords", listWords);
        return "user/learning/user_learning";
    }

}