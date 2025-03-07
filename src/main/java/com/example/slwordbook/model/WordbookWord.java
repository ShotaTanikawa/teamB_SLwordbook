package com.example.slwordbook.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "words_wordbooks")
public class WordbookWord {

    // 複合主キー（Wordbook と Word の ID）
    @EmbeddedId
    private WordbookWordId id;

    @ManyToOne
    @MapsId("wordbookId")
    @JoinColumn(name = "wordbook_id")
    private Wordbook wordbook;

    @ManyToOne
    @MapsId("wordId")
    @JoinColumn(name = "word_id")
    private Word word;

    // お気に入りフラグ
    @Column(name = "favorite")
    private boolean favorite;

    
    public WordbookWordId getId() {
        return id;
    }

    public void setId(WordbookWordId id) {
        this.id = id;
    }

    public Wordbook getWordbook() {
        return wordbook;
    }

    public void setWordbook(Wordbook wordbook) {
        this.wordbook = wordbook;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }  

    
}
