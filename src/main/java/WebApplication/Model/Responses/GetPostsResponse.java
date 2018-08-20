package WebApplication.Model.Responses;


import WebApplication.Model.Helpers.Article;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetPostsResponse {

    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public GetPostsResponse(List<Article> articles) {
        this.articles = articles;
    }
}
