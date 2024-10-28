package vt01.demo_thy.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

@RestController
@RequestMapping("admin/categories")
public class CategoryController {
    ICategoryService categoryService;

    @GetMapping("add")
    public String add(ModelMap model) {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setIsEdit(false);
        model.addAttribute("category", categoryModel);
        return "admin/categories/addOrEdit";
    }

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model,
                                     @Valid @ModelAttribute("category") CategoryModel categoryModel, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("admin/categories/addOrEdit");
        }

        CategoryEntity entity = new CategoryEntity();
        BeanUtils.copyProperties(categoryModel, entity);
        categoryService.save(entity);
        String message = "";
        if (categoryModel.isIsEdit() == true) {
            message = "Category is Edited!!!";
        } else {
            message = "Category is saved";
        }
        model.addAttribute("message", message);
        return new ModelAndView("admin/categories/searchpaginated");

    }


    @RequestMapping("")
    public String list(ModelMap model) {
        List<CategoryEntity> list = categoryService.findAll();
        model.addAttribute("category", list);
        return "admin/categories/list";

    }

    @GetMapping("edit/categoryId")
    public ModelAndView edit(ModelMap model, @PathVariable("categoryId") Long categoryId) {
        Optional<CategoryEntity> optionalCategory = categoryService.findById(categoryId);
        CategoryModel categoryModel = new CategoryModel();
        if (optionalCategory.isPresent()) {
            CategoryEntity entity = optionalCategory.get();
            BeanUtils.copyProperties(entity, categoryModel);
            categoryModel.setIsEdit(true);
            model.addAttribute("admin/categories/addOrEdit", categoryModel);
            return new ModelAndView("admin/categories/addOrEdit", model);

        }

        model.addAttribute("message", "Category not found");
        return new ModelAndView("forward: admin/categories", model);
    }

    @GetMapping("delete/{categoryId}")
    public ModelAndView delete(ModelMap model, @PathVariable("categoryId") Long categoryId) {
        categoryService.deleteById(categoryId);
        model.addAttribute("message", "Category deleted successfully");
        return new ModelAndView("forward: admin/categories/searchpaginated", model);
    }

    @GetMapping("search")
    public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
        List<CategoryEntity> list = null;
        if (StringUtils.hasText(name)) {
            list = categoryService.findByCategoryNameContaining(name);

        } else {
            list = categoryService.findAll();
        }

        model.addAttribute("categories", list);
        return "admin/categories/search";
    }

    @RequestMapping("searchpaginated")
    public String search(ModelMap model,
        @RequestParam(name = "name", required = false) String name,
        @RequestParam("page") Optional<Integer> page,
        @RequestParam("size") Optional<Integer> size){

        int count = (int) categoryService.count();
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);
        Pageable pageable = (Pageable) PageRequest.of(currentPage-1, pageSize, Sort.by("name"));
        Page<CategoryEntity> resultPage = null;
        if(StringUtils.hasText(name)){
            resultPage = categoryService.findByCategoryNameContaining(name, pageable);
            model.addAttribute("name", name);
        }
        else{
            resultPage = categoryService.findAll(pageable);
        }

        int totalPages = resultPage.getTotalPages();
        if(totalPages>0){
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);
            if(totalPages>count){
                if(end == totalPages) start = end - count;
                else if (start==1)  end = start + count;
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("categoryPage", resultPage);
        return "admin/categories/searchpaginated";
    }


}