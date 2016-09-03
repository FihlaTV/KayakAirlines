package com.oguzbabaoglu.kayakairlines.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ListUtilTest {

  @Test public void testTransform() {
    List<Integer> intList = Arrays.asList(1, 2, 3);
    List<String> result = ListUtil.transform(intList, Object::toString);

    assertThat(result).containsExactly("1", "2", "3");
  }

  @Test public void testSort() {
    List<Integer> intList = Arrays.asList(3, 1, 2);
    List<Integer> result = ListUtil.sort(intList);

    assertThat(result).containsExactly(1, 2, 3);
  }
}