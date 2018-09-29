package WebApplication.Model.Requests;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetPostRequest {

    private long postId;

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public GetPostRequest(long postId) {
        this.postId = postId;
    }
}
