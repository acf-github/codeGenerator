package com.bv.codegenerator.domain;

import java.util.ArrayList;
import java.util.List;

public class PlanilhaRepresentation {

	private String entityName;
	private String tableName;
	private List<AtributosPlanilha> atributoList;

	public PlanilhaRepresentation(String entityName) {
		this.entityName = entityName;
		this.atributoList = new ArrayList<AtributosPlanilha>();
	}

	public String getRequestDtoConstrutor() {
		String retorno = "";

		for (AtributosPlanilha atributosPlanilha : atributoList) {

			retorno = retorno + "@JsonProperty(\"" + atributosPlanilha.getAtributeName() + "\")"
					+ atributosPlanilha.getAtributeTypeValue() + " " + atributosPlanilha.getAtributeName() + ",";
		}
		return retorno;
	}
	
	public String getNomeTeste() {
		return "testeNome";
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public List<AtributosPlanilha> getAtributoList() {
		return atributoList;
	}

	public void setAtributoList(List<AtributosPlanilha> atributoList) {
		this.atributoList = atributoList;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
