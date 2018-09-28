import {Message, User, UserInfo} from "./Helpers";

export interface LoginResponse {

    userId: number;
    isAdmin: boolean;

}

export interface RegisterResponse {

    userId: number;

}


export interface UsersListResponse {

    usersList: User[];

}


export interface GetInfoResponse {

    userInfo: UserInfo;

}

export interface GetPostsResponse {

    //articles: Articles[];
}

export interface InsertPostResponse {
    
    postId: number;
}

export interface LikeResponse {
    
    notificationId: number;
}

export interface CommentResponse {
    
    notificationId: number;
}

export interface GetConnectionsResponse {
	
	users: UserInfo[];
}

export interface GetSearchResultsResponse {
    
    results: UserInfo[];
}

export interface GetJobsResponse {
    
    //fromFriends: Jobs[];
    //alike: Jobs[];
    //fromNeighbors: Jobs[];
}

export interface JobApplicationResponse {
    
    success: boolean;
}

export interface InsertJobResponse {
    
    jobId: number;
}

export interface GetMyApplicantsResponse {
    
    users: UserInfo[];
}

export interface GetMyJobsResponse {
    
    //jobs: Jobs[];
}

export interface EditJobResponse {
    
    
}

export interface SendConnectionResponse {
    
    
}

export interface GetConnectionResponse {

    users: UserInfo[];
	
}

export interface AcceptConnectionResponse {
    
    
}

export interface RejectConnectionResponse {
    
    
}

export interface GetPersonalInfoResponse {
    
    
}

export interface PostExperienceResponse {
    
    
}

export interface PostEducationResponse {
    
    
}

export interface PostSkillResponse {
    
    
}

export interface UpdateExperienceResponse {
    
    
}

export interface UpdateEducationResponse {
    
    
}

export interface UpdateSkillResponse {
    
    
}

export interface SendMessageResponse {
    
    
}

export interface GetMessagesResponse {

    messages: Message[];

}

export interface GetNotificationsResponse {
    
    
}

export interface GetConversationsResponse {

    users: UserInfo[];
    
}

export interface UpdateEmailResponse {
    
	
}

export interface UpdatePasswordResponse {
    
    
}