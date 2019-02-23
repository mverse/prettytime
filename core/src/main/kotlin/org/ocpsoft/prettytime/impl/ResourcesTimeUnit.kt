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
package org.ocpsoft.prettytime.impl

import org.ocpsoft.prettytime.TimeUnit

/**
 * @author [Lincoln Baxter, III](mailto:lincolnbaxter@gmail.com)
 */
abstract class ResourcesTimeUnit : TimeUnit {
  override var maxQuantity: Long = 0
  override var millisPerUnit: Long = 1

  /**
   * Return the name of the resource bundle from which this unit's format should be loaded.
   */
  abstract val resourceKeyPrefix: String

  val resourceBundleName = "org.ocpsoft.prettytime.i18n.Resources"
  override val isPrecise = true
  override fun toString(): String = resourceKeyPrefix

  override fun hashCode(): Int {
    val prime = 31
    var result = 1
    result = prime * result + (maxQuantity xor maxQuantity.ushr(32)).toInt()
    result = prime * result + (millisPerUnit xor millisPerUnit.ushr(32)).toInt()
    return result
  }

  override fun equals(other: Any?): Boolean {
    if (this === other)
      return true
    if (other == null)
      return false
    if (javaClass != other.javaClass)
      return false
    val other = other as ResourcesTimeUnit?
    if (maxQuantity != other!!.maxQuantity)
      return false
    return millisPerUnit == other.millisPerUnit
  }
}
