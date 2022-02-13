package com.zd.esearch.controller;

import com.zd.esearch.dto.QuestionDTO;
import com.zd.esearch.model.Question;
import com.zd.esearch.model.User;
import com.zd.esearch.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model){
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        return "publish";
    }

    @GetMapping("/publish")
    public String Publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            @RequestParam(value = "id",required = false) Long id,
                            HttpServletRequest request,
                            Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        User user =(User) request.getSession().getAttribute("user");
        if (user==null){
            model.addAttribute("error","用户未登陆");
            return "publish";
        }
        if (title==null || title==""){
            model.addAttribute("error","问题标题不能为空");
            return "publish";
        }
        //User user =(User) request.getSession().getAttribute("user");
        if (description==null || description==""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }

        if (tag==null ||tag==""){
            model.addAttribute("error","标签不能为空,并且以逗号隔开");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator((long)user.getId());
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
