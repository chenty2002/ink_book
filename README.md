# 软件工程实践第20组 ink_book 项目后端仓库

## （暂定）技术栈：Springboot MyBatis MySQL

## 数据库表

create table group_user
(

    groupId   int not null,
    
    userId    int not null,
    
    userLevel int null,
    
    primary key (groupId, userId)

)

create table `groups`

(

    groupId         int auto_increment
    
        primary key,
        
    groupName       varchar(255) null,
    
    groupCreateTime datetime     null,
    
    groupProfile    varchar(255) null
    
)

create table project_user

(

    projectId int not null,
    
    userId    int not null,
    
    primary key (projectId, userId)
    
)

create table projects

(

    projectId          int auto_increment
    
        primary key,
        
    projectName        varchar(255) null,
    
    projectDescription varchar(255) null
    
)

create table users

(

    userId       int auto_increment
    
        primary key,
        
    userName     varchar(255) null,
    
    password     varchar(255) null,
    
    userProfile  varchar(255) null,
    
    userRealName varchar(255) null,
    
    userEmail    varchar(255) null
    
)
