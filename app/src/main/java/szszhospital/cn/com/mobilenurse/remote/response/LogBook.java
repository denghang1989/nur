package szszhospital.cn.com.mobilenurse.remote.response;

public class LogBook {


    /**
     * Item112 :
     * Item114 :
     * age : 63岁
     * bed : 泌23
     * condition :
     * diagnose : 肾肿瘤
     * name : 樊定秀
     * no : 236053
     * operation :
     * par :
     * rw :
     * sex : 女
     * type : 今日手术
     */

    public String Item112;
    public String Item114;
    public String age;
    public String bed;
    public String condition;
    public String diagnose;
    public String name;
    public String no;
    public String operation;
    public String par;
    public String rw;
    public String sex;
    public String type;

    @Override
    public String toString() {
        return "LogBook{" +
                "Item112='" + Item112 + '\'' +
                ", Item114='" + Item114 + '\'' +
                ", age='" + age + '\'' +
                ", bed='" + bed + '\'' +
                ", condition='" + condition + '\'' +
                ", diagnose='" + diagnose + '\'' +
                ", name='" + name + '\'' +
                ", no='" + no + '\'' +
                ", operation='" + operation + '\'' +
                ", par='" + par + '\'' +
                ", rw='" + rw + '\'' +
                ", sex='" + sex + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
