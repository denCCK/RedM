package com.example.Redm.controller;

import com.example.Redm.classes.Dates;
import com.example.Redm.classes.RedmineFilter;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    RedmineFilter redmineFilter = new RedmineFilter();
    Dates dates = new Dates();
    @GetMapping("/users")
    public String users(Model model) throws RedmineException {
        model.addAttribute("title", "Пользователи");
        List<User> users = redmineFilter.getUsersList();
        model.addAttribute("users", users);
        return "users";
    }
    @GetMapping("/user/{id}")
    public String user(@PathVariable int id, Model model) throws RedmineException {
        User user = redmineFilter.getManager().getUserManager().getUserById(id);
        model.addAttribute("title", user.getFirstName() + ' ' + user.getLastName());
        model.addAttribute("user", user);
        return "user";
    }

    // TODO: 04.05.2023 Общий метод 
//    @GetMapping("/user/{id}/{type}")
//    public String SearchIssues(@PathVariable int id, @PathVariable String type, Model model) throws RedmineException {
//        List<Issue> issues =  redmineFiler.SearchAllAssignedIssues(id);
//        switch (type) {
//            case "allIssues":
//                break;
//        }
//        model.addAttribute("title", "Задачи");
//        model.addAttribute("issues", issues);
//        return "issues";
//    }
    
    @GetMapping("/user/{id}/allIssues")
    public String allIssues(@PathVariable int id, Model model) throws RedmineException {
        List<Issue> issues =  redmineFilter.SearchAllAssignedIssues(id);
        model.addAttribute("title", "Задачи");
        model.addAttribute("issues", issues);
        return "issues";
    }
    @GetMapping("/user/{id}/less50Issues")
    public String less50Issues(@PathVariable int id, Model model) throws RedmineException {
        List<Issue> issues =  redmineFilter.SearchByLessHalfDoneRatioAndAssignedIssues(id);
        model.addAttribute("title", "Задачи");
        model.addAttribute("issues", issues);
        return "issues";
    }
    @GetMapping("/user/{id}/more50Issues")
    public String more50Issues(@PathVariable int id, Model model) throws RedmineException {
        List<Issue> issues =  redmineFilter.SearchByMoreHalfDoneRatioAndAssignedIssues(id);
        model.addAttribute("title", "Задачи");
        model.addAttribute("issues", issues);
        return "issues";
    }

    @GetMapping("/user/{id}/byStatusIssues/{statusId}")
    public String byStatusIssues(@PathVariable int id, @PathVariable int statusId, Model model) throws RedmineException {
        List<Issue> issues =  redmineFilter.SearchByStatusAndAssignedIssues(id, statusId);
        model.addAttribute("title", "Задачи");
        model.addAttribute("issues", issues);
        return "issues";
    }

    @GetMapping("/user/{id}/byPriorityIssues/{priorityId}")
    public String byPriorityIssues(@PathVariable int id, @PathVariable int priorityId, Model model) throws RedmineException {
        List<Issue> issues =  redmineFilter.SearchByPriorityAndAssignedIssues(id, priorityId);
        model.addAttribute("title", "Задачи");
        model.addAttribute("issues", issues);
        return "issues";
    }

    @GetMapping("/user/{id}/dates/{searchId}")
    public String datesIn(@PathVariable int id, @PathVariable int searchId, Model model) {
        String option = "";
        switch (searchId) {
            case 3:
                option += "YM";
                break;
        }
        model.addAttribute("dates", dates);
        model.addAttribute("id", id);
        model.addAttribute("searchId", searchId);
        return "dates" + option;
    }

    @PostMapping("/user/{id}/dates/{searchId}")
    public String datesOut(@PathVariable int id, @PathVariable int searchId, @ModelAttribute Dates dates, Model model) throws ParseException {
        this.dates = dates;
        return "redirect:/user/" + id + "/timeEntrySearch/" + searchId;
    }

    @GetMapping("/user/{id}/timeEntrySearch/{searchId}")
    public String timeEntrySearch(@PathVariable int id, @PathVariable int searchId, Model model) {
        Map<String, Float> entries = new HashMap<>();
        switch (searchId) {
            case 1:
                //entries = redmineFiler.getManager().getTimeEntryManager().getTimeEntries()
                break;
            case 2:
                entries = redmineFilter.SearchLess8HourDays(id, dates.StartDateToString(), dates.EndDateToString());
                break;
            case 3:
                entries = redmineFilter.SearchLess40HourDWeeks(id, dates.getYear(),dates.getMonth());
                break;
        }
        model.addAttribute("entries", entries);
        return "entry";
    }
}
