package org.unitsheet.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.IterableUtil.toArray;

@RunWith(MockitoJUnitRunner.class)
public class CollectionsTest {

    @Test
    public void checkSetOf() {
        Set<Integer> set = Collections.setOf(1, 2, 3);
        assertThat(set).isEqualTo(newHashSet(1, 2, 3));
    }

    @Test
    public void checkListOf() {
        List<Integer> list = Collections.listOf(1, 2, 3);
        assertThat(list).isEqualTo(newArrayList(1, 2, 3));
    }
}