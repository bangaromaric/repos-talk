/* MIT License
 *
 * Copyright (c) 2021 Florian Trecul
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
*/

package ga.banga.country.utils

import ga.banga.country.R.drawable

object Constants {

  // Application
  const val BASE_URL = "https://countriesnow.space/api/v0.1/countries/"
  const val GABON = "Gabon"
  const val filter = "currency,flag,unicodeFlag,dialCode"
  val IMG_LBV_LIST = listOf(
    "https://media-cdn.tripadvisor.com/media/photo-s/09/25/0f/a7/gabon.jpg",
    "https://www.gabonreview.com/wp-content/uploads/2020/06/LBV-GABON.jpg",
    "https://www.libreville-aeroport.com/wp-content/uploads/2020/02/GP_7513-1-1520x1160-c-default.jpg",
    "https://www.quotatrip.com/uploads/vacation_ideas/2026/days/d74dfb9d9ab619c0b81c8cf08e546a69.jpeg",
    "https://www.monde-du-voyage.com/gabon/vol-libreville-promo.jpg",
    "https://www.libreville-aeroport.com/wp-content/uploads/2019/04/Park-Inn-by-Radisson-Libreville.jpg"
  )

  val DRAWABLE_GABON = listOf(
    drawable.gabon1, drawable.gabon2, drawable.gabon3, drawable.gabon4,
    drawable.gabon5, drawable.gabon6
  )

}