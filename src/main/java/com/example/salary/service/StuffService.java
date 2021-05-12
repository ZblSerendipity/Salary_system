package com.example.salary.service;


import com.example.salary.domain.Absence;
import com.example.salary.domain.Salary;
import com.example.salary.domain.Stuff;
import com.example.salary.domain.User;
import com.example.salary.mapper.StuffMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

@Service
public class StuffService {
        @Resource
        StuffMapper stuffMapper;

        public User findUserByUnum(String unum){
            return stuffMapper.findUserByUnum(unum);
        }
        public void  checkLogin(String unum,String upassword)throws Exception{
            Subject subject = SecurityUtils.getSubject();

            UsernamePasswordToken token = new UsernamePasswordToken(unum,upassword);
            subject.login(token);
            System.out.println("验证login");
            subject.getSession();
        }



        //查询所有员工
        public List<Stuff> queryAll(Integer count){

            return stuffMapper.queryAll(count);
        };

        //查询员工数
        public Integer getStuffRows(){
            return stuffMapper.getStuffRows();
        };

        //删除员工
        public Integer delStuff(String unum){
            stuffMapper.delUrs(unum);
            stuffMapper.delUser(unum);
            return stuffMapper.delStuff(unum);
        };
        //插入新员工
        public Integer addNewStuff(String unum,String uname,Integer age,String position,String bankid,String pid,String dnum,String gender){

            Md5Hash pass = new Md5Hash("123456");

            stuffMapper.addNewUser(unum,uname,pass.toString());
            return stuffMapper.addNewStuff(unum, uname, position, age, bankid, pid, dnum, gender);
        };

        public String queryNumByName(String uname){
            return stuffMapper.queryNumByName(uname);
        };
        //插入缺勤信息
        public Integer insertAbs(String unum, Date date){
            return stuffMapper.insertAbs(unum, date);

        };
        //更新工资表中缺勤天数
        public Integer updateAbs(String unum,Date date,Integer count){
            return stuffMapper.updateAbs(unum, date, count);
        };
        // 查询缺勤表中对应员工的缺勤天数
        public Integer queryAbsDays(String unum , Date date){
            return stuffMapper.queryAbsDays(unum, date);
        };

        //缺勤人员查询
        public List<Absence> queryAllAbs(Integer page,Integer size){
            Integer begin = (page - 1)* size;
            return stuffMapper.queryAllAbs(begin,size);
        };
        //缺勤表条数查询
        public Integer queryAbsTabRows(){
            return stuffMapper.queryAbsTabRows();
        };
          //对应缺勤人员查询
         public List<Absence> queryAbsByNum(String unum,Integer page,Integer size){
             Integer begin = (page - 1)* size;
             return stuffMapper.queryAbsByNum(unum,begin,size);
         };
         //对应缺勤人员缺勤数查询
         public Integer queryAbsRowsByUnum(String unum){
             return stuffMapper.queryAbsRowsByUnum(unum);
         };

    //批量插入员工工资信息
    public boolean insStuffsWage(List<Salary> list){



        return false;
    }
    //根据员工号查找员工姓名
    public String queryNameByNum(String unum){
        return stuffMapper.queryNameByNum(unum);
    };
    //根据员工姓名查找员工
    public String queryUnumByUname(String uname){
        return stuffMapper.queryNumByName(uname);
    };
    //查询对应工号的员工
    public List<Stuff> queryStuff(String unum){
        return stuffMapper.queryStuff(unum);
    };

    //插入银行卡信息
    public  Integer insertBank(String bankid,String banksort,String bankplace){
        return stuffMapper.insertBank(bankid, banksort, bankplace);
    };
}
