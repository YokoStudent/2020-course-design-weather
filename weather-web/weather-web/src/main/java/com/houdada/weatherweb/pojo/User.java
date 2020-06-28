package com.houdada.weatherweb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private Integer sex;

    private Date createTime;
}
