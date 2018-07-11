package target.service;

import springMVC.annotation.Service;

@Service("target2Service")
public class Target2Service {
    public String doSothing(){
        return "Target2Service";
    }
}
