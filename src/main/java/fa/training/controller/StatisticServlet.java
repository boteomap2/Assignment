package fa.training.controller;

import fa.training.config.HibernateConfig;
import fa.training.entity.ServiceUsage;
import java.io.IOException;
import java.util.List;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

@WebServlet(urlPatterns = "/statistic")
public class StatisticServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();

        String sql = "with tong_tien_table as ( select sddv.MaKH, sum(sddv.SoLuong * dv.DonGia) as TongTien from SUDUNGDICHVU sddv join DICHVU dv on sddv.MaDV = dv.MaDV group by sddv.MaKH ) select kh.MaKH, kh.TenKH, m.MaMay, m.ViTri, m.TrangThai, sdm.NgayBatDauSuDung, sdm.GioBatDauSuDung, sdm.ThoiGianSuDung, dv.MaDV, sddv.NgaySuDung, sddv.GioSuDung, sddv.SoLuong, ttb.TongTien from KHACHHANG kh left join SUDUNGMAY sdm on kh.MaKH = sdm.MaKH left join SUDUNGDICHVU sddv on kh.MaKH = sddv.MaKH left join MAY m on sdm.MaMay = m.MaMay left join DICHVU dv on sddv.MaDV = dv.MaDV left join tong_tien_table ttb on kh.MaKH = ttb.MaKH";
        Query query = session.createNativeQuery(sql);

        List<Object[]> data = (List<Object[]>) query.getResultList();
        for (Object[] items : data) {
            for (int i = 0; i < items.length; i++) {
                if (items[i] == null) {
                    items[i] = '-';
                }
            }
        }
        session.getTransaction().commit();
        session.close();

        request.setAttribute("items", data);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/statistic.jsp");
        requestDispatcher.forward(request, response);
    }
}
