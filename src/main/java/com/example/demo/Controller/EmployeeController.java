package com.example.demo.Controller;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.entities.Employee;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String home(){
        return "redirect:/user/index";
    }

    //@RequestMapping(value = "/index", method = RequestMethod.GET)
    @GetMapping("/user/index")
    public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int page
            , @RequestParam(name = "search", defaultValue = "") String searchStr){
        //List<Employee> listEmp = employeeRepository.findAll();
        //model.addAttribute("listEmployees", listEmp);
        //Page<Employee> employees = employeeRepository.findAll(PageRequest.of(page, 5));
        Page<Employee> employees =
                employeeRepository.findByfirstNameContains(searchStr, PageRequest.of(page, 5));
        model.addAttribute("listEmployees", employees);
        model.addAttribute("pages", new int[employees.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("search", searchStr);
        return  "employees";
    }

    @GetMapping("/admin/delete")
    public  String delete(Long id, int page , String search){
        employeeRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&search="+search;
    }

    @GetMapping("/admin/createEmpForm")
    public String form(Model model){
        model.addAttribute("employe", new Employee());
        return "createEmp";
    }

    @PostMapping("/admin/save")
    public String saveEmp(Model model, @Valid Employee employe, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("employe", employe);
            return "createEmp";
        }
        employeeRepository.save(employe);
        return "redirect:/user/index";
    }

    @GetMapping("/admin/edit")
    public String form(Model model, Long id){
        Employee employee = employeeRepository.findById(id).get();
        model.addAttribute("employe", employee);
        return "editEmp";
    }

    @GetMapping("/403")
    public String notAut(){
        return "403";
    }

    @GetMapping("/login")
    public String login(){
        return "login1";
    }
}
