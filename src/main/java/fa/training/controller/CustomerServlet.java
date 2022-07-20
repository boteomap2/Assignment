package fa.training.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fa.training.dao.CustomerDao;
import fa.training.dao.impl.CustomerDaoImpl;
import fa.training.entity.Customer;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/customer/*")
public class CustomerServlet extends HttpServlet {
    private CustomerDao customerDao;

    @Override
    public void init() {
        customerDao = new CustomerDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException {
        String action = request.getPathInfo();

        try {
            switch (action) {
                case "/list/add":
                    addCustomer(request, response);
                    break;
                case "/list/update":
                    updateCustomer(request, response);
                    break;
                case "/list/delete":
                    deleteCustomer(request, response);
                    break;
                default:
                    listCustomer(request, response);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listCustomer(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        List<Customer> listCustomer = customerDao.getAllCustomer();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String listCustomerJson = ow.writeValueAsString(listCustomer);
        request.setAttribute("listCustomerJson", listCustomerJson);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/customer-list.jsp");
        dispatcher.forward(request, response);
    }

    private void addCustomer(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        

        Customer customer = new Customer(name, address, phone, email);
        boolean result = customerDao.saveCustomer(customer);

        if (result) {
            response.getWriter().write(customer.getCustomerId());
        }
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
        throws IOException{
        String customerId = request.getParameter("customerId");
        boolean result = customerDao.deleteCustomer(customerId);
        if (result) {
            response.getWriter().write("ok");
        }
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        String customerId = request.getParameter("customerId");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        Customer customer = customerDao.getCustomer(customerId);
        if (customer == null) return;

        customer.setName(name);
        customer.setAddress(address);
        customer.setPhone(phone);
        customer.setEmail(email);

        boolean result = customerDao.updateCustomer(customer);

        if (result) {
            response.getWriter().write("ok");
        }
    }
}
