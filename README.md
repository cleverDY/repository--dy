# work3
## 1.SQL的基本操作
### 1.建立一个数据库
```sql
create database 数据库名称;
```
### 2.建表
```sql
 create table 表名(
         字段1 字段类型,
         字段2 字段类型,
         …
         字段n 字段类型
);
```
例如  
```sql
 create table student(
 id int,
 name varchar(20),
 gender varchar(10),
 birthday date
 );
```
### 3.增加
```sql
INSERT INTO 表名（字段名1,字段名2,...) VALUES (值 1,值 2,...);
```
也可以利用占位符  
```sql
insert into mysqlstudy.tb_goods (goodid, goodname, goodprice, goodNumber)
        values (#{goodId}, #{goodName}, #{goodPrice}, #{goodNumber})
```
#{orderId}、#{goodName}、#{orderNumber}、#{orderTime} 和 #{orderPrice} 是占位符，表示这里应该传入的数据需要从外部传递进来，它们将在执行 SQL 语句时替换成实际的值。  
### 4.删除
在该语法中：表名用于指定要执行删除操作的表；[WHERE 条件表达式]为可选参数用于指定删除的条件。
```sql
DELETE FROM 表名 [WHERE 条件表达式];
```
同时有批量删除
```sql
DELETE FROM mysqlstudy.tb_goods
WHERE goodId IN (value1, value2, value3, ...)
```

