package target.action;

import springMVC.annotation.Autowired;
import springMVC.annotation.Controller;
import target.service.Target1Service;
import target.service.Target2Service;
import target.service.Target3Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller("/targetAction")
public class TargetAction {
    @Autowired
    Target1Service target1Service;
    @Autowired
    Target2Service target2Service;
    @Autowired
    Target3Service target3Service;

    @Controller("/doSomething")
    public void doSomething(HttpServletRequest request, HttpServletResponse response, String param) throws Exception {
        PrintWriter out = response.getWriter();
        out.append(target1Service.doSothing() + "\n");
        out.append(target2Service.doSothing() + "\n");
        out.append(target3Service.doSothing() + "\n");
        out.close();
    }
}
