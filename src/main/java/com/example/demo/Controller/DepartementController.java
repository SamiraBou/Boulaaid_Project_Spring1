package com.example.demo.Controller;

import com.example.demo.dao.DepartementRepository;
import com.example.demo.entities.Departement;
import com.example.demo.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class DepartementController {
    @Autowired
    private DepartementRepository departementRepository;

    @GetMapping("/user/departement")
    public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int page
            , @RequestParam(name = "search", defaultValue = "") String searchStr) {
        Page<Departement> departements =
                departementRepository.findByNomContains(searchStr, PageRequest.of(page, 5));
        model.addAttribute("listDepartements", departements);
        model.addAttribute("pages", new int[departements.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("search", searchStr);
        return "dept/departement";
    }

    @GetMapping("/admin/departement/delete")
    public String delete(Long id, int page, String search) {
        departementRepository.deleteById(id);
        return "redirect:/user/departement?page=" + page + "&search=" + search;
    }

    @GetMapping("/admin/createDeptForm")
    public String form(Model model) {
        model.addAttribute("employe", new Employee());
        return "dept/createDept";
    }

    @PostMapping("/admin/departement/save")
    public String saveEmp(Model model, @Valid Departement departement, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("departement", departement);
            return "dept/createDept";
        }
        departementRepository.save(departement);
        return "redirect:/user/departement";
    }

    @GetMapping("/admin/departement/edit")
    public String form(Model model, Long id) {
        Departement departement = departementRepository.findById(id).get();
        model.addAttribute("departement", departement);
        return "editDept";
    }
}
