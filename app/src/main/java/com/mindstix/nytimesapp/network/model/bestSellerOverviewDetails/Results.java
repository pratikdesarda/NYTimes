
package com.mindstix.nytimesapp.network.model.bestSellerOverviewDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bestsellers_date",
    "published_date",
    "published_date_description",
    "previous_published_date",
    "next_published_date",
    "lists"
})
public class Results {

    @JsonProperty("bestsellers_date")
    private String bestsellersDate;
    @JsonProperty("published_date")
    private String publishedDate;
    @JsonProperty("published_date_description")
    private String publishedDateDescription;
    @JsonProperty("previous_published_date")
    private String previousPublishedDate;
    @JsonProperty("next_published_date")
    private String nextPublishedDate;
    @JsonProperty("lists")
    private java.util.List<com.mindstix.nytimesapp.network.model.bestSellerOverviewDetails.List> lists = null;

    @JsonProperty("bestsellers_date")
    public String getBestsellersDate() {
        return bestsellersDate;
    }

    @JsonProperty("bestsellers_date")
    public void setBestsellersDate(String bestsellersDate) {
        this.bestsellersDate = bestsellersDate;
    }

    @JsonProperty("published_date")
    public String getPublishedDate() {
        return publishedDate;
    }

    @JsonProperty("published_date")
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    @JsonProperty("published_date_description")
    public String getPublishedDateDescription() {
        return publishedDateDescription;
    }

    @JsonProperty("published_date_description")
    public void setPublishedDateDescription(String publishedDateDescription) {
        this.publishedDateDescription = publishedDateDescription;
    }

    @JsonProperty("previous_published_date")
    public String getPreviousPublishedDate() {
        return previousPublishedDate;
    }

    @JsonProperty("previous_published_date")
    public void setPreviousPublishedDate(String previousPublishedDate) {
        this.previousPublishedDate = previousPublishedDate;
    }

    @JsonProperty("next_published_date")
    public String getNextPublishedDate() {
        return nextPublishedDate;
    }

    @JsonProperty("next_published_date")
    public void setNextPublishedDate(String nextPublishedDate) {
        this.nextPublishedDate = nextPublishedDate;
    }

    @JsonProperty("lists")
    public java.util.List<com.mindstix.nytimesapp.network.model.bestSellerOverviewDetails.List> getLists() {
        return lists;
    }

    @JsonProperty("lists")
    public void setLists(java.util.List<com.mindstix.nytimesapp.network.model.bestSellerOverviewDetails.List> lists) {
        this.lists = lists;
    }

}
