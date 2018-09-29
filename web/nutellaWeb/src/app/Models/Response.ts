import {
    Education,
    Experience,
    Message,
    Skill,
    User,
    UserInfo,
    Notification,
    Job,
    JobApplication,
    Article,
    Comment,
    Like
} from "./Helpers";

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

    articles: Article[];
}

export interface GetPostResponse {

    post: Article;
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
    
    fromFriends: Job[];
    alike: Job[];
    fromNeighbors: Job[];
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

export interface GetMyApplicationsResponse {
    
    jobs: JobApplication[];
}

export interface GetMyJobsResponse {

    jobs: Job[];

}

export interface EditJobResponse {
    
    
}

export interface SendConnectionResponse {
    
    notificationId: number;

}


export interface GetConnectionRequestsResponse {

    users: UserInfo[];

}

export interface AcceptConnectionResponse {
    
    
}

export interface RejectConnectionResponse {
    
    
}

export interface GetPersonalInfoResponse {
    
    experience: Experience[];
    education: Education[];
    skills: Skill[];

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

    notifications: Notification[];
    
}

export interface GetConversationsResponse {

    users: UserInfo[];
    
}

export interface UpdateEmailResponse {
    
	
}

export interface UpdatePasswordResponse {
    
    
}

export interface UploadFileResponse {

    link: string;

}