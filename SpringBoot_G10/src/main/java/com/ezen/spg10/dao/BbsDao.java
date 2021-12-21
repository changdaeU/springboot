package com.ezen.spg10.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ezen.spg10.BbsDto;

@Repository
public class BbsDao implements IBbsDao{

   @Autowired
   private JdbcTemplate template;
   
   public List<BbsDto> list() {
      String sql = "select * from bbs";
      List<BbsDto> list = template.query(sql, new BeanPropertyRowMapper<BbsDto>(BbsDto.class));
      return list;
   }

   public int write(BbsDto bdto) {
      int result = 0;
      String sql = "insert into bbs values(bbs_seq.nextVal, ?, ?, ?)";
      result = template.update(sql, bdto.getWriter(), bdto.getTitle(), bdto.getContent());
      return result;
   }

   public int update(BbsDto bdto) {
      
      return 0;
   }

   public int delete(String id) {
      String sql = "delete from bbs where id = ?";
      return template.update(sql, id);
   }

   public BbsDto view(String id) {
      String query = "select * from bbs where id = '" + id  + "'";
      BbsDto bdto = template.queryForObject(query, new BeanPropertyRowMapper<BbsDto>(BbsDto.class));
      return bdto;
   }
   
}