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
 */
class Resources_ru : ListResourceBundle(), TimeFormatProvider {
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
            return "сейчас"
          }
          return if (duration.isInPast) {
            "только что"
          } else null
        }
      }
      is Century -> return TimeFormatAided("век", "века", "веков")
      is Day -> return TimeFormatAided("день", "дня", "дней")
      is Decade -> return TimeFormatAided("десятилетие", "десятилетия", "десятилетий")
      is Hour -> return TimeFormatAided("час", "часа", "часов")
      is Millennium -> return TimeFormatAided("тысячелетие", "тысячелетия", "тысячелетий")
      is Millisecond -> return TimeFormatAided("миллисекунду", "миллисекунды", "миллисекунд")
      is Minute -> return TimeFormatAided("минуту", "минуты", "минут")
      is Month -> return TimeFormatAided("месяц", "месяца", "месяцев")
      is Second -> return TimeFormatAided("секунду", "секунды", "секунд")
      is Week -> return TimeFormatAided("неделю", "недели", "недель")
      is Year -> return TimeFormatAided("год", "года", "лет")
      // error
      else -> return null
    }
  }

  private class TimeFormatAided(vararg plurals: String) : TimeFormat {
    private val plurals: Array<out String>

    init {
      if (plurals.size != russianPluralForms) {
        throw IllegalArgumentException("Wrong plural forms number for russian language!")
      }
      this.plurals = plurals
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
      if (pluralIdx > russianPluralForms) {
        // impossible happening
        throw IllegalStateException("Wrong plural index was calculated somehow for russian language")
      }

      val result = StringBuilder()

      if (future) {
        result.append("через ")
      }

      result.append(time)
      result.append(' ')
      result.append(plurals[pluralIdx])

      if (past) {
        result.append(" назад")
      }

      return result.toString()
    }
  }

  companion object {
    private val OBJECTS = emptyArray<Array<String>>()

    private val tolerance = 50

    // see http://translate.sourceforge.net/wiki/l10n/pluralforms
    private val russianPluralForms = 3
  }
}
