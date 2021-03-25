package net.jimblackler.jsonschematypes.plugin;

public class JsonSchemaTypesPluginExtension {
  private String resourcesPath = "";
  private String packageOut = "org.example";
  private String mode = "org.json";

  public String getResourcesPath() {
    return resourcesPath;
  }

  public void setResourcesPath(String resourcesPath) {
    this.resourcesPath = resourcesPath;
  }

  public String getPackageOut() {
    return packageOut;
  }

  public void setPackageOut(String packageOut) {
    this.packageOut = packageOut;
  }

  public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }
}
