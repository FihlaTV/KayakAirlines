package com.oguzbabaoglu.kayakairlines.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ListUtilTest {

  @Test public void testTransform() {
    List<Integer> intList = Arrays.asList(1, 2, 3);
    List<String> result = ListUtil.transform(intList, Object::toString);

    assertThat(result).containsExactly("1", "2", "3");
    assertThat(ListUtil.transform(null, null)).isEqualTo(Collections.emptyList());
  }

  @Test public void testSort() {
    List<Integer> intList = Arrays.asList(3, 1, 2);
    List<Integer> result = ListUtil.sort(intList);

    assertThat(result).containsExactly(1, 2, 3);
  }

  @Test public void testFilter() {
    List<Integer> intList = Arrays.asList(1, 2, 3);
    List<Integer> result = ListUtil.filter(intList, integer -> integer % 2 == 1);

    assertThat(result).containsExactly(1, 3);
    assertThat(ListUtil.filter(null, null)).isEqualTo(Collections.emptyList());
  }

  @Test public void testAsArrayList() {
    List<Integer> otherList = Arrays.asList(1, 2, 3);
    List<Integer> arrayList = new ArrayList<>(otherList);

    assertThat(ListUtil.asArrayList(null)).isNull();

    assertThat(ListUtil.asArrayList(otherList)).isInstanceOf(ArrayList.class);
    assertThat(ListUtil.asArrayList(otherList)).containsExactly(1, 2, 3);

    assertThat(ListUtil.asArrayList(arrayList)).isEqualTo(arrayList);
  }
}