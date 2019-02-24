/*
 * Copyright 2012 <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ocpsoft.prettytime

import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import java.time.Duration.ofSeconds
import java.time.Instant
import java.util.*

/**
 * All the tests for PrettyTime.
 *
 * @author Thomas Weitzel <tweitzel></tweitzel>@synformation.com>
 */
class PrettyTimeI18n_Test {

  /*
    * A note when you want to use the YourKit profiler: To use the YourKit
    * profiler (http://yourkit.com), run with VM argument for profiling:
    * -agentlib:yjpagent=onexit=snapshot,tracing
    */


  // Method setUp() is called automatically before every test method
  @Before
  fun setUp() {
    Locale.setDefault(Locale.ROOT)
  }

  @Test
  fun testPrettyTimeDefault() {
    // The default resource bundle should be used
    val p = PrettyTime(Date(0), Locale.ROOT)
    assertEquals("moments from now", p.format(Date(1)))
  }

  @Test
  fun testPrettyTimeGerman() {
    // The German resource bundle should be used
    val p = PrettyTime(Locale.GERMAN)
    p.reference = Date(0).toInstant()
    assertEquals("Jetzt", p.format(Date(1)))
  }

  @Test
  fun testPrettyTimeSpanish() {
    // The Spanish resource bundle should be used
    val now = Instant.now()
    val p = PrettyTime(locale = Locale("es"), reference = now)
    assertEquals("en un instante", p.format(now.plusMillis(1000)))
  }

  @Test
  fun testPrettyTimeDefaultCenturies() {
    // The default resource bundle should be used
    val p = PrettyTime(Date(3155692597470L * 3L), Locale.ROOT)
    assertEquals("3 centuries ago", p.format(Date(0)))
  }

  @Test
  fun testPrettyTimeGermanCenturies() {
    // The default resource bundle should be used
    val p = PrettyTime(Date(3155692597470L * 3L), Locale.GERMAN)
    assertEquals(p.format(Date(0)), "vor 3 Jahrhunderten")
  }

  @Test
  fun testPrettyTimeViaDefaultLocaleDefault() {
    // The default resource bundle should be used
    Locale.setDefault(Locale.ROOT)
    val p = PrettyTime(Date(0))
    assertEquals(p.format(Date(1)), "moments from now")
  }

  @Test
  fun testPrettyTimeViaDefaultLocaleGerman() {
    // The German resource bundle should be used
    Locale.setDefault(Locale.GERMAN)
    val p = PrettyTime(Date(0))
    assertEquals(p.format(Date(1)), "Jetzt")
  }

  @Test
  fun testPrettyTimeViaDefaultLocaleDefaultCenturies() {
    // The default resource bundle should be used
    Locale.setDefault(Locale.ROOT)
    val p = PrettyTime(Date(3155692597470L * 3L))
    assertEquals(p.format(Date(0)), "3 centuries ago")
  }

  @Test
  fun testPrettyTimeViaDefaultLocaleGermanCenturies() {
    // The default resource bundle should be used
    Locale.setDefault(Locale.GERMAN)
    val p = PrettyTime(Date(3155692597470L * 3L))
    assertEquals(p.format(Date(0)), "vor 3 Jahrhunderten")
  }

  @Test
  fun testPrettyTimeRootLocale() {
    var t = 1L
    val p = PrettyTime(Date(0), Locale.ROOT)
    while (1000L * 60L * 60L * 24L * 365L * 1000000L > t) {
      assertEquals(p.format(Date(0)).endsWith("now"), true)
      t *= 2L
    }
  }

  @Test
  fun testPrettyTimeGermanLocale() {
    var t = 1L
    val p = PrettyTime(Date(0), Locale.GERMAN)
    while (1000L * 60L * 60L * 24L * 365L * 1000000L > t) {
      assertEquals(p.format(Date(0)).startsWith("in") || p.format(Date(0)).startsWith("Jetzt"), true)
      t *= 2L
    }
  }

  companion object {
    @BeforeClass @AfterClass fun resetLocale() {
      Locale.setDefault(Locale.ROOT)
    }
  }
}
