package com.example.salary.service;


import com.example.salary.domain.Absence;
import com.example.salary.domain.Salary;
import com.example.salary.domain.Stuff;
import com.example.salary.mapper.StuffMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

@Service
public class StuffService {
        @Resource
        StuffMapper stuffMapper;
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
            return stuffMapper.delStuff(unum);
        };
        //插入新员工
        public Integer addNewStuff(String unum,String uname,Integer age,String position,String bankid,String pid,String dnum,String gender){
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
}
