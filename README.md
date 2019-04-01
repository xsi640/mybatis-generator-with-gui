# mybatis generator with gui

​	基于JavaFX的mybatis代码生成器，使用最简单快捷的方式逆向数据库生成mybatis操作数据库的基础代码。

​	这个项目是学习mybatis的时候，觉得没有顺手的mybatis代码生成器，就自己边学边做用javaFX写的（JavaFX也是现学的），本着从简的方式，没有提供更多自定义的功能。项目架构也是比较乱，后期会抽空做做重构。

​	提供数据库操作方法：

```java
public interface StudentMapper {
    int insert(Student student);
    int insertCollection(List<Student> students);
    int insertOrUpdate(Student student);
    int update(Student student);
    int delete(java.lang.Integer id);
    int deleteAll();
    int deletes(List<java.lang.Integer> ids);
    List<Student> findAll();
    Student findById(java.lang.Integer id);
    int count();
}
```

都是一些比较普通和常用的方法。

​	界面截图：

​	![image](https://github.com/xsi640/mybatis-generator-with-gui/screenshot/blob/master/1.png)
    ![image](https://github.com/xsi640/mybatis-generator-with-gui/screenshot/blob/master/2.png)
    ![image](https://github.com/xsi640/mybatis-generator-with-gui/screenshot/blob/master/3.png)