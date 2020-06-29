#### 1\. 用户操作

---

##### **1\. 注册**

###### 接口功能
> 用户注册

###### URL
> [http://localhost:8081/api/v1/user](http://localhost:8081/api/v1/user)

###### 支持格式
> JSON

###### HTTP请求方式
> POST

###### 请求参数
> | 参数     | 必选 | 类型   | 说明                   |
> | :------- | :--- | :----- | ---------------------- |
> | username | ture | string | 姓名                   |
> | password | true | string | 密码                   |
> | sex      | true | int    | 性别。1：男；0：女。   |
> | age      | true | int    | 年龄。min：0；max：120 |

###### 返回字段
> | 返回字段 | 字段类型 | 说明                                                      |
> | :------- | :------- | :-------------------------------------------------------- |
> | status   | int      | 返回结果状态。200：注册成功；403：注册失败；504请求超时。 |
> | msg      | string   | 描述。successmsg；errormsg                                |

###### 接口示例
> 地址：[http://localhost:8081/api/v1/user/register?username="yoko"...&registerTime="2020-6-26"](http://localhost:8081/api/v1/user/register?username="yoko"...&registerTime="2020-6-26")
>
``` javascript
{
    "status": 200,
    "msg": "注册成功",
}
```

```javascript
{
    "status": 403,
    "msg": "密码不一致",
}
```

##### **2\. 登录**

###### 接口功能

> 用户登录

###### URL

> [http://localhost:8081/api/v1/user](http://localhost:8081/api/v1/user)

###### 支持格式

> JSON

###### HTTP请求方式

> POST

###### 请求参数

> | 参数     | 必选 | 类型   | 说明 |
> | :------- | :--- | :----- | ---- |
> | username | ture | string | 姓名 |
> | password | true | string | 密码 |

###### 返回字段

> | 返回字段 | 字段类型 | 说明                                                      |
> | :------- | :------- | :-------------------------------------------------------- |
> | status   | int      | 返回结果状态。200：登录成功；403：登录失败；504请求超时。 |
> | msg      | string   | 描述。successmsg；errormsg                                |
> | token    | string   | 登录成功的用户token                                       |

###### 接口示例

> 地址：[http://localhost:8081/api/v1/user/login?username="yoko"&password="123456"](http://localhost:8081/api/v1/user/login)
>

``` javascript
{
    "status": 200,
    "msg": "登录成功",
    "token": "nnAMqeLWK8X1XPzF",
}
```

```javascript
{
    "status": 403,
    "msg": "不存在用户",
}
```
##### **3\. 退出**

###### 接口功能

> 用户退出登录

###### URL

> [http://localhost:8081/api/v1/user](http://localhost:8081/api/v1/user)

###### 支持格式

> JSON

###### HTTP请求方式

> GET

###### 请求头

> | 参数        | 必选  | 类型   | 说明      |
> | :---------- | :---- | :----- | --------- |
> | accessToken | false | string | 用户token |

###### 返回字段

> | 返回字段 | 字段类型 | 说明                                                      |
> | :------- | :------- | :-------------------------------------------------------- |
> | status   | int      | 返回结果状态。200：登录成功；403：登录失败；504请求超时。 |
> | msg      | string   | 描述。successmsg；errormsg                                |

###### 接口示例

> 地址：[http://localhost:8081/api/v1/user/logout"](http://localhost:8081/api/v1/user/logout)
>

``` javascript
{
    "status": 200,
    "msg": "success",
}
```

#### 2\. 天气操作

---

##### **1\. 获取今日查询次数**

###### 接口功能

> 用户登录状态使用token获取用户今日查询次数，未登录状态使用请求ip获取今日查询次数

###### URL

> [http://localhost:8081/api/v1/weather](http://localhost:8081/api/v1/weather)

###### 支持格式

> JSON

###### HTTP请求方式

> GET

###### 请求头

> | 参数        | 必选  | 类型   | 说明      |
> | :---------- | :---- | :----- | --------- |
> | accessToken | false | string | 用户token |

###### 返回字段

> | 返回字段 | 字段类型 | 说明                                              |
> | :------- | :------- | :------------------------------------------------ |
> | status   | int      | 返回结果状态。200：成功；403：失败；504请求超时。 |
> | msg      | string   | 描述。successmsg；errormsg                        |
> | times    | int      | 今日查询次数                                      |

###### 接口示例

> 地址：[http://localhost:8081/api/v1/weather/getTimes](http://localhost:8081/api/v1/weather/getTimes)
>

``` javascript
{
    "status": 200,
    "msg": "成功",
    "times":10
}
```

```javascript
{
    "status": 403,
    "msg": "系统错误",
}
```

##### **2\. 查询天气**

###### 接口功能

> 查询天气

###### URL

> [http://localhost:8081/api/v1/weather](http://localhost:8081/api/v1/weather)

###### 支持格式

> JSON

###### HTTP请求方式

> GET

###### 请求头

> | 参数        | 必选  | 类型   | 说明      |
> | :---------- | :---- | :----- | --------- |
> | accessToken | false | string | 用户token |

###### 请求参数

> | 参数   | 必选 | 类型   | 说明 |
> | :----- | :--- | :----- | ---- |
> | cityID | ture | string | 姓名 |

###### 返回字段

> | 返回字段 | 字段类型 | 说明                                              |
> | :------- | :------- | :------------------------------------------------ |
> | status   | int      | 返回结果状态。200：成功；403：失败；504请求超时。 |
> | msg      | string   | 描述。successmsg；errormsg                        |
> | data     | array    | 未来n天天气预报                                   |

###### 接口示例

> 地址：[http://localhost:8081/api/v1/weather/getWeather](http://localhost:8081/api/v1/weather/getWeather)

``` javascript
{
    "status": 200,
    "msg": "成功",
    "data":{
        "today": {
            "date": "2020-06-25",
            "high": "高温 29℃",
            "low": "低温 26℃",
            "week": "星期四",
            "sunrise": "05:41",
            "sunset": "19:29",
            "aqi": 16,
            "fx": "南风",
            "fl": "2级",
            "type": "大雨",
            "notice": "出门最好穿雨衣，勿挡视线"
        }
        "forecast": [
            {
                "date": "2020-06-25",
                "high": "31",
                "low": "27",
                "notice": "雨虽小，注意保暖别感冒"
            },
            .......
            {
                "date": "2020-07-10",
                "high": "35",
                "low": "27",
                "notice": "不要被阴云遮挡住好心情"
            }
        ]
    }
}
```

```javascript
{
    "status": 403,
    "msg": "系统错误",
}
```

##### **3\. 最近查询**

###### 接口功能

> 获取最近一周查询最频繁的城市

###### URL

> [http://localhost:8081/api/v1/weather](http://localhost:8081/api/v1/weather)

###### 支持格式

> JSON

###### HTTP请求方式

> GET

###### 请求头

> | 参数        | 必选  | 类型   | 说明      |
> | :---------- | :---- | :----- | --------- |
> | accessToken | false | string | 用户token |

###### 返回字段

> | 返回字段 | 字段类型 | 说明                                              |
> | :------- | :------- | :------------------------------------------------ |
> | status   | int      | 返回结果状态。200：成功；403：失败；504请求超时。 |
> | msg      | string   | 描述。successmsg；errormsg                        |
> | data     | array    | 不大于5个城市名称                                 |

###### 接口示例

> 地址：[http://localhost:8081/api/v1/weather/getRecentCitys](http://localhost:8081/api/v1/weather/getRecentCitys)

``` javascript
{
    "status": 200,
    "msg": "成功",
    "data":["永州","长沙","常德","杭州"]
}
```

```javascript
{
    "status": 403,
    "msg": "查询失败",
}
```