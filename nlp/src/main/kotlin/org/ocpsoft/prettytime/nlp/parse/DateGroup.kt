package org.ocpsoft.prettytime.nlp.parse

import java.util.Date

/**
 * Represents a [Date] instanced parsed out of natural language text.
 *
 * @author [](mailto:lincolnbaxter@gmail.com>Lincoln Baxter, III</a>
) */
interface DateGroup {
  /**
   * Get the line in which this [DateGroup] was found.
   */
  val line: Int

  /**
   * Get the text fragment parsed into this [DateGroup].
   */
  val text: String

  /**
   * Get the [Date] to which this [DateGroup] recurs.
   */
  val recursUntil: Date

  /**
   * Get the starting position of this [DateGroup] in the language text.
   */
  val position: Int

  /**
   * Get all [Date] instances parsed from the language text.
   */
  val dates: List<Date>

  /**
   * Return `true` if this [DateGroup] is a recurring event.
   */
  val isRecurring: Boolean

  /**
   * If this [DateGroup] is recurring, return the interval in milliseconds with which this [DateGroup]
   * recurs, otherwise return -1;
   */
  val recurInterval: Long
}
