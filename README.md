#Mybatis
这个## 1.简单查询

### 1.1查询一个字段

select 字段名 from 表名;

其中要注意：

select和from都是关键字

字段名和表名都是标识符

强调：

对于SQL通用：所有SQL以”；“结尾，不区分大小写

查询部门名字： 

```sql
mysql> select dname from dept;
+------------+
| dname      |
+------------+
| ACCOUNTING |
| RESEARCH   |
| SALES      |
| OPERATIONS |
+------------+
4 rows in set (0.00 sec)
```

### 1.2 查询两个字段，或者多个字段

使用逗号隔开

查询部门编号和名字：

```sql
mysql> select deptno,dname from dept;
+--------+------------+
| deptno | dname      |
+--------+------------+
|     10 | ACCOUNTING |
|     20 | RESEARCH   |
|     30 | SALES      |
|     40 | OPERATIONS |
+--------+------------+
4 rows in set (0.00 sec)
```

### 1.3 查询所有字段

第一种：把每个字段都写上

select a,b,c,d...... from 表

第二种：使用星号：

```sql
mysql> select * from dept;
+--------+------------+----------+
| DEPTNO | DNAME      | LOC      |
+--------+------------+----------+
|     10 | ACCOUNTING | NEW YORK |
|     20 | RESEARCH   | DALLAS   |
|     30 | SALES      | CHICAGO  |
|     40 | OPERATIONS | BOSTON   |
+--------+------------+----------+
4 rows in set (0.00 sec)
```

这种方式和的缺点：

1.效率低

2.可读性差

不建议开发，自己玩

可以在dos窗口中快速看数据

### 1.4 给查询的列起别名

```sql
mysql> select deptno,dname as deptname from dept;
+--------+------------+
| deptno | deptname   |
+--------+------------+
|     10 | ACCOUNTING |
|     20 | RESEARCH   |
|     30 | SALES      |
|     40 | OPERATIONS |
+--------+------------+
4 rows in set (0.00 sec)
```

使用as关键字起别名。

注意：只是将显示的查询结果列明显示为deptname，原表列明还是叫做：dname

记住：select语句永远不会进行修改，只查询

as关键字可以省略嘛？可以的！

```sql
mysql> select deptno,dname deptname from dept;
+--------+------------+
| deptno | deptname   |
+--------+------------+
|     10 | ACCOUNTING |
|     20 | RESEARCH   |
|     30 | SALES      |
|     40 | OPERATIONS |
+--------+------------+
4 rows in set (0.00 sec)
```

假设别名里面有空格，怎么办？

```sql
mysql> select deptno,dname dept name from dept;
ERROR 1064 (42000): You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'name from dept' at line 1
```

DBMS看到这样的语句会进行SQL编译，不符合语法，编译报错。

怎么解决？

```sql
mysql> select deptno,dname 'dept name' from dept;//加单引号
+--------+------------+
| deptno | dept name  |
+--------+------------+
|     10 | ACCOUNTING |
|     20 | RESEARCH   |
|     30 | SALES      |
|     40 | OPERATIONS |
+--------+------------+
4 rows in set (0.00 sec)
```



使用单引号括起来（双引号也可以）

注意：在所有的数据库中，字符串统一使用单引号括起来 ，双引号在oracle数据库中用不了，但在mysql中可以用。

再次强调，数据库中的字符串都是采用单引号括起来，这是标准的，双引号不标准。

### 1.5 计算员工的年薪

```sql
mysql> select ename,sal from emp;
+--------+---------+
| ename  | sal     |
+--------+---------+
| SMITH  |  800.00 |
| ALLEN  | 1600.00 |
| WARD   | 1250.00 |
| JONES  | 2975.00 |
| MARTIN | 1250.00 |
| BLAKE  | 2850.00 |
| CLARK  | 2450.00 |
| SCOTT  | 3000.00 |
| KING   | 5000.00 |
| TURNER | 1500.00 |
| ADAMS  | 1100.00 |
| JAMES  |  950.00 |
| FORD   | 3000.00 |
| MILLER | 1300.00 |
+--------+---------+
14 rows in set (0.00 sec)
```

```sql
mysql>  select ename,sal*12 from emp;
+--------+----------+
| ename  | sal*12   |
+--------+----------+
| SMITH  |  9600.00 |
| ALLEN  | 19200.00 |
| WARD   | 15000.00 |
| JONES  | 35700.00 |
| MARTIN | 15000.00 |
| BLAKE  | 34200.00 |
| CLARK  | 29400.00 |
| SCOTT  | 36000.00 |
| KING   | 60000.00 |
| TURNER | 18000.00 |
| ADAMS  | 13200.00 |
| JAMES  | 11400.00 |
| FORD   | 36000.00 |
| MILLER | 15600.00 |
+--------+----------+
14 rows in set (0.00 sec)
```

结论：字段可以使用数学表达式！

```sql
mysql> select ename,sal*12 as yearsal from emp;
+--------+----------+
| ename  | yearsal  |
+--------+----------+
| SMITH  |  9600.00 |
| ALLEN  | 19200.00 |
| WARD   | 15000.00 |
| JONES  | 35700.00 |
| MARTIN | 15000.00 |
| BLAKE  | 34200.00 |
| CLARK  | 29400.00 |
| SCOTT  | 36000.00 |
| KING   | 60000.00 |
| TURNER | 18000.00 |
| ADAMS  | 13200.00 |
| JAMES  | 11400.00 |
| FORD   | 36000.00 |
| MILLER | 15600.00 |
+--------+----------+
14 rows in set (0.00 sec)
```

起别名~

```sql
mysql> select ename,sal*12 as '年薪' from emp;
+--------+----------+
| ename  | 年薪     |
+--------+----------+
| SMITH  |  9600.00 |
| ALLEN  | 19200.00 |
| WARD   | 15000.00 |
| JONES  | 35700.00 |
| MARTIN | 15000.00 |
| BLAKE  | 34200.00 |
| CLARK  | 29400.00 |
| SCOTT  | 36000.00 |
| KING   | 60000.00 |
| TURNER | 18000.00 |
| ADAMS  | 13200.00 |
| JAMES  | 11400.00 |
| FORD   | 36000.00 |
| MILLER | 15600.00 |
+--------+----------+
14 rows in set (0.00 sec)
```

别名是中文用单引号括起来。

## 2. 条件查询

### 2.1 什么是条件查询

不是将表中所有数据都查询出来

语法格式：

```txt
select

	字段1，字段2，字段3...

 from

	表名

 where

	条件;
```

### 2.2 都有哪些条件

=等于

​	查询薪资等于八百的员工姓名和编号？

```sql
mysql> select empno,ename from emp where sal = 800;
+-------+-------+
| empno | ename |
+-------+-------+
|  7369 | SMITH |
+-------+-------+
1 row in set (0.00 sec)
```

查询SMITH的编号和薪资

```sql	
select empno,sal from emp where ename = 'SMITH';
```

查询薪资不等于八百的员工姓名和编号？

<>或！=不等于

```sql
mysql> select empno,ename from emp where sal != 800;
mysql> select empno,ename from emp where sal <> 800;//小于号和大于号组成不等号
+-------+--------+
| empno | ename  |
+-------+--------+
|  7499 | ALLEN  |
|  7521 | WARD   |
|  7566 | JONES  |
|  7654 | MARTIN |
|  7698 | BLAKE  |
|  7782 | CLARK  |
|  7788 | SCOTT  |
|  7839 | KING   |
|  7844 | TURNER |
|  7876 | ADAMS  |
|  7900 | JAMES  |
|  7902 | FORD   |
|  7934 | MILLER |
+-------+--------+
'''
# 项目名称

简要介绍项目的概况和目标。

## 安装

描述如何安装和配置项目的步骤。

### 依赖项

列出项目所需的依赖项和版本号。

## 使用方法

提供项目的使用指南和示例代码。

### 示例

提供简单的示例，展示项目的用法和功能。

## 功能列表

列出项目的主要功能和特性。

## 贡献指南

描述如何贡献代码或报告问题。

## 版本历史

记录项目的版本更新和变更。

## 常见问题

列出一些常见问题和解决方法。

## 授权和许可

说明项目的授权许可方式。

## 联系方式

提供作者或团队的联系方式。

