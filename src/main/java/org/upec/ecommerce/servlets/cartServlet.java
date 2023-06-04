package org.upec.ecommerce.servlets;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import org.upec.ecommerce.utils.*;

@WebServlet("/cart")
public class cartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public cartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
                        
        switch (action) {
        case "add":
        	addToCart(request, response);
        	break;
        case "remove":
        	removeFromCart(request, response);
        	break;
        default:
            getCart(request, response);
            break;
        }
        
	}
	
	protected void getCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
		HttpSession session = request.getSession();
		List<Product> cartProducts =  (List<Product>)session.getAttribute("cartProducts");
		if (cartProducts == null) {
			cartProducts = new ArrayList<Product>();
			session.setAttribute("cartProducts", cartProducts);			
		}
		int subtotal = 0;
		int tvaTotal = 0;
		
		for (Product p : cartProducts) {
			subtotal += p.getPrice();
			tvaTotal += p.getPrice() * p.getTva() / 100;
		}
		
		session.setAttribute("subtotal", subtotal);
		session.setAttribute("tvaTotal", tvaTotal);
		session.setAttribute("total", subtotal + tvaTotal);
		
        dispatcher.forward(request, response);
	}
	
	protected void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
        	response.sendRedirect("cart");
        	return;
        }
        
        int id = Integer.parseInt(request.getParameter("id"));

		HttpSession session = request.getSession();
		List<Product> cartProducts =  (List<Product>)session.getAttribute("cartProducts");
		if (cartProducts == null) {
			cartProducts = new ArrayList<Product>();
		}
		
        for (Product p : Repository.myDataBase) {
        	if (p.getId() == id) {
        		if (cartProducts.indexOf(p) == -1)
        			cartProducts.add(p);
        		break;
        	}
        }
        
        response.sendRedirect("cart");
	}
	
	protected void removeFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		List<Product> cartProducts =  (List<Product>)session.getAttribute("cartProducts");
		
        if (cartProducts == null|| request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
        	response.sendRedirect("cart");
        	return;
        }
        
        int id = Integer.parseInt(request.getParameter("id"));
		
        for (Product p : cartProducts) {
        	if (p.getId() == id) {
        		cartProducts.remove(cartProducts.indexOf(p));
        		break;
        	}
        }
        
        response.sendRedirect("cart");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);		
	}

}
