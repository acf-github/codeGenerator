package com.bv.codegenerator.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bv.codegenerator.service.CodeGeneratorService;

@RestController
@RequestMapping("generator")
public class CodeGeneratorController {
	
	private CodeGeneratorService codeGeneratorService;
	
	@Autowired
	public CodeGeneratorController(CodeGeneratorService codeGeneratorService) {
		this.codeGeneratorService = codeGeneratorService;
	}

	@GetMapping(value = "/generate")
	public ResponseEntity<?> generate() {
		return ResponseEntity.status(HttpStatus.OK).body(codeGeneratorService.generate());
	}

}
