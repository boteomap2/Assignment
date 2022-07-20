package fa.training.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fa.training.dao.ServiceDao;
import fa.training.dao.impl.ServiceDaoImpl;
import fa.training.entity.Service;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/service/*")
public class ServiceServlet extends HttpServlet {
    private ServiceDao serviceDao;

    @Override
    public void init() {
        serviceDao = new ServiceDaoImpl();
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
                    addService(request, response);
                    break;
                case "/list/update":
                    updateService(request, response);
                    break;
                case "/list/delete":
                    deleteService(request, response);
                    break;
                case "/register":
                    break;
                default:
                    listService(request, response);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listService(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        List<Service> listService = serviceDao.getAllService();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String listServiceJson = ow.writeValueAsString(listService);
        request.setAttribute("listServiceJson", listServiceJson);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/service-list.jsp");
        dispatcher.forward(request, response);
    }

    private void addService(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        String name = request.getParameter("name");
        String unit = request.getParameter("unit");
        int price = Integer.parseInt(request.getParameter("price"));

        Service service = new Service(name, unit, price);
        boolean result = serviceDao.saveService(service);

        if (result) {
            response.getWriter().write(service.getServiceId());
        }
    }

    private void deleteService(HttpServletRequest request, HttpServletResponse response)
        throws IOException{
        String serviceId = request.getParameter("serviceId");
        boolean result = serviceDao.deleteService(serviceId);
        if (result) {
            response.getWriter().write("ok");
        }
    }

    private void updateService(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        String serviceId = request.getParameter("serviceId");
        String name = request.getParameter("name");
        String unit = request.getParameter("unit");
        int price = Integer.parseInt(request.getParameter("price"));

        Service service = serviceDao.getService(serviceId);
        if (service == null) return;

        service.setName(name);
        service.setUnit(unit);
        service.setPrice(price);
        boolean result = serviceDao.updateService(service);

        if (result) {
            response.getWriter().write("ok");
        }
    }
}
