package ra.crudjsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ra.crudjsp.dto.ProductRequest;
import ra.crudjsp.model.Product;
import ra.crudjsp.service.IProductService;

import java.util.List;

@Controller
@RequestMapping("/product-controller")
public class ProductControllerMVC {
    @Autowired
    private IProductService productService;
    @RequestMapping(method = RequestMethod.GET)
    public String showAll(Model model){
        List<Product> list = productService.findAll();
        model.addAttribute("list",list);
        return "product";
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(@ModelAttribute ProductRequest productRequest){
        productService.add(productRequest);
        return "redirect:/product-controller";
    }
    @RequestMapping(value = "/delete" , method = RequestMethod.GET)
    public String delete(@RequestParam Long id){
        productService.deleteById(id);
        return "redirect:/product-controller";
    }
    @RequestMapping(value = "/edit" , method = RequestMethod.GET)
    public String edit(@RequestParam Long id , Model model){
        Product product = productService.findById(id);
        model.addAttribute("product",product);
        return "edit";
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(@ModelAttribute Product product){
        productService.update(product);
        return "redirect:/product-controller";
    }
}
