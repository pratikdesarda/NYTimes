
package com.mindstix.nytimesapp.network.model.articleSearchDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "hits",
    "offset",
    "time"
})
public class Meta {

    @JsonProperty("hits")
    private Integer hits;
    @JsonProperty("offset")
    private Integer offset;
    @JsonProperty("time")
    private Integer time;

    @JsonProperty("hits")
    public Integer getHits() {
        return hits;
    }

    @JsonProperty("hits")
    public void setHits(Integer hits) {
        this.hits = hits;
    }

    @JsonProperty("offset")
    public Integer getOffset() {
        return offset;
    }

    @JsonProperty("offset")
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @JsonProperty("time")
    public Integer getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(Integer time) {
        this.time = time;
    }

}
