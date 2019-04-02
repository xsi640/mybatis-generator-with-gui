# mybatis generator with gui

​	基于JavaFX的mybatis代码生成器，使用最简单快捷的方式逆向数据库生成mybatis操作数据库的基础代码。

​	这个项目是学习mybatis的时候，觉得没有顺手的mybatis代码生成器，就自己边学边做用javaFX写的（JavaFX也是现学的），本着从简的方式，没有提供更多自定义的功能，如果想提供更多功能或遇到问题可以在Issus中提出。

​	提供数据库操作方法：

```java
public interface StudentMapper {
    int insert(Student student);
    int insertCollection(List<Student> students);
    int insertOrUpdate(Student student);
    int insertOrUpdateCollection(List<Student> students);
    int update(Student student);
    int delete(java.lang.Integer id);
    int deleteAll();
    int deletes(List<java.lang.Integer> ids);
    List<Student> findAll();
    List<Student> findByWhere(@Param("where") String where);
    List<Student> findByWhereOrder(@Param("where") String where, @Param("order") String order);
    List<Student> findByLimit(@Param("offset") String offset, @Param("limit") String limit);
    List<Student> findByWhereLimit(@Param("where") String where, @Param("offset") String offset, @Param("limit") String limit);
    List<Student> findByWhereOrderLimit(@Param("where") String where, @Param("order") String order, @Param("offset") String offset, @Param("limit") String limit);
    Student findById(java.lang.Integer id);
    int count();
    int count(@Param("where") String where);
}
```

都是一些比较普通和常用的方法。

​	界面截图：

​	![image](https://raw.githubusercontent.com/xsi640/mybatis-generator-with-gui/master/screenshot/01.png)
​    ![image](https://raw.githubusercontent.com/xsi640/mybatis-generator-with-gui/master/screenshot/02.png)
​    ![image](https://raw.githubusercontent.com/xsi640/mybatis-generator-with-gui/master/screenshot/03.png)
