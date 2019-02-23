package org.ocpsoft.prettytime.i18n

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


import java.util.ListResourceBundle

import org.ocpsoft.prettytime.Duration
import org.ocpsoft.prettytime.TimeFormat
import org.ocpsoft.prettytime.TimeUnit
import org.ocpsoft.prettytime.impl.TimeFormatProvider
import org.ocpsoft.prettytime.units.Minute

class Resources_xx : ListResourceBundle(), TimeFormatProvider {

  public override fun getContents() = OBJECTS

  override fun getFormatFor(t: TimeUnit): TimeFormat? {
    return if (t is Minute) {
      object : TimeFormat {

        override fun decorate(duration: Duration, time: String): String {
          var result = if (duration.getQuantityRounded(50) > 1) time + "i" else "o"
          result += if (duration.isInPast) " ago" else " from now"
          return result
        }

        override fun decorateUnrounded(duration: Duration, time: String): String {
          var result = if (duration.quantity > 1) time + "i" else "o"
          result += if (duration.isInPast) " ago" else " from now"
          return result
        }

        override fun format(duration: Duration): String {
          return duration.getQuantityRounded(50).toString() + " minut"
        }

        override fun formatUnrounded(duration: Duration): String {
          return duration.quantity.toString() + " minut"
        }
      }
    } else null
  }

  companion object {
    private val OBJECTS = arrayOf<Array<Any>>()
  }
}
