package com.cloudera.sprue;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message.Level;
import org.kie.api.io.KieResources;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

public class KieSessionFactory  implements Serializable {

	static StatelessKieSession statelessKieSession;

	public static StatelessKieSession getKieSession(String filename) {
		if (statelessKieSession == null)
			statelessKieSession = getNewKieSession(filename);
		return statelessKieSession;
	}

	public String readFile(String filename, Charset encoding) {
		String res = "";
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(filename));
			res = new String(encoded, encoding);
		} catch (IOException e) {
			System.out.println("Error reading rules file " + filename);
			e.printStackTrace();
		}
		return res;
	}

	public static StatelessKieSession getNewKieSession(String drlFileName) {
		System.out.println("creating a new kie session");

		KieServices kieServices = KieServices.Factory.get();
		KieResources kieResources = kieServices.getResources();

		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
		KieRepository kieRepository = kieServices.getRepository();

		Resource resource = kieResources.newFileSystemResource(drlFileName);
		kieFileSystem.write(resource);

		KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);

		kb.buildAll();

		if (kb.getResults().hasMessages(Level.ERROR)) {
			throw new RuntimeException("Build Errors:\n"
					+ kb.getResults().toString());
		}

		KieContainer kContainer = kieServices.newKieContainer(kieRepository
				.getDefaultReleaseId());
		return kContainer.newStatelessKieSession();
	}
}
