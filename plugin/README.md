jsconschematypes plugin is a Gradle plugin to generate Java classes from
standard JSON shemas, that allow access to JSON data with the benefit of
compile-time type and structure checking.

It is written by jimblackler@gmail.com and offered under an
[Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0).

It is a client of the underlying jsonschematypes Java library.

# Usage

These instructions have been tested on IntellJ IDEA, and Android Studio.

Add `id 'net.jimblackler.jsonschematypes' version '0.8.1'` to the *plugins*
section in your module's `build.gradle`.

```groovy
plugins {
    //...
    id 'net.jimblackler.jsonschematypes' version '0.8.1'
}
```

Also in the module's `build.gradle` add a section to configure the namespace of
the generated types.

```groovy
jsonSchemaTypes {
    resourcesPath = 'schemas'
    packageOut = 'com.example.myproject'
}
```

To your `settings.gradle` file, under `pluginManagement`, make sure JitPack is
listed as a repository.

```groovy
pluginManagement {
    repositories {
        maven { url "https://jitpack.io" }
        //...
    }
}
```

Add the schmea .json file or files to your module's `resources` folder under a
folder you created called (in the case) `schemas`.

Build the project. You will now be able to import your schemas in your Java code
under the `com.example.myproject` namespace.
