package com.bv.codegenerator.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Service;

import com.bv.codegenerator.domain.AtributosPlanilha;
import com.bv.codegenerator.domain.GeneratorBackEnd;
import com.bv.codegenerator.domain.PlanilhaRepresentation;
import com.bv.codegenerator.domain.AtributosPlanilha.AtributeType;
import com.bv.codegenerator.service.CodeGeneratorService;

@Service
public class CodeGeneratorServiceImpl implements CodeGeneratorService {

	@Override
	public List<File> generate() {

		List<File> retorno = new ArrayList<File>();
		Map<String, PlanilhaRepresentation> mapEntity = new HashMap<String, PlanilhaRepresentation>();

		try {

			File excelFile = new File(
					"C:/Users/a.cernuda.fernandez/Desktop/Code-Generator/src/main/resources/CustomerExample.xlsx");
			FileInputStream fis = new FileInputStream(excelFile);
			Workbook wb = WorkbookFactory.create(fis);

			Sheet s = wb.getSheet("BackEnd");

			int rowNum = 1;

			while (s.getRow(rowNum) != null) {

				Cell entityName = s.getRow(rowNum).getCell(0);
				Cell tableName = s.getRow(rowNum).getCell(1);
				
				Cell entityAtribute = s.getRow(rowNum).getCell(2);
				Cell entityAtributeColumn = s.getRow(rowNum).getCell(3);
				Cell entityAtributeNullAble = s.getRow(rowNum).getCell(4);
				Cell entityAtributeType = s.getRow(rowNum).getCell(5);

				if (!mapEntity.containsKey(entityName.getStringCellValue())) {
					mapEntity.put(entityName.getStringCellValue(),
							new PlanilhaRepresentation(entityName.getStringCellValue()));
				}

				PlanilhaRepresentation temp = mapEntity.get(entityName.getStringCellValue());
				temp.setTableName(tableName.getStringCellValue());

				AtributosPlanilha atributeTemp = new AtributosPlanilha();
				atributeTemp.setAtributeName(entityAtribute.getStringCellValue());
				atributeTemp
						.setAtributeType(AtributeType.valueOf(entityAtributeType.getStringCellValue().toUpperCase()));
				atributeTemp.setColumnName(entityAtributeColumn.getStringCellValue());
				atributeTemp.setNullAble(new Boolean(entityAtributeNullAble.getBooleanCellValue()).toString());
				
				temp.getAtributoList().add(atributeTemp);
				rowNum++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		VelocityEngine velocity = new VelocityEngine();
		velocity.init();

		retorno.addAll(GeneratorBackEnd.getInstance().generate(velocity, mapEntity.values()));

		return retorno;
	}
}
