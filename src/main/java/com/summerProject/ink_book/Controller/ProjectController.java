package com.summerProject.ink_book.Controller;

import com.alibaba.fastjson.JSONObject;
import com.summerProject.ink_book.Entity.Project;
import com.summerProject.ink_book.Service.ProjectService;
import com.summerProject.ink_book.Utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    // 建立项目 POST 请求体传项目信息
    /*
        url: /newProject
        请求体参数
        {
            "groupId": ,
            "projectName": ,
            "projectDescription":
        }
     */
    @PostMapping("/newProject")
    public Result<Project> newProject(@RequestBody JSONObject object) {
        log.info("[ProjectController.NewProject] --- requesting create a project");
        Project project = new Project();
        project.setProjectName(object.getString("projectName"));
        project.setProjectDescription(object.getString("projectDescription"));
        project.setCreateTime(LocalDateTime.now());
        return projectService.newProject(project, object.getInteger("groupId"));
    }

    // 删除项目 DELETE 请求体传项目id
    /*
        url: /deleteProject
        请求体参数
        {
            "projectId":
        }
     */
    @DeleteMapping("/deleteProject")
    public Result<String> deleteProject(@RequestBody JSONObject object) {
        log.info("[ProjectController.DeleteProject] --- requesting delete a project");
        return projectService.deleteProject(object.getInteger("projectId"));
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
        url: /groupProject?groupId=
     */
    @GetMapping("/groupProject")
    public Result<List<Project>> groupProject(@RequestParam("groupId") Integer groupId) {
        log.info("[ProjectController.groupProject] --- requesting all projects of a group");
        return projectService.getGroupProject(groupId, 0);
    }

    // 查询团队回收站项目 GET url?传参
    /*
        url: /groupDeletedProject?groupId=
     */
    @GetMapping("/groupDeletedProject")
    public Result<List<Project>> groupDeletedProject(@RequestParam("groupId") Integer groupId) {
        log.info("[ProjectController.groupDeletedProject] --- requesting all deleted projects of a group");
        return projectService.getGroupProject(groupId, 1);
    }

    // 根据关键词搜索项目 POST url?传关键词和日期范围 请求体传groupId
    /*
        url: /search?word= &start= &end=
        请求体参数
        {
            "groupId": ,
            "deleted":
        }
     */
    @PostMapping("/search")
    public Result<List<Project>> searchProject(@RequestParam(value = "word", required = false) String word,
                                               @RequestParam(value = "start", required = false) String startTime,
                                               @RequestParam(value = "end", required = false) String endTime,
                                               @RequestBody JSONObject object) {
        log.info("[ProjectController.searchProject] --- requesting search a project with words");
        LocalDateTime start, end;
        if (startTime != null)
            start = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        else start = null;
        if (endTime != null)
            end = LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        else end = null;
        return projectService.getProjectByCons(object.getInteger("groupId"), word, start, end, object.getInteger("deleted"));
    }


    @PostMapping("/restore")
    public Result<String> restoreProject(@RequestBody JSONObject object) {
        return projectService.restoreProject(object.getInteger("projectId"));
    }
}
