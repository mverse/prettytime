@file:Suppress("NAME_SHADOWING")

package org.ocpsoft.prettytime.nlp

import com.joestelmach.natty.Parser
import org.ocpsoft.prettytime.nlp.parse.DateGroup
import java.util.*
import com.joestelmach.natty.DateGroup as NattyDateGroup

/**
 * A utility for parsing natural language date and time expressions. (e.g. "Let's get lunch at two pm",
 * "I did it 3 days ago")
 *
 *
 * **Usage:**
 *
 *
 * `
 * PrettyTimeParser p = new PrettyTimeParser();<br></br>
 * List<Date> parsed = p.parse("I'll be there at two");<br></br>
 * //result: Date - 2:00PM
` *
 *
 *
 *
 * @author [](mailto:lincolnbaxter@gmail.com>Lincoln Baxter, III</a>
) */
class PrettyTimeParser(timezone: TimeZone) {
  private val parser:Parser = Parser(timezone)
  private val translations = HashMap<String, String>()
  private val periods = HashSet<String>()
  private val tensNames = arrayOf("", " ten", " twenty", " thirty", " forty", " fifty",
      " sixty", " seventy", " eighty", " ninety")
  private val numNames = arrayOf("", " one", " two", " three", " four", " five",
      " six", " seven", " eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen",
      " fifteen", " sixteen", " seventeen", " eighteen", " nineteen")

  /**
   * Create a new [PrettyTimeParser] with the current system default [TimeZone].
   */
  constructor() : this(TimeZone.getDefault()) {
    for (hours in 0..23) {
      for (min in 0..59) {
        translations[provideRepresentation(hours * 100 + min)] = "" + hours * 100 + min
      }
    }

    translations[provideRepresentation(60)] = "" + 60
    translations[provideRepresentation(70)] = "" + 70
    translations[provideRepresentation(80)] = "" + 80
    translations[provideRepresentation(90)] = "" + 90
    translations[provideRepresentation(100)] = "" + 100

    periods.add("morning")
    periods.add("afternoon")
    periods.add("evening")
    periods.add("night")
    periods.add("am")
    periods.add("pm")
    periods.add("ago")
    periods.add("from now")
  }

  /**
   * Provides a string representation for the number passed. This method works for limited set of numbers as parsing
   * will only be done at maximum for 2400, which will be used in military time format.
   */
  private fun provideRepresentation(number: Int): String {
    var key: String
    when {
      number == 0 -> key = "zero"
      number < 20 -> key = numNames[number]
      number < 100 -> {
        val unit = number % 10
        key = tensNames[number / 10] + numNames[unit]
      }
      else -> {
        val unit = number % 10
        val ten = number % 100 - unit
        val hundred = (number - ten) / 100
        key = when {
          hundred < 20 -> numNames[hundred] + " hundred"
          else -> tensNames[hundred / 10] + numNames[hundred % 10] + " hundred"
        }
        key += when {
          ten + unit in 11..19 -> numNames[ten + unit]
          else -> tensNames[ten / 10] + numNames[unit]
        }
      }
    }
    return key.trim { it <= ' ' }
  }

  /**
   * Parse the given language and return a [List] with all discovered [Date] instances.
   */
  fun parse(language: String): List<Date> {
    val language = words2numbers(language)
    val groups = parser.parse(language)
    return groups.flatMap { group ->
      group.dates
    }
  }

  /**
   * Parse the given language and return a [List] with all discovered [DateGroup] instances.
   */
  fun parseSyntax(language: String): List<DateGroup> {
    val language = words2numbers(language)
    val groups = parser.parse(language)
    val now = Date()
    return groups.map { group ->
      DateGroupImpl(now, group)
    }
  }

  private fun words2numbers(language: String): String {
    var language = language
    for ((key, value) in translations) {
      language = language.replace("\\b$key\\b".toRegex(), value)
    }
    return language
  }

  private inner class DateGroupImpl(private val now: Date, group: NattyDateGroup) : DateGroup {
    override val dates: List<Date> = group.dates
    override val line: Int = group.line
    override val position: Int = group.position
    override val recursUntil: Date = group.recursUntil
    override val text: String = group.text
    override val isRecurring: Boolean = group.isRecurring

    override val recurInterval: Long
      get() {
        return when {
          isRecurring -> dates[0].time - now.time
          else -> -1
        }
      }
  }
}
