package target.service;

import springMVC.annotation.Autowired;
import springMVC.annotation.Service;

@Service("target1Service")
public class Target1Service {
    @Autowired
    Target2Service target2Service;
    @Autowired
    Target3Service target3Service;

    public String doSothing(){
        return target2Service.doSothing() + "," + target3Service.doSothing();
    }
}
