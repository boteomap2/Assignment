package fa.training.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fa.training.dao.DeviceDao;
import fa.training.dao.impl.DeviceDaoImpl;
import fa.training.entity.Device;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/device/*")
public class DeviceServlet extends HttpServlet {
    private DeviceDao deviceDao;

    @Override
    public void init() {
        deviceDao = new DeviceDaoImpl();
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
                case "/list/add":
                    addDevice(request, respond);
                    break;
                case "/list/update":
                    updateDevice(request, respond);
                    break;
                case "/list/delete":
                    deleteDevice(request, respond);
                    break;
                case "/register":
                    registerDevice(request, respond);
                    break;
                default:
                    listDevice(request, respond);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listDevice(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        List<Device> listDevice = deviceDao.getAllDevice();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String listDeviceJson = ow.writeValueAsString(listDevice);
        request.setAttribute("listDeviceJson", listDeviceJson);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/device-list.jsp");
        dispatcher.forward(request, response);
    }

    private void addDevice(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        String position = request.getParameter("position");
        String status = request.getParameter("status");

        Device device = new Device(position, status);
        boolean result = deviceDao.saveDevice(device);

        if (result) {
            response.getWriter().write(Integer.toString(device.getDeviceId()));
        }
    }

    private void deleteDevice(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        int deviceId = Integer.parseInt(request.getParameter("deviceId"));
        boolean result = deviceDao.deleteDevice(deviceId);
        if (result) {
            response.getWriter().write("ok");
        }
    }

    private void updateDevice(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        int deviceId = Integer.parseInt(request.getParameter("deviceId"));
        String position = request.getParameter("position");
        String status = request.getParameter("status");

        Device device = deviceDao.getDevice(deviceId);
        if (device == null) return;

        device.setPosition(position);
        device.setStatus(status);
        boolean result = deviceDao.updateDevice(device);

        if (result) {
            response.getWriter().write("ok");
        }
    }

    private void registerDevice(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/device-register.jsp");
        dispatcher.forward(request, response);
    }
}
