import {User, UserInfo} from "./Helpers";

export interface LoginResponse {

    userId: number;
    admin: boolean;

}

export interface RegisterResponse {

    userId: number;

}


export interface UsersListResponse {

    usersList: User[];

}


export interface UserInfoResponse {

    userInfo: UserInfo;

}
