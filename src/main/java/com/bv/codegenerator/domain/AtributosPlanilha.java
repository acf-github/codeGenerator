package com.bv.codegenerator.domain;

public class AtributosPlanilha {
	
	public enum AtributeType {
		STRING, DOUBLE, INTEGER, BOOLEAN, LONG, LIST, DECIMAL, DATE
	}

	private String atributeName;
	private String columnName;
	private String nullAble;
	
	private AtributeType atributeType;

	private AtributeType atributeListType;
	private AtributeType atributeMapKType;
	private AtributeType atributeMapVType;

	public String getAtributeName() {
		return atributeName;
	}
	public String getAtributeNameForGetSet() {
		return atributeName.replace(atributeName.substring(0,1), atributeName.substring(0,1).toUpperCase());
	}

	public void setAtributeName(String atributeName) {
		this.atributeName = atributeName;
	}

	public AtributeType getAtributeType() {
		return atributeType;
	}

	public String getAtributeTypeValue() {
		
		String retorno = "";
		
		switch (atributeType) {
		case STRING:
			retorno = "String";
			break;
		case BOOLEAN:
			retorno = "boolean";
			break;
		case DECIMAL:
		case DOUBLE:
			retorno = "Double";
			break;
		case DATE:
			retorno = "Date";
			break;
		case INTEGER:
			retorno = "Integer";
			break;
		case LIST:
			retorno = "List<T>";
			break;
		case LONG:
			retorno = "Long";
			break;

		default:
			retorno = atributeType.name();
			break;
		}
		return retorno;
	}

	public void setAtributeType(AtributeType atributeType) {
		this.atributeType = atributeType;
	}

	public AtributeType getAtributeListType() {
		return atributeListType;
	}

	public void setAtributeListType(AtributeType atributeListType) {
		this.atributeListType = atributeListType;
	}

	public AtributeType getAtributeMapVType() {
		return atributeMapVType;
	}

	public void setAtributeMapVType(AtributeType atributeMapVType) {
		this.atributeMapVType = atributeMapVType;
	}

	public AtributeType getAtributeMapKType() {
		return atributeMapKType;
	}

	public void setAtributeMapKType(AtributeType atributeMapKType) {
		this.atributeMapKType = atributeMapKType;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getNullAble() {
		return nullAble;
	}

	public void setNullAble(String nullAble) {
		this.nullAble = nullAble;
	}


}
