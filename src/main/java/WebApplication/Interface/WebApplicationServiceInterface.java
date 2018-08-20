package WebApplication.Interface;

import WebApplication.Model.Requests.*;
import WebApplication.Model.Responses.*;

public interface WebApplicationServiceInterface {

    RegisterResponse Register(RegisterRequest request);

    LoginResponse Login(LoginRequest request);

    GetUsersListResponse GetUsersList();

    GetInfoResponse GetInfo(GetInfoRequest request);

    InsertPostResponse InsertPost(InsertPostRequest request);

    GetConnectionsResponse GetConnections(GetConnectionsRequest request);
}
