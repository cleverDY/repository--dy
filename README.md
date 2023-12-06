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

