package com.example.slwordbook.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale.Category;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.slwordbook.model.Wordbook;
import com.example.slwordbook.model.WordbookWord;
import com.example.slwordbook.service.CategoryService;
import com.example.slwordbook.service.UserService;
import com.example.slwordbook.service.WordbookService;
import com.example.slwordbook.service.WordbookWordService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/learning/wordbooks")
public class UserLearningController {

    @Autowired
    private UserService userService;

    @Autowired
    private WordbookService wordbookService;

    @Autowired
    private WordbookWordService wordbookWordService;


    @GetMapping
    public String learningForm(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        List<Wordbook> wordbooks = wordbookService.findByUserId(currentUser.getUsername());//?

        model.addAttribute("wordbooks", wordbooks);
        return "user/learning/learning_select";
    }

    @GetMapping("/{id}")
    public String learningStart(@PathVariable("id") Long id, Model model,
            @RequestParam(name = "nums") int nums) {
        List <WordbookWord> wordbookWords = wordbookWordService.findByWordbookId(id);
        Collections.shuffle(wordbookWords);
        List<WordbookWord> selectedWords = wordbookWords.stream()
            .limit(nums)
            .collect(Collectors.toList());
            
        model.addAttribute("selectedWords", selectedWords);
        return "user/learning/user_learning";
    }

    // バリデーションは設定しないで、htmlで最大数までしか選べないように。
}