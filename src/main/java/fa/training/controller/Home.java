package fa.training.controller;

import fa.training.config.HibernateConfig;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

@WebServlet(urlPatterns = "/home")
public class Home extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.close();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/home.jsp");
        requestDispatcher.forward(request, response);
    }
}
