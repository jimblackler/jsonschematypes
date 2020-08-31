jsconschematypes is a Java library to generate Java classes from standard JSON
Schemas.

It is written by jimblackler@gmail.com and offered under an
[Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0).

It allows Java applications that read JSON data to be type-checked at compile
time, reducing the chances of errors due to misinterpreting the format or
mistyping property names. The generated files will also trigger IDE code
completion, assisting the programmer in writing code that reads the JSON.

It is compatible with the following metaschemas:

*   http://json-schema.org/draft-03/schema#
*   http://json-schema.org/draft-04/schema#
*   http://json-schema.org/draft-06/schema#
*   http://json-schema.org/draft-07/schema#

The classes created by jsonschematypes are wrappers around the official JSON
classes
[`org.json.JSONObject`](https://www.javadoc.io/doc/org.json/json/20171018/org/json/JSONObject.html)
and
[`org.json.JSONArray`](https://www.javadoc.io/doc/org.json/json/20171018/org/json/JSONArray.html)

That approach makes it an alternative to using a library like
[jsonschema2pojo](https://github.com/joelittlejohn/jsonschema2pojo) that enables
deserialization of a JSON string into a tree of generated Java objects. The
approach can generally only support a subset of JSON Schema. If more complex
schema elements not understood by the generator - such as `anyOf` - are used,
parts of the schema are not included in the generated class, making parts of the
loaded JSON data invisible to the Java code reading the data in the best case.
In the worst case the entire data tree is unreadable.

As jsonschematypes does not peform any deserialization itself, but defers that
to the the `org.json` library, it is guaranteed that a whole JSON object can be
read. The classes include methods `getJsonObject()` and `getJsonArray()` that
allow the Java application to fall back to unstructured access whenever
necessary or desired. Neccessary in the case of a complex schema structure being
infeasible for the class generator to adapt, and desired in the case of
gradually adapting a standard Java app that uses `org.json` types to using the
structured classes generated by jsonschematypes.

# Example

The following JSON schema...

```json
{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "type": "object",
  "properties": {
    "firstName": {
      "type": "string"
    },
    "age": {
      "type": "integer"
    }
  },
  "required": ["firstName", "age"]
}
```

... would result in the following generated Java code.

```java
package org.example;

import org.json.JSONObject;

public class Person {

  private final JSONObject jsonObject;

  public Person(JSONObject jsonObject) {
    this.jsonObject = jsonObject;
  }

  public JSONObject getJSONObject() {
    return jsonObject;
  }

  public String getFirstName() {
    return jsonObject.getString("firstName");
  }

  public int getAge() {
    return jsonObject.getInt("age");
  }
}
```

By constructing a new `Person` object and passing in an existing `JSONObject`, a
program can access the data, pass and store it, and maintain greater type safety
and code clarify than handling a raw `JSONObject`.

# Implementation

The library uses the `net.jimblackler.jsonschemafriend` Schema loader/validator
to help interpret the schemas and their structure.