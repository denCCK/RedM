package com.example.Redm.controller;

import com.example.Redm.classes.RedmineFilter;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProjectController {
    RedmineFilter redmineFilter = new RedmineFilter();
    @GetMapping("/projects")
    public String projects(Model model) throws RedmineException {
        model.addAttribute("title", "Проекты");
        List<Project> projects = redmineFilter.getProjectsList();
        model.addAttribute("projects", projects);
        return "projects";
    }
    @GetMapping("/project/{id}")
    public String project(@PathVariable int id, Model model) throws RedmineException {
        Project project = redmineFilter.getManager().getProjectManager().getProjectById(id);
        model.addAttribute("title", project.getName());
        model.addAttribute("project", project);
        return "project";
    }
    @GetMapping("/project/{id}/allIssues")
    public String allIssues(@PathVariable int id, Model model) throws RedmineException {
        List<Issue> issues =  redmineFilter.SearchAllIssues(id);
        model.addAttribute("title", "Задачи");
        model.addAttribute("issues", issues);
        return "issues";
    }
    @GetMapping("/project/{id}/less50Issues")
    public String less50Issues(@PathVariable int id, Model model) throws RedmineException {
        List<Issue> issues =  redmineFilter.SearchByLessHalfDoneRatioIssues(id);
        model.addAttribute("title", "Задачи");
        model.addAttribute("issues", issues);
        return "issues";
    }
    @GetMapping("/project/{id}/more50Issues")
    public String more50Issues(@PathVariable int id, Model model) throws RedmineException {
        List<Issue> issues =  redmineFilter.SearchByMoreHalfDoneRatioIssues(id);
        model.addAttribute("title", "Задачи");
        model.addAttribute("issues", issues);
        return "issues";
    }
    @GetMapping("/project/{id}/byStatusIssues/{statusId}")
    public String byStatusIssues(@PathVariable int id, @PathVariable int statusId, Model model) throws RedmineException {
        List<Issue> issues =  redmineFilter.SearchByStatusIssues(id, statusId);
        model.addAttribute("title", "Задачи");
        model.addAttribute("issues", issues);
        return "issues";
    }

    @GetMapping("/project/{id}/byStatusIssues/{priorityId}")
    public String byPriorityIssues(@PathVariable int id, @PathVariable int priorityId, Model model) throws RedmineException {
        List<Issue> issues =  redmineFilter.SearchByPriorityIssues(id, priorityId);
        model.addAttribute("title", "Задачи");
        model.addAttribute("issues", issues);
        return "issues";
    }
}
