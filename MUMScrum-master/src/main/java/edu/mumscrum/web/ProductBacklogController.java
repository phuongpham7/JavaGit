package edu.mumscrum.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mumscrum.domain.Product;
import edu.mumscrum.service.ProductService;

@Controller
public class ProductBacklogController {
	private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value ="/product/list", method= RequestMethod.GET)
	public String getProduct(Model model){
		model.addAttribute("product", productService.getAllProducts());
		return "product/list";
	}
	
	@RequestMapping(value = "/product/add", method = RequestMethod.GET)
    public String add(Model model) {
        return "product/add";
    }
	
	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
    public String add(@RequestParam(value = "inputName")String name, @RequestParam(value = "inputDescription")String description){
		Product product = new Product();
		product.setName(name);
		product.setDescription(description);

        productService.save(product);

        return "redirect:/product/" + product.getId();
    }
	
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public String showProduct(@PathVariable("id") long id, Model model) {

		logger.debug("showUser() id: {}", id);

		Product product = productService.findById(id);
		if (product == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "User not found");
		}
		model.addAttribute("pb", product);

		return "product/show";

	}
}
