package murach.util;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import murach.business.Product;
import murach.data.ProductIO;

import java.time.LocalDate;
import java.util.List;

@WebListener
public class CartContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();

        String custServEmail = sc.getInitParameter("custServEmail");
        sc.setAttribute("custServEmail", custServEmail);

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        sc.setAttribute("currentYear", currentYear);

        String productsPath = sc.getRealPath("/WEB-INF/products.txt");

        List<Product> products = ProductIO.getProducts(productsPath);
        sc.setAttribute("products", products);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
