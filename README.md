# Lycorse-DPL
Lycorse Document Processing Library - Java Library for Microsoft Office and LibreOffice documents processing.

The project is still in developing. 
# Getting Started
## Basic replacement
To replace word in a document you can use the `DocumentManager` class:
```Java
File file = new File("Document.odt");
DocumentManager documentManager = DocumentManagerProvider.createDocumentManager(file);
Document document = documentManager.openDocument(file);
document.replace("{Search}", "Value");
document.saveDocumentAs(
        new File("C:/Document.docx"),
        DocumentConvertTypes.MS_WORD_2007_XML);
```

## Advanced functionality
In order to use more functionality you can use the `LibreOfficeUnoManager`.

### Replacement
```Java
File document = new File("Document.odt");
LibreOfficeUnoManager libreOfficeUnoManager = new LibreOfficeUnoManager();
libreOfficeUnoManager.openDocument(document);
libreOfficeUnoManager.replaceAll("${Search}", "Value");

```