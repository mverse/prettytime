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

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.ocpsoft.prettytime.units.JustNow
import java.time.Instant

class PrettyTimeAPIManipulationTest {
  internal var t = PrettyTime()

  @Test
  fun testApiMisuse3() {
    t.clearUnits()
  }

  @Test
  fun testApiMisuse10() {
    Assert.assertNotNull(t.locale)
  }



  @Test
  fun testApiMisuse15() {
    t.toString()
  }

  @Test
  fun testUnits() {
    val unit = t.getUnit(JustNow::class.java)
    assertNotNull(unit)
  }

  @Test
  fun testChangeUnit() {
    val unit = t.getUnit(JustNow::class.java)
    assertEquals(1000L * 60L, unit!!.maxQuantity)
    unit.maxQuantity = 1
    assertEquals(1, t.getUnit(JustNow::class.java)!!.maxQuantity)
  }
}
