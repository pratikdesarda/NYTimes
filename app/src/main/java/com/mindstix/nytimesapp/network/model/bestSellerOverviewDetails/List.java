
package com.mindstix.nytimesapp.network.model.bestSellerOverviewDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "list_id",
    "list_name",
    "list_name_encoded",
    "display_name",
    "updated",
    "list_image",
    "list_image_width",
    "list_image_height",
    "books"
})
public class List {

    @JsonProperty("list_id")
    private Integer listId;
    @JsonProperty("list_name")
    private String listName;
    @JsonProperty("list_name_encoded")
    private String listNameEncoded;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("updated")
    private String updated;
    @JsonProperty("list_image")
    private String listImage;
    @JsonProperty("list_image_width")
    private Integer listImageWidth;
    @JsonProperty("list_image_height")
    private Integer listImageHeight;
    @JsonProperty("books")
    private java.util.List<Book> books = null;

    @JsonProperty("list_id")
    public Integer getListId() {
        return listId;
    }

    @JsonProperty("list_id")
    public void setListId(Integer listId) {
        this.listId = listId;
    }

    @JsonProperty("list_name")
    public String getListName() {
        return listName;
    }

    @JsonProperty("list_name")
    public void setListName(String listName) {
        this.listName = listName;
    }

    @JsonProperty("list_name_encoded")
    public String getListNameEncoded() {
        return listNameEncoded;
    }

    @JsonProperty("list_name_encoded")
    public void setListNameEncoded(String listNameEncoded) {
        this.listNameEncoded = listNameEncoded;
    }

    @JsonProperty("display_name")
    public String getDisplayName() {
        return displayName;
    }

    @JsonProperty("display_name")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @JsonProperty("updated")
    public String getUpdated() {
        return updated;
    }

    @JsonProperty("updated")
    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @JsonProperty("list_image")
    public String getListImage() {
        return listImage;
    }

    @JsonProperty("list_image")
    public void setListImage(String listImage) {
        this.listImage = listImage;
    }

    @JsonProperty("list_image_width")
    public Integer getListImageWidth() {
        return listImageWidth;
    }

    @JsonProperty("list_image_width")
    public void setListImageWidth(Integer listImageWidth) {
        this.listImageWidth = listImageWidth;
    }

    @JsonProperty("list_image_height")
    public Integer getListImageHeight() {
        return listImageHeight;
    }

    @JsonProperty("list_image_height")
    public void setListImageHeight(Integer listImageHeight) {
        this.listImageHeight = listImageHeight;
    }

    @JsonProperty("books")
    public java.util.List<Book> getBooks() {
        return books;
    }

    @JsonProperty("books")
    public void setBooks(java.util.List<Book> books) {
        this.books = books;
    }

}
