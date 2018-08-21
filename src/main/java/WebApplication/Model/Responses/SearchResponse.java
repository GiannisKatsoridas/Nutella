package WebApplication.Model.Responses;


import WebApplication.Model.Entities.UsersEntity;
import WebApplication.Model.Helpers.UserInfo;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SearchResponse {

    public List<UserInfo> getResults() {
        return results;
    }

    public void setResults(List<UserInfo> results) {
        this.results = results;
    }

    public SearchResponse(List<UserInfo> results) {
        this.results = results;
    }

    private List<UserInfo> results;
}
