# Abstract File Mapper

# How to use

## Example
Domain class
```java
public class TestClass {

    @FieldMap(offset = 0, size = 4, mask = "##.##")
    private Double valor;

    @FieldMap(offset = 4, size = 1)
    private String status;

    @FieldMap(offset = 5, size = 14, mask = "##.###.###/####-##")
    private String cnpj;

    public Double getValor() {
        return valor;
    }

    public String getStatus() {
        return status;
    }

    public String getCnpj() {
        return cnpj;
    }
}
```


Mapper class
```java
public class FileMapper extends AbstractFileMapper<TestClass> {
       @Override
       public TestClass ToType(String fileLine) throws ReflectiveOperationException {
           return super.ToType(fileLine);
       }
   }
```

Main class 
```java
public class Main {

    public static void main(String[] args) throws ReflectiveOperationException {
        // write your code here
        String teste = "2023N12345678000134";

        FileMapper fileMapper = new FileMapper();

        System.out.println(fileMapper.ToType(teste).getValor() + "\n" + fileMapper.ToType(teste).getStatus() + "\n" + fileMapper.ToType(teste).getCnpj());
    }
}
```

Out
```text
20.23
N
12.345.678/0001-34
```

# Collaborators