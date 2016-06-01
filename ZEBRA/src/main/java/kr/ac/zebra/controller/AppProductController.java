package kr.ac.zebra.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.zebra.dto.Product;
import kr.ac.zebra.service.AppProductService;

@Controller
public class AppProductController {
	private AppProductService appProductService;

	@Autowired
	public void setAppProductRegisterService(AppProductService appProductService) {
		this.appProductService = appProductService;
	}

	@RequestMapping("/appProductRegister")
	public String showAppProductRegister(HttpServletRequest request) {

		System.out.println("appProductRegister");
		String id = request.getParameter("id");
		String barcode = request.getParameter("barcode");
		String productName = request.getParameter("productName");

		if (appProductService.isExit(barcode)) {
			request.setAttribute("result", "");

			return "appProductRegister";
		} else {
			if (appProductService.insertProduct(id, barcode, productName)) {
				request.setAttribute("result", "{\"1\"}");
			} else {
				request.setAttribute("result", "{\"0\"}");
			}

			return "appProductRegister";
		}
	}

	@RequestMapping("/appProductSearch")
	public String showSearchProducts(HttpServletRequest request) {

		String keyword = request.getParameter("keyword");
		System.out.println(keyword);

		List<Product> searchProduct = appProductService.getProductSearch(keyword);

		System.out.println(searchProduct);

		request.setAttribute("productList", searchProduct);

		return "appProductSearch";
	}

	@RequestMapping("/appCategory")
	public String getCategoryProduct(HttpServletRequest request) {

		List<Product> categoryProducts = appProductService.getCategoryProducts(request.getParameter("category"));

		request.setAttribute("products", categoryProducts);

		return "appCategory";
	}

}