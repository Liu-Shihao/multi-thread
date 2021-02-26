package com.lsh.reflectbylsh;

import lombok.Data;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/2/25 7:38 下午
 * @desc ：
 */
@Data
public class Student extends Person {
    private String schoolName;
    private Integer score;
    public String teacher;


    public Student(){
        System.out.println("Student无参构造");
    }
    public Student(String schoolName,int score){
        this.schoolName = schoolName;
        this.score = score;
        System.out.println("Student有参构造");
    }
}
