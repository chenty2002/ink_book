package com.summerProject.ink_book.Controller;

import com.summerProject.ink_book.Entity.Project;
import com.summerProject.ink_book.Entity.User;
import com.summerProject.ink_book.Service.ProjectService;
import com.summerProject.ink_book.Service.ProjectUserService;
import com.summerProject.ink_book.Utils.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectUserService projectUserService;//新增删除用户与项目关系

    private final ProjectService projectService;

    public ProjectController(ProjectUserService projectUserService, ProjectService projectService) {
        this.projectUserService = projectUserService;
        this.projectService = projectService;
    }


    // 建立项目 POST 请求体传参
    @PostMapping("/newProject")
    public Result<Project> NewProject(@RequestBody Project project) {
        return projectService.NewProject(project);
    }

    // 删除项目 GET url?传参
    @GetMapping("/deleteProject/{projectId}")
    public Result<Project> DeleteProject(@PathVariable("projectId") Integer projectId) {
        /*if(!projectUserService.FindProjectUser(projectId,userId)){
            return Result.fail("delete project Failure");
        }//判断用户是否拥有该项目*/

        /*if(result.getMsg().equals("delete project success")){//删除项目成功
            projectUserService.DeleteProjectUserByPId(projectId);//删除用户项目关系
        }*/
        return projectService.DeleteProject(projectId);
    }

    // 编辑项目信息 POST 请求体传参
    @PostMapping("/modifyProject")
    public Result<Project> ModifyProject(@RequestBody Project project) {
        /*if(!projectUserService.FindProjectUser(projectId,userId)){
            return Result.fail("modify project Failure");
        }//判断用户是否拥有该项目*/
        return projectService.ModifyProject(project);
    }


    // 根据projectId查询项目信息 GET url?传参
    @GetMapping("/projectInfoPid/{projectId}")
    public Result<Project> projectInfo(@PathVariable("projectId") Integer projectId) {
        return projectService.getProjectInfo(projectId);
    }

    // 查询该用户参与的所有项目信息 GET url?传参
    @GetMapping("/UserProjectInfo/{userId}")
    public Result<List<Project>> userAllProjects(@PathVariable("userId") Integer userId) {
        return projectService.getUserProject(userId, 0);
    }

    // 查询回收站内该用户参与的所有项目信息 GET url?传参
    @GetMapping("/deletedUserProjectInfo/{userId}")
    public Result<List<Project>> deletedUserProjects(@PathVariable("userId") Integer userId) {
        return projectService.getUserProject(userId, 1);
    }

    // 用户加入项目 POST url?传pid 请求体传uid
    @PostMapping("/newUser")
    public Result<String> newProjectUser(Integer pid, @RequestBody Integer uid) {
        return projectUserService.NewProjectUser(pid, uid);
    }

    // 查询项目成员 GET url?传projectId
    @GetMapping("/allUser/{projectId}")
    public Result<List<User>> allProjectUser(@PathVariable("projectId") Integer pid) {
        return projectUserService.getProjectUser(pid);
    }

    // 删除项目成员 POST url?传pid 请求体传uid
    @PostMapping("deleteUser")
    public Result<String> deleteProjectUser(Integer pid, @RequestBody Integer uid) {
        return projectUserService.deleteProjectUser(pid, uid);
    }

}
