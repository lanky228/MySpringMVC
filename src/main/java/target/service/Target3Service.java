package target.service;

import springMVC.annotation.Service;

@Service("targetService3")
public class Target3Service {
    public String doSothing(){
       return "Target3Service";
    }
}
