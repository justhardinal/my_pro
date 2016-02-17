package com.community.dao;

import com.community.dao.MemberDao;
import com.community.CareerCommunityApplication;
import com.community.entity.Member;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.sql.DataSource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CareerCommunityApplication.class)
public class MemberDaoTest {
    
    @Autowired
    MemberDao md;
    
    @Autowired
    DataSource ds;
    
    @Test
    public void testInsert() throws SQLException {
        Member m = new Member();
        m.setFullname("Hardinal Fahmi Syaputra");
        m.setNickname("Hardinal");
        m.setBirthdate(new Date());
        m.setEmail("hardinal.fahmi@gmail.com");
        m.setAddress("Jakarta");
        m.setPhoneno("0821441443");
        
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
    
    
    
}
