package WebApplication.Model.Responses;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetInfoResponse {

    ///In case of an error all the values would be null

    private String firstName;
    private String lastName;
    private String email;
    private String image;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public GetInfoResponse(String firstName, String lastName, String email, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.image = image;
    }
}
