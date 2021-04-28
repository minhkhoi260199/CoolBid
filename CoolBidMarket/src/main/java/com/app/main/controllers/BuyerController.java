package com.app.main.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.main.models.Product;
import com.app.main.paypal.PayPalResult;
import com.app.main.paypal.PayPalSucess;
import com.app.main.services.PayPalService;
import com.app.main.services.ProductService;
import com.app.main.services.RoleService;
import com.app.main.services.UserService;

@Controller
@RequestMapping({"buyer"})
public class BuyerController {
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	ProductService productService;
	@Autowired
	private PayPalService payPalService;
	
	@RequestMapping(value = {"", "index"} ,method = RequestMethod.GET)
	public String index(ModelMap modelMap, HttpServletRequest request, Authentication authentication) {
		PayPalResult result = PayPalSucess.getPayPal(request, payPalService.getPayPalConfig());
		modelMap.put("users", userService.findUserByUsername(authentication.getName()));
		modelMap.put("roles", roleService.findAll());
		System.out.println("Welcome: " +authentication.getName());
		return "buyer/index";
	}
	
	@RequestMapping(value = {"invoices"} ,method = RequestMethod.GET)
	public String index2(ModelMap modelMap, Authentication authentication) {
		modelMap.put("users", userService.findUserByUsername(authentication.getName()));
		modelMap.put("roles", roleService.findAll());
		modelMap.put("products", productService.findAll());
		return "buyer/invoices";
	}
	@RequestMapping(value = {"invoiceDetail/{id}"} ,method = RequestMethod.GET)
	public String invoiceDetail(@PathVariable("id") int id,ModelMap modelMap, Authentication authentication) {
		modelMap.put("users", userService.findUserByUsername(authentication.getName()));
		modelMap.put("roles", roleService.findAll());
		Product product = productService.findById(id);
		modelMap.put("product", product);
		modelMap.put("paypalConfig", payPalService.getPayPalConfig());
		return "buyer/invoiceDetail";
	}
}
