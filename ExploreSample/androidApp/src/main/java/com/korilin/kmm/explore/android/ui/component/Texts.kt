package com.korilin.kmm.explore.android.ui.component

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.korilin.kmm.explore.android.ui.theme.appColors

@Composable
fun Title(text: String, modifier: Modifier = Modifier) = Text(
    text = text,
    modifier = modifier,
    color = appColors.textOnPrimary,
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
)

@Composable
fun EditText(
    requireValue: () -> String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) = OutlinedTextField(
    value = requireValue(),
    onValueChange = onValueChange,
    placeholder = { Text(text = "Input Record Message!") },
    maxLines = 99,
    colors = TextFieldDefaults.outlinedTextFieldColors(
        textColor = appColors.primaryTextColor,
        placeholderColor = appColors.secondaryTextColor,
        backgroundColor = appColors.editorBackground,
        unfocusedBorderColor = appColors.borderColor,
        focusedBorderColor = appColors.primaryColor
    ),
    modifier = modifier
)


@Preview
@Composable
fun EditTextPreview() = EditText(requireValue = { "" }, onValueChange = {})
