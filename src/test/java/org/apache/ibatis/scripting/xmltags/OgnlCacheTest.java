package org.apache.ibatis.scripting.xmltags;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

class OgnlCacheTest {
  @Test
  void concurrentAccess() throws Exception {
    class DataClass {
      @SuppressWarnings("unused")
      private int id;
    }
    int run = 1000;
    Map<String, Object> context = new HashMap<>();
    List<Future<Object>> futures = new ArrayList<>();
    context.put("data", new DataClass());
    ExecutorService executor = Executors.newCachedThreadPool();
    IntStream.range(0, run).forEach(i -> {
      futures.add(executor.submit(() -> {
        return OgnlCache.getValue("data.id", context);
      }));
    });
    for (int i = 0; i < run; i++) {
      assertNotNull(futures.get(i).get());
    }
    executor.shutdown();
  }

  @Test
  void issue2609() throws Exception {
    Map<String, Object> context = new HashMap<>();
    context.put("d1", java.sql.Date.valueOf("2022-01-01"));
    context.put("d2", java.sql.Date.valueOf("2022-01-02"));
    assertEquals(-1, OgnlCache.getValue("d1.compareTo(d2)", context));
  }
}
