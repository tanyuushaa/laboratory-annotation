package com.example.processors;

import com.example.annotation.GenerateClass;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@SupportedAnnotationTypes("com.example.annotations.GenerateClass")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class AnnotationProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(GenerateClass.class)) {
            GenerateClass generateClass = element.getAnnotation(GenerateClass.class);
            String className = generateClass.className();

            try (PrintWriter writer = (PrintWriter) processingEnv.getFiler().createSourceFile("com.example." + className).openWriter()) {
                writer.println("package com.example;");
                writer.println("public class " + className + " {");
                writer.println("    public void generatedMethod() {");
                writer.println("        System.out.println(\"This is a generated class.\");");
                writer.println("    }");
                writer.println("}");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
