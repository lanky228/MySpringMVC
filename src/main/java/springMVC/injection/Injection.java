package springMVC.injection;

import springMVC.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Injection {
    /**
     * 依赖注入
     *
     * @throws Exception
     */
    public void injection(Map<String, Object> instanceMap) throws Exception {
        if (instanceMap.isEmpty()) {
            return;
        }
        Map<String, Object> classNameMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {
            classNameMap.put(entry.getValue().getClass().getName(), entry.getValue());
        }
        for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {
            // 拿到里面的所有属性
            Field fields[] = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(Autowired.class)) {
                    continue;
                }
                String className = field.getGenericType().getTypeName();
                if (!classNameMap.containsKey(className)) {
                    continue;
                }
                // 可访问私有属性
                field.setAccessible(true);
                field.set(entry.getValue(), classNameMap.get(className));
            }
        }
    }
}
