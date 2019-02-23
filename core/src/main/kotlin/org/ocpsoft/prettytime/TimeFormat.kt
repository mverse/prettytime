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

/**
 * Format a Duration object.
 *
 * @author [Lincoln Baxter, III](mailto:lincolnbaxter@gmail.com)
 */
interface TimeFormat {
  /**
   * Given a populated [Duration] object. Apply formatting (with rounding) and output the result.
   *
   * @param The original [Duration] instance from which the time string should be decorated.
   */
  fun format(duration: Duration): String

  /**
   * Given a populated [Duration] object. Apply formatting (without rounding) and output the result.
   *
   * @param The original [Duration] instance from which the time string should be decorated.
   */
  fun formatUnrounded(duration: Duration): String

  /**
   * Decorate with past or future prefix/suffix (with rounding)
   *
   * @param duration The original [Duration] instance from which the time string should be decorated.
   * @param time The formatted time string.
   */
  fun decorate(duration: Duration, time: String): String

  /**
   * Decorate with past or future prefix/suffix (without rounding)
   *
   * @param duration The original [Duration] instance from which the time string should be decorated.
   * @param time The formatted time string.
   */
  fun decorateUnrounded(duration: Duration, time: String): String
}
