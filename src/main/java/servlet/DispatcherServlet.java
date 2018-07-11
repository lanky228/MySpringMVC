package servlet;

import springMVC.SpringMVC;
import springMVC.router.Beanhandel;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class DispatcherServlet extends HttpServlet {

    SpringMVC springMVC = new SpringMVC();

    public DispatcherServlet() throws Exception {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String url = req.getRequestURI();
        String context = req.getContextPath();
        String path = url.replace(context, "");
        boolean flag = false;
        for (Beanhandel beanhandel : springMVC.getRouter().getBeanhandelList()) {
            if (Pattern.matches(beanhandel.getPattern(), path)) {
                try {
                    beanhandel.getMethod().invoke(beanhandel.getObject(), new Object[]{req, resp, null});
                    flag = true;
                } catch (Exception e) {
                    PrintWriter out = resp.getWriter();
                    out.append("404");
                    out.close();
                }
            }
        }
        if(!flag){
            PrintWriter out = resp.getWriter();
            out.append("404");
            out.close();
        }
    }
}
