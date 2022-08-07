# 软件工程实践第20组 ink_book 项目后端仓库

## （暂定）技术栈：Springboot MyBatis MySQL

## 数据库表

CREATE TABLE `article` (

`articleId` int NOT NULL AUTO_INCREMENT,

`title` varchar(100) DEFAULT NULL,

`author` int DEFAULT NULL,

`content` longtext,

PRIMARY KEY (`articleId`)

) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3

***

CREATE TABLE `group_project` (

`projectId` int NOT NULL,

`groupId` int NOT NULL,

PRIMARY KEY (`projectId`,`groupId`) USING BTREE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC

***

CREATE TABLE `group_user` (

`groupId` int NOT NULL,

`userId` int NOT NULL,

`userLevel` int DEFAULT NULL,

PRIMARY KEY (`groupId`,`userId`) USING BTREE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC

***

CREATE TABLE `groups` (

`groupId` int NOT NULL AUTO_INCREMENT,

`groupName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,

`groupCreateTime` datetime DEFAULT NULL,

`groupProfile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,

PRIMARY KEY (`groupId`) USING BTREE

) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC

***

CREATE TABLE `projects` (

`projectId` int NOT NULL AUTO_INCREMENT,

`projectName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,

`projectDescription` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,

`deleted` int NOT NULL DEFAULT '0',

PRIMARY KEY (`projectId`) USING BTREE

) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC

***

CREATE TABLE `users` (

`userId` int NOT NULL AUTO_INCREMENT,

`userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,

`password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,

`userProfile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,

`userRealName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,

`userEmail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,

PRIMARY KEY (`userId`) USING BTREE

) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC


