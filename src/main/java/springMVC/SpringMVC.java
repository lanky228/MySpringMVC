package springMVC;

import springMVC.injection.Injection;
import springMVC.ioc.Ioc;
import springMVC.router.Router;

public class SpringMVC {
    Ioc ioc = new Ioc();
    Injection injection = new Injection();
    Router router = new Router();

    public SpringMVC() throws Exception {
        injection.injection(ioc.getInstanceMap());
        router.router(ioc.getInstanceMap());
    }

    public static void main(String[] args) throws Exception {
        SpringMVC springMVC = new SpringMVC();
        System.out.println(springMVC.getRouter().getBeanhandelList().size());
    }

    public Ioc getIoc() {
        return ioc;
    }

    public void setIoc(Ioc ioc) {
        this.ioc = ioc;
    }

    public Injection getInjection() {
        return injection;
    }

    public void setInjection(Injection injection) {
        this.injection = injection;
    }

    public Router getRouter() {
        return router;
    }

    public void setRouter(Router router) {
        this.router = router;
    }
}
