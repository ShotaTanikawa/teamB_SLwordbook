package com.example.slwordbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.slwordbook.model.Category;
import com.example.slwordbook.model.User;
import com.example.slwordbook.model.Word;
import com.example.slwordbook.service.CategoryService;
import com.example.slwordbook.service.UserService;
import com.example.slwordbook.service.WordService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/categories")
public class UserWordController {
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private WordService wordService;

    @Autowired
    private UserService userService;

    //カテゴリー内の単語一覧機能・検索機能
    @GetMapping("/{categoryId}")
    public String wordIndex(@PathVariable("categoryId") Long categoryId,
                            @RequestParam(name = "keyword", required = false) String keyword, Model model) {
        Category category = categoryService.findCategoryById(categoryId);
        List<Word> words;

        //辞書区分を区別するためのログインユーザー取得
        User loginUser = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        

        // 検索条件があれば絞り込む
        if (keyword != null && !keyword.isEmpty()) {
            // 名前で検索
            words = wordService.searchWithUser(keyword, categoryId, loginUser.getId());
        } else {
            // 検索条件がなければ全ての単語を取得
            words = wordService.findAllWordsByCategoryWithUser(categoryId, loginUser.getId());
        }

        model.addAttribute("category", category);
        model.addAttribute("words", words);
        model.addAttribute("keyword", keyword);
        model.addAttribute("loginUser", loginUser);
        return "user/categories/user_wordslist.html";
    }

    //単語新規登録(入力)
    @GetMapping("/{categoryId}/new")
    public String wordAddForm(@PathVariable("categoryId") Long categoryId, Model model) {
        Category category = categoryService.findCategoryById(categoryId);
        model.addAttribute("word", new Word());
        model.addAttribute("category", category);
        return "user/categories/word_form.html";
    }

    //単語新規登録(出力)
    @PostMapping("/{categoryId}/save")
    public String wordAdd(@PathVariable("categoryId") Long categoryId, @Valid Word word, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("word", word);
            return "user/categories/word_form.html";
        }
        
        Category category = categoryService.findCategoryById(categoryId);
        word.setCategory(category);

        //ユーザーID取得
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User loginUser = userService.findByUsername(name);
        word.setUser(loginUser);

        wordService.save(word);
        return "redirect:/user/categories/{categoryId}";
    }

    //単語編集(入力)
    @GetMapping("/{categoryId}/edit/{wordId}")
    public String wordEditForm(@PathVariable("categoryId") Long categoryId, @PathVariable("wordId") Long wordId, Model model) {
        Category category = categoryService.findCategoryById(categoryId);
        Word word = wordService.findWordById(wordId);
        model.addAttribute("category", category);
        model.addAttribute("word", word);
        return "user/categories/word_edit.html";
    }

    //単語編集(出力)
    @PostMapping("/{categoryId}/edit/{wordId}")
    public String wordEdit(@PathVariable("categoryId") Long categoryId, @PathVariable("wordId") Long wordId, Word word) {
        wordService.update(word);
        return "redirect:/user/categories/{categoryId}";
    }

    //単語詳細
    @GetMapping("/{categoryId}/detail/{wordId}")
    public String wordDetail(@PathVariable("categoryId") Long categoryId, @PathVariable("wordId") Long wordId, Model model) {
        Category category = categoryService.findCategoryById(categoryId);
        Word word = wordService.findWordById(wordId);
        User loginUser = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("category", category);
        model.addAttribute("word", word);
        model.addAttribute("loginUser", loginUser);
        return "user/categories/word_detail.html";
    }

    //単語削除
    @GetMapping("/{categoryId}/delete/{wordId}")
    public String wordDelete(@PathVariable("categoryId") Long categoryId, @PathVariable("wordId") Long wordId) {
        wordService.deleteWordById(wordId);
        return "redirect:/user/categories/{categoryId}";
    }
}
