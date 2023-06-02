package com.example.demo;

import com.example.demo.schemas.JSONSchemaTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws FileNotFoundException {
        SpringApplication.run(DemoApplication.class, args);
        JSONSchemaTest jsonSchemaTest = new JSONSchemaTest();
        jsonSchemaTest.validateJsonDocument();
        System.out.println("Validating imdb.xml against imdb.xsd - " + validateXMLSchema("src/main/java/com/example/demo/schemas/imdb/imdb.xsd", "src/main/java/com/example/demo/schemas/imdb/imdb.xml"));
        System.out.println("Validating netflix.xml against netflix.xsd - " + validateXMLSchema("src/main/java/com/example/demo/schemas/netflix/netflix.xsd", "src/main/java/com/example/demo/schemas/netflix/netflix.xml"));
        System.out.println("Validating report.xml against report.xsd - " + validateXMLSchema("src/main/java/com/example/demo/schemas/report/report.xsd", "src/main/java/com/example/demo/schemas/report/report.xml"));
    }

    public static boolean validateXMLSchema(String xsdPath, String xmlPath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
            return true;
        } catch (SAXException | IOException var5) {
            System.out.println("Exception: " + var5.getMessage());
            return false;
        }
    }
}
