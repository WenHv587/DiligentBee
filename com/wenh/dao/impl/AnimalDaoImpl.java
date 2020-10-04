package task1.com.wenh.dao.impl;

import task1.com.wenh.dao.AnimalDao;
import task1.com.wenh.pojo.Animal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * @author LWenH
 * @create 2020/9/29 - 14:04
 *
 * 对动物进行增删改查的具体实现类。
 */
public class AnimalDaoImpl implements AnimalDao {
    private static ArrayList<Animal> array = new ArrayList<>();

    /**
     * 增加动物信息
     * @param animal 要添加的动物对象
     */
    @Override
    public void add(Animal animal) {
        array.add(animal);
        System.out.println("已存储完毕!");
    }

    /**
     * 删除动物信息
     * @param num 用户的选择信息
     * @param id 根据id删除动物信息
     * @param name 要删除的属性名
     */
    @Override
    public void delete(String num, String id, String name) {
        /*
        分两种情况
        1. 删除整条动物的信息
        2. 删除动物的某个属性
         */
        switch (num) {
            case "1":
                /*
                删除整条动物信息
                 */
                Iterator<Animal> it = array.iterator();
                while (it.hasNext()) {
                    Animal animal = it.next();
                    if (animal.getId().equalsIgnoreCase(id)) {
                        it.remove();
                        System.out.println("已成功删除!");
                    }
                }
                break;
            case "2":
                /*
                删除动物的某个扩展属性
                (我认为基本属性id、种类、年龄、性别是不应该被删除的，这是描述一个动物的基本内容)
                 */
                 for (Animal animal : array) {
                     HashMap animalMap = animal.getAnimalMap();
                     Set<String> animalSet = animalMap.keySet();
                     for (String key : animalSet) {
                         if (name.equals(key) && id.equalsIgnoreCase(animal.getId())) {
                             animalMap.remove(name);
                             System.out.println("已成功删除!");
                             break;
                         }
                     }
                 }
        }
    }

    /**
     * 修改动物信息
     * @param num 用户选择标记
     * @param id 要修改的动物信息的id
     * @param name 要修改的属性名
     * @param property 要修改的属性的新值
     */
    @Override
    public void set(String num, String id, String name, String property) {
        if (num.equals("1")) {
            /*
            修改现有的属性
             */
            for (Animal animal : array) {
                if (animal.getId().equalsIgnoreCase(id)) {
                switch (name) {
                    case "种类":
                        animal.setSpecie(property);
                        System.out.println("已修改成功!");
                        break;
                    case "年龄":
                        animal.setAge(Integer.parseInt(property));
                        System.out.println("已修改成功!");
                        break;
                    case "性别":
                        animal.setGender(property);
                        System.out.println("已修改成功!");
                        break;
                    default:
                        // 与以上的三个基本属性都不匹配的话，需要修改的则是扩展属性
                        HashMap<String,String> animalMap = animal.getAnimalMap();
                        Set<String> animalSet = animalMap.keySet();
                        for (String key : animalSet) {
                            if (name.equals(key)) {
                                animalMap.put(key, property);
                                System.out.println("已修改成功!");
                            }
                        }
                    }
                }
            }
        } else if (num.equals("2")) {
            /*
            增添新的属性
             */
            for (Animal animal : array) {
                HashMap<String,String> animalMap = animal.getAnimalMap();
                if (animal.getId().equalsIgnoreCase(id)) {
                    animalMap.put(name,property);
                    System.out.println("已修改成功!");
                }
            }
        }
    }

    /**
     * 查询全部动物信息
     */
    @Override
    public void select() {
        sort();
        System.out.println("===============================查询结果===============================");
        for (Animal animal : array) {
            HashMap<String,String> animalMap = animal.getAnimalMap();
            // 默认的打印信息格式
            StringBuilder sb = new StringBuilder(
                    "id:" + animal.getId() +
                    "---种类:" + animal.getSpecie() +
                    "---年龄:" + animal.getAge() +
                    "---性别:" + animal.getGender());
            if (0 == animalMap.size()) { // 说明没有扩展属性
                // 只有基本信息，使用默认格式即可
                System.out.println(sb.toString());
            } else {
                // 有扩展属性，需要遍历扩展属性的集合，并在默认格式后添加新属性
                Set<String> animalSet = animalMap.keySet();
                for (String key : animalSet) {
                    String value = animalMap.get(key);
                    sb.append("---").append(key).append(":").append(value);
                }
                System.out.println(sb.toString());
            }
        }
    }

    /**
     * 多条件组合查询动物信息
     * @param params 用户传递的动物属性值
     * @return 如果查询到了相应的动物信息则返回true,否则返回false;
     */
    @Override
    public boolean selectByParam(ArrayList<String> params) {
        sort();
        System.out.println("===============================查询结果===============================");
        boolean flag = false;
        for (Animal animal : array) {
            HashMap<String,String> animalMap = animal.getAnimalMap();
            Set<String> set = animalMap.keySet();
            // 在确定用户输入的属性存在的前提下
            ArrayList<String> valueList = new ArrayList<>(); // 这个集合是存放一条动物信息中所有属性值的集合
            valueList.add(animal.getId());
            valueList.add(animal.getSpecie());
            valueList.add(animal.getAge() + "");
            valueList.add(animal.getGender());
            for (String key : set) {
                String value = animalMap.get(key);
                valueList.add(value);
            }
            ArrayList<String> paramList = new ArrayList<>(); // 这个集合是存放用户输入的限制属性值的集合
            for (String param : params) {
                paramList.add(param);
            }
            if (valueList.containsAll(paramList)) { // 如果用户输入的限制属性值被某个动物信息全部包含
                // 默认的打印信息格式
                StringBuilder sb = new StringBuilder(
                        "id:" + animal.getId() +
                                "---种类:" + animal.getSpecie() +
                                "---年龄:" + animal.getAge() +
                                "---性别:" + animal.getGender());
                if (0 == animalMap.size()) { // 说明没有扩展属性
                    // 只有基本信息，使用默认格式即可
                    System.out.println(sb.toString());
                    flag = true;
                } else {
                    // 有扩展属性，需要遍历扩展属性的集合，并在默认格式后添加新属性
                    Set<String> animalSet = animalMap.keySet();
                    for (String key2 : animalSet) {
                        String value2 = animalMap.get(key2);
                        sb.append("---").append(key2).append(":").append(value2);
                    }
                    System.out.println(sb.toString());
                    flag = true;
                }
            }
        }
        return flag;
    }

    /**
     * 判断装动物的集合array是否为空
     * @return 有动物信息则返回true,否则返回false
     */
    public static boolean ifNull() {
        boolean flag = false;
        if (0 != array.size()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 校验用户输入的id是否正确
     * @param id 用户输入的id
     * @return 有相应的id则返回true,否则返回false
     */
    public static boolean checkID(String id) {
        boolean flag = false;
        for (Animal animal : array) {
            if (animal.getId().equalsIgnoreCase(id)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 按照年龄从小到大对动物信息进行排序
     */
    public static void sort() {
        array.sort(new Comparator<Animal>() {
            @Override
            public int compare(Animal o1, Animal o2) {
                int num1 = o1.getAge()-o2.getAge();
                int num2 = num1==0 ? (o1.getId().compareTo(o2.getId())) : num1;
                return num2;
            }
        });
    }

    /**
     * 对添加的动物信息进行查重
     * @param animal 用户添加的某一动物信息
     * @return 动物信息完全一样返回false,不完全一样返回true
     */
    public static boolean checkRepeat(Animal animal) {
        boolean flag = true;
        if (array.isEmpty()) {
            return flag;
        }
        for (Animal animal1 : array) {
            if (animal.getSpecie().equals(animal1.getSpecie())
             && animal.getAge() == animal1.getAge()
             && animal.getGender().equals(animal1.getGender())
             && animal.getAnimalMap().equals(animal1.getAnimalMap())) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 在修改或删除已有的属性时，校验用户输入的属性是否存在
     * @param property 用户要修改的属性值名称
     * @param id 动物信息的id
     * @return 存在返回true.不存在返回false
     */
    public static boolean checkExist(String id, String property) {
        boolean flag = true;
        for (Animal animal : array) {
            if (id.equalsIgnoreCase(animal.getId())) {
                HashMap<String,String> animalMap = animal.getAnimalMap();
                Set<String> set = animalMap.keySet();
                if (!set.contains(property) && !property.equals("种类") && !property.equals("年龄") && !property.equals("性别")) {
                    flag=false;
                }
            }
        }
        return flag;
    }

    /**
     * 判断动物是否有扩展属性
     * @param id
     * @return 有扩展属性返回true,没有则返回false
     */
    public static boolean checkMap(String id) {
        for (Animal animal : array) {
            if (id.equalsIgnoreCase(animal.getId())) {
                HashMap<String,String> animalMap = animal.getAnimalMap();
                if (animalMap.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 在组合条件查询时，对用户输入的属性名称进行校验
     * @param names 用户输入的属性名称的数组
     * @return 如果用户输入的属性名称能被任何一个动物信息包含，则返回true，否则返回false
     */
    public static boolean checkName(ArrayList<String> names) {
        boolean flag = false;
        for (String name : names) {
            if (name.equals("种类") || name.equals("年龄") || name.equals("性别")) {
                flag = true;
            }
            for (Animal animal : array) {
                HashMap<String,String> animalMap = animal.getAnimalMap();
                Set<String> set = animalMap.keySet();
                if (set.contains(name)) {
                    flag = true; // 只要包含就是true，无论包含几个
                }
            }
        }
        return flag;
    }
}
