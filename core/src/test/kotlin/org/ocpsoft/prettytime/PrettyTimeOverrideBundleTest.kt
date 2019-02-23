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
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import java.util.Date
import java.util.Locale

class PrettyTimeOverrideBundleTest {
  private var locale: Locale? = null

  @Before

  fun setUp() {
    locale = Locale.getDefault()
    Locale.setDefault(Locale.ROOT)
  }

  @Test

  fun testHoursAgo() {
    val t = PrettyTime(reference = Date((1000 * 60 * 60 * 3).toLong()).toInstant(),
        locale = Locale.ENGLISH,
        overrideResourceBundle = "org.ocpsoft.prettytime.i18n.override.Resources")
    Assert.assertEquals("3 hours ago override", t.format(Date(0)))
  }

  @After

  fun tearDown() {
    Locale.setDefault(locale!!)
  }
}
