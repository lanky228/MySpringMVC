package springMVC.router;

import springMVC.annotation.Controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Router {
    List<Beanhandel> beanhandelList = new ArrayList<>();

    public void router(Map<String, Object> instanceMap) {
        if (instanceMap.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {
            if (!entry.getValue().getClass().isAnnotationPresent(Controller.class)) {
                continue;
            }
            Controller actionController = entry.getValue().getClass().getAnnotation(Controller.class);
            Method[] methods = entry.getValue().getClass().getMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(Controller.class)) {
                    continue;
                }
                Controller controller = method.getAnnotation(Controller.class);
                Beanhandel beanhandel = new Beanhandel(actionController.value() + controller.value(), entry.getValue(), method);
                beanhandelList.add(beanhandel);
            }
        }
    }

    public List<Beanhandel> getBeanhandelList() {
        return beanhandelList;
    }

    public void setBeanhandelList(List<Beanhandel> beanhandelList) {
        this.beanhandelList = beanhandelList;
    }
}
