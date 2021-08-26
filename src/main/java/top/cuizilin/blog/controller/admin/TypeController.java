package top.cuizilin.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.cuizilin.blog.pojo.Type;
import top.cuizilin.blog.service.TypeService;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@RequestParam(defaultValue = "1", required = false, value = "pageNo") Integer pageNo, Model model){
        PageHelper.startPage(pageNo, 5);
        PageInfo pageInfo = new PageInfo(typeService.getTypeList());
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type", new Type());
        return "admin/type-input";
    }


    @GetMapping("/types/update")
    public String update(Integer id, Model model){
        Type type = typeService.getTypeById(id);
        model.addAttribute("type", type);
        return "admin/type-input";
    }

    @PostMapping("/types/input")
    public String inputType(Type type){
        Integer id = type.getId();
        if(id == null){
            //如果id为空，则新增
            typeService.addType(type);
            return "redirect:/admin/types";
        }
        //id不为空，则修改
        typeService.updateType(type);
        return "redirect:/admin/types";
    }

    @GetMapping("/types/delete")
    public String delete(Integer id){
        typeService.deleteTypeById(id);
        return "redirect:/admin/types";
    }
}
