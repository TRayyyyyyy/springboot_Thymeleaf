package vt01.demo_thy.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vt01.demo_thy.Entity.CategoryEntity;
import vt01.demo_thy.Model.CategoryModel;
import vt01.demo_thy.imp.ICategoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;

    @GetMapping()
    public String getAllCategory(ModelMap model) {
        List<CategoryEntity> list = categoryService.findAll();
        model.addAttribute("categories", list);
        return "admin/categories/list";
    }

    @GetMapping("/add")
    public String add(CategoryEntity category, ModelMap model){
        model.addAttribute("category", category);
        return "admin/categories/add";
    }

    @PostMapping("/saveOfAdd")
    public String saveOfAdd(CategoryEntity category, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "admin/categories/add";
        }
        categoryService.save(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model){
        CategoryEntity category = categoryService.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        model.addAttribute("category", category);
        return "admin/categories/edit";
    }

    @PostMapping("/saveOfEdit/{id}")
    public String update(@PathVariable("id") long id,CategoryEntity category, BindingResult result,
                         Model model){
        if (result.hasErrors()){
            return "admin/categories/edit";
        }
        category.setCategoryId(id);
        categoryService.save(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model){
        categoryService.deleteById(id);
        return "redirect:/admin/category";
    }

}
