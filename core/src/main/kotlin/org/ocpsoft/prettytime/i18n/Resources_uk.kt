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

import org.ocpsoft.prettytime.Duration
import org.ocpsoft.prettytime.TimeFormat
import org.ocpsoft.prettytime.TimeUnit
import org.ocpsoft.prettytime.impl.TimeFormatProvider
import org.ocpsoft.prettytime.units.Century
import org.ocpsoft.prettytime.units.Day
import org.ocpsoft.prettytime.units.Decade
import org.ocpsoft.prettytime.units.Hour
import org.ocpsoft.prettytime.units.JustNow
import org.ocpsoft.prettytime.units.Millennium
import org.ocpsoft.prettytime.units.Millisecond
import org.ocpsoft.prettytime.units.Minute
import org.ocpsoft.prettytime.units.Month
import org.ocpsoft.prettytime.units.Second
import org.ocpsoft.prettytime.units.Week
import org.ocpsoft.prettytime.units.Year
import java.util.*

/**
 * Created with IntelliJ IDEA. User: Tumin Alexander Date: 2012-12-13 Time: 03:33
 *
 * reedit to Ukrainian with Eclipse). User: Ihor Lavrynuk Date: 2013-01-06 Time: 15:04
 */
class Resources_uk : ListResourceBundle(), TimeFormatProvider {
  override fun getContents() = OBJECTS

  override fun getFormatFor(t: TimeUnit): TimeFormat? {
    when (t) {
      is JustNow -> return object : TimeFormat {
        override fun format(duration: Duration): String {
          return performFormat(duration) ?: ""
        }

        override fun formatUnrounded(duration: Duration): String {
          return performFormat(duration) ?: ""
        }

        override fun decorate(duration: Duration, time: String): String {
          return time
        }

        override fun decorateUnrounded(duration: Duration, time: String): String {
          return time
        }

        private fun performFormat(duration: Duration): String? {
          if (duration.isInFuture) {
            return "зараз"
          }
          return when {
            duration.isInPast -> "щойно"
            else -> null
          }
        }
      }
      is Century -> return TimeFormatAided("століття", "століття", "столітть")
      is Day -> return TimeFormatAided("день", "дні", "днів")
      is Decade -> return TimeFormatAided("десятиліття", "десятиліття", "десятиліть")
      is Hour -> return TimeFormatAided("годину", "години", "годин")
      is Millennium -> return TimeFormatAided("тисячоліття", "тисячоліття", "тисячоліть")
      is Millisecond -> return TimeFormatAided("мілісекунду", "мілісекунди", "мілісекунд")
      is Minute -> return TimeFormatAided("хвилину", "хвилини", "хвилин")
      is Month -> return TimeFormatAided("місяць", "місяці", "місяців")
      is Second -> return TimeFormatAided("секунду", "секунди", "секунд")
      is Week -> return TimeFormatAided("тиждень", "тижні", "тижнів")
      is Year -> return TimeFormatAided("рік", "роки", "років")
      // error
      else -> return null
    }
  }

  private class TimeFormatAided(vararg plurals: String) : TimeFormat {
    private val pluarls: Array<out String>

    init {
      if (plurals.size != slavicPluralForms) {
        throw IllegalArgumentException("Wrong plural forms number for slavic language!")
      }
      this.pluarls = plurals
    }

    override fun format(duration: Duration): String {
      val quantity = duration.getQuantityRounded(tolerance)
      val result = StringBuilder()
      result.append(quantity)
      return result.toString()
    }

    override fun formatUnrounded(duration: Duration): String {
      val quantity = duration.quantity
      val result = StringBuilder()
      result.append(quantity)
      return result.toString()
    }

    override fun decorate(duration: Duration, time: String): String {
      return performDecoration(
          duration.isInPast,
          duration.isInFuture,
          duration.getQuantityRounded(tolerance),
          time)
    }

    override fun decorateUnrounded(duration: Duration, time: String): String {
      return performDecoration(
          duration.isInPast,
          duration.isInFuture,
          duration.quantity,
          time)
    }

    private fun performDecoration(past: Boolean, future: Boolean, n: Long, time: String): String {
      // a bit cryptic, yet well-tested
      // consider http://translate.sourceforge.net/wiki/l10n/pluralforms
      val pluralIdx = if (n % 10 == 1L && n % 100 != 11L)
        0
      else if (n % 10 >= 2 && n % 10 <= 4
          && (n % 100 < 10 || n % 100 >= 20))
        1
      else
        2
      if (pluralIdx > slavicPluralForms) {
        // impossible happening
        throw IllegalStateException("Wrong plural index was calculated somehow for slavic language")
      }

      val result = StringBuilder()

      if (future) {
        result.append("через ")
      }

      result.append(time)
      result.append(' ')
      result.append(pluarls[pluralIdx])

      if (past) {
        result.append(" тому")
      }

      return result.toString()
    }
  }

  companion object {
    private val OBJECTS = emptyArray<Array<*>>()

    private val tolerance = 50

    // see http://translate.sourceforge.net/wiki/l10n/pluralforms
    private val slavicPluralForms = 3
  }
}
