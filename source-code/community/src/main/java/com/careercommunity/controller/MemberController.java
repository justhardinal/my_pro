package com.careercommunity.controller;

import com.careercommunity.dao.MemberDao;
import com.careercommunity.entity.Member;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member")
public class MemberController {
    
    @Autowired
    MemberDao md;
    
    @InitBinder //convert date 
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
    @RequestMapping("/list")
    public void listMember(Model mdl) {
        mdl.addAttribute("listMember", md.findAll());
    }
    
    @RequestMapping("/delete")
    public String hapus(@RequestParam(value = "id") String id) {
        md.delete(id);
        return "redirect:list";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Member member) {
        return "register";
    }
    
    @RequestMapping(value = "/view{id}", method = RequestMethod.GET)
    public String viewForm(@RequestParam(value = "id") String id, 
            Model mdl){
        mdl.addAttribute("member",new Member());
        
        if (id!=null && !id.isEmpty()){
            Member m = md.findOne(id);
            if (m != null){
                mdl.addAttribute("member", m);
            }
        }
        return "/member/view";
    }
    
    @RequestMapping(value = "/edit{id}", method = RequestMethod.GET)
    public String editForm(@RequestParam(value = "id") String id, 
            Model mdl){
        mdl.addAttribute("member",new Member());
        
        if (id!=null && !id.isEmpty()){
            Member m = md.findOne(id);
            if (m != null){
                mdl.addAttribute("member", m);
            }
        }
        return "/member/edit";
    }
    
    @RequestMapping(value = "/edit{id}", method = RequestMethod.POST)
    public String prosesUpdateForm(@RequestParam(value = "id") String id,
            @Valid Member m,
            BindingResult error) {
        if (error.hasErrors()) {
            return "/member/edit";
        }
        m.setId(id);
        md.save(m);
        return "redirect:list";
    }
    
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addForm(Member member) {
        return "/member/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addProsesForm(@Valid Member m, BindingResult error) {
        if (error.hasErrors()) {
            return "/member/add";
        }

        md.save(m);
        return "redirect:list";
    }
    
    
}
