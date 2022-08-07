package com.summerProject.ink_book.Controller;

import com.alibaba.fastjson.JSONObject;
import com.summerProject.ink_book.Entity.Project;
import com.summerProject.ink_book.Entity.User;
import com.summerProject.ink_book.Service.ProjectService;
import com.summerProject.ink_book.Utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    // 建立项目 POST url?传用户id 请求体传项目信息
    /*
        url: /newProject?userId=
        请求体参数
        {
            "projectName": ,
            "projectDescription":
        }
     */
    @PostMapping("/newProject")
    public Result<Project> newProject(@RequestParam("userId") Integer userid, @RequestBody JSONObject object) {
        log.info("[ProjectController.NewProject] --- requesting create a project");
        Project project = new Project();
        project.setProjectName(object.getString("projectName"));
        project.setProjectDescription(object.getString("projectDescription"));
        return projectService.newProject(project, userid);
    }

    // 删除项目 DELETE url?传参
    /*
        url: /deleteProject?projectId=
     */
    @DeleteMapping("/deleteProject")
    public Result<String> deleteProject(@RequestParam("projectId") Integer projectId) {
        log.info("[ProjectController.DeleteProject] --- requesting delete a project");
        return projectService.deleteProject(projectId);
    }

    // 编辑项目信息 POST 请求体传参
    /*
        url: /modifyProject
        请求体参数
        {
            "projectId": ,
            "projectName": , (可选)
            "projectDescription": (可选)
     */
    @PostMapping("/modifyProject")
    public Result<String> modifyProject(@RequestBody JSONObject object) {
        log.info("[ProjectController.ModifyProject] --- requesting modify a project");
        Project project = new Project();
        project.setProjectId(object.getInteger("projectId"));
        project.setProjectName(object.getString("projectName"));
        project.setProjectDescription(object.getString("projectDescription"));
        return projectService.modifyProject(project);
    }


    // 根据projectId查询项目信息 GET url?传参
    /*
        url: /projectInfoPid?projectId=
     */
    @GetMapping("/projectInfoPid")
    public Result<Project> projectInfo(@RequestParam("projectId") Integer projectId) {
        log.info("[ProjectController.projectInfo] --- requesting project info");
        return projectService.getProjectInfo(projectId);
    }

    // 查询该用户参与的所有(未删除)项目信息 GET url?传参
    /*
        url: /userProjectInfo?userId=
     */
    @GetMapping("/userProjectInfo")
    public Result<List<Project>> userAllProjects(@RequestParam("userId") Integer userId) {
        log.info("[ProjectController.userAllProjects] --- requesting all projects of a user");
        return projectService.getUserProject(userId, 0);
    }

    // 查询回收站内该用户参与的所有项目信息 GET url?传参
    /*
        url: /deletedUserProjectInfo?userId=
     */
    @GetMapping("/deletedUserProjectInfo")
    public Result<List<Project>> deletedUserProjects(@RequestParam("userId") Integer userId) {
        log.info("[ProjectController.deletedUserProjects] --- requesting all deleted projects of a user");
        return projectService.getUserProject(userId, 1);
    }

    // 用户加入项目 POST 请求体传参
    /*
        url: /newUser
        请求体参数
        {
            "userId": ,
            "projectId":
        }
     */
    @PostMapping("/newUser")
    public Result<String> newProjectUser(@RequestBody JSONObject object) {
        log.info("[ProjectController.newProjectUser] --- requesting add a new user to a project");
        return projectService.NewProjectUser(object.getInteger("projectId"), object.getInteger("userId"));
    }

    // 查询项目成员 GET url?传projectId
    /*
        url: /allUser?projectId=
     */
    @GetMapping("/allUser")
    public Result<List<User>> allProjectUser(@RequestParam("projectId") Integer pid) {
        log.info("[ProjectController.allProjectUser] --- requesting all users of a project");
        return projectService.getProjectUser(pid);
    }

    // 删除项目成员 POST url?传pid 请求体传uid
    /*
        url: /deleteUser?projectId=
        请求体参数
        {
            "userId":
        }
     */
    @DeleteMapping("/deleteUser")
    public Result<String> deleteProjectUser(@RequestParam("projectId") Integer pid, @RequestBody JSONObject object) {
        log.info("[ProjectController.deleteProjectUser] --- requesting delete a member of a project");
        Integer uid = object.getInteger("userId");
        return projectService.deleteProjectUser(pid, uid);
    }

}
