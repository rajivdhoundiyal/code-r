package com.codeproof.data.impl;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.UnknownHostException;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.Mongo;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/applicationContext.xml" })
public abstract class AbstractDataServiceTest<DS> {

	private final static int PORT = 27018;
	private static MongodStarter runtime;
	private static IMongodConfig mongodConfig;
	private static MongodExecutable mongodExecutable;
	private static MongodProcess mongodProcess;
	@Autowired
	private Mongo mongo;
	
	/*@Autowired*/
	SessionFactory sessionFactory;
	
	protected DS dataService;

	@Autowired
	MongoTemplate mongoTemplate;

	@BeforeClass
	public static void onInitialization() throws InterruptedException, UnknownHostException,
			IOException {

		runtime = MongodStarter.getDefaultInstance();
		
		mongodConfig = new MongodConfigBuilder()
				.version(Version.Main.DEVELOPMENT)
				.net(new Net(PORT, Network.localhostIsIPv6())).build();

		mongodExecutable = runtime.prepare(mongodConfig);

		mongodProcess = mongodExecutable.start();
		
	}

	@Before
	public void onSetUp() throws InterruptedException {
		//assertNotNull(sessionFactory);
		assertNotNull(mongoTemplate);
	}
	
	@AfterClass
	public static void onFinalTearDown() throws InterruptedException {
		mongodProcess.stop();
		mongodExecutable.stop();
		mongodExecutable = null;
		runtime = null;
	}
	
	@After
	public void ontearDown() {
		mongo.dropDatabase("yourdb");
		assertNotNull(mongoTemplate);
	}
	
	
	protected abstract void setDataService(DS dataService);
}
