package com.securenaut.securenet.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownBar() {
    val ipList = listOf(
        "192.168.0.1",
        "10.0.0.1",
        "172.16.0.1",
        "192.168.1.1",
        "10.1.1.1",
        "192.168.2.1"
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedIP by remember { mutableStateOf(ipList[0]) }

    // Menu box
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        // Textfield
        TextField(
            modifier =
            Modifier.menuAnchor(), // menuAnchor modifier must be passed to the text field for correctness.
            readOnly = true,
            value = selectedIP,
            onValueChange = {},
            label = { Text("Malicious IP Addresses") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )

        // Menu
        ExposedDropdownMenu(

            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
        ) {
            // Menu items
            ipList.forEach { ipOption ->
                DropdownMenuItem(
                    text = { Text(ipOption) },
                    onClick = {
                        selectedIP = ipOption
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}