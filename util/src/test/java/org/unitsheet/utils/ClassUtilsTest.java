package org.unitsheet.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.unitsheet.utils.test.HasDefaultNoArgConstructor;
import org.unitsheet.utils.test.HasExplicitNoArgConstructor;
import org.unitsheet.utils.test.HasPrivateNoArgConstructor;
import org.unitsheet.utils.test.MissingNoArgConstructor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.unitsheet.utils.ClassUtils.*;

@RunWith(MockitoJUnitRunner.class)
public class ClassUtilsTest {

    @Test
    public void checkHasNoArgumentConstructorIsTrueWhenClassHasDefaultNoArgConstructor() {
        assertThat(hasPublicNoArgumentConstructor(HasDefaultNoArgConstructor.class)).isTrue();
    }

    @Test
    public void checkHasNoArgumentConstructorIsTrueWhenClassHasExplicitNoArgConstructor() {
        assertThat(hasPublicNoArgumentConstructor(HasExplicitNoArgConstructor.class)).isTrue();
    }

    @Test
    public void checkHasNoArgumentConstructorIsFalseWhenClassHasNoArgConstructor() {
        assertThat(hasPublicNoArgumentConstructor(MissingNoArgConstructor.class)).isFalse();
    }

    @Test
    public void checkHasNoArgumentConstructorIsFalseWhenClassHasPrivateNoArgConstructor() {
        assertThat(hasPublicNoArgumentConstructor(HasPrivateNoArgConstructor.class)).isFalse();
    }

    @Test
    public void checkListFieldIsFoundToBeCollection() throws NoSuchFieldException {
        boolean isCollection = isCollection(List.class);

        assertThat(isCollection).isTrue();
    }

    @Test
    public void checkCompnentTypeNameIsStringForStringArray() {
        Class<? extends String[]> aClass = new String[]{}.getClass();
        String  typeName = getComponentTypeName(aClass);

        assertThat(typeName).isEqualTo("java.lang.String");
    }

}