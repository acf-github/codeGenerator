package com.bv.codegenerator.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class GeneratorBackEnd implements Generate {

	private static GeneratorBackEnd instance;
	private static final String URL_RESOURCE = "/src/main/resources/BackEnd/";
	private static final String URL_DESTINO = System.getProperty("user.home") + "/Desktop/Generated/";

	public static GeneratorBackEnd getInstance() {
		return instance == null ? new GeneratorBackEnd() : instance;
	}

	@Override
	public List<File> generate(VelocityEngine velocity, Collection<PlanilhaRepresentation> planilhaList) {

		List<File> retorno = new ArrayList<File>();

		retorno.add(generateApplication(velocity));
		retorno.add(generateApplicationLocal(velocity));
		retorno.addAll(generateEntities(velocity, planilhaList));

		return retorno;
	}

	private File generateApplication(VelocityEngine velocity) {

		VelocityContext context = new VelocityContext();
		Template t = velocity.getTemplate(URL_RESOURCE + "template-application.vm");

		StringWriter writer = new StringWriter();
		t.merge(context, writer);

		File arquivo = new File(URL_DESTINO + "src/main/resources/application.yml");
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(arquivo);
			fileWriter.write(writer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return arquivo;
	}
	
	private File generateApplicationLocal(VelocityEngine velocity) {

		VelocityContext context = new VelocityContext();
		Template t = velocity.getTemplate(URL_RESOURCE + "template-application-local.vm");

		StringWriter writer = new StringWriter();
		t.merge(context, writer);

		File arquivo = new File(URL_DESTINO + "src/main/resources/application-local.yml");
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(arquivo);
			fileWriter.write(writer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return arquivo;
	}

	private List<File> generateEntities(VelocityEngine velocity, Collection<PlanilhaRepresentation> planilhaList) {

		List<File> retorno = new ArrayList<File>();
		Template t = velocity.getTemplate(URL_RESOURCE + "template-entities.vm");

		for (PlanilhaRepresentation planilha : planilhaList) {

			VelocityContext context = new VelocityContext();
			context.put("planilha", planilha);

			StringWriter writer = new StringWriter();
			t.merge(context, writer);

			File arquivo = new File(
					URL_DESTINO + "src/main/java/br/com/bv/crud/model/" + planilha.getEntityName() + ".java");
			retorno.add(arquivo);
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter(arquivo);
				fileWriter.write(writer.toString());
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return retorno;
	}
}
