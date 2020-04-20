package dev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  przyjełam, że url będzie najpierw zawierał lokalizacje klienta a następnie w tablicy order
 *  będzie przekazywane id.produktu|ilosc produktu
 *  localhost:8765/api/store?x=1&y=4&order[]=1|4&order[]=3|4
 */

@WebServlet(name = "Store", urlPatterns = {"/api/store/*"})
public class StoreServlet extends HttpServlet {
    private static final String X_PARAM = "x";
    private static final String Y_PARAM = "y";
    private static final String ORDER_PARAM = "order[]";
    private final Logger logger = LoggerFactory.getLogger(Servlet.class);

    private StoreService service;
    /**
     * servlet needs it
     */
    @SuppressWarnings("unused")
    public StoreServlet(){
        this(new StoreService());

    }

    StoreServlet(StoreService service){
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Request got with parameters: " + req.getParameterMap());
        double x = Double.parseDouble(req.getParameter(X_PARAM));
        double y = Double.parseDouble(req.getParameter(Y_PARAM));
        String[] order = req.getParameterValues(ORDER_PARAM);
        logger.info(Arrays.toString(order));
        List<Order> orderItemList = new ArrayList<Order>();
        for (String a: order
             ) {
            String[] tempArr = a.split("\\|");

            orderItemList.add(new Order(Integer.parseInt(tempArr[0]), Integer.parseInt(tempArr[1])));
        }
        for (Order o: orderItemList
             ) {
            System.out.println(o.toString());
        }

        resp.getWriter().write(service.handleOrderList(orderItemList, new Coordinates(x,y)));

    }
}

