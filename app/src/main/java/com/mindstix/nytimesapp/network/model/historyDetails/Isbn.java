
package com.mindstix.nytimesapp.network.model.historyDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "isbn10",
        "isbn13"
})
class Isbn {

    @JsonProperty("isbn10")
    private String isbn10;
    @JsonProperty("isbn13")
    private String isbn13;

    @JsonProperty("isbn10")
    public String getIsbn10() {
        return isbn10;
    }

    @JsonProperty("isbn10")
    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    @JsonProperty("isbn13")
    public String getIsbn13() {
        return isbn13;
    }

    @JsonProperty("isbn13")
    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

}
