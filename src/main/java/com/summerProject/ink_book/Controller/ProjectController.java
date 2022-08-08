package com.summerProject.ink_book.Controller;

import com.alibaba.fastjson.JSONObject;
import com.summerProject.ink_book.Entity.Project;
import com.summerProject.ink_book.Service.ProjectService;
import com.summerProject.ink_book.Utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
            "groupId": ,
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
        project.setCreateTime(LocalDateTime.now());
        return projectService.newProject(project, userid, object.getInteger("groupId"));
    }

    // 删除项目 DELETE url?传用户id 请求体传团队id和项目id
    /*
        url: /deleteProject?userId=
        请求体参数
        {
            "groupId": ,
            "projectId":
        }
     */
    @DeleteMapping("/deleteProject")
    public Result<String> deleteProject(@RequestParam("userId") Integer userId, @RequestBody JSONObject object) {
        log.info("[ProjectController.DeleteProject] --- requesting delete a project");
        return projectService.deleteProject(userId, object.getInteger("groupId"), object.getInteger("projectId"));
    }

    // 编辑项目信息 POST 请求体传参
    /*
        url: /modifyProject
        请求体参数
        {
            "projectId": ,
            "projectName": , (可选)
            "projectDescription": (可选)
        }
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
        url: /projectInfo?projectId=
     */
    @GetMapping("/projectInfo")
    public Result<Project> projectInfo(@RequestParam("projectId") Integer projectId) {
        log.info("[ProjectController.projectInfo] --- requesting project info");
        return projectService.getProjectInfo(projectId);
    }

    // 查询团队所有项目并排序 GET url?传参
    /*
        url: /groupProject?groupId= &sort=
        sort参数
        无 默认排序
        time1 按时间从旧到新
        time2 按时间从新到旧
        name1 按项目名称字典序
        name2 按项目名称字典倒序
     */
    @GetMapping("/groupProject")
    public Result<List<Project>> groupProject(@RequestParam("groupId") Integer groupId, @RequestParam(value = "sort", required = false, defaultValue = "") String sort) {
        log.info("[ProjectController.groupProject] --- requesting all projects of a group");
        return projectService.getGroupProject(groupId, 0, sort);
    }

    // 查询团队回收站项目 GET url?传参
    /*
        url: /groupDeletedProject?groupId= &sort=
        sort参数
        无 默认排序
        time1 按时间从新到旧
        time2 按时间从旧到新
        name1 按项目名称字典序
        name2 按项目名称字典倒序
     */
    @GetMapping("/groupDeletedProject")
    public Result<List<Project>> groupDeletedProject(@RequestParam("groupId") Integer groupId, @RequestParam(value = "sort", required = false, defaultValue = "") String sort) {
        log.info("[ProjectController.groupDeletedProject] --- requesting all deleted projects of a group");
        return projectService.getGroupProject(groupId, 1, sort);
    }

    // 根据关键词搜索项目 GET url?传关键词 请求体传groupId
    /*
        url: /search?word=
        请求体参数
        {
            "groupId":
        }
     */
    @GetMapping("/search")
    public Result<List<Project>> searchProject(@RequestParam("word") String word, @RequestBody JSONObject object) {
        log.info("[ProjectController.searchProject] --- requesting search a project with words");
        return projectService.getProjectByCons(object.getInteger("groupId"), word);
    }

}
