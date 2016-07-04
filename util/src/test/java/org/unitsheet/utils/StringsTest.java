package org.unitsheet.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class StringsTest {

    @Test
    public void checkIsNotEmptyIsFalseWhenNull() {
        assertThat(Strings.isNotEmpty(null)).isFalse();
    }

    @Test  public void checkIsNotEmptyIsFalseWhenEmptyString() {
        assertThat(Strings.isNotEmpty("")).isFalse();
    }

    @Test
    public void checkIsNotEmptyIsTrueWhenString() {
        assertThat(Strings.isNotEmpty("blah")).isTrue();
    }

    @Test
    public void checkIsEmptyIsTrueWhenNull() {
        assertThat(Strings.isEmpty(null)).isTrue();
    }

    @Test
    public void checkIsEmptyIsTrueWhenemprtString() {
        assertThat(Strings.isEmpty("")).isTrue();
    }

    @Test
    public void checkIsEmptyIsFalseWhenString() {
        assertThat(Strings.isEmpty("blah")).isFalse();
    }
}