package org.ocpsoft.prettytime

import java.util.Locale

/**
 * An object that behaves differently for various [Locale] settings.
 *
 * @author [Lincoln Baxter, III](mailto:lincolnbaxter@gmail.com)
 */
interface LocaleAware<TYPE> {
  var locale: Locale
}
