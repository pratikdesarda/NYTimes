
package com.mindstix.nytimesapp.network.model.historyDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "book_review_link",
    "first_chapter_link",
    "sunday_review_link",
    "article_chapter_link"
})
public class Review {

    @JsonProperty("book_review_link")
    private String bookReviewLink;
    @JsonProperty("first_chapter_link")
    private String firstChapterLink;
    @JsonProperty("sunday_review_link")
    private String sundayReviewLink;
    @JsonProperty("article_chapter_link")
    private String articleChapterLink;

    @JsonProperty("book_review_link")
    public String getBookReviewLink() {
        return bookReviewLink;
    }

    @JsonProperty("book_review_link")
    public void setBookReviewLink(String bookReviewLink) {
        this.bookReviewLink = bookReviewLink;
    }

    @JsonProperty("first_chapter_link")
    public String getFirstChapterLink() {
        return firstChapterLink;
    }

    @JsonProperty("first_chapter_link")
    public void setFirstChapterLink(String firstChapterLink) {
        this.firstChapterLink = firstChapterLink;
    }

    @JsonProperty("sunday_review_link")
    public String getSundayReviewLink() {
        return sundayReviewLink;
    }

    @JsonProperty("sunday_review_link")
    public void setSundayReviewLink(String sundayReviewLink) {
        this.sundayReviewLink = sundayReviewLink;
    }

    @JsonProperty("article_chapter_link")
    public String getArticleChapterLink() {
        return articleChapterLink;
    }

    @JsonProperty("article_chapter_link")
    public void setArticleChapterLink(String articleChapterLink) {
        this.articleChapterLink = articleChapterLink;
    }

}
