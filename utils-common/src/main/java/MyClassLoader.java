
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader{
    private String name;//类加载器名字
    private String path="d:\\";//加载类的路径
    private final  String fileType=".class";//class文件的扩展名


    public MyClassLoader(String name){
        super(); //让系统类加载器成为该类的父加载器

        this.name=name;

    }

    public MyClassLoader(ClassLoader parent,String name){
        super(parent);//指定该类加载器的父加载器

        this.name=name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 读取class文件
     * @param name
     * @return
     */
    private byte[] loadClassData(String name){
        InputStream is =null;
        byte[] data =null;
        ByteArrayOutputStream baos=null;
        try{
            this.name =this.name.replace(".","\\");
            is=new FileInputStream(new File(path+name+fileType));
            baos=new ByteArrayOutputStream();
            int ch=0;
            while(-1!=(ch=is.read())){
                baos.write(ch);
            }
            data =baos.toByteArray();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                is.close();
                baos.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return  data;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data=this.loadClassData(name);

        return this.defineClass(name,data,0,data.length);
    }

    public static void main(String[] args) throws Exception {
        MyClassLoader loader1 =new MyClassLoader("loader1");
        loader1.setPath("d:\\classloadTest\\serverlib\\");

        MyClassLoader loader2=new MyClassLoader(loader1,"loader2");
        loader2.setPath("d:\\classloadTest\\clientlib\\");

        MyClassLoader loader3=new MyClassLoader(null,"loader3");
        loader3.setPath("d:\\classloadTest\\otherlib\\");

//        test(loader2);
//        test(loader3);
  /*
        //系统类加载器和自定义类加载命名空间不同，所以Sample中的属性会取不到
        Class clazz=loader1.loadClass("Sample");
        Object object=clazz.newInstance();
        Sample sample= (Sample) object;
        System.out.println(sample.v1);*/

/*        //反射方式可以突破类加载器不同命名空间问题
        Class clazz = loader1.loadClass("Sample");
        Object object = clazz.newInstance();
        Field field = clazz.getField("v1");
        int v1 = field.getInt(object);
        System.out.println(v1);*/


        Class clazz = loader1.loadClass("Sample");
        System.out.println(clazz.hashCode());

        Object object=clazz.newInstance();

        loader1=null;
        clazz=null;
        object=null;

        loader1 =new MyClassLoader("loader1");

        loader1.setPath("d:\\classloadTest\\serverlib\\");

        clazz=loader1.loadClass("Sample");

        System.out.println(clazz.hashCode());



    }

    //创建sample对象
    public static void test(ClassLoader loader) throws Exception {
        Class clazz=loader.loadClass("Sample");
        Object object=clazz.newInstance();
        Sample sample= (Sample) object;
        System.out.println(sample.v1);
    }

}
