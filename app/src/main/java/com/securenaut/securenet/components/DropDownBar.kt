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
import com.securenaut.securenet.data.IPData


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownBar(ipData: IPData) {
    var ip: Boolean = false
    var ipList:List<String> = listOf()
    if(ipData.ip != null){
        ip=true
        ipList = listOf(ipData.ip)
    }else{
        ipList = listOf(ipData.domain!!)
    }


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