package net.jimblackler.jsonschematypes.codegen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import net.jimblackler.jsonschemafriend.SchemaException;
import net.jimblackler.jsonschemafriend.SchemaStore;

public class CodeGeneration {
  public static void build(URL url, CodeGenerator codeGenerator) throws CodeGenerationException {
    SchemaStore schemaStore = new SchemaStore();

    try (InputStream stream = url.openStream()) {
      try (BufferedReader bufferedReader =
               new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
        String resource;
        while ((resource = bufferedReader.readLine()) != null) {
          if (!resource.endsWith(".json")) {
            continue;
          }
          URI uri = URI.create(url + (url.toString().endsWith("/") ? "" : "/") + resource);
          codeGenerator.build(schemaStore.loadSchema(uri));
        }
      }
    } catch (SchemaException | IOException e) {
      throw new CodeGenerationException(e);
    }
  }

  public static void build(URI uri, CodeGenerator codeGenerator) throws CodeGenerationException {
    try {
      codeGenerator.build(new SchemaStore().loadSchema(uri));
    } catch (SchemaException e) {
      throw new CodeGenerationException(e);
    }
  }
}
