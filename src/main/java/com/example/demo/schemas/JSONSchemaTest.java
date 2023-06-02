package com.example.demo.schemas;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class JSONSchemaTest {

    /**
     * Function for validating json files to predefined json schemas
     * @throws ValidationException when there are errors in validation
     * @throws FileNotFoundException when file path is not found
     */
    public void validateJsonDocument() throws ValidationException, FileNotFoundException {

        /* Report */
        File jsonDataReport = new File("src/main/java/com/example/demo/schemas/report/report.json");
        JSONTokener jsonDataFileReport = new JSONTokener(new FileInputStream(jsonDataReport));
        JSONObject jsonObjectReport = new JSONObject(jsonDataFileReport);

        File schemaFileReport = new File("src/main/java/com/example/demo/schemas/report/report_valid.json");
        JSONTokener schemaDataReport = new JSONTokener(new FileInputStream(schemaFileReport));
        JSONObject jsonSchemaReport = new JSONObject(schemaDataReport);

        Schema schemaValidatorReport = SchemaLoader.load(jsonSchemaReport);
        schemaValidatorReport.validate(jsonObjectReport);

        /* IMDB */
        File jsonDataImdb = new File("src/main/java/com/example/demo/schemas/imdb/imdb.json");
        JSONTokener jsonDataFileImdb = new JSONTokener(new FileInputStream(jsonDataImdb));
        JSONObject jsonObjectImdb = new JSONObject(jsonDataFileImdb);

        File schemaFileImdb = new File("src/main/java/com/example/demo/schemas/imdb/imdb_valid.json");
        JSONTokener schemaDataImdb = new JSONTokener(new FileInputStream(schemaFileImdb));
        JSONObject jsonSchemaImdb = new JSONObject(schemaDataImdb);

        Schema schemaValidatorImdb = SchemaLoader.load(jsonSchemaImdb);
        schemaValidatorImdb.validate(jsonObjectImdb);

        /* Netflix */
        File jsonDataNetflix = new File("src/main/java/com/example/demo/schemas/netflix/netflix.json");
        JSONTokener jsonDataFileNetflix = new JSONTokener(new FileInputStream(jsonDataNetflix));
        JSONObject jsonObjectNetflix = new JSONObject(jsonDataFileNetflix);

        File schemaFileNetflix = new File("src/main/java/com/example/demo/schemas/netflix/netflix_valid.json");
        JSONTokener schemaDataNetflix = new JSONTokener(new FileInputStream(schemaFileNetflix));
        JSONObject jsonSchemaNetflix = new JSONObject(schemaDataNetflix);

        Schema schemaValidatorNetflix = SchemaLoader.load(jsonSchemaNetflix);
        schemaValidatorNetflix.validate(jsonObjectNetflix);
    }
}

