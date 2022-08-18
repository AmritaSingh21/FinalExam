package com.example.finalloanproject.web;

import com.example.finalloanproject.entities.Loantable;
import com.example.finalloanproject.repositories.LoantableRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@SessionAttributes({"err"})
public class LoantableController {

    @Autowired
    private LoantableRepository repo;
    static String error = "";

    @GetMapping(path = "/index")
    public String getLoantableList(Model model, ModelMap mm, HttpSession session) {
        List<Loantable> list;
        list = repo.findAll();
        model.addAttribute("list", list);
        model.addAttribute("err", "");
        return "loantable";
    }

    @GetMapping("/delete")
    public String delete(Long id) {
        repo.deleteById(id);
        return "redirect:/index";
    }

    @PostMapping(path = "/save")
    public String addLoan(Model model, Loantable loan, BindingResult
            bindingResult, ModelMap mm, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "redirect:/index";
        } else {
            List<Loantable> clients = repo.findLoantableByClientno(loan.getClientno());
            System.out.println("reached");
            if(clients != null && clients.size()>0){
                List<Loantable> list;
                list = repo.findAll();
                model.addAttribute("list", list);
                model.addAttribute("err","The record you are trying to add is already "+
                        "existing. Choose a different customer number.");
                return "loantable";
            }
            repo.save(loan);
            return "redirect:index";
        }
    }

    @PostMapping(path = "/update")
    public String update(Model model, Loantable loan, BindingResult
            bindingResult, ModelMap mm, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "redirect:/index";
        } else {
            repo.save(loan);
            return "redirect:index";
        }
    }

    @GetMapping("/editLoan")
    public String editLoan(Model model, Long id, HttpSession session){
        Loantable client = repo.findById(id).orElse(null);
        if(client==null)
            throw new RuntimeException("Student does not exist");
        model.addAttribute("client", client);
        return "editLoan";
    }

    @GetMapping("/add")
    public String add(Model model, HttpSession session){
        model.addAttribute("client", new Loantable());
        return "addLoan";
    }

    @GetMapping(path = "/")
    public String getLoantableList2(Model model) {
        List<Loantable> list;
        list = repo.findAll();
        model.addAttribute("list", list);
        return "loantable";
    }
    
}
