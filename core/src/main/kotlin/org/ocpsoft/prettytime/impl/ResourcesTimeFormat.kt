package org.ocpsoft.prettytime.impl

import org.ocpsoft.prettytime.Duration
import org.ocpsoft.prettytime.LocaleAware
import org.ocpsoft.prettytime.TimeFormat
import org.ocpsoft.prettytime.format.SimpleTimeFormat
import java.util.*

/**
 * Represents a simple method of formatting a specific [Duration] of time
 *
 * @author lb3
 */
data class ResourcesTimeFormat(
    private var bundle: ResourceBundle? = null,
    private val unit: ResourcesTimeUnit,
    private var override: TimeFormat? = null,
    private var internalLocale: Locale? = null,
    /* If used this bundle will override the included bundle */
    private val overrideResourceBundle: String? = null)
  : SimpleTimeFormat(), TimeFormat, LocaleAware<ResourcesTimeFormat> {

  override var locale: Locale
    set(value) {
      internalLocale = value
      bundle = null
      if (overrideResourceBundle != null) {
        try {
          // Attempt to load the bundle that the user passed in, maybe it exists, maybe not
          bundle = ResourceBundle.getBundle(overrideResourceBundle, value)
        } catch (e: Exception) {
          // Throw away if the bundle doesn't contain this local
        }
      }

      // If the bundle doesn't exist then load the default included one
      if (bundle == null) {
        bundle = ResourceBundle.getBundle(unit.resourceBundleName, value)
      }

      if (bundle is TimeFormatProvider) {
        val format = (bundle as TimeFormatProvider).getFormatFor(unit)
        if (format != null) {
          this.override = format
        }
      } else {
        override = null
      }

      if (override == null) {
        pattern = bundle!!.getString("${unit.resourceKeyPrefix}Pattern")
        futurePrefix = bundle!!.getString("${unit.resourceKeyPrefix}FuturePrefix")
        futureSuffix = bundle!!.getString("${unit.resourceKeyPrefix}FutureSuffix")
        pastPrefix = bundle!!.getString("${unit.resourceKeyPrefix}PastPrefix")
        pastSuffix = bundle!!.getString("${unit.resourceKeyPrefix}PastSuffix")
        singularName = bundle!!.getString("${unit.resourceKeyPrefix}SingularName")
        pluralName = bundle!!.getString("${unit.resourceKeyPrefix}PluralName")

        try {
          futurePluralName = bundle!!.getString("${unit.resourceKeyPrefix}FuturePluralName")
        } catch (e: Exception) {
        }

        try {
          futureSingularName = bundle!!.getString("${unit.resourceKeyPrefix}FutureSingularName")
        } catch (e: Exception) {
        }

        try {
          pastPluralName = bundle!!.getString("${unit.resourceKeyPrefix}PastPluralName")
        } catch (e: Exception) {
        }

        try {
          pastSingularName = bundle!!.getString("${unit.resourceKeyPrefix}PastSingularName")
        } catch (e: Exception) {
        }
      }
    }
    get() = internalLocale!!

  override fun decorate(duration: Duration, time: String): String {
    return when (val override = this.override) {
      null -> super.decorate(duration, time)
      else -> override.decorate(duration, time)
    }
  }

  override fun decorateUnrounded(duration: Duration, time: String): String {
    return when (val override = override) {
      null -> super.decorateUnrounded(duration, time)
      else -> override.decorateUnrounded(duration, time)
    }
  }

  override fun format(duration: Duration): String {
    return when (val override = override) {
      null -> super.format(duration)
      else -> override.format(duration)
    }
  }

  override fun formatUnrounded(duration: Duration): String {
    return when (val override = override) {
      null -> super.formatUnrounded(duration)
      else -> override.formatUnrounded(duration)
    }
  }
}
