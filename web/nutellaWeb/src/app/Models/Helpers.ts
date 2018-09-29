export interface User {

    firstName: string;
    lastName: string;
    email: string;
    pass: string;
    passConf: string;
    phone: string
    id: number;

}


export interface UserInfo {

    userId: number;
    firstName: string;
    lastName: string;
    email: string;
    image: string;

}

export interface Article {

    postId: number;
    creator: number;
    text: string;
    likes: Like[];
    comments: Comment[];

}

export interface Message {

    userFrom: number;
    userTo: number;
    content: string;
    timestamp: string;

}

export interface Experience {

    userId: number;
    experienceId: number;
    companyTitle: string;
    position: string;
    dateFrom: string;
    dateTo: string;

}

export interface Education {

    userId: number;
    educationId: number;
    degree: string;
    institution: string;
    yearFrom: string;
    yearTo: string;

}

export interface Skill {

    userId: number;
    skillId: number;
    skill: string;

}

export interface Notification {

    id: number;
    userFrom: number;
    userTo: number;
    timestamp: number;
    post: number;
    category: number;

}

export interface Job {

    id: number;
    creator: number;
    title: string;
    description: string;
    date: string;

}

export interface JobApplication {

    applicant: number;
    job: number;

}

export interface Like{

    userId: number;
    postId: number;
    timestamp: string;

}

export interface Comment {

    userId: number;
    postId: number;
    timestamp: string;
    text: string;

}