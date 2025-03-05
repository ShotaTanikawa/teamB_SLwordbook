package com.example.slwordbook.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class WordbookWordId implements Serializable {

    private Long wordbookId;
    private Long wordId;

    // equals と hashCode を実装
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordbookWordId that = (WordbookWordId) o;
        return Objects.equals(wordbookId, that.wordbookId) &&
            Objects.equals(wordId, that.wordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordbookId, wordId);
    }
}
