package kr.ac.jejunu.userdao;


import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.in;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

public class UserDaoTests {
    private static UserDao userDao;

    @BeforeAll
    public static void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test
    public void testGet() throws SQLException {
        Integer id = 200;
        String name = "Jade";
        String password = "1234";

        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void testInsert() throws SQLException {
        String name = "sgdho";
        String password = "aghrtk";
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userDao.insert(user);

        assertThat(user.getId(), greaterThan(0));
        User insertedUser = userDao.get(user.getId());
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));
    }

    @Test
    public void testUpdate() throws SQLException {
        String name = "asrdho";
        String password = "ahrtk";
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userDao.insert(user);

        String newName = "hltj";
        String newPassword = "fhgo";
        user.setName(newName);
        user.setPassword(newPassword);

        userDao.update(user);

        User updatedUser = userDao.get(user.getId());
        assertThat(updatedUser.getName(), is(newName));
        assertThat(updatedUser.getPassword(), is(newPassword));
    }

    @Test
    public void testDelete() throws SQLException {
        String name = "hjydho";
        String password = "tk135";
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userDao.insert(user);
        userDao.delete(user.getId());

        User deletedUser = userDao.get(user.getId());
        assertThat(deletedUser, IsNull.nullValue());
    }
}