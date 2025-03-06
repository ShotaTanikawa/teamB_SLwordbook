package com.example.slwordbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.slwordbook.model.Category;
import com.example.slwordbook.model.Word;
import com.example.slwordbook.service.CategoryService;
import com.example.slwordbook.service.WordService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/categories")
public class AdminWordController {
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private WordService wordService;

    //カテゴリー内の単語一覧機能
    @GetMapping("/{categoryId}")
    public String wordIndex(@PathVariable("categoryId") Long categoryId, Model model) {
        Category category = categoryService.findCategoryById(categoryId);
        List<Word> words = wordService.findAllWords();
        model.addAttribute("category", category);
        model.addAttribute("words", words);
        return "admin/categories/admin_wordslist.html";
    }

    // //カテゴリー内の単語一覧機能・検索機能
    // @GetMapping
    // public String wordIndex(@PathVariable("categoryId") Long categoryId,
    //                         @RequestParam(name = "name", required = false) String name, Model model) {
    //     Category category = categoryService.findCategoryById(categoryId);
    //     List<Word> words = wordService.findAllWords();
    //     List<Word> searchWord = wordService.findByWord(name);
    //     model.addAttribute("category", category);
    //     model.addAttribute("words", words);
    //     model.addAttribute("searchWord", searchWord);
    //     return "admin/categories/admin_wordslist.html";
    // }

    //単語新規登録(入力)
    @GetMapping("/{categoryId}/new")
    public String wordAddForm(@PathVariable("categoryId") Long categoryId, Model model) {
        Category category = categoryService.findCategoryById(categoryId);
        model.addAttribute("word", new Word());
        model.addAttribute("category", category);
        return "admin/categories/word_new.html";

    }

    //単語新規登録(出力)
    @PostMapping("/{categoryId}/save")
    public String wordAdd(@PathVariable("categoryId") Long id, @Valid Word word, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("word", word);
            return "admin/categories/word_new.html";
        }
        wordService.save(word);
        return "redirect:/admin/categories/{categoryId}";
    }

    //単語編集(入力)
    @GetMapping("/{categoryId}/edit/{wordId}")
    public String wordEditForm(@PathVariable("categoryId") Long categoryId, @PathVariable("wordId") Long wordId, Model model) {
        Category category = categoryService.findCategoryById(categoryId);
        Word word = wordService.findWordById(wordId);
        model.addAttribute("category", category);
        model.addAttribute("word", word);
        return "admin/categories/word_edit.html";
    }

    //単語編集(出力)
    @PostMapping("/{categoryId}/edit")
    public String wordEdit(@PathVariable("categoryId") Long categoryId, @PathVariable("wordId") Long wordId, Word word) {
        wordService.update(word);
        return "redirect:/admin/categories/{categoryId}";
    }

    //単語詳細
    @GetMapping("/{categoryId}/detail/{wordId}")
    public String wordDetail(@PathVariable("categoryId") Long categoryId, @PathVariable("wordId") Long wordId, Model model) {
        Category category = categoryService.findCategoryById(categoryId);
        Word word = wordService.findWordById(wordId);
        model.addAttribute("category", category);
        model.addAttribute("word", word);
        return "admin/categories/admin_worddetail.html";
    }

    //単語削除
    @GetMapping("/{categoryId}/delete/{wordId}")
    public String wordDelete(@PathVariable("categoryId") Long categoryId, @PathVariable("wordId") Long wordId) {
        wordService.deleteWordById(wordId);
        return "redirect:/admin/categories/{categoryId}";
    }
}
