package WebApplication.Model.Responses;


import WebApplication.Model.Entities.UsersEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SearchResponse {

    public List<UsersEntity> getResults() {
        return results;
    }

    public void setResults(List<UsersEntity> results) {
        this.results = results;
    }

    public SearchResponse(List<UsersEntity> results) {
        this.results = results;
    }

    private List<UsersEntity> results;
}
