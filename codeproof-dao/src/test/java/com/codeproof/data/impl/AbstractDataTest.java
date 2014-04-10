package com.codeproof.data.impl;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

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
public class AbstractDataTest<DataService> {

	private DBCollection collection;
	private MongoClient client;
	private MongodStarter runtime;
	private IMongodConfig mongodConfig;
	private MongodExecutable mongodExecutable;

	@Autowired
	MongoTemplate mongoTemplate;

	@Before
	public void setUp() throws InterruptedException, UnknownHostException,
			IOException {

		int port = 27017;

		runtime = MongodStarter.getDefaultInstance();
		mongodConfig = new MongodConfigBuilder()
				.version(Version.Main.PRODUCTION)
				.net(new Net(port, Network.localhostIsIPv6())).build();

		mongodExecutable = runtime.prepare(mongodConfig);

		MongodProcess mongod = mongodExecutable.start();

		/*
		 * server = new MongoServer(new MemoryBackend());
		 * 
		 * // bind on a random local port InetSocketAddress address = new
		 * InetSocketAddress(27017); server.bind(address);
		 */

		// client = new MongoClient(new ServerAddress(address));
		// collection = client.getDB("testdb").getCollection("testcollection");

		assertNotNull(mongoTemplate);
	}

	@After
	public void tearDown() {
		// client.close();
		mongodExecutable.stop();
	}
}
