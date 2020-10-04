# DiligentBee
这个仓库是用来提交作业的仓库(第一次使用git/github 不太熟练，多多包涵，如有错误欢迎指出)

---

## 项目介绍

- 项目是一个动物信息管理系统，主要的功能就是对动物信息进行增删改查，由于是初学者，所有写的十分简陋，肯定也有很多出错的地方，希望能指正。
- 主要包含以下几个模块
  - pojo
    - Animal.java 这个类中主要是对动物信息的基本描述，其中主要包括id，种类，年龄，性别这四个基本属性和一个存放扩展属性的Map集合。
  - dao
    - AnimalDao.java 这是一个接口，里面是对动物信息进行增删改查的抽象方法。
    - impl
      - AnimalDaoImpl.java 这是上述接口的实现类，里面实现了对动物信息进行增删改查的所有功能，同时还有一些要依赖的方法(比如对动物信息的判断)。
  - test
    - Test.java  这是一个测试类，其中写了所有的操作功能以及对增删改查方法的调用，是整个程序的入口。
  - utils
    - IDutils.java 这个类是生成随机id的方法，主要功能就是生成一个5位数的随机的不重复的id。
    - CheckUtils.java 这个类的功能就是对用户输入的年龄等各种属性进行校验，防止用户随意输入

---



#### 总结

- 这是我第一次自己写的一个算是一个小项目的作业吧，写代码的过程中也遇到了不少问题，比如应该选择什么样的容器来装动物信息，其实我第一次写的是
```java
TreeMap<Animal,Map<String,String>> 
```
   - 这个就是把动物的几个基本信息定义在了Animal类中，用户添加的扩展属性放在了Map<String,String>中，然后再将他们放入一个TreeMap中。但是感觉这次的这个更好一点，至少比上面的好。

​    

- 在功能的具体实现类Test.java中，我写的比较乱，只有一个main方法，行数写的有点多了，但是其中大部分就是一些对用户输入的校验和一些判断(其实最开始一共就只有200行不到，但是主要功能写完之后就开始加判断，越加越多，就成了这样了。)  

-  总的来说，代码就是勉强实现了基本功能，写的不够精简，用的也不是最好的实现,希望大佬可以指出不足
