# ZipFiles

## Visão geral
Biblioteca utilizada para compactar e descompactar arquivos independentes ou um conjunto de arquivos.

## Setup

#### Gradle

`implementation 'com.github.dercilima:zipfiles:0.0.1'`

#### Maven

```
<dependency>
    <groupId>com.github.dercilima</groupId>
    <artifactId>zipfiles</artifactId>
    <version>0.0.1</version>
</dependency>
```

## Como usar

### Compactar

```java
try {
    // Arquivo para zipar
    final File fileToZip = new File("your_path", "your_file.txt");

    // Diretório de saída
    final File outputDirectory = new File("your_source_path", "YourFileZip.zip");

    // Zip
    new ZipFiles(Collections.singletonList(fileToZip), outputDirectory).zip();

} catch (IOException e) {
    e.printStackTrace();
}
```

### Descompactar

```java
try {
    // Arquivo zipado
    final File zipFile = new File("your_path", "YourFileZip.zip");

    // Diretório de saída
    final File outputDir = new File("your_source_path", "extracted_directory");

    // Unzip
    new ZipFiles(zipFile, outputDir).unzip();

} catch (IOException e) {
    e.printStackTrace();
}
```

## Developed By

* Derci Santos
 
&nbsp;&nbsp;&nbsp;**Email** - dercilima.si@gmail.com

## License

```
Copyright 2018 Derci Santos

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
