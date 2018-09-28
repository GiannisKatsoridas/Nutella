export interface LoginRequest {

    email: string;
    password: string;
}

export interface RegisterRequest {

    firstName: string;
    lastName: string;
    password: string;
    passConfirm: string;
    phone: string;
    image: string;
    email: string;
}

export interface UsersListRequest {
    
}

export interface GetInfoRequest {
    
    userId: number;
}

export interface GetPostsRequest {

    userId: number;
}

export interface InsertPostRequest {
    
    userId: number;
    text: string;
    media: string[];
}

export interface LikeRequest {
    
    userId: number;
    postId: number;
}

export interface CommentRequest {
    
    userId: number;
    postId: number;
    text: string;
}

export interface GetConnectionsRequest {
    
    userId: number;
}

export interface GetSearchResultsRequest {
    
    searchQuery: string;
}

export interface GetJobsRequest {
    
    userId: number;
}

export interface JobApplicationRequest {
    
    userId: number;
    jobId: number;
}

export interface InsertJobRequest {
    
    userId: number;
    jobTitle: string;
    jobDescription: string;
    skills: string[];
}

export interface GetMyApplicantsRequest {
    
    jobId: number;
}

export interface GetMyJobsRequest {
    
    jobId: number;
}

export interface EditJobRequest {
    
    jobId: number;
    jobTitle: string;
    jobDescription: string;
}

export interface SendConnectionRequest {
    
    sender: number;
    receiver: number;
}

export interface GetConnectionRequest {
    
    userId: number;
}

export interface AcceptConnectionRequest {
    
    userIdFrom: number;
    userIdTo: number;
}

export interface RejectConnectionRequest {
    
    userIdFrom: number;
    userIdTo: number;
}

export interface GetPersonalInfoRequest {
    
    userId: number;
}

export interface PostExperienceRequest {
    
    userId: number;
    companyTitle: string;
    position: string;
    //dateFrom: java.sql.Date;
    //dataTo: java.sql.Date;
}

export interface PostEducationRequest {
    
    userId: number;
    institution: string;
    degree: string;
    yearFrom: string;
    yearTo: string;
}

export interface PostSkillRequest {
    
    userId: number;
    skill: string;
}

export interface UpdateExperienceRequest {
    
    experienceId: number;
    companyTitle: string;
    position: string;
    //dateFrom: java.sql.Date;
    //dateTo: java.sql.Date;
}

export interface UpdateEducationRequest {
    
    educationId: number;
    institution: string;
    degree: string;
    yearFrom: string;
    yearTo: string;
}

export interface UpdateSkillRequest {
    
    skillId: number;
    skill: string;
}

export interface SendMessageRequest {
    
    userFrom: number;
    userTo: number;
    message: string;
}

export interface GetMessagesRequest {
    
    userFrom: number;
    friendId: number;
}

export interface GetNotificationsRequest {
    
    userId: number;
}

export interface GetConversationsRequest {
    
    userId: number;
}

export interface UpdateEmailRequest {
    
    userId: number;
    email: string;
    password: string;

}

export interface UpdatePasswordRequest {
    
    userId: number;
    oldPassword: string;
    newPassword: string;
}