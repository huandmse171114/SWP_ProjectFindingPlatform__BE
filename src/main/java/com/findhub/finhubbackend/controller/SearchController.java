package com.findhub.finhubbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.findhub.finhubbackend.controller.ProjectController;

import com.findhub.finhubbackend.dto.MajorDTO;
import com.findhub.finhubbackend.dto.MemberDTO;
import com.findhub.finhubbackend.dto.PublisherDTO;
import com.findhub.finhubbackend.dto.TeamDTO;
import com.findhub.finhubbackend.entity.account.Account;
import com.findhub.finhubbackend.model.ProjectResponseModel;
import com.findhub.finhubbackend.model.SearchResponseModel;
import com.findhub.finhubbackend.service.category.CategoryService;
import com.findhub.finhubbackend.service.major.MajorService;
import com.findhub.finhubbackend.service.member.MemberService;
import com.findhub.finhubbackend.service.publisher.PublisherService;
import com.findhub.finhubbackend.service.search.SearchService;
import com.findhub.finhubbackend.service.skill.SkillService;
import com.findhub.finhubbackend.service.team.TeamService;
import com.findhub.finhubbackend.util.Config.ApiPath;
import com.findhub.finhubbackend.util.Config.SubPath;
import com.findhub.finhubbackend.util.Config.Var;
import com.findhub.finhubbackend.util.Utils;

@RestController
@RequestMapping(ApiPath.SEARCH)
public class SearchController {

    @Autowired
    private SearchService filterService;

    @Autowired
    private ProjectController projectController;

    @Autowired
    private MajorService majorService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private MemberService memberService;

    @GetMapping(SubPath.SEARCH_ACCOUNTS)
    public Account filterAccountById(@PathVariable("id") int id) {
        return filterService.findAccountById(id);
    }

    private List<ProjectResponseModel> getProjects(String keyword) {
        // List<ProjectResponseModel> projects;

        return (Utils.isNum(keyword))
                ? projectController.getAllByNameContainingOrIdLike(keyword)
                : projectController.findAllByNameContaining(keyword);
    }

    private List<MajorDTO> getMajors(String keyword) {
        // List<MajorDTO> majors;

        return (Utils.isNum(keyword))
                ? majorService.getAllByNameLikeOrCodeLikeOrIdLike(keyword)
                : majorService.getAllByNameLikeOrCodeLike(keyword);
    }

    private List<MemberDTO> getMembers(String keyword) {
        // List<MemberDTO> members;

        return (Utils.isNum(keyword))
                ? memberService.getAllByNameContainingOrEmailContainingOrPhoneContainingOrIdLike(keyword)
                : memberService.getAllByNameContaining(keyword);

    }

    private List<TeamDTO> getTeams(String keyword) {
        // List<TeamDTO> teams;

        return (Utils.isNum(keyword))
                ? teamService.getAllByIdStartingWithOrNameContaining(keyword)
                : teamService.getAllByNameContaining(keyword);
    }

    private List<PublisherDTO> getPublishers(String keyword) {
        // List<PublisherDTO> publishers;

        return (Utils.isNum(keyword))
                ? publisherService.getAllByNameContainingOrEmailContainingOrPhoneContainingOrIdLike(keyword)
                : publisherService.getAllByNameContainingOrEmailContainingOrPhoneContaining(keyword);
    }

    private List<String> getSkills(String keyword) {
        List<String> skills = new ArrayList<>();

        skillService.findAllByNameContaining(keyword)
                .forEach(each -> skills.add(each.getName()));

        return skills;
    }

    private List<String> getCategories(String keyword) {
        List<String> categories = new ArrayList<>();

        categoryService.findAllByNameContaining(keyword)
                .forEach(each -> categories.add(each.getName()));

        return categories;
    }

    @GetMapping(SubPath.SEARCH_PROJECTS)
    public ResponseEntity<?> responseProjects(@PathVariable(Var.KEYWORD) String keyword) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getProjects(keyword));
    }

    @GetMapping(SubPath.SEARCH_MAJORS)
    public ResponseEntity<?> responseMajors(@PathVariable(Var.KEYWORD) String keyword) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getMajors(keyword));
    }

    @GetMapping(SubPath.SEARCH_MEMBERS)
    public ResponseEntity<?> responseMembers(@PathVariable(Var.KEYWORD) String keyword) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getMembers(keyword));
    }

    @GetMapping(SubPath.SEARCH_TEAMS)
    public ResponseEntity<?> responseTeams(@PathVariable(Var.KEYWORD) String keyword) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getTeams(keyword));
    }

    @GetMapping(SubPath.SEARCH_SKILLS)
    public ResponseEntity<?> responseSkills(@PathVariable(Var.KEYWORD) String keyword) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getSkills(keyword));
    }

    @GetMapping(SubPath.SEARCH_CATEGORIES)
    public ResponseEntity<?> responseCategories(@PathVariable(Var.KEYWORD) String keyword) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getCategories(keyword));
    }

    @GetMapping(SubPath.KEYWORD)
    public ResponseEntity<?> getAllMatchResult(@PathVariable(Var.KEYWORD) String keyword) {
        List<ProjectResponseModel> projects = getProjects(keyword);
        List<MajorDTO> majors = getMajors(keyword);
        List<MemberDTO> members = getMembers(keyword);
        List<PublisherDTO> publishers = getPublishers(keyword);
        List<TeamDTO> teams = getTeams(keyword);
        List<String> skills = getSkills(keyword);
        List<String> categories = new ArrayList<>();

        categoryService.findAllByNameContaining(keyword)
                .forEach(each -> categories.add(each.getName()));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SearchResponseModel
                        .builder()
                        .projects(projects)
                        .skills(skills)
                        .members(members)
                        .publishers(publishers)
                        .teams(teams)
                        .categories(categories)
                        .majors(majors)
                        .build());
    }
}
