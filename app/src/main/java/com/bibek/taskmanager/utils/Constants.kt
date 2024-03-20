package com.bibek.taskmanager.utils

import androidx.compose.ui.unit.dp
import java.time.LocalDate

object Constants {

    const val PREFERENCES_NAME = "TASK_PREFERENCES"
    const val ACCESS_TOKEN = "ACCESS_TOKEN"
    const val PAN_IMAGE_RUL = "PAN_IMAGE_RUL"
    const val AADHAAR_FRONT_IMAGE_URL = "AADHAAR_FRONT_IMAGE_URL"
    const val AADHAAR_BACK_IMAGE_URL = "AADHAAR_FRONT_IMAGE_URL"
    const val SOME_THING_WENT_WRONG = "Something went wrong"
    const val MOBILE_NUMBER = "MOBILE_NUMBER"
    const val REFRESH_TOKEN = "REFRESH_TOKEN"
    const val ADMIN_NAME = "ADMIN_NAME"
    const val USER_NAME = "USER_NAME"
    const val PASSWORD_KEY = "PASSWORD"
    const val PAN_NAME = "PAN_NAME"
    const val PAN_NO = "PAN_NO"
    const val AADHAAR_NO = "AADHAAR_NO"
    const val EMAIL = "EMAIL"


    const val CLIENT_ID = "281786862420-pfac1dbojrdc10ub7vo65hf9ghm98bis.apps.googleusercontent.com"
    const val CLIENT_SECRET = "client_secret"
    const val Geo_Location = "Geo-Location"
    const val Location = "Geo-Location"

    const val GRANT_TYPE = "grant_type"
    const val PASSWORD = "password"
    const val USERNAME = "username"
    const val UNKNOWN_ERROR= "Unknown Error"
    const val AUTHORIZATION_HEADER = "Authorization"
    const val USER_AGENT_HEADER = "User-Agent"
    const val BASIC_AUTH_CREDENTIALS = "Basic aXN1LWprLWJhbmstb2F1dGgyLWNsaWVudDppc3UtamstYmFuay1vYXV0aC1wYXNzd29yZA=="

    private val DEFAULT_RANGE_START_DATE = LocalDate.of(1980, 3, 15)
    private const val DEFAULT_RANGE_END_YEAR_OFFSET = 20L
     val DEFAULT_RANGE_END_DATE = LocalDate.now()

    internal val DEFAULT_RANGE = DEFAULT_RANGE_START_DATE..DEFAULT_RANGE_END_DATE

    val EXTRA_LARGE_PADDING = 40.dp
    val LARGE_PADDING = 20.dp
    val MEDIUM_PADDING = 16.dp
    val SMALL_PADDING = 10.dp
    val EXTRA_SMALL_PADDING = 6.dp

    val PAGING_INDICATOR_WIDTH = 12.dp
    val PAGING_INDICATOR_SPACING = 8.dp

    val TOP_APP_BAR_HEIGHT = 56.dp
    val HERO_ITEM_HEIGHT = 400.dp
    val NAME_PLACEHOLDER_HEIGHT = 30.dp
    val ABOUT_PLACEHOLDER_HEIGHT = 15.dp
    val RATING_PLACEHOLDER_HEIGHT = 20.dp
    val NETWORK_ERROR_ICON_HEIGHT = 120.dp

    val INFO_ICON_SIZE = 32.dp

    val MIN_SHEET_HEIGHT = 140.dp
    val EXPANDED_RADIUS_LEVEL = 0.dp

    const val Unknown_Error =  "Unknown Error"


}


 enum class Result {
    SUCCESS,
    ERROR
};