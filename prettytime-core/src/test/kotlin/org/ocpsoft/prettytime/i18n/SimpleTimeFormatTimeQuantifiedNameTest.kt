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
package org.ocpsoft.prettytime.i18n

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.ocpsoft.prettytime.PrettyTime
import java.util.*

class SimpleTimeFormatTimeQuantifiedNameTest {
  private lateinit var locale: Locale

  @Before
  fun setUp() {
    locale = Locale.getDefault()
    Locale.setDefault(Locale("yy"))
  }

  @Test fun testFuturePluralName() {
    val p = PrettyTime(reference = Date(0).toInstant())
    Assert.assertEquals("2 futuredays from now", p.format(Date((1000 * 60 * 60 * 24 * 2).toLong()).toInstant()))
  }

  @Test
  fun testPastPluralName() {
    val p = PrettyTime(reference = Date((1000 * 60 * 60 * 24 * 2).toLong()).toInstant())
    Assert.assertEquals("2 pastdays ago", p.format(Date(0).toInstant()))
  }

  @Test fun testFutureSingularName() {
    val p = PrettyTime(reference = Date(0).toInstant())
    Assert.assertEquals("1 futureday from now", p.format(then = Date((1000 * 60 * 60 * 24).toLong()).toInstant()))
  }

  @Test fun testPastSingularName() {
    val p = PrettyTime(reference = Date((1000 * 60 * 60 * 24).toLong()).toInstant())
    Assert.assertEquals("1 pastday ago", p.format(Date(0).toInstant()))
  }

  @Test fun testFuturePluralNameEmpty() {
    val p = PrettyTime(Date(0).toInstant())
    Assert.assertEquals("2 from now", p.format(Date((1000 * 60 * 60 * 2).toLong()).toInstant()))
  }

  @Test fun testPastPluralNameMissing() {
    val p = PrettyTime(Date((1000 * 60 * 60 * 2).toLong()).toInstant())
    Assert.assertEquals("2 hours ago", p.format(Date(0).toInstant()))
  }

  @Test fun testFutureSingularNameCopy() {
    val p = PrettyTime(Date(0).toInstant())
    Assert.assertEquals("1 hour from now", p.format(Date((1000 * 60 * 60).toLong()).toInstant()))
  }

  @Test fun testPastSingularNameNull() {
    val p = PrettyTime(Date((1000 * 60 * 60).toLong()).toInstant())
    Assert.assertEquals("1 hour ago", p.format(Date(0).toInstant()))
  }

  @After fun tearDown() {
    Locale.setDefault(locale)
  }
}
