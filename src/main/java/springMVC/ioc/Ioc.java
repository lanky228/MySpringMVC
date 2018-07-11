package springMVC.ioc;

import org.apache.commons.lang3.StringUtils;
import springMVC.annotation.Controller;
import springMVC.annotation.Service;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制反转
 */
public class Ioc {
    private List<String> packageNames = new ArrayList<>();
    private Map<String, Object> instanceMap = new HashMap<>();
    private String packageName = "";

    public Ioc() throws Exception {
        scanPackage(packageName);
        filterAndInstance();
    }

    /**
     * 生成实例
     *
     * @throws Exception
     */
    void filterAndInstance() throws Exception {
        if (packageNames.size() <= 0) {
            return;
        }
        for (String className : packageNames) {
            Class<?> clazz = Class.forName(className.replace(".class", "").trim());
            if (clazz.isAnnotationPresent(Controller.class)) {
                Object instance = clazz.newInstance();
                Controller controller = clazz.getAnnotation(Controller.class);
                String key = controller.value();
                instanceMap.put(key, instance);
            } else if (clazz.isAnnotationPresent(Service.class)) {
                Object instance = clazz.newInstance();
                Service service = clazz.getAnnotation(Service.class);
                String key = service.value();
                instanceMap.put(key, instance);
            } else {
                continue;
            }
        }
    }


    /**
     * 获取类文件名
     *
     * @param packageName
     * @throws Exception
     */
    private void scanPackage(String packageName) throws Exception {
        String packagePath = replaceTo(packageName);
        if (packagePath == null) {
            return;
        }
        URL url = this.getClass().getClassLoader().getResource(packagePath);
        String pathFile = url.getFile();
        pathFile = URLDecoder.decode(pathFile, "utf-8");
        File file = new File(pathFile);
        String fileList[] = file.list();
        for (String path : fileList) {
            File eachFile = new File(pathFile + "/" + path);
            String childFileNmae = StringUtils.isBlank(packageName) ? eachFile.getName() : packageName + "." + eachFile.getName();
            if (eachFile.isDirectory()) {
                scanPackage(childFileNmae);
            } else {
                if (eachFile.getPath().indexOf(".class") == -1) {
                    continue;
                }
                packageNames.add(childFileNmae);
            }
        }
    }

    /**
     * 包名转换为路径
     *
     * @param path
     * @return
     */
    private String replaceTo(String path) {
        if (path == null) {
            return null;
        }
        if (path.indexOf(".") == 0) {
            return null;
        }
        return path.replaceAll("\\.", "/");
    }

    public List<String> getPackageNames() {
        return packageNames;
    }

    public void setPackageNames(List<String> packageNames) {
        this.packageNames = packageNames;
    }

    public Map<String, Object> getInstanceMap() {
        return instanceMap;
    }

    public void setInstanceMap(Map<String, Object> instanceMap) {
        this.instanceMap = instanceMap;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
