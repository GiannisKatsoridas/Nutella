package WebApplication.Model.Responses;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class InsertPostResponse {

    ///If the postId == -1 then there was an internal server error

    private long postId;

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public InsertPostResponse(long postId) {
        this.postId = postId;
    }
}
