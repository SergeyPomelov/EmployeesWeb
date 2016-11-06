package spring;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Classes descended from this will be able to use the spring context.
 *
 * @author Sergey Pomelov on 01/08/2014.
 */
@SuppressWarnings({"ClassMayBeInterface", "AbstractClassWithoutAbstractMethods"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/spring-core-config_test.xml")
public abstract class SpringContextTest { }
