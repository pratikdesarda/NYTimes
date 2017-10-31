
package com.mindstix.nytimesapp.network.model.articleSearchDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "isMajor",
        "rank",
        "name",
        "value"
})
class Keyword {

    @JsonProperty("isMajor")
    private Object isMajor;
    @JsonProperty("rank")
    private Integer rank;
    @JsonProperty("name")
    private String name;
    @JsonProperty("value")
    private String value;

    @JsonProperty("isMajor")
    public Object getIsMajor() {
        return isMajor;
    }

    @JsonProperty("isMajor")
    public void setIsMajor(Object isMajor) {
        this.isMajor = isMajor;
    }

    @JsonProperty("rank")
    public Integer getRank() {
        return rank;
    }

    @JsonProperty("rank")
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

}
