/*
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package runner;

import java.io.File;
import java.util.Collection;

import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import bs.nina.monitor.CloneEvent;

public class RuleRunner {

	public RuleRunner() {
	}

	public void runRules(String[] rules, Object[] facts) {

		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();

		for (int i = 0; i < rules.length; i++) {
			String ruleFile = rules[i];
			System.out.println("Loading file: " + ruleFile);
			File f = new File(ruleFile);
			kbuilder.add(ResourceFactory.newFileResource(ruleFile),
					ResourceType.DRL);
		}
		if (kbuilder.hasErrors()) {
			System.err.println(kbuilder.getErrors().toString());
		}

		Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();
		kbase.addKnowledgePackages(pkgs);
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

		for (int i = 0; i < facts.length; i++) {
			// Object fact = facts[i];
			// System.out.println("Inserting fact: " + fact);
			// ksession.insert(fact);
			// ksession.fireAllRules();
			CloneEvent fact = (CloneEvent) facts[i];
			System.out.println("Inserting fact: " + fact);
			ksession.insert(fact);
		}
		ksession.fireAllRules();
		// System.out.println("yayaya");

	}
}
