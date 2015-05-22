package com.beingjavaguys.dao;


import org.hibernate.SessionFactory;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = HibernateConfig.class)
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
@Transactional
public class BaseTest {

    @Ignore
    @Test
    public void testDummy() {
        Assert.assertNull(null);
    }
    
    @Autowired
    private SessionFactory sessionFactory; 
    
    @Before
    public void setUp() throws Exception {
    	TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(sessionFactory.openSession()));
	}

    @After
    public void tearDown() throws Exception {
		SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager.unbindResource(sessionFactory);
		SessionFactoryUtils.closeSession(sessionHolder.getSession());
    }
   
}
