# Abstract File Mapper

# How to use

## Example
```java
public class FileMapper extends AbstractFileMapper<TestClass> {
       @Override
       public TestClass ToType(String fileLine) throws ReflectiveOperationException {
           return super.ToType(fileLine);
       }
   }
```

# Collaborators