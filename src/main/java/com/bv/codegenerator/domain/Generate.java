package com.bv.codegenerator.domain;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.apache.velocity.app.VelocityEngine;

public interface Generate {

	List<File> generate(VelocityEngine velocity, Collection<PlanilhaRepresentation> planilhaList);
}
