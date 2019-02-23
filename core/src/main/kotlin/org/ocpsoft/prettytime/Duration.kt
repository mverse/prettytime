package org.ocpsoft.prettytime

/**
 * Represents a quantity of any given [TimeUnit]
 *
 * @author [Lincoln Baxter, III](mailto:lincolnbaxter@gmail.com)
 */
interface Duration {
  /**
   * Return the calculated quantity of [TimeUnit] instances.
   */
  val quantity: Long

  /**
   * Return the [TimeUnit] represented by this [Duration]
   */
  val unit: TimeUnit

  /**
   * Return the number of milliseconds left over when calculating the number of [TimeUnit]'s that fit into the
   * given time range.
   */
  val delta: Long

  /**
   * Return true if this [Duration] represents a number of [TimeUnit] instances in the past.
   */
  val isInPast: Boolean

  /**
   * Return true if this [Duration] represents a number of [TimeUnit] instances in the future.
   */
  val isInFuture: Boolean

  /**
   * Return the calculated quantity of [TimeUnit] instances, rounded up if [.getDelta] is greater than or
   * equal to the given tolerance.
   */
  fun getQuantityRounded(tolerance: Int): Long
}
