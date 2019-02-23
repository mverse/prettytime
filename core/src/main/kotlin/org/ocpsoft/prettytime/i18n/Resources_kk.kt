package org.ocpsoft.prettytime.i18n

import java.util.ListResourceBundle
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

/**
 * Created by Azimkhan Yerzhan on 5/8/2017
 */
class Resources_kk : ListResourceBundle(), TimeFormatProvider {

  override fun getFormatFor(t: TimeUnit): TimeFormat? {
    if (t is JustNow) {
      return object : TimeFormat {
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
            return "дәл қазір"
          }
          return if (duration.isInPast) {
            "жана ғана"
          } else null
        }
      }
    } else if (t is Century) {
      return KkTimeFormat("ғасыр", "ғасырдан")
    } else if (t is Day) {
      return KkTimeFormat("күн", "күннен")
    } else if (t is Decade) {
      return KkTimeFormat("онжылдық", "онжылдықтан")
    } else if (t is Hour) {
      return KkTimeFormat("сағат", "сағаттан")
    } else if (t is Millennium) {
      return KkTimeFormat("мыңжылдық", "мыңжылдықтан")
    } else if (t is Millisecond) {
      return KkTimeFormat("миллисекунд", "миллисекундтан")
    } else if (t is Minute) {
      return KkTimeFormat("минут", "минуттан")
    } else if (t is Month) {
      return KkTimeFormat("ай", "айдан")
    } else if (t is Second) {
      return KkTimeFormat("секунд", "секундтан")
    } else if (t is Week) {
      return KkTimeFormat("апта", "аптадан")
    } else if (t is Year) {
      return KkTimeFormat("жыл", "жылдан")
    }
    return null
  }

  private class KkTimeFormat(vararg plurals: String) : TimeFormat {
    private val tolerance = 50
    private val forms: Array<out String>

    init {
      if (plurals.size != 2) {
        throw IllegalArgumentException("Future and past forms must be provided for kazakh language!")
      }
      this.forms = plurals
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
      val builder = StringBuilder()
      val formIndex = if (past) 0 else 1

      builder.append(time)
      builder.append(' ')
      builder.append(forms[formIndex])
      builder.append(' ')

      if (past) {
        builder.append("бұрын")
      }

      if (future) {
        builder.append("кейін")
      }
      return builder.toString()
    }
  }
  override fun getContents() = OBJECTS

  companion object {
    private val OBJECTS = emptyArray<Array<String>>()
  }
}
