
package com.mindstix.nytimesapp.network.model.articleSearchDetails;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "web_url",
    "snippet",
    "abstract",
    "blog",
    "source",
    "multimedia",
    "headline",
    "keywords",
    "pub_date",
    "document_type",
    "byline",
    "type_of_material",
    "_id",
    "word_count",
    "score",
    "print_page",
    "new_desk"
})
public class Doc {

    @JsonProperty("web_url")
    private String webUrl;
    @JsonProperty("snippet")
    private String snippet;
    @JsonProperty("abstract")
    private String _abstract;
    @JsonProperty("blog")
    private Blog blog;
    @JsonProperty("source")
    private String source;
    @JsonProperty("multimedia")
    private List<Multimedium> multimedia = null;
    @JsonProperty("headline")
    private Headline headline;
    @JsonProperty("keywords")
    private List<Keyword> keywords = null;
    @JsonProperty("pub_date")
    private String pubDate;
    @JsonProperty("document_type")
    private String documentType;
    @JsonProperty("byline")
    private Byline byline;
    @JsonProperty("type_of_material")
    private String typeOfMaterial;
    @JsonProperty("_id")
    private String id;
    @JsonProperty("word_count")
    private Integer wordCount;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("print_page")
    private String printPage;
    @JsonProperty("new_desk")
    private String newDesk;

    @JsonProperty("web_url")
    public String getWebUrl() {
        return webUrl;
    }

    @JsonProperty("web_url")
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @JsonProperty("snippet")
    public String getSnippet() {
        return snippet;
    }

    @JsonProperty("snippet")
    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    @JsonProperty("abstract")
    public String getAbstract() {
        return _abstract;
    }

    @JsonProperty("abstract")
    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    @JsonProperty("blog")
    public Blog getBlog() {
        return blog;
    }

    @JsonProperty("blog")
    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("multimedia")
    public List<Multimedium> getMultimedia() {
        return multimedia;
    }

    @JsonProperty("multimedia")
    public void setMultimedia(List<Multimedium> multimedia) {
        this.multimedia = multimedia;
    }

    @JsonProperty("headline")
    public Headline getHeadline() {
        return headline;
    }

    @JsonProperty("headline")
    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    @JsonProperty("keywords")
    public List<Keyword> getKeywords() {
        return keywords;
    }

    @JsonProperty("keywords")
    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    @JsonProperty("pub_date")
    public String getPubDate() {
        return pubDate;
    }

    @JsonProperty("pub_date")
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @JsonProperty("document_type")
    public String getDocumentType() {
        return documentType;
    }

    @JsonProperty("document_type")
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    @JsonProperty("byline")
    public Byline getByline() {
        return byline;
    }

    @JsonProperty("byline")
    public void setByline(Byline byline) {
        this.byline = byline;
    }

    @JsonProperty("type_of_material")
    public String getTypeOfMaterial() {
        return typeOfMaterial;
    }

    @JsonProperty("type_of_material")
    public void setTypeOfMaterial(String typeOfMaterial) {
        this.typeOfMaterial = typeOfMaterial;
    }

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("word_count")
    public Integer getWordCount() {
        return wordCount;
    }

    @JsonProperty("word_count")
    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    @JsonProperty("score")
    public Integer getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(Integer score) {
        this.score = score;
    }

    @JsonProperty("print_page")
    public String getPrintPage() {
        return printPage;
    }

    @JsonProperty("print_page")
    public void setPrintPage(String printPage) {
        this.printPage = printPage;
    }

    @JsonProperty("new_desk")
    public String getNewDesk() {
        return newDesk;
    }

    @JsonProperty("new_desk")
    public void setNewDesk(String newDesk) {
        this.newDesk = newDesk;
    }

}
