##### user用户表

| 字段               | 类型         | 说明           |
| ------------------ | ------------ | -------------- |
| user_id            | int(11)      | 用户自增id     |
| user_username      | varchar(40)  | 用户名         |
| user_password      | varchar(255) | 密码           |
| user_sex           | tinyint(1)   | 性别。1男，0女 |
| user_age           | int          | 年龄           |
| user_register_time | date         | 注册时间       |

#####  inquiry_record查询表

| 字段               | 类型        | 说明                           |
| ------------------ | ----------- | ------------------------------ |
| record_id          | int(11)     | 记录自增id                     |
| user_id            | int(11)     | 用户id                         |
| inquiry_time       | datetime    | 查询时间                       |
| inquiry_city_value | varchar(20) | 查询城市名称。湖南-常德-武陵区 |