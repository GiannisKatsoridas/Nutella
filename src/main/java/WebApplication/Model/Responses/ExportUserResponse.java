package WebApplication.Model.Responses;

import WebApplication.Model.Helpers.MyXMLObject;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExportUserResponse {

    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ExportUserResponse(String info) {
        this.info = info;
    };

}
