package fa.training.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fa.training.dao.CustomerDao;
import fa.training.dao.DeviceDao;
import fa.training.dao.DeviceUsageDao;
import fa.training.dao.ServiceDao;
import fa.training.dao.ServiceUsageDao;
import fa.training.dao.impl.CustomerDaoImpl;
import fa.training.dao.impl.DeviceDaoImpl;
import fa.training.dao.impl.DeviceUsageDaoImpl;
import fa.training.dao.impl.ServiceDaoImpl;
import fa.training.dao.impl.ServiceUsageDaoImpl;
import fa.training.entity.Customer;
import fa.training.entity.Device;
import fa.training.entity.DeviceUsage;
import fa.training.entity.Service;
import fa.training.entity.ServiceUsage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/register/*")
public class RegisterServlet extends HttpServlet {
    private DeviceUsageDao deviceUsageDao;
    private ServiceUsageDao serviceUsageDao;
    private CustomerDao customerDao;
    private DeviceDao deviceDao;

    private ServiceDao serviceDao;

    private List<Device> listDeviceInactive;
    private List<Service> listService;
    private List<Customer> listCustomer;

    @Override
    public void init() {
        deviceUsageDao = new DeviceUsageDaoImpl();
        serviceUsageDao = new ServiceUsageDaoImpl();
        customerDao = new CustomerDaoImpl();
        deviceDao = new DeviceDaoImpl();
        serviceDao = new ServiceDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse respond)
        throws ServletException {
        doGet(request, respond);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse respond)
        throws ServletException {
        String action = request.getPathInfo();

        try {
            switch (action) {
                case "/device":
                    showAddDeviceUsagePage(request, respond);
                    break;
                case "/service":
                    showAddServiceUsagePage(request, respond);
                    break;
                case "/device/add":
                    addDeviceUsage(request, respond);
                    break;
                case "/service/add":
                    addServiceUsage(request, respond);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void showAddDeviceUsagePage(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        listCustomer = customerDao.getAllCustomer();
        listDeviceInactive = deviceDao.getAllDeviceInactive();

        request.setAttribute("listCustomer", listCustomer);
        request.setAttribute("listDeviceInactive", listDeviceInactive);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/device-register.jsp");
        dispatcher.forward(request, response);
    }

    private void showAddServiceUsagePage(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        listCustomer = customerDao.getAllCustomer();
        listService = serviceDao.getAllService();

        request.setAttribute("listCustomer", listCustomer);
        request.setAttribute("listService", listService);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/service-register.jsp");
        dispatcher.forward(request, response);
    }

    private void addDeviceUsage(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        String customerId = request.getParameter("customerId");
        int deviceId = Integer.parseInt(request.getParameter("deviceId"));
        LocalDate startDateUsage = LocalDate.parse(request.getParameter("startDateUsage"));
        LocalTime startTimeUsage = LocalTime.parse(request.getParameter("startTimeUsage"));
        int timeUsage = Integer.parseInt(request.getParameter("timeUsage"));

        Customer customer = listCustomer.stream().filter(c -> c.getCustomerId().equals(customerId)).findFirst().orElse(null);
        Device device = listDeviceInactive.stream().filter(iDevice -> iDevice.getDeviceId() == deviceId).findFirst().orElse(null);

        if (device == null) return;
        if (customer == null) return;

        DeviceUsage deviceUsage = new DeviceUsage(customer, device, startDateUsage, startTimeUsage, timeUsage);
        boolean result = deviceUsageDao.saveDeviceUsage(deviceUsage, device);

        if (result) {
            listDeviceInactive = listDeviceInactive.stream().filter(iDevice -> iDevice.getDeviceId() != deviceId).collect(
                Collectors.toList());
            ObjectWriter ow = new ObjectMapper().registerModule(new JavaTimeModule()).writer().withDefaultPrettyPrinter();
            String listDeviceJson = ow.writeValueAsString(listDeviceInactive);
            response.getWriter().write(listDeviceJson);
        };
    }

    private void addServiceUsage(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        String customerId = request.getParameter("customerId");
        String serviceId = request.getParameter("serviceId");
        LocalDate dateUsage = LocalDate.parse(request.getParameter("dateUsage"));
        LocalTime timeUsage = LocalTime.parse(request.getParameter("timeUsage"));
        int amount = Integer.parseInt(request.getParameter("amount"));

        Customer customer = listCustomer.stream().filter(c -> c.getCustomerId().equals(customerId)).findFirst().orElse(null);
        Service service = listService.stream().filter(s -> s.getServiceId().equals(serviceId)).findFirst().orElse(null);

        if (service == null) return;
        if (customer == null) return;

        ServiceUsage serviceUsage = new ServiceUsage(customer, service, dateUsage, timeUsage, amount);
        boolean result = serviceUsageDao.saveServiceUsage(serviceUsage);

        if (result) {
            response.getWriter().write("ok");
        }
    }
}
