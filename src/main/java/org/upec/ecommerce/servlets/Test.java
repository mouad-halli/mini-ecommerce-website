package org.upec.ecommerce.servlets;

import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.upec.ecommerce.utils.*;

@WebServlet("/")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1;
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String action = request.getServletPath();
        
        switch (action) {
        case "/search":
        	searchProducts(request, response);
            break;
        case "/create-product-form":
            showProductForm(request, response);
            break;
        case "/create-product":
            createProduct(request, response);
            break;
        case "/update-product-form":
            showEditProductForm(request, response);
            break;
        case "/update-product":
            updateProduct(request, response);
            break;
        case "/delete-product":
            deleteProduct(request, response);
            break;
        default:
            getProducts(request, response);
            break;
        }
        
	}
	
	protected void showProductForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
        request.setAttribute("productName", "");
        request.setAttribute("productCategory", "");
        request.setAttribute("productPrice", "");
        request.setAttribute("productTva", "");
        request.setAttribute("title", "Create Product");
        request.setAttribute("buttonText", "CREATE");
        request.setAttribute("link", "create-product");
        dispatcher.forward(request, response);
	}
	
	protected void searchProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String query = request.getParameter("q");
		ArrayList<Product> searchResult = new ArrayList<Product>();
		
        for (Product p : Repository.myDataBase) {
        	if (p.getCategory().equals(query))
        		searchResult.add(p);
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("search-page.jsp");
        request.setAttribute("searchResult", searchResult);
        dispatcher.forward(request, response);
		
	}
	
	protected void getProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getServletPath().equals("/catalogue") ? "catalogue.jsp" : "index.jsp";
				
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        request.setAttribute("products", Repository.myDataBase);
        dispatcher.forward(request, response);
		
	}
	
	protected void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputName = request.getParameter("product-name");
		String inputCategory = request.getParameter("product-category");
		String inputPrice = request.getParameter("product-price");
		String inputTva = request.getParameter("product-tva");
				
		if (inputName == null || inputName.isEmpty() || inputCategory == null || inputCategory.isEmpty()
			|| inputPrice == null || inputPrice.isEmpty() || inputTva == null || inputTva.isEmpty()) {
			response.sendRedirect("product-form.jsp");
			return;
		}
			
		Repository.myDataBase.add(new Product(Repository.myDataBase.size() + 1, inputName, inputCategory, Integer.parseInt(inputPrice), Integer.parseInt(inputTva)));
		
		response.sendRedirect("catalogue");
	}
	
	protected void showEditProductForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
        if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
        	response.sendRedirect("catalogue");
        	return;
        }
        
        int id = Integer.parseInt(request.getParameter("id"));

        
        for (Product p : Repository.myDataBase) {
        	if (p.getId() == id) {
        		request.setAttribute("productId", p.getId());
                request.setAttribute("productName", p.getProductName());
                request.setAttribute("productCategory", p.getCategory());
                request.setAttribute("productPrice", p.getPrice());
                request.setAttribute("productTva", p.getTva());
                request.setAttribute("title", "Update Product");
                request.setAttribute("buttonText", "UPDATE");
                request.setAttribute("link", "update-product");
        		break;
        	}
        }
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
        dispatcher.forward(request, response);
        
	}
	
	protected void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
        	response.sendRedirect("product-form.jsp");
        	return;
        }
        int id = Integer.parseInt(request.getParameter("id"));
        
        String inputName = request.getParameter("product-name");
        String inputCategory = request.getParameter("product-category");
        String inputPrice = request.getParameter("product-price");
        String inputTva = request.getParameter("product-tva");
        
        
        for (Product p : Repository.myDataBase) {
        	if (p.getId() == id) {
        		if (inputName != null && !inputName.isEmpty())
        			p.setProductName(inputName);
        		
        		if (inputCategory != null && !inputCategory.isEmpty())
        			p.setCategory(inputCategory);
        			
        		if (inputPrice != null && !inputCategory.isEmpty())
        			p.setPrice(Integer.parseInt(inputPrice));
        		
        		if (inputTva != null && !inputCategory.isEmpty())
        			p.setTva(Integer.parseInt(inputTva));
        		break;
        	}
        }
       
		
		response.sendRedirect("catalogue");
	}
	
	protected void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
        	response.sendRedirect("product-form.jsp");
        	return;
        }
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        for (Product p : Repository.myDataBase) {
        	if (p.getId() == id) {
        		Repository.myDataBase.remove(Repository.myDataBase.indexOf(p));
        		break;
        	}
        }
        
        response.sendRedirect("catalogue");
	}
	
}
