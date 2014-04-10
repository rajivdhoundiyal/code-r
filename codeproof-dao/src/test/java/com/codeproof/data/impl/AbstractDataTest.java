package com.codeproof.data.impl;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/applicationContext.xml" })
public class AbstractDataTest<DataService> {

	private static MongodStarter runtime;
	private static IMongodConfig mongodConfig;
	private static MongodExecutable mongodExecutable;

	@Autowired
	MongoTemplate mongoTemplate;

	@BeforeClass
	public static void onInitialization() throws InterruptedException, UnknownHostException,
			IOException {

		int port = 27017;

		runtime = MongodStarter.getDefaultInstance();
		mongodConfig = new MongodConfigBuilder()
				.version(Version.Main.PRODUCTION)
				.net(new Net(port, Network.localhostIsIPv6())).build();

		mongodExecutable = runtime.prepare(mongodConfig);

		mongodExecutable.start();
	}

	@Before
	public void onSetUp() {
		assertNotNull(mongoTemplate);
	}
	
	@After
	public void ontearDown() {
		assertNotNull(mongoTemplate);
	}
	
	@AfterClass
	public static void onFinalTearDown() {
		mongodExecutable.stop();
	}
}
