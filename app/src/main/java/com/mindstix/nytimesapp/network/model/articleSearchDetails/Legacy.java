
package com.mindstix.nytimesapp.network.model.articleSearchDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "xlargewidth",
    "xlarge",
    "xlargeheight",
    "thumbnailheight",
    "thumbnail",
    "thumbnailwidth"
})
public class Legacy {

    @JsonProperty("xlargewidth")
    private String xlargewidth;
    @JsonProperty("xlarge")
    private String xlarge;
    @JsonProperty("xlargeheight")
    private String xlargeheight;
    @JsonProperty("thumbnailheight")
    private String thumbnailheight;
    @JsonProperty("thumbnail")
    private String thumbnail;
    @JsonProperty("thumbnailwidth")
    private String thumbnailwidth;

    @JsonProperty("xlargewidth")
    public String getXlargewidth() {
        return xlargewidth;
    }

    @JsonProperty("xlargewidth")
    public void setXlargewidth(String xlargewidth) {
        this.xlargewidth = xlargewidth;
    }

    @JsonProperty("xlarge")
    public String getXlarge() {
        return xlarge;
    }

    @JsonProperty("xlarge")
    public void setXlarge(String xlarge) {
        this.xlarge = xlarge;
    }

    @JsonProperty("xlargeheight")
    public String getXlargeheight() {
        return xlargeheight;
    }

    @JsonProperty("xlargeheight")
    public void setXlargeheight(String xlargeheight) {
        this.xlargeheight = xlargeheight;
    }

    @JsonProperty("thumbnailheight")
    public String getThumbnailheight() {
        return thumbnailheight;
    }

    @JsonProperty("thumbnailheight")
    public void setThumbnailheight(String thumbnailheight) {
        this.thumbnailheight = thumbnailheight;
    }

    @JsonProperty("thumbnail")
    public String getThumbnail() {
        return thumbnail;
    }

    @JsonProperty("thumbnail")
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @JsonProperty("thumbnailwidth")
    public String getThumbnailwidth() {
        return thumbnailwidth;
    }

    @JsonProperty("thumbnailwidth")
    public void setThumbnailwidth(String thumbnailwidth) {
        this.thumbnailwidth = thumbnailwidth;
    }

}
