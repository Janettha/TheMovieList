package com.janettha.jetpackcompose.themoviedb.sys.config

import com.janettha.jetpackcompose.themoviedb.sys.util.permission.AppPermission
import com.janettha.jetpackcompose.themoviedb.BuildConfig

interface Constants {

    class Images {

        companion object {
            const val GALLERY_CODE = 1
        }

    }

    class Permission {

        companion object {

            const val LOCATION_REQUEST_CODE = 1010

            const val LOCATION_BACKGROUND_REQUEST_CODE = 56

            val LOCATION_PERMISSIONS =
                listOf(
                    AppPermission.AccessFineLocation.androidPermission,
                    AppPermission.AccessCoarseLocation.androidPermission,
                    AppPermission.AccessBackgroundLocation.androidPermission,
                )
        }

    }

    /**
     * Web consumer constants
     */
    class Web {

        companion object {
            /**
             * Client max connect timeout
             */
            const val CONNECT_TIMEOUT = 10L

            /**
             * Client max write timeout
             */
            const val WRITE_TIMEOUT = 30L

            /**
             * Client max read timeout
             */
            const val READ_TIMEOUT = 10L

            /**
             * Web API base URL
             */
            const val API_BASE_URL = BuildConfig.WS_SERVICE

            const val API_KEY = BuildConfig.API_KEY
        }

    }

    /*
    * Track location service
    * */
    class TrackLocationService {

        object Constants {
            const val ACTION_START_OR_RESUME_SERVICE = "ACTION_START_OR_RESUME_SERVICE"
            const val ACTION_PAUSE_SERVICE = "ACTION_PAUSE_SERVICE"
            const val ACTION_STOP_SERVICE = "ACTION_STOP_SERVICE"
        }

    }

    /*
    * Notifications
    * */
    class Notification {

        object Constants {
            const val NOTIFICATION_CHANNEL_ID = "movies_channel"
            const val NOTIFICATION_CHANNEL_NAME = "moviesCatalogoApp"
            const val NOTIFICATION_ID = 26
        }

    }
}