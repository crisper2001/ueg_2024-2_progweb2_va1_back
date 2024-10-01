/*

ESTA É A CLASSE QUE UTILIZA REFLEXÃO E A ANOTAÇÃO PERSONALIZADA!

 */

package br.ueg.aluno.backend.mappers;

import br.ueg.aluno.backend.annotations.Get;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class GenericMapper {
    public <S, D> D map(S source, Class<D> destinationClass) {
        try {
            D destination = destinationClass.getDeclaredConstructor().newInstance();
            Method[] sourceMethods = source.getClass().getMethods();

            for (Method sourceMethod : sourceMethods) {
                if (isGetter(sourceMethod)) {
                    String propertyName = getPropertyNameFromGetter(sourceMethod);
                    try {
                        Method setter = destinationClass.getMethod("set" + propertyName, sourceMethod.getReturnType());
                        setter.invoke(destination, sourceMethod.invoke(source));
                    } catch (NoSuchMethodException ignored) {
                    }
                }
            }
            return destination;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao mapear objetos", e);
        }
    }

    private boolean isGetter(Method method) {
        return method.isAnnotationPresent(Get.class) && !method.getName().equals("getClass");
    }

    private String getPropertyNameFromGetter(Method getter) {
        return getter.getName().substring(3);
    }
}
