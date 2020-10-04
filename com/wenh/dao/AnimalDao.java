package task1.com.wenh.dao;

import task1.com.wenh.pojo.Animal;

import java.util.ArrayList;

/**
 * @author LWenH
 * @create 2020/9/29 - 14:04
 */
public interface AnimalDao {
    /**
     * 增加动物信息
     * @param animal 要添加的动物对象
     */
    public abstract void add(Animal animal);

    /**
     * 删除动物信息
     * @param id 根据id删除动物信息
     */
    public abstract void delete(String num, String id, String name);

    /**
     * 修改动物信息
     * @param num 用户选择标记
     * @param id 要修改的动物信息的id
     * @param name 要修改的属性名
     * @param property 要修改的属性的新值
     */
    public abstract void set(String num, String id, String name, String property);

    /**
     * 查询全部动物信息
     */
    public abstract void select();

    /**
     * 按条件查询动物信息
     * @param params 用户输入属性值的集合
     * @return 有满足用户条件的动物返回true，否则返回false
     */
    public abstract boolean selectByParam(ArrayList<String> params);
}
