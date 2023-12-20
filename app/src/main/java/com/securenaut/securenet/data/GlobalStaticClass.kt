package com.securenaut.securenet.data

import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import org.json.JSONObject
import java.io.File

object GlobalStaticClass {

    lateinit var apkHash : String
    lateinit var staticAnalysisReport : JSONObject
    lateinit var staticAnalysisReportString: String
    lateinit var appIconDrawable: Drawable
    lateinit var appName: String
    lateinit var apkFile: File
    lateinit var srcDir: String
    lateinit var action: String
    lateinit var summary: String
    lateinit var appPermissions: List<String>
    lateinit var sharedPrefInstance: SharedPreferences
    lateinit var packageName: String
    var blackList: MutableList<Pair<String, String>> = mutableListOf()
    lateinit var installedAppsData: MutableList<MutableMap<String,Any>>
    lateinit var lastScan: String
    lateinit var daPackageName: String

    val normalPermissions = listOf(
        // Normal Permissions
        "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS",
        "android.permission.ACCESS_NETWORK_STATE",
        "android.permission.ACCESS_NOTIFICATION_POLICY",
        "android.permission.ACCESS_WIFI_STATE",
        "android.permission.BLUETOOTH",
        "android.permission.BLUETOOTH_ADMIN",
        "android.permission.BROADCAST_STICKY",
        "android.permission.CHANGE_NETWORK_STATE",
        "android.permission.CHANGE_WIFI_MULTICAST_STATE",
        "android.permission.CHANGE_WIFI_STATE",
        "android.permission.DISABLE_KEYGUARD",
        "android.permission.EXPAND_STATUS_BAR",
        "android.permission.GET_PACKAGE_SIZE",
        "android.permission.INSTALL_SHORTCUT",
        "android.permission.INTERNET",
        "android.permission.KILL_BACKGROUND_PROCESSES",
        "android.permission.MODIFY_AUDIO_SETTINGS",
        "android.permission.NFC",
        "android.permission.READ_SYNC_SETTINGS",
        "android.permission.READ_SYNC_STATS",
        "android.permission.RECEIVE_BOOT_COMPLETED",
        "android.permission.REORDER_TASKS",
        "android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS",
        "android.permission.REQUEST_INSTALL_PACKAGES",
        "android.permission.SET_ALARM",
        "android.permission.SET_TIME_ZONE",
        "android.permission.SET_WALLPAPER",
        "android.permission.SET_WALLPAPER_HINTS",
        "android.permission.TRANSMIT_IR",
        "android.permission.UNINSTALL_SHORTCUT",
        "android.permission.USE_FINGERPRINT",
        "android.permission.VIBRATE",
        "android.permission.WAKE_LOCK",
        "android.permission.WRITE_SYNC_SETTINGS",
    )
    val dangerousPermissions = listOf<String>(
                // Dangerous Permissions
                "android.permission.ACCESS_FINE_LOCATION",
                "android.permission.ACCESS_COARSE_LOCATION",
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE",
                "android.permission.CAMERA",
                "android.permission.READ_CONTACTS",
                "android.permission.WRITE_CONTACTS",
                "android.permission.GET_ACCOUNTS",
                "android.permission.READ_PHONE_STATE",
                "android.permission.CALL_PHONE",
                "android.permission.READ_CALL_LOG",
                "android.permission.WRITE_CALL_LOG",
                "android.permission.ADD_VOICEMAIL",
                "android.permission.USE_SIP",
                "android.permission.PROCESS_OUTGOING_CALLS",
                "android.permission.BODY_SENSORS",
                "android.permission.SEND_SMS",
                "android.permission.RECEIVE_SMS",
                "android.permission.READ_SMS",
                "android.permission.RECEIVE_WAP_PUSH",
                "android.permission.RECEIVE_MMS",
                "android.permission.READ_CALENDAR",
                "android.permission.WRITE_CALENDAR",
        )

    val signaturePermissions = listOf<String>(
        // Signature Permissions
        "android.permission.BIND_ACCESSIBILITY_SERVICE",
        "android.permission.BIND_AUTOFILL_SERVICE",
        "android.permission.BIND_CARRIER_MESSAGING_SERVICE",
        "android.permission.BIND_CARRIER_SERVICES",
        "android.permission.BIND_CHOOSER_TARGET_SERVICE",
        "android.permission.BIND_CONDITION_PROVIDER_SERVICE",
        "android.permission.BIND_DEVICE_ADMIN",
        "android.permission.BIND_DREAM_SERVICE",
        "android.permission.BIND_INCALL_SERVICE",
        "android.permission.BIND_INPUT_METHOD",
        "android.permission.BIND_MIDI_DEVICE_SERVICE",
        "android.permission.BIND_NFC_SERVICE",
        "android.permission.BIND_NOTIFICATION_LISTENER_SERVICE",
        "android.permission.BIND_PRINT_SERVICE",
        "android.permission.BIND_QUICK_SETTINGS_TILE",
        "android.permission.BIND_REMOTEVIEWS",
        "android.permission.BIND_SCREENING_SERVICE",
        "android.permission.BIND_TELECOM_CONNECTION_SERVICE",
        "android.permission.BIND_TEXT_SERVICE",
        "android.permission.BIND_TV_INPUT",
        "android.permission.BIND_VISUAL_VOICEMAIL_SERVICE",
        "android.permission.BIND_VOICE_INTERACTION",
        "android.permission.BIND_VPN_SERVICE",
        "android.permission.BIND_VR_LISTENER_SERVICE",
        "android.permission.BIND_WALLPAPER"
    )

    val permissionsMap = mapOf(
        "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS" to mapOf("permission_name" to "Access Extra Location Commands", "permission_descp" to "Allows the app to access extra location provider commands."),
        "android.permission.ACCESS_NETWORK_STATE" to mapOf("permission_name" to "View Network Connections", "permission_descp" to "Allows the app to view information about network connections such as which networks exist and are connected."),
        "android.permission.ACCESS_NOTIFICATION_POLICY" to mapOf("permission_name" to "Access Notification Policy", "permission_descp" to "Allows the app to read and write notification policy."),
        "android.permission.ACCESS_WIFI_STATE" to mapOf("permission_name" to "View Wi-Fi Connections", "permission_descp" to "Allows the app to view information about Wi-Fi networking, such as whether Wi-Fi is enabled and name of connected Wi-Fi devices."),
        "android.permission.BLUETOOTH" to mapOf("permission_name" to "Bluetooth Permission", "permission_descp" to "Allows the app to view the configuration of the Bluetooth on the phone, and to make and accept connections with paired devices."),
        "android.permission.BLUETOOTH_ADMIN" to mapOf("permission_name" to "Bluetooth Administration", "permission_descp" to "Allows the app to discover and pair with remote devices, change Bluetooth settings, and see the Bluetooth devices paired with your phone."),
        "android.permission.BROADCAST_STICKY" to mapOf("permission_name" to "Send Sticky Broadcast", "permission_descp" to "Allows the app to send sticky broadcasts, which remain after the broadcast ends. Excessive use may make the phone slow or unstable by causing it to use too much memory."),
        "android.permission.CHANGE_NETWORK_STATE" to mapOf("permission_name" to "Change Network Connectivity", "permission_descp" to "Allows the app to change the state of network connectivity."),
        "android.permission.CHANGE_WIFI_MULTICAST_STATE" to mapOf("permission_name" to "Allow Wi-Fi Multicast Reception", "permission_descp" to "Allows the app to receive packets sent to all devices on a Wi-Fi network using multicast addresses, not just your phone. Uses more power than the non-multicast mode."),
        "android.permission.CHANGE_WIFI_STATE" to mapOf("permission_name" to "Change Wi-Fi State", "permission_descp" to "Allows the app to connect to and disconnect from Wi-Fi access points and to make changes to device configuration for Wi-Fi networks."),
        "android.permission.DISABLE_KEYGUARD" to mapOf("permission_name" to "Disable Key Lock", "permission_descp" to "Allows the app to disable the key lock and any associated password security. For example, the phone disables the key lock when receiving an incoming phone call, then re-enables the key lock when the call is finished."),
        "android.permission.EXPAND_STATUS_BAR" to mapOf("permission_name" to "Expand/Collapse Status Bar", "permission_descp" to "Allows the app to expand or collapse the status bar."),
        "android.permission.GET_PACKAGE_SIZE" to mapOf("permission_name" to "Measure App Storage Space", "permission_descp" to "Allows the app to retrieve its code, data, and cache sizes"),
        "android.permission.INSTALL_SHORTCUT" to mapOf("permission_name" to "Install Shortcuts",  "permission_descp" to "Allows the app to add Home screen shortcuts without user intervention."),
        "android.permission.INTERNET" to mapOf("permission_name" to "Full Network Access", "permission_descp" to "Allows the app to create network sockets and use custom network protocols. The browser and other applications provide means to send data to the internet, so this permission is not required to send data to the internet."),
        "android.permission.KILL_BACKGROUND_PROCESSES" to mapOf("permission_name" to "Close Other Apps", "permission_descp" to "Allows the app to end background processes of other apps, even if memory isn't low."),
        "android.permission.MODIFY_AUDIO_SETTINGS" to mapOf("permission_name" to "Change Audio Settings", "permission_descp" to "Allows the app to modify global audio settings such as volume and routing."),
        "android.permission.NFC" to mapOf("permission_name" to "Control Near-Field Communication", "permission_descp" to "Allows the app to communicate with Near-Field Communication (NFC) tags, cards, and readers."),
        "android.permission.READ_SYNC_SETTINGS" to mapOf("permission_name" to "Read Sync Settings", "permission_descp" to "Allows the app to read the sync settings for an account. For example, this can determine whether the People app is synced with an account."),
        "android.permission.READ_SYNC_STATS" to mapOf("permission_name" to "Read Sync Statistics", "permission_descp" to "Allows an app to read the sync stats; e.g. the history of syncs that have occurred."),
        "android.permission.RECEIVE_BOOT_COMPLETED" to mapOf("permission_name" to "Run at Startup", "permission_descp" to "Allows the app to have itself started as soon as the system has finished booting. This can make it take longer to start the phone and allow the app to slow down the overall phone by always running."),
        "android.permission.REORDER_TASKS" to mapOf("permission_name" to "Reorder Running Apps", "permission_descp" to "Allows the app to move tasks to the foreground and background. The app may do this without your input."),
        "android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS" to mapOf("permission_name" to "Ignore Battery Optimizations", "permission_descp" to "Permission an app must hold in order to use ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS."),
        "android.permission.REQUEST_INSTALL_PACKAGES" to mapOf("permission_name" to "Install Packages", "permission_descp" to "Allows the app to request installation of packages."),
        "android.permission.SET_ALARM" to mapOf("permission_name" to "Set Alarm", "permission_descp" to "Allows the app to set an alarm in an installed alarm clock app. Some alarm clock apps may not implement this feature."),
        "android.permission.SET_TIME_ZONE" to mapOf("permission_name" to "Set Time Zone", "permission_descp" to "Allows the app to change the phone's time zone."),
        "android.permission.SET_WALLPAPER" to mapOf("permission_name" to "Set Wallpaper", "permission_descp" to "Allows the app to set the system wallpaper."),
        "android.permission.SET_WALLPAPER_HINTS" to mapOf("permission_name" to "Set Wallpaper Hints", "permission_descp" to "Allows the app to set the system wallpaper size hints."),
        "android.permission.TRANSMIT_IR" to mapOf("permission_name" to "Transmit Infrared", "permission_descp" to "Allows the app to use the phone's infrared transmitter."),
        "android.permission.UNINSTALL_SHORTCUT" to mapOf("permission_name" to "Uninstall Shortcuts", "permission_descp" to "Don't keep activities around when system needs memory "),
        "android.permission.USE_FINGERPRINT" to mapOf("permission_name" to "Use Fingerprint Hardware", "permission_descp" to "Allows the app to use fingerprint hardware."),
        "android.permission.VIBRATE" to mapOf("permission_name" to "Control Vibration", "permission_descp" to "Allows the app to control the vibrator."),
        "android.permission.WAKE_LOCK" to mapOf("permission_name" to "Prevent Phone from Sleeping", "permission_descp" to "Allows the app to prevent the phone from going to sleep."),
        "android.permission.WRITE_SYNC_SETTINGS" to mapOf("permission_name" to "Toggle Sync On And Off", "permission_descp" to "Allows an app to modify the sync settings for an account. For example, this can be used to enable sync of the People app with an account."),

        "android.permission.ACCESS_COARSE_LOCATION" to mapOf("permission_name" to "Approximate Location Access", "permission_descp" to "Allows the app to get your approximate location based on network location sources such as cell towers and Wi-Fi."),
        "android.permission.ACCESS_FINE_LOCATION" to mapOf("permission_name" to "Precise Location Access", "permission_descp" to "Allows the app to get your precise location using location services using GPS or network location sources such as cell towers and Wi-Fi."),
        "android.permission.READ_EXTERNAL_STORAGE" to mapOf("permission_name" to "Read External Storage", "permission_descp" to "Allows the app to read from external storage."),
        "android.permission.WRITE_EXTERNAL_STORAGE" to mapOf("permission_name" to "Modify/Delete External Storage", "permission_descp" to "Allows the app to write to external storage."),
        "android.permission.CAMERA" to mapOf("permission_name" to "Camera", "permission_descp" to "Required to be able to access the camera device."),
        "android.permission.READ_CONTACTS" to mapOf("permission_name" to "Read Contacts", "permission_descp" to "Allows the app to read data about your contacts stored on your phone, including the frequency with which you've called, emailed, or communicated in other ways with specific individuals."),
        "android.permission.WRITE_CONTACTS" to mapOf("permission_name" to "Write Contacts", "permission_descp" to "Allows the app to modify the data about your contacts stored on your phone, including the frequency with which you've called, emailed, or communicated in other ways with specific contacts."),
        "android.permission.GET_ACCOUNTS" to mapOf("permission_name" to "Find Accounts on the Device", "permission_descp" to "Allows the app to get the list of accounts known by the phone. This may include any accounts created by applications you have installed."),
        "android.permission.READ_PHONE_STATE" to mapOf("permission_name" to "Read Phone State", "permission_descp" to "Allows read only access to phone state, including the phone number of the device, current cellular network information, the status of any ongoing calls, and a list of any PhoneAccounts registered on the device."),
        "android.permission.CALL_PHONE" to mapOf("permission_name" to "Directly Call Numbers", "permission_descp" to "Allows the app to call phone numbers without your intervention. This may result in unexpected charges or calls. Malicious apps may cost you money by making calls without your confirmation."),
        "android.permission.READ_CALL_LOG" to mapOf("permission_name" to "Read Call Log", "permission_descp" to "Allows the app to read your phone's call log, including data about incoming and outgoing calls. This permission allows apps to save your call log data, and malicious apps may share call log data without your knowledge."),
        "android.permission.WRITE_CALL_LOG" to mapOf("permission_name" to "Write Call Log", "permission_descp" to "Allows the app to modify your phone's call log, including data about incoming and outgoing calls. Malicious apps may use this to erase or modify your call log."),
        "android.permission.ADD_VOICEMAIL" to mapOf("permission_name" to "Add Voicemails", "permission_descp" to "Allows the app to add messages to your voicemail inbox."),
        "android.permission.USE_SIP" to mapOf("permission_name" to "Make/Receive Internet Calls", "permission_descp" to "Allows the app to make and receive internet calls."),
        "android.permission.PROCESS_OUTGOING_CALLS" to mapOf("permission_name" to "Reroute Outgoing Calls", "permission_descp" to "Allows the app to see the number being dialed during an outgoing call with the option to redirect the call to a different number or abort the call altogether."),
        "android.permission.BODY_SENSORS" to mapOf("permission_name" to "Body Sensors (like heart rate monitors)", "permission_descp" to "Allows the app to access data from sensors that monitor your physical condition, such as your heart rate."),
        "android.permission.SEND_SMS" to mapOf("permission_name" to "Send SMS Messages", "permission_descp" to "Allows the app to send SMS messages. This may result in unexpected charges. Malicious apps may cost you money by sending messages without your confirmation."),
        "android.permission.RECEIVE_SMS" to mapOf("permission_name" to "Receive SMS", "permission_descp" to "Allows the app to receive and process SMS messages. This means the app could monitor or delete messages sent to your device without showing them to you."),
        "android.permission.READ_SMS" to mapOf("permission_name" to "Read SMS or MMS", "permission_descp" to "Allows the app to read SMS messages stored on your phone or SIM card. Malicious apps may read your confidential messages."),
        "android.permission.RECEIVE_WAP_PUSH" to mapOf("permission_name" to "Receive Text Messages (WAP)", "permission_descp" to "Allows the app to receive and process WAP messages. This permission includes the ability to monitor or delete messages sent to you without showing them to you."),
        "android.permission.RECEIVE_MMS" to mapOf("permission_name" to "Receive MMS Messages", "permission_descp" to "Allows the app to receive and process MMS messages. This means the app could monitor or delete messages sent to your device without showing them to you."),
        "android.permission.READ_CALENDAR" to mapOf("permission_name" to "Read Calendar Events", "permission_descp" to "Allows the app to read all calendar events stored on your phone and share or save your calendar data."),
        "android.permission.WRITE_CALENDAR" to mapOf("permission_name" to "Add or Modify Calendar Events and Send Email to Guests", "permission_descp" to "Allows the app to add, remove, change events on your calendar and send emails to guests without owners' knowledge."),

        "android.permission.BIND_ACCESSIBILITY_SERVICE" to mapOf("permission_name" to "Act as an accessibility service", "permission_descp" to "Must be required by an AccessibilityService, to ensure that only the system can bind to it."),
        "android.permission.BIND_AUTOFILL_SERVICE" to mapOf("permission_name" to "Act as an autofill service", "permission_descp" to "Must be required by a AutofillService, to ensure that only the system can bind to it."),
        "android.permission.BIND_CARRIER_MESSAGING_SERVICE" to mapOf("permission_name" to "Carrier Messaging Service", "permission_descp" to "Allows the holder to act as a carrier messaging service."),
        "android.permission.BIND_CARRIER_SERVICES" to mapOf("permission_name" to "Carrier Services", "permission_descp" to "Allows the holder to act as a carrier services."),
        "android.permission.BIND_CHOOSER_TARGET_SERVICE" to mapOf("permission_name" to "Choose Target Service", "permission_descp" to "Must be required by a ChooserTargetService, to ensure that only the system can bind to it."),
        "android.permission.BIND_CONDITION_PROVIDER_SERVICE" to mapOf("permission_name" to "Condition Provider Service", "permission_descp" to "Must be required by a ConditionProviderService, to ensure that only the system can bind to it."),
        "android.permission.BIND_DEVICE_ADMIN" to mapOf("permission_name" to "Interact with Device Admin", "permission_descp" to "Must be required by device administration receiver, to ensure that only the system can interact with it."),
        "android.permission.BIND_DREAM_SERVICE" to mapOf("permission_name" to "Dream Service", "permission_descp" to "Must be required by an DreamService, to ensure that only the system can bind to it."),
        "android.permission.BIND_INCALL_SERVICE" to mapOf("permission_name" to "In-call Service", "permission_descp" to "Must be required by a InCallService, to ensure that only the system can bind to it."),
        "android.permission.BIND_INPUT_METHOD" to mapOf("permission_name" to "Input Method", "permission_descp" to "Must be required by an InputMethodService, to ensure that only the system can bind to it."),
        "android.permission.BIND_MIDI_DEVICE_SERVICE" to mapOf("permission_name" to "MIDI Device Service", "permission_descp" to "Must be required by an MidiDeviceService, to ensure that only the system can bind to it."),
        "android.permission.BIND_NFC_SERVICE" to mapOf("permission_name" to "NFC Service", "permission_descp" to "Must be required by an NfcService, to ensure that only the system can bind to it."),
        "android.permission.BIND_NOTIFICATION_LISTENER_SERVICE" to mapOf("permission_name" to "Notification Listener Service", "permission_descp" to "Must be required by an NotificationListenerService, to ensure that only the system can bind to it."),
        "android.permission.BIND_PRINT_SERVICE" to mapOf("permission_name" to "Print Service", "permission_descp" to "Must be required by a PrintService, to ensure that only the system can bind to it."),
        "android.permission.BIND_QUICK_SETTINGS_TILE" to mapOf("permission_name" to "Quick Settings Tile Service", "permission_descp" to "Must be required by a QuickSettingsTileService to ensure that only the system can bind to it."),
        "android.permission.BIND_REMOTEVIEWS" to mapOf("permission_name" to "Remote Views Service", "permission_descp" to "Must be required by a RemoteViewsService, to ensure that only the system can bind to it."),
        "android.permission.BIND_SCREENING_SERVICE" to mapOf("permission_name" to "Call Screening Service", "permission_descp" to "Must be required by a CallScreeningService, to ensure that only the system can bind to it."),
        "android.permission.BIND_TELECOM_CONNECTION_SERVICE" to mapOf("permission_name" to "Telecom Connection Service", "permission_descp" to "Must be required by a ConnectionService, to ensure that only the system can bind to it."),
        "android.permission.BIND_TEXT_SERVICE" to mapOf("permission_name" to "Text Service", "permission_descp" to "Must be required by a TextService (e.g. SpellCheckerService), to ensure that only the system can bind to it."),
        "android.permission.BIND_TV_INPUT" to mapOf("permission_name" to "TV Input Service", "permission_descp" to "Must be required by a TvInputService to ensure that only the system can bind to it."),
        "android.permission.BIND_VISUAL_VOICEMAIL_SERVICE" to mapOf("permission_name" to "Visual Voicemail Service", "permission_descp" to "Must be required by a link VisualVoicemailService to ensure that only the system can bind to it."),
        "android.permission.BIND_VOICE_INTERACTION" to mapOf("permission_name" to "Voice Interaction Service", "permission_descp" to "Must be required by a VoiceInteractionService, to ensure that only the system can bind to it."),
        "android.permission.BIND_VPN_SERVICE" to mapOf("permission_name" to "VPN Service", "permission_descp" to "Must be required by a VpnService, to ensure that only the system can bind to it."),
        "android.permission.BIND_VR_LISTENER_SERVICE" to mapOf("permission_name" to "VR Listener Service", "permission_descp" to "Must be required by an VrListenerService, to ensure that only the system can bind to it."),
        "android.permission.BIND_WALLPAPER" to mapOf("permission_name" to "Wallpaper Service", "permission_descp" to "Must be required by a WallpaperService, to ensure that only the system can bind to it.")
    )

}