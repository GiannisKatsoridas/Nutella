package WebApplication.Model.Responses;

import WebApplication.Model.Helpers.Article;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetPostResponse {

    private Article post;

    public Article getPost() {
        return post;
    }

    public void setPost(Article post) {
        this.post = post;
    }

    public GetPostResponse(Article post) {
        this.post = post;
    }
}
