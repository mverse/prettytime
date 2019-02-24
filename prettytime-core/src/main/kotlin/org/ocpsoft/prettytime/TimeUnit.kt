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

import org.ocpsoft.prettytime.units.Hour
import org.ocpsoft.prettytime.units.Second

/**
 * Defines a Unit of time (e.g. seconds, minutes, hours) and its conversion to milliseconds.
 *
 * @author [Lincoln Baxter, III](mailto:lincolnbaxter@gmail.com)
 */
interface TimeUnit {

  /**
   * The number of milliseconds represented by each instance of this TimeUnit. Must be a positive number greater than
   * zero.
   */
  val millisPerUnit: Long

  /**
   * The maximum quantity of this Unit to be used as a threshold for the next largest Unit (e.g. if one
   * `Second` represents 1000ms, and `Second` has a maxQuantity of 5, then if the difference
   * between compared timestamps is larger than 5000ms, PrettyTime will move on to the next smallest TimeUnit for
   * calculation; `Minute`, by default)
   *
   *
   * millisPerUnit * maxQuantity = maxAllowedMs
   *
   *
   * If maxQuantity is zero, it will be equal to the next highest `TimeUnit.getMillisPerUnit() /
   * this.getMillisPerUnit()` or infinity if there are no greater TimeUnits
   */
  val maxQuantity: Long

  /**
   * Whether or not this [TimeUnit] represents a price measurement of time, or a general concept of time. E.g:
   * "minute" as opposed to "moment".
   */
  val isPrecise: Boolean
}
