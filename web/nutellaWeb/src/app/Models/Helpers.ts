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
    
    text: string;
}

export interface Jobs {
    
    likes: string;
    comments: string;
}

export interface Message {

    userFrom: number;
    userTo: number;
    content: string;
    timestamp: string;

}