package com.example.demo.Controller;

import com.example.demo.dao.RemunerationRepository;
import com.example.demo.entities.Departement;
import com.example.demo.entities.Remuneration;
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
public class RemunerationController {
    @Autowired
    private RemunerationRepository remunerationRepository;

    @GetMapping("/user/remuneration")
    public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int page
            , @RequestParam(name = "search", defaultValue = "") String searchStr) {
        Page<Remuneration> remunerations =
                remunerationRepository.findByNomContains(searchStr, PageRequest.of(page, 5));
        model.addAttribute("listRemunerations", remunerations);
        model.addAttribute("pages", new int[remunerations.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("search", searchStr);
        return "remuneration/remuneration";
    }

    @GetMapping("/admin/remuneration/delete")
    public String delete(Long id, int page, String search) {
        remunerationRepository.deleteById(id);
        return "redirect:/user/departement?page=" + page + "&search=" + search;
    }

    @GetMapping("/admin/remuneration/createRemForm")
    public String form(Model model) {
        model.addAttribute("remuneration", new Remuneration());
        return "remuneration/createRem";
    }

    @PostMapping("/admin/remuneration/save")
    public String saveEmp(Model model, @Valid Remuneration remuneration, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("remuneration", remuneration);
            return "remuneration/createRem";
        }
        remunerationRepository.save(remuneration);
        return "redirect:/user/remuneration";
    }

    @GetMapping("/admin/remuneration/edit")
    public String form(Model model, Long id) {
        Remuneration remuneration = remunerationRepository.findById(id).get();
        model.addAttribute("remuneration", remuneration);
        return "editRem";
    }
}
