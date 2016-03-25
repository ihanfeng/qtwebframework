# 项目概述

---

## 1. 项目背景
---

整个BU已经创建了两年，做过的项目已经有好几个了，但是由于没有一个比较合适的基础代码框架，导致每次接手项目似乎都是从零开始，或者干脆沿用已经非常老旧的代码，加大了代码维护和拓展的负担。本人有幸在BU的几个项目组都从事过开发工作，对于目前各个项目组的短板都算是比较了解，妄图想以一己之力改变目前的状况，故而催生了这个项目

---

## 2. 设计思想

---

###  -CodeAnywhere-  ###

>　　大部分同事习惯使用**Myeclipse**,也有同事喜欢使用**eclipse**，本人比较推崇**intellij idea**,所以使用任何一种IDE私有的代码结构都是不明智的，同时由于编码人员对之前项目依赖管理诟病甚深，所以本项目采用已经非常成熟的**Maven**作为项目后端构建工具。

>　　至于前端，由于各位同事并非都对前端技术感兴趣，目前比较流行的**bower**,**grunt**等构建工具我并未引入到项目中来，以减少各位同事的学习成本（本文是以MarkDown编写的，算是让阅读者强行学习下新技术吧）

### -面向对象- ###

> 　　面向对象思想并不是说你使用一门面向对象的语言就能慢慢形成的，至少我是学习了《大话设计模式》之后才意识到这一点的。从我对之前代码的观察来看，大部分人写状态判断的逻辑的时候还是习惯以大段的`if`和`else`进行判断，而不是结合状态模式，责任链模式等进行代码编写
>　　下面我罗列出面向对象设计的六大原则（尽量实现，而非一定实现）：

>* 单一职责原则

>* 里氏替换原则

>* 依赖倒置原则

>* 接口隔离原则

>* 迪米特法则

>* 开闭原则

>  这些原则讲解与示例请参考[这里](http://www.cnblogs.com/lhws/archive/2012/03/10/2389189.html)
> 我在这里只是阐述下项目中我所遇到的违反设计原则的情况
>1. 臃肿的类设计
　　
　　放下臃肿的接口设计不谈，单从model类的设计来看，最基本的User类，很多设计者不仅喜欢把username,password等登录需要的信息放到User类中，还喜欢把诸如地址，性别，年龄都放到User类中，实际上这是不科学的，username,password是属于系统级的属性，是用来登录系统，判断当前登录实体的，地址，性别，年龄等是业务级的属性，业务需要的时候才去添加该属性，所以应该将他们分别设计成Account类和UserInfo类
　　
>2. 不明所以的参数
　　
　　在很多控制层和服务层，服务层和数据层接口参数上，开发者为了方便起见，直接将接口参数定义成`Map　param`,`String sql`等，除了定义者，谁都不明白这个接口的用法是什么
　　
>3. 越级处理

> 　　这就体现在在服务层甚至控制层直接编写sql语句，这种现象在使用Hibernate框架的项目中尤其突出。使用框架的目的并不是为了显得高大上，MVC也不是为了说说而已，这种越级编写的代码实在令人恼火


### －模块划分－

> 　　经过简单培训之后，大家搭建框架都喜欢在项目下面直接放置四个包类似于com.xxx.controller,com.xxx.service,com.xxx.dao,com.xxx.dao.model。在项目早期这种代码结构无可厚非，但是一旦项目模块增大，在同一个包下会充斥这各种各样的无关类，无形中增加了代码编写的困难，尤其是，我想在某模块中增加一个拦截器，我在创建包的时候就会充满各种疑问，我这个拦截器是创建在controller下还是创建在service下，实际上controller层和service层都有拦截器，但作用各不相同；尤其是我觉得某个模块的业务逻辑复杂到了必须使用设计模式解决的时候，我所需要的处理类置于何处才显得不突兀？所以我认为合理的包划分的方式是com.xxx.模块.model...

###　－分布式－

> 　　项目初期必须评估项目一旦需要分布式处理，哪些模块必须做修改，哪些模块需要修改，工作量有多大。
　　在这里我以定时任务为例。某个项目包含对数据库处理的定时任务，在只是部署一个实例的时候没有什么问题，可是一旦部署到两个以上的实例的时候，将会产生各种各样的冲突。quartz是一个开源的java定时任务框架，其工作模式有几种，其中常用的是内存模式和数据库模式，在初期设计的时候，将其设置为数据库模式就能永久的保存任务运行状态，同时又能满足分布式部署的需求，但是配置起来比较麻烦，一般的用户都是用默认的内存模式，我们做项目，需要考虑到将来复杂的情况，不能因为怕麻烦就不去管他，为将来业务拓展埋下隐患

　　
##3.　技术选型

### -spring-

> 　　常见的java web框架有SSH(Struts/SpringMVC,Spring,Hibernate),Spring,Hibernate),　SSM(Struts/SpringMVC,Spring,Mybatis)，无论控制层和数据层的框架如何改变，spring作为一个项目的核心框架的地位都不曾改变，可见其功能强大，关于spring的有点和作用我就不一一赘述了

### －springmvc－

> 　　SpringMVC作为控制层框架，之所以选择springmvc而不是struts，是因为其配置，编码，性能和功能都比struts高出许多，有兴趣的同学可以自行搜索对比。

### -hibernate-

> 　　我懒得写建表语句了，所以选了它。它还有一个好处就是二级缓存配置比较方便

###-shiro－

> 　　shiro是一款轻量级的安全框架，之所以需要引入安全框架而不是自己写过滤器，也是考虑到业务拓展的需要，一旦现在的鉴权方式发生改变，比如由本地数据库鉴权改成cas鉴权或者oauth鉴权，只要修改下配置便能实现，尤其是将来做分布式RESTFUL风格的网站时，自写过滤器都不能实现鉴权，所以引入了安全框架

### -ehcache-

> 　　ehcache作为一个缓存框架，不仅能做数据库缓存，还能做应用缓存，shiro和springmvc都可以将计算结果放到缓存中，减轻服务器的io和计算压力


### -Bootstrap-

> 　　Bootstrap是一款开源的UI框架，特点是容易定制，容易书写还特别漂亮,而且是移动设备优先的，也就是说，你可以写一次代码，能在pc和移动设备上都显示的比较漂亮。

##4. 项目运行

1. 检出

     git clone https://github.com/zmlgit/qtwebframework.git

2. 创建数据库

    １)推荐使用mysql. 在mysql中创建`qtframework`数据库，
    ２)然后创建修改`src/main/resources/database.propreties`中的数据库连接语句

3. 选择`src/main/resources/scheduler/tables_mysql.sql` 中的建表语句，为quartz定时任务建表

4. 安装maven

5. 运行`mvn package`命令

6. 将`target/core.war` 复制到tomcat下运行


















