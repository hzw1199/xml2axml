package android.content.res;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by Roy on 15-10-6.
 */
public class Resources {

    public HashMap<String,Integer> attrMap=new HashMap<>();

    private void init(){
        try {
            Field[] fs = android.R.attr.class.getFields();
            for (Field f : fs) attrMap.put(f.getName(), (Integer)f.get(null));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Resources(){
        init();
    }

    public int getIdentifier(String name, String type, String pkg) {
        if ("android".equals(pkg)&&"attr".equals(type)){
            Integer x=attrMap.get(name);
            if (x==null) System.out.println("attr not found: " + name);
            else {
                System.out.format("@%s:%s/%s=0x%x\n",pkg,type,name,x);
                return x;
            }
        }else{
            System.out.format("@%s:%s/%s=0x%x\n",pkg,type,name,0);
        }
        return 0;
    }
}
