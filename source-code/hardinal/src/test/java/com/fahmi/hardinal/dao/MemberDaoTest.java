package com.fahmi.hardinal.dao;

import com.fahmi.hardinal.dao.MemberDao;
import com.fahmi.hardinal.HardinalApplication;
import com.fahmi.hardinal.entity.Member;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HardinalApplication.class)
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = "/data/member.sql"
)

public class MemberDaoTest {
    
    @Autowired
    MemberDao md;
    
    @Autowired
    DataSource ds;
    
    @After
    public void testDelete() throws Exception{
        String sql="delete from member where email='hardinal.fahmi@gmail.com'";
         try (Connection c = ds.getConnection()) {
             c.createStatement().executeUpdate(sql);
         }
    }
    
    @Test
    public void testInsert() throws SQLException {
        Member m = new Member();
        m.setFullname("Hardinal Fahmi Syaputra");
        m.setNickname("Hardinal");
        m.setBirthdate(new Date());
        m.setEmail("hardinal.fahmi@gmail.com");
        m.setAddress("Jakarta");
        m.setPhoneno("0821441443");
        m.setPasswd("123");
        md.save(m);
        
        String sql = "select count(*) as jumlah "
                + "from member "
                + "where email ='hardinal.fahmi@gmail.com'";
        
        try(Connection c = ds.getConnection()){
            ResultSet rs = c.createStatement().executeQuery(sql);
            Assert.assertTrue(rs.next());

            Long jumlahRow = rs.getLong("jumlah");
            Assert.assertEquals(1L, jumlahRow.longValue());
        }
    }
    
    @Test
    public void testHitung(){
        Long jumlah = md.count();
        Assert.assertEquals(3L, jumlah.longValue());
    }
    
    @Test
    public void testFindById(){
        Member m = md.findOne("m0001");
        Assert.assertNotNull(m);
        Assert.assertEquals("Fahmi Syaputra", m.getFullname());
        Assert.assertEquals("member.test.001@gmail.com", m.getEmail());
        
        Member mx = md.findOne("xx");
        Assert.assertNull(mx);
        
    }
    
}
