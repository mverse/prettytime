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

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * All the tests for PrettyTime.
 *
 * @author Thomas Weitzel <tweitzel></tweitzel>@synformation.com>
 */
class PrettyTimeI18n_FR_Test {

  /*
     * A note when you want to use the YourKit profiler: To use the YourKit
     * profiler (http://yourkit.com), run with VM argument for profiling:
     * -agentlib:yjpagent=onexit=snapshot,tracing
     */

  // Stores current locale so that it can be restored
  private var locale: Locale? = null

  // Method setUp() is called automatically before every test method
  @Before

  fun setUp() {
    locale = Locale.getDefault()
  }

  @Test
  fun testPrettyTimeFRENCH() {
    // The FRENCH resource bundle should be used
    val p = PrettyTime(Locale.FRENCH)
    assertEquals("à l'instant", p.format(Date()))
  }

  @Test
  fun testPrettyTimeFRENCHCenturies() {
    val p = PrettyTime(Date(3155692597470L * 3L), Locale.FRENCH)
    assertEquals(p.format(Date(0)), "il y a 3 siècles")
  }

  @Test
  fun testPrettyTimeViaDefaultLocaleFRENCH() {
    // The FRENCH resource bundle should be used
    Locale.setDefault(Locale.FRENCH)
    val p = PrettyTime()
    assertEquals(p.format(Date()), "à l'instant")
  }

  @Test
  fun testPrettyTimeFRENCHLocale() {
    var t = 1L
    val p = PrettyTime(Date(0), Locale.FRENCH)
    while (1000L * 60L * 60L * 24L * 365L * 1000000L > t) {
      assertTrue(p.format(Date(0)).startsWith("dans") || p.format(Date(0)).startsWith("à l'instant"))
      t *= 2L
    }
  }

  // Method tearDown() is called automatically after every test method
  @After

  fun tearDown() {
    Locale.setDefault(locale!!)
  }
}
