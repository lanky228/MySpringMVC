package springMVC.router;

import java.lang.reflect.Method;

public class Beanhandel {
    private String pattern;
    private Object object;
    private Method method;

    public Beanhandel(String pattern, Object object, Method method) {
        this.pattern = pattern;
        this.object = object;
        this.method = method;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
